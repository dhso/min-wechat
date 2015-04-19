package com.minws.wechat.model.shop;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

@SuppressWarnings("serial")
@TableBind(tableName = "shop_history", pkName = "history_id")
public class ShopHistory extends Model<ShopHistory> {
	public static final ShopHistory dao = new ShopHistory();

	/**
	 * 分页获取所有商城历史记录
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<Record> getAllHistory(int pageNumber, int pageSize) {
		return Db.paginate(pageNumber, pageSize, "select *", "from shop_history");
	}

	/**
	 * 根据openId获取历史记录
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param openId
	 * @return
	 */
	public Page<Record> getHistoryByUid(int pageNumber, int pageSize, String openId) {
		return Db.paginate(pageNumber, pageSize, "select * ", "from shop_charge_history where open_id = ?", openId);
	}

	/**
	 * 添加历史
	 * 
	 * @param openId
	 * @param eventId
	 * @param eventDesc
	 * @param updateId
	 */
	public void addHistory(String openId, int eventId, String eventDesc, String updateId) {
		Db.update("insert into shop_history(open_id,event_id,event_desc,update_id) values(?,?,?,?)", openId, eventId, eventDesc, updateId);
	}
}
