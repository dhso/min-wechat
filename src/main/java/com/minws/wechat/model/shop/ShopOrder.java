package com.minws.wechat.model.shop;

import java.util.Date;
import java.util.List;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;
import com.minws.wechat.frame.kit.IdentityKit;

@SuppressWarnings("serial")
@TableBind(tableName = "shop_order", pkName = "id")
public class ShopOrder extends Model<ShopOrder> {
	public static final ShopOrder dao = new ShopOrder();

	public List<ShopOrder> getOrdersByUid(String uid) {
		return ShopOrder.dao.find("select * from shop_order as so left join shop_user as su on su.id = so.user_id where su.uid = ?", uid);
	}

	public void addOrderByUid(String uid, String totalPrice, String note, String payStyle, String cartData) {
		new ShopOrder().set("user_id", ShopUser.dao.getIdByUid(uid)).set("order_id", IdentityKit.uuid4()).set("totalprice", totalPrice).set("note", note).set("pay_style", payStyle).set("pay_status", "0").set("order_status", "0").set("create_dt", new Date()).set("cartdata", cartData).save();
	}
}
