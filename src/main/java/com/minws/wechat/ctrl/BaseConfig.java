package com.minws.wechat.ctrl;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.minws.wechat.frame.util.ProsMap;
import com.minws.wechat.sdk.api.ApiConfig;

public class BaseConfig extends JFinalConfig {

	public void configConstant(Constants cs) {
		// 配置微信 API 相关常量
		ApiConfig.setDevMode(ProsMap.getBooPro("wx.devMode"));
		ApiConfig.setUrl(ProsMap.getStrPro("wx.url"));
		ApiConfig.setToken(ProsMap.getStrPro("wx.token"));
		ApiConfig.setAppId(ProsMap.getStrPro("wx.appId"));
		ApiConfig.setAppSecret(ProsMap.getStrPro("wx.appSecret"));
	}

	public void configRoute(Routes rs) {
		rs.add(ProsMap.getStrPro("wx.wechatPath"), WechatController.class);
		rs.add(ProsMap.getStrPro("wx.apiPath"), ApiController.class, ProsMap.getStrPro("wx.apiPath"));
	}

	public void configPlugin(Plugins ps) {
		//C3p0Plugin c3p0Plugin = new C3p0Plugin(ProsMap.getStrPro("wx.jdbcUrl"), ProsMap.getStrPro("wx.user"), ProsMap.getStrPro("wx.password").trim());
		//ps.add(c3p0Plugin);

		EhCachePlugin ecp = new EhCachePlugin();
		ps.add(ecp);
	}

	public void configInterceptor(Interceptors me) {

	}

	public void configHandler(Handlers me) {

	}

	public static void main(String[] args) {
		JFinal.start("src/main/webapp", 80, "/", 5);
	}
}
