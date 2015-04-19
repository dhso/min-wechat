package com.minws.wechat.model.shop;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.minws.wechat.frame.kit.StringKit;

@SuppressWarnings("serial")
@TableBind(tableName = "shop_user", pkName = "open_id")
public class ShopUser extends Model<ShopUser> {
	public static final ShopUser dao = new ShopUser();

	/**
	 * 获取所有用户
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<Record> getAllUser(int pageNumber, int pageSize) {
		return Db.paginate(pageNumber, pageSize, "select *", "from shop_user");
	}

	/**
	 * 获取用户
	 * 
	 * @param openId
	 * @return
	 */
	public Record getUser(String openId) {
		return Db.findFirst("select * from shop_user where open_id = ?", openId);
	}

	/**
	 * 添加用户
	 * 
	 * @param openId
	 * @param from
	 * @param name
	 * @param phone
	 * @param password
	 * @param address
	 * @param money
	 * @return
	 */
	public int addUser(String openId, String from, String name, String phone, String password, String address, String money) {
		return Db.update("insert into shop_user(open_id,from,name,phone,password,address,money) values (?,?,?,?,?,?,?)", openId, from, name, phone, password, address, money);
	}

	/**
	 * 更新用户
	 * 
	 * @param openId
	 * @param name
	 * @param phone
	 * @param address
	 * @return
	 */
	public int updateUser(String openId, String name, String phone, String address) {
		return Db.update("update shop_user set name = ?, phone = ?, address = ? where open_id = ?", name, phone, address, openId);
	}

	/**
	 * 充值/扣钱
	 * 
	 * @param targetOpenId
	 * @param money
	 * @param updateId
	 */
	public void addMoney(String targetOpenId, String money, String updateId) {
		Record shopUser = Db.findFirst("select * from shop_user where open_id = ?", targetOpenId);
		Db.update("update shop_user set money = ? where open_id= ?", StringKit.toFloat(shopUser.getStr("money")) + StringKit.toFloat(money), targetOpenId);
		ShopHistory.dao.addHistory(targetOpenId, 1, money, updateId);
	}
}
