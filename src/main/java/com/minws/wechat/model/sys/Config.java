package com.minws.wechat.model.sys;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
@TableBind(tableName = "config", pkName = "id")
public class Config extends Model<Config> {
	public static final Config dao = new Config();

	/**
	 * 获取配置
	 * 
	 * @param key
	 * @return
	 */
	public String getValueByKey(String key) {
		return Db.findFirst("select c.value from config c where c.key = ?", key).get("value", "");
	}

	/**
	 * 新建配置
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public int setValue(String key, String value) {
		return Db.update("update config c set c.value = ? where c.key = ?", value, key);
	}

}
