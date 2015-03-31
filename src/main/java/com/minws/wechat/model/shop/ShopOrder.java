package com.minws.wechat.model.shop;

import java.util.Date;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.minws.wechat.frame.kit.IdentityKit;

@SuppressWarnings("serial")
@TableBind(tableName = "shop_order", pkName = "id")
public class ShopOrder extends Model<ShopOrder> {
	public static final ShopOrder dao = new ShopOrder();

	public List<ShopOrder> getOrdersByUid(String uid) {
		return ShopOrder.dao.find("select * from shop_order as so left join shop_user as su on su.id = so.user_id where su.uid = ?", uid);
	}

	@Before(Tx.class)
	public boolean addOrderByUid(String uid, String totalPrice, String note, String payStyle, String cartData, String username, String phone, String address) {
		if (null == ShopUser.dao.getUserByUid(uid)) {
			ShopUser.dao.addUser(uid, username, phone, address);
		}
		return new ShopOrder().set("user_id", ShopUser.dao.getUserByUid(uid).get("id")).set("order_id", IdentityKit.uuid4()).set("totalprice", totalPrice).set("note", note).set("pay_style", payStyle).set("pay_status", "0").set("order_status", "0").set("create_dt", new Date()).set("cartdata", cartData).save();
	}
}
