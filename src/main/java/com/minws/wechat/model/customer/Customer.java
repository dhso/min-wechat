/**
 * 
 */
/**
 * @author hadong
 *
 */
package com.minws.wechat.model.customer;

import java.util.Date;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
@TableBind(tableName = "customers", pkName = "id")
public class Customer extends Model<Customer> {
	public static final Customer dao = new Customer();

	/**
	 * 新增客户
	 * 
	 * @param card_id
	 * @param name
	 * @param gender
	 * @param brithdate
	 * @param mobile
	 * @param telephone
	 * @param qq
	 * @param email
	 * @param identity_card
	 * @param zip_code
	 * @param address
	 */
	public boolean newCustomer(String card_id, String name, String gender, Date brithdate, String mobile, String telephone, String qq, String email, String identity_card, String zip_code, String address) {
		return Customer.dao.set("card_id", card_id).set("name", name).set("gender", gender).set("brithdate", brithdate).set("mobile", mobile).set("telephone", telephone).set("qq", qq).set("email", email).set("identity_card", identity_card).set("zip_code", zip_code).set("address", address).save();
	}
}