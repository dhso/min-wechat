package com.minws.wechat.model.SHOP;

import java.util.List;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
@TableBind(tableName = "shop_good", pkName = "id")
public class ShopGood extends Model<ShopGood> {
	public static final ShopGood dao = new ShopGood();

	public List<ShopGood> getAllGood() {
		return ShopGood.dao.find("select * from shop_good");
	}
}
