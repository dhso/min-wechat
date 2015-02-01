package com.minws.wechat.controller;

import java.io.IOException;

import org.apache.shiro.authz.annotation.RequiresRoles;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfinal.core.Controller;
import com.minws.wechat.entity.ErrorMsg;
import com.minws.wechat.entity.WechatMenu;
import com.minws.wechat.model.Config;
import com.minws.wechat.sdk.api.MenuApi;

public class BackController extends Controller {

	@RequiresRoles("admin")
	public void index() {
		render("index.htm");
	}

	@RequiresRoles("admin")
	public void manageWechatMenu() throws JsonParseException, JsonMappingException, IOException {
		if ("POST".equalsIgnoreCase(this.getRequest().getMethod().toUpperCase())) {
			String newMenuJson = getPara("newMenuJson", "");
			if (Config.dao.updateValueByKey("wechat_menu", newMenuJson)) {
				MenuApi.createMenu(newMenuJson);
				setAttr("errorMsg", new ErrorMsg("alert-success", "菜单更新成功！"));
			} else {
				setAttr("errorMsg", new ErrorMsg("alert-error", "菜单更新失败！"));
			}
		}
		String wechatMenuJson = Config.dao.getValueByKey("wechat_menu").get("value", "");
		WechatMenu wechatMenu = new ObjectMapper().readValue(wechatMenuJson, WechatMenu.class);
		setAttr("wechatMenuJson", wechatMenuJson);
		setAttr("wechatMenu", wechatMenu);
		render("manageWechatMenu.htm");
	}

}
