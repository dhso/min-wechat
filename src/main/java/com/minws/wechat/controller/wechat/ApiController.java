package com.minws.wechat.controller.wechat;


import com.jfinal.core.Controller;
import com.minws.wechat.frame.sdk.wechat.api.ApiResult;
import com.minws.wechat.frame.sdk.wechat.api.MenuApi;
import com.minws.wechat.frame.sdk.wechat.api.UserApi;
import com.minws.wechat.model.sys.Config;

public class ApiController extends Controller {

	/*
	 * public void index() { render("/api/index.html"); }
	 */

	/**
	 * 获取公众号菜单
	 */
	public void getMenu() {
		ApiResult apiResult = MenuApi.getMenu();
		if (apiResult.isSucceed())
			renderText(apiResult.getJson());
		else
			renderText(apiResult.getErrorMsg());
	}
	
	/**
	 * 获取公众号关注用户
	 */
	public void refreshMenu() {
		ApiResult apiResult = MenuApi.createMenu(Config.dao.getValueByKey("wechat_menu"));
		// TODO 用 jackson 解析结果出来
		if (apiResult.isSucceed())
			renderText(apiResult.getJson());
		else
			renderText(apiResult.getErrorMsg());
	}

	/**
	 * 获取公众号关注用户
	 */
	public void getFollowers() {
		ApiResult apiResult = UserApi.getFollows();
		// TODO 用 jackson 解析结果出来
		renderText(apiResult.getJson());
	}
}
