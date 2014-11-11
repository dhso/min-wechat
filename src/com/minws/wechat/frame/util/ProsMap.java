package com.minws.wechat.frame.util;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 全局配置类
 * 
 */
public class ProsMap {

	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = Maps.newHashMap();

	/**
	 * 全局编码
	 */
	public final static String ENCODING = "UTF-8";

	/**
	 * 属性文件加载对象
	 */
	private static PropertiesLoader propertiesLoader = null;

	static {
		if (propertiesLoader == null) {
			propertiesLoader = new PropertiesLoader("wechat_config.properties", "message_zh.properties");
		}
	}

	/**
	 * 获取配置(String)
	 */
	public static String getStrPro(String key) {
		String value = map.get(key);
		if (value == null) {
			value = propertiesLoader.getProperty(key);
			map.put(key, value);
		}
		return value;
	}

	/**
	 * 获取配置(Boolean)
	 */
	public static Boolean getBooPro(String key) {
		String value = map.get(key);
		Boolean resultBool = null;
		if (value == null) {
			value = propertiesLoader.getProperty(key);
			map.put(key, value);
		}

		if (value != null) {
			if (value.trim().equalsIgnoreCase("true"))
				resultBool = true;
			else if (value.trim().equalsIgnoreCase("false"))
				resultBool = false;
		}
		return resultBool;
	}
}
