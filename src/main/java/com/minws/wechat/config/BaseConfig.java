package com.minws.wechat.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.handler.UrlSkipHandler;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.ext.plugin.config.ConfigPlugin;
import com.jfinal.ext.plugin.shiro.ShiroInterceptor;
import com.jfinal.ext.plugin.shiro.ShiroPlugin;
import com.jfinal.ext.plugin.tablebind.AutoTableBindPlugin;
import com.jfinal.ext.plugin.tablebind.SimpleNameStyles;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.render.FreeMarkerRender;
import com.jfinal.render.IErrorRenderFactory;
import com.jfinal.render.RedirectRender;
import com.jfinal.render.Render;
import com.minws.wechat.controller.ApiController;
import com.minws.wechat.controller.BackController;
import com.minws.wechat.controller.FrontController;
import com.minws.wechat.controller.SecurityController;
import com.minws.wechat.controller.ShopController;
import com.minws.wechat.controller.WechatController;
import com.minws.wechat.frame.kit.HttpKit;
import com.minws.wechat.frame.plugin.shiro.FreemarketShiroTags;
import com.minws.wechat.frame.sdk.alipay.ctrl.AlipayController;
import com.minws.wechat.sdk.api.ApiConfig;

public class BaseConfig extends JFinalConfig {

	private Routes routes;

	@Override
	public void configConstant(Constants cs) {
		loadPropertyFile("config.txt");
		cs.setDevMode(getPropertyToBoolean("wx.devMode"));
		cs.setError401View("/security/signin");
		cs.setError403View("/security/signin");
		cs.setError404View("/security/err404");
		cs.setError500View("/security/err500");
		cs.setErrorRenderFactory(new IErrorRenderFactory() {
			@Override
			public Render getRender(int errorCode, String view) {
				return new RedirectRender(view);
			}
		});
		// 配置微信 API 相关常量
		ApiConfig.setDevMode(getPropertyToBoolean("wx.devMode"));
		ApiConfig.setUrl(getProperty("wx.url"));
		ApiConfig.setToken(getProperty("wx.token"));
		ApiConfig.setAppId(getProperty("wx.appId"));
		ApiConfig.setAppSecret(getProperty("wx.appSecret"));
	}

	@Override
	public void configRoute(Routes rs) {
		this.routes = rs;

		rs.add("/wechat", WechatController.class);
		rs.add("/api", ApiController.class);
		rs.add("/security", SecurityController.class, "/security");
		rs.add("/front", FrontController.class, "/front");
		rs.add("/shop", ShopController.class, "/shop");
		rs.add("/back", BackController.class, "/back");
		rs.add("/alipay", AlipayController.class, "/alipay");
	}

	@Override
	public void configPlugin(Plugins ps) {
		// 添加shiro支持
		ps.add(new ShiroPlugin(routes));
		// 添加缓存支持
		ps.add(new EhCachePlugin(BaseConfig.class.getClassLoader().getResource("ehcache-model.xml")));
		// 配置数据库连接池插件
		DruidPlugin druidPlugin = new DruidPlugin(getProperty("wx.jdbcUrl"), getProperty("wx.jdbcUser"), getProperty("wx.jdbcPassword"), getProperty("wx.jdbcDriver"));
		druidPlugin.addFilter(new StatFilter());
		ps.add(druidPlugin);
		// 添加自动绑定model与表插件
		AutoTableBindPlugin autoTableBindPlugin = new AutoTableBindPlugin(druidPlugin, SimpleNameStyles.LOWER_UNDERLINE);
		autoTableBindPlugin.setShowSql(true);
		autoTableBindPlugin.setContainerFactory(new CaseInsensitiveContainerFactory());
		// autoTableBindPlugin.setAutoScan(false);
		ps.add(autoTableBindPlugin);
		// 添加资源文件
		ps.add(new ConfigPlugin("message_zh.txt", "config.txt"));
	}

	@Override
	public void configInterceptor(Interceptors me) {
		// shiro拦截器
		me.add(new ShiroInterceptor());
		// 让 模版 可以使用session
		me.add(new SessionInViewInterceptor());
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
		FreeMarkerRender.getConfiguration().setSharedVariable("shiro", new FreemarketShiroTags());
	}
}
