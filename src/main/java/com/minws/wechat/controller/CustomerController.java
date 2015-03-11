package com.minws.wechat.controller;

import java.util.Date;

import org.apache.shiro.authz.annotation.RequiresRoles;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.minws.wechat.entity.ErrorMsg;
import com.minws.wechat.model.customer.Customer;
import com.minws.wechat.validator.CustomerValidator;

public class CustomerController extends Controller {

	@RequiresRoles("admin")
	public void index() {
		render("manageCustomer.htm");
	}

	/**
	 * 新增客户
	 */
	@RequiresRoles("admin")
	@Before(CustomerValidator.class)
	public void newCustomer() {
		String card_id = getPara("card_id");
		String name = getPara("name");
		String gender = getPara("gender", "1");
		Date brithdate = getParaToDate("brithdate");
		String mobile = getPara("mobile", "");
		String telephone = getPara("telephone", "");
		String qq = getPara("qq", "");
		String email = getPara("email", "");
		String identity_card = getPara("identity_card", "");
		String zip_code = getPara("zip_code", "");
		String address = getPara("address", "");
		if (Customer.dao.newCustomer(card_id, name, gender, brithdate, mobile, telephone, qq, email, identity_card, zip_code, address)) {
			renderJson(new ErrorMsg("errorMsg", "添加成功！"));
		} else {
			renderJson(new ErrorMsg("errorMsg", "添加失败！"));
		}
	}
}
