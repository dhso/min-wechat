package com.minws.wechat.controller;

import java.io.IOException;

import org.apache.shiro.authz.annotation.RequiresRoles;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfinal.core.Controller;
import com.minws.wechat.entity.WechatMenu;
import com.minws.wechat.model.Config;


public class BackController extends Controller{

	@RequiresRoles("admin")
	public void index() {
		render("index.htm");
	}
	
	@RequiresRoles("admin")
	public void manageWechatMenu() throws JsonParseException, JsonMappingException, IOException{
		String wechatMenu = Config.dao.getValueByKey("wechat_menu").get("value","");
		WechatMenu  wechat_menu = new ObjectMapper().readValue(wechatMenu, WechatMenu.class);
		setAttr("wechatMenu", wechat_menu);
		render("manageWechatMenu.htm");
	}

}
