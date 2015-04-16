package com.minws.wechat.model.shop;

import java.util.Date;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.minws.wechat.frame.kit.IdentityKit;
import com.minws.wechat.frame.kit.StringKit;

@SuppressWarnings("serial")
@TableBind(tableName = "shop_order", pkName = "id")
public class ShopOrder extends Model<ShopOrder> {
	public static final ShopOrder dao = new ShopOrder();

	public Page<Record> getOrdersByUid(String uid) {
		return Db.paginate(1, 20, "select *", "from shop_order as so left join shop_user as su on su.id = so.user_id where su.uid = ? order by so.create_dt desc", uid);
	}

	@Before(Tx.class)
	public void addOrderByUid(String uid, String totalPrice, String note, String payStyle, String cartData, String username, String phone, String address) {
		if (null == ShopUser.dao.getUserByUid(uid)) {
			ShopUser.dao.addUser(uid, username, phone, address);
		} else {
			ShopUser.dao.updateUser(uid, username, phone, address);
		}
		ShopUser shopUser = ShopUser.dao.getUserByUid(uid);
		new ShopOrder().set("user_id", shopUser.getInt("id")).set("order_id", IdentityKit.uuid4()).set("totalprice", totalPrice).set("note", note).set("pay_style", payStyle).set("pay_status", "0").set("order_status", "0").set("create_dt", new Date()).set("cartdata", cartData).save();
		ShopChargeHistory.dao.addHistory(String.valueOf(shopUser.getInt("id")), "-" + totalPrice, String.valueOf(StringKit.toFloat(shopUser.getStr("current_money")) - StringKit.toFloat(totalPrice)), "订单支付消费");
		ShopUser.dao.changeMoney(String.valueOf(shopUser.getInt("id")), String.valueOf(StringKit.toFloat(shopUser.getStr("current_money")) - StringKit.toFloat(totalPrice)));
	}

	@Before(Tx.class)
	public void delOrder(String orderId) {
		ShopOrder shopOrder = ShopOrder.dao.findFirst("select * from shop_order where order_id = ?", orderId);
		ShopUser shopUser = ShopUser.dao.findById(shopOrder.get("user_id"));
		Db.update("delete from shop_order where order_status = '0' and order_id = ?", orderId);
		ShopChargeHistory.dao.addHistory(shopOrder.getStr("user_id"), "+" + shopOrder.getStr("totalPrice"), String.valueOf(StringKit.toFloat(shopUser.getStr("current_money")) + StringKit.toFloat(shopOrder.getStr("totalPrice"))), "订单取消支付");
		ShopUser.dao.changeMoney(String.valueOf(shopUser.getInt("id")), String.valueOf(StringKit.toFloat(shopUser.getStr("current_money")) + StringKit.toFloat(shopOrder.getStr("totalPrice"))));
	}

	public List<Record> getToDoOrders() {
		return Db.find("select so.order_id as orderId,so.totalprice as totalPrice,so.cartdata as cartData,so.create_dt as createDt,so.note as note,so.pay_status as payStatus,so.order_status as orderStatus, su.username as userName from shop_order so left join shop_user su on su.id = so.user_id where so.order_status='0' or so.pay_status='0' order by so.create_dt desc");
	}

	public int closeOrder(String orderId) {
		return Db.update("update shop_order set order_status = '1',pay_status = '1' where order_id = ?", orderId);
	}

	public int payOrder(String orderId, String payStatus) {
		return Db.update("update shop_order set pay_status = ? where order_id = ?", payStatus, orderId);
	}

	public int dealOrder(String orderId, String orderStatus) {
		return Db.update("update shop_order set order_status = ? where order_id = ?", orderStatus, orderId);
	}
}
