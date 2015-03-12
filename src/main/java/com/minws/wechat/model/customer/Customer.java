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
import com.jfinal.plugin.activerecord.Page;

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
		Customer customer = new Customer().set("card_id", card_id).set("name", name).set("gender", gender).set("brithdate", brithdate).set("mobile", mobile).set("telephone", telephone).set("qq", qq).set("email", email).set("identity_card", identity_card).set("zip_code", zip_code).set("address", address).set("create_dt", new Date());
		return customer.save();
	}

	/**
	 * 分页查询客户
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<Customer> listCustomer(int pageNumber, int pageSize) {
		return Customer.dao.paginate(pageNumber, pageSize, "select cst.name as name,cst.email as email", "from customers cst where cst.card_status = '1' order by cst.create_dt desc");
	}
}