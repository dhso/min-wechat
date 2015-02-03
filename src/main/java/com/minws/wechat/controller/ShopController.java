package com.minws.wechat.controller;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	public void addOrder() throws JsonProcessingException, IOException {
		String uid = getPara("uid", "");
		String cartData = getPara("cartData", "");
		String totalPrice = getPara("totalPrice", "");
		JsonNode userData = new ObjectMapper().readTree(getPara("userData"));
		String username = userData.get(0).get("value").asText();
		String phone = userData.get(1).get("value").asText();
		String payStyle = userData.get(2).get("value").asText();
		String address = userData.get(3).get("value").asText();
		String note = userData.get(4).get("value").asText();
		if (StringKit.isNotBlank(uid)) {
			ShopOrder.dao.addOrderByUid(uid, totalPrice, note, payStyle, cartData, username, phone, address);
		} else {
			renderText("uid is empty!");
		}
	}
}
