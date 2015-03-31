package com.minws.wechat.model.shop;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
@TableBind(tableName = "shop_user", pkName = "id")
public class ShopUser extends Model<ShopUser> {
	public static final ShopUser dao = new ShopUser();

	public ShopUser getUserByUid(String uid) {
		return ShopUser.dao.findFirst("select * from shop_user as su where su.uid = ?", uid);
	}

	public Boolean addUser(String uid, String username, String phone, String address) {
		return new ShopUser().set("uid", uid).set("username", username).set("phone", phone).set("address", address).save();
	}
}
