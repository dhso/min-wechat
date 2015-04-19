package com.minws.wechat.model.shop;

import java.util.Date;
import java.util.List;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.minws.wechat.frame.kit.IdentityKit;
import com.minws.wechat.frame.kit.StringKit;

@SuppressWarnings("serial")
@TableBind(tableName = "shop_order", pkName = "id")
public class ShopOrder extends Model<ShopOrder> {
	public static final ShopOrder dao = new ShopOrder();

	/**
	 * 分页获取订单
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param openId
	 * @return
	 */
	public Page<Record> getOrderPage(int pageNumber, int pageSize, String openId) {
		return Db.paginate(pageNumber, pageSize, "select *", "from shop_order as so left join shop_user as su on su.open_id = so.open_id where su.open_id = ? order by so.create_dt desc", openId);
	}

	/**
	 * 添加订单
	 * 
	 * @param openId
	 * @param from
	 * @param totalPrice
	 * @param orderNote
	 * @param payStyle
	 * @param orderData
	 * @param name
	 * @param phone
	 * @param address
	 */
	public void addOrder(String openId, String from, String totalPrice, String orderNote, String payStyle, String orderData, String name, String phone, String address) {
		Record shopUser = ShopUser.dao.getUser(openId);
		if (null == shopUser) {
			ShopUser.dao.addUser(openId, from, name, phone, "", address, "0");
			shopUser = ShopUser.dao.getUser(openId);
		} else if (!name.equals(shopUser.getStr("name")) || !phone.equals(shopUser.getStr("phone")) || !address.equals(shopUser.getStr("address"))) {
			ShopUser.dao.updateUser(openId, name, phone, address);
		}
		if(StringKit.toFloat(shopUser.getStr("money")) >= StringKit.toFloat(totalPrice)){
			Db.update("insert into shop_order(order_id,open_id,total_price,pay_style,pay_status,order_note,order_data,order_status,create_dt) values (?,?,?,?,?,?,?,?,?)", IdentityKit.uuid4(), openId, totalPrice, payStyle, "1", orderNote, orderData, "0", new Date());
		}else{
			Db.update("insert into shop_order(order_id,open_id,total_price,pay_style,pay_status,order_note,order_data,order_status,create_dt) values (?,?,?,?,?,?,?,?,?)", IdentityKit.uuid4(), openId, totalPrice, payStyle, "0", orderNote, orderData, "0", new Date());
		}
		Db.update("update shop_user set money = ? where open_id = ?", String.valueOf(StringKit.toFloat(shopUser.getStr("money")) - StringKit.toFloat(totalPrice)), openId);
	}

	/**
	 * 删除订单
	 * 
	 * @param openId
	 * @param orderId
	 */
	public void delOrder(String openId, String orderId) {
		Record shopUser = Db.findFirst("select * from shop_user where open_id = ?", openId);
		Record shopOrder = Db.findFirst("select * from shop_order where order_id = ?", orderId);
		if (Db.update("delete from shop_order where order_status = '0' and open_id = ? and order_id = ?", openId, orderId) > 0) {
			Db.update("update shop_user set money = ? where open_id = ?", String.valueOf(StringKit.toFloat(shopUser.getStr("money")) + StringKit.toFloat(shopOrder.getStr("total_price"))), openId);
		}
	}

	/**
	 * 获取将要处理的订单
	 * 
	 * @return
	 */
	public List<Record> getToDoOrders() {
		return Db.find("select so.order_id as orderId,so.total_price as totalPrice,so.order_data as orderData,so.create_dt as createDt,so.order_note as orderNote,so.pay_status as payStatus,so.order_status as orderStatus, su.name as userName from shop_order so left join shop_user su on su.open_id = so.open_id where so.order_status !='2' order by so.create_dt desc");
	}

	/**
	 * 设置将要处理的订单的支付状态
	 * 
	 * @param orderId
	 * @param payStatus
	 * @return
	 */
	public int payOrder(String orderId, String payStatus) {
		return Db.update("update shop_order set pay_status = ? where order_id = ?", payStatus, orderId);
	}

	/**
	 * 设置将要处理的订单的成交状态
	 * 
	 * @param orderId
	 * @param orderStatus
	 * @return
	 */
	public int dealOrder(String orderId, String orderStatus) {
		return Db.update("update shop_order set order_status = ? where order_id = ?", orderStatus, orderId);
	}
	

	/**
	 * 关闭将要处理的订单
	 * 
	 * @param orderId
	 * @return 
	 */
	public int closeOrder(String orderId) {
		return Db.update("update shop_order set order_status = '2' where order_id = ?", orderId);
	}
}
