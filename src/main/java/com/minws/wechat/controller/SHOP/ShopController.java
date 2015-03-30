package com.minws.wechat.controller.SHOP;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.minws.wechat.entity.WX.Shop;
import com.minws.wechat.frame.kit.StringKit;
import com.minws.wechat.model.SHOP.ShopGood;
import com.minws.wechat.model.SHOP.ShopMenu;
import com.minws.wechat.model.SHOP.ShopOrder;
import com.minws.wechat.model.SHOP.ShopUser;
import com.minws.wechat.model.SYS.Config;

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

	@ActionKey("/shop/order/getOrders")
	public void getOrders() {
		String uid = getSessionAttr("uid");
		if (StringKit.isNotBlank(uid)) {
			renderJson(ShopOrder.dao.getOrdersByUid(uid));
		} else {
			renderText("uid is empty!");
		}
	}

	@ActionKey("/shop/order/addOrder")
	public void addOrder() throws JsonProcessingException, IOException {
		String uid = getSessionAttr("uid");
		String cartData = getPara("cartData", "");
		String totalPrice = getPara("totalPrice", "");
		String username = getPara("userData[0][value]", "");
		String phone = getPara("userData[1][value]", "");
		String payStyle = getPara("userData[2][value]", "");
		String address = getPara("userData[3][value]", "");
		String note = getPara("userData[4][value]", "");
		if (StringKit.isNotBlank(uid)) {
			ShopOrder.dao.addOrderByUid(uid, totalPrice, note, payStyle, cartData, username, phone, address);
			renderNull();
		} else {
			renderText("uid is empty!");
		}
	}
}
