package com.minws.wechat.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresRoles;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jfinal.core.Controller;
import com.minws.wechat.entity.Message;
import com.minws.wechat.frame.kit.StringKit;
import com.minws.wechat.model.customer.Customer;

public class CustomerController extends Controller {

	@RequiresRoles("admin")
	public void index() {
		render("manageCustomer.htm");
	}

	/**
	 * 新增客户
	 * 
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @throws ParseException
	 */
	@RequiresRoles("admin")
	public void newCustomer() throws JsonParseException, JsonMappingException, IOException, ParseException {
		Map<String, String> map = StringKit.convertStreamToJsonMap(getRequest().getInputStream());
		String card_id = map.get("card_id");
		String name = map.get("name");
		String gender = map.get("gender");
		Date brithdate = StringKit.toDate(map.get("birthdate"), StringKit.DateType1);
		String mobile = map.get("mobile");
		String telephone = map.get("telephone");
		String qq = map.get("qq");
		String email = map.get("email");
		if (!StringKit.validator(email, StringKit.emailAddressPattern)) {
			renderJson(new Message("500", "error", "邮箱地址不正确！"));
			return;
		}
		String identity_card = map.get("identity_card");
		String zip_code = map.get("zip_code");
		String address = map.get("address");
		if (Customer.dao.newCustomer(card_id, name, gender, brithdate, mobile, telephone, qq, email, identity_card, zip_code, address)) {
			renderJson(new Message("200", "success", "添加成功！"));
		} else {
			renderJson(new Message("500", "error", "添加失败！"));
		}
	}
}
