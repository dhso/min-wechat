package com.minws.wechat.model;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
@TableBind(tableName = "config", pkName = "id")
public class Config extends Model<Config> {
	public static final Config dao = new Config();

	public String getValueByKey(String key) {
		return Config.dao.findFirst("select cfg.value from config as cfg where cfg.key = ?", key).get("value", "");
	}

	public boolean updateValueByKey(String key, String value) {
		return Config.dao.findFirst("select * from config as cfg where cfg.key = ?", key).set("value", value).update();
	}

}
