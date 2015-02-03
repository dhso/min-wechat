package com.minws.wechat.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.minws.wechat.entity.Shop;
import com.minws.wechat.frame.kit.StringKit;
import com.minws.wechat.model.Config;
import com.minws.wechat.model.shop.ShopGood;
import com.minws.wechat.model.shop.ShopMenu;
import com.minws.wechat.model.shop.ShopOrder;
import com.minws.wechat.model.shop.ShopUser;

public class ShopController extends Controller {

	public void index() {
		String uid = getPara("uid", "");
		String from = getPara("from", "");
		if (StringKit.isNotBlank(uid) && "wechat".equalsIgnoreCase(from)) {
			setSessionAttr("uid", uid);
			setAttr("shop_menu", ShopMenu.dao.getAllMenu());
			setAttr("shop", new Shop(Config.dao.getValueByKey("shop_name"), Config.dao.getValueByKey("shop_notification")));
			setAttr("shop_good", ShopGood.dao.getAllGood());
			setAttr("shop_user", ShopUser.dao.getUserByUid(uid));
			render("index.htm");
		} else {
			renderText("请使用微信打开");
		}

	}

	@ActionKey("/order/getOrders")
	public void getOrders() {
		String uid = getPara("uid", "");
		if (StringKit.isNotBlank(uid)) {
			renderJson(ShopOrder.dao.getOrdersByUid(uid));
		} else {
			renderText("uid is empty!");
		}
	}

	@ActionKey("/order/addOrder")
	public void addOrder() {
		String uid = getPara("uid", "");
		String cartData = getPara("cartData", "");
		String totalPrice = getPara("totalPrice", "");
		List userData = getPara("userData").;
		
		if (StringKit.isNotBlank(uid)) {
			renderJson(ShopOrder.dao.addOrderByUid(uid, totalPrice, note, payStyle, cartData));
		} else {
			renderText("uid is empty!");
		}
	}
}
