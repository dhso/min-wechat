package com.minws.wechat.model.sys;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
@TableBind(tableName = "config", pkName = "id")
public class Config extends Model<Config> {
	public static final Config dao = new Config();

	public String getValueByKey(String key) {
		return Db.findFirst("select c.value from config c where c.key = ?", key).get("value", "");
	}

	public int setValueByKey(String key, String value) {
		return Db.update("update config set value = ? where key = ?", value, key);
	}

}
