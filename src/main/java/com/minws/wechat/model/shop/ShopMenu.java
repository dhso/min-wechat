package com.minws.wechat.model.shop;

import java.util.List;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
@TableBind(tableName = "shop_menu", pkName = "id")
public class ShopMenu extends Model<ShopMenu> {
	public static final ShopMenu dao = new ShopMenu();

	public List<ShopMenu> getAllMenu() {
		return ShopMenu.dao.find("select * from shop_menu");
	}
}
