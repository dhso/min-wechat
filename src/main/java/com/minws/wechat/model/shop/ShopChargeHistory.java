package com.minws.wechat.model.shop;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

@SuppressWarnings("serial")
@TableBind(tableName = "shop_charge_history", pkName = "id")
public class ShopChargeHistory extends Model<ShopChargeHistory> {
	public static final ShopChargeHistory dao = new ShopChargeHistory();

	public Page<ShopChargeHistory> getAllHistory(int pageNumber, int pageSize) {
		return ShopChargeHistory.dao.paginate(pageNumber, pageSize, "select *", "from shop_charge_history");
	}

	public Page<ShopChargeHistory> getHistoryByUid(int pageNumber, int pageSize, String uid) {
		return ShopChargeHistory.dao.paginate(pageNumber, pageSize, "select * ", "from shop_charge_history where user_id = ?", uid);
	}

	/**
	 * 添加金额历史
	 * 
	 * @param id
	 *            用户id
	 * @param pay
	 *            支付的钱(支出- / 充值+)
	 * @param remain
	 *            剩下的钱
	 * @param channel
	 *            变更渠道
	 */
	public void addHistory(String id, String pay, String remain, String channel) {
		new ShopChargeHistory().set("user_id", id).set("pay", pay).set("remain", remain).set("channel", channel).save();
	}
}
