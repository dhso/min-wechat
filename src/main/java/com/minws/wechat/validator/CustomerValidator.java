/**
 * 
 */
/**
 * @author Administrator
 *
 */
package com.minws.wechat.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;
import com.minws.wechat.entity.ErrorMsg;

public class CustomerValidator extends Validator {

	protected void validate(Controller c) {
		if ("POST".equalsIgnoreCase(c.getRequest().getMethod().toUpperCase())) {
			validateRequiredString("card_id", "errorMsg", "请输入卡号");
			validateRequiredString("name", "errorMsg", "请输入姓名");
			validateRequiredString("gender", "errorMsg", "请输入性别");
			validateRequiredString("brithdate", "errorMsg", "请输入生日");
			validateEmail("email", "errorMsg", "请输入正确的邮箱");
		}
	}

	protected void handleError(Controller c) {
		c.renderJson(new ErrorMsg("errorMsg", c.getAttrForStr("errorMsg")));
	}
}