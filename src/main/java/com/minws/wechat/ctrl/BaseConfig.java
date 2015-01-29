package com.minws.wechat.ctrl;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.handler.UrlSkipHandler;
import com.jfinal.ext.plugin.config.ConfigPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.minws.wechat.frame.kit.HttpKit;
import com.minws.wechat.sdk.api.ApiConfig;
import com.minws.wechat.sdk.api.MenuApi;

public class BaseConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants cs) {
		loadPropertyFile("config.txt");
		// 配置微信 API 相关常量
		ApiConfig.setDevMode(getPropertyToBoolean("wx.devMode"));
		ApiConfig.setUrl(getProperty("wx.url"));
		ApiConfig.setToken(getProperty("wx.token"));
		ApiConfig.setAppId(getProperty("wx.appId"));
		ApiConfig.setAppSecret(getProperty("wx.appSecret"));
	}

	@Override
	public void configRoute(Routes rs) {
		rs.add(getProperty("wx.wechatPath"), WechatController.class);
		rs.add(getProperty("wx.apiPath"), ApiController.class, getProperty("wx.apiPath"));
	}

	@Override
	public void configPlugin(Plugins ps) {
		EhCachePlugin ecp = new EhCachePlugin();
		ps.add(ecp);
		ps.add(new ConfigPlugin("message_zh.txt", "config.txt"));
	}

	@Override
	public void configInterceptor(Interceptors me) {

	}

	@Override
	public void configHandler(Handlers me) {
		me.add(new UrlSkipHandler(".*/static/.*", false));
		me.add(new ContextPathHandler("baseUrl"));
	}

	public static void main(String[] args) {
		JFinal.start("src/main/webapp", 80, "/", 5);
	}

	@Override
	public void afterJFinalStart() {
		if (getPropertyToBoolean("wx.proxy.open", false))
			HttpKit.setProxy(getProperty("wx.proxy.http.host"), getProperty("wx.proxy.http.port"), getProperty("wx.proxy.auth.username"), getProperty("wx.proxy.auth.password"));
		super.afterJFinalStart();
		MenuApi.createMenu(getProperty("wx.menus"));

	}
}
