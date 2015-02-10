package com.minws.wechat.controller;

import java.io.IOException;

import org.apache.shiro.authz.annotation.RequiresRoles;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.ehcache.CacheInterceptor;
import com.jfinal.plugin.ehcache.CacheName;
import com.minws.wechat.entity.ErrorMsg;
import com.minws.wechat.entity.WechatMenu;
import com.minws.wechat.frame.sdk.wechat.api.MenuApi;
import com.minws.wechat.model.Config;
import com.minws.wechat.model.back.Article;

public class BackController extends Controller {

	@RequiresRoles("admin")
	public void index() {
		render("index.htm");
	}

	public void welcome() {
		render("pages/welcome.htm");
	}

	public void packages() {
		render("pages/packages.htm");
	}

	@Before(CacheInterceptor.class)
	@CacheName("articleList")
	public void articleList() {
		Integer categoryId = getParaToInt("categoryId", 1);
		Integer pageNumber = getParaToInt("pageNumber", 1);
		Integer pageSize = getParaToInt("pageSize", 10);
		setAttr("articlePage", Article.dao.findArticlesByCategoryId(categoryId, pageNumber, pageSize));
		setAttr("categoryId", categoryId);
		render("pages/articleList.htm");
	}

	public void articleManage() {
		Integer categoryId = getParaToInt("categoryId", 1);
		Integer pageNumber = getParaToInt("pageNumber", 1);
		Integer pageSize = getParaToInt("pageSize", 10);
		setAttr("articlePage", Article.dao.findAllArticles(pageNumber, pageSize));
		setAttr("categoryId", categoryId);
		render("pages/articleList.htm");
	}

	public void pictureList() {
		Integer categoryId = getParaToInt("categoryId", 1);
		Integer pageNumber = getParaToInt("pageNumber", 1);
		Integer pageSize = getParaToInt("pageSize", 10);
		render("pages/pictureList.htm");
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
		String wechatMenuJson = Config.dao.getValueByKey("wechat_menu");
		WechatMenu wechatMenu = new ObjectMapper().readValue(wechatMenuJson, WechatMenu.class);
		setAttr("wechatMenuJson", wechatMenuJson);
		setAttr("wechatMenu", wechatMenu);
		render("manageWechatMenu.htm");
	}

}
