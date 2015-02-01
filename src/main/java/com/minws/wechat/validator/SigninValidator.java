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

public class SigninValidator extends Validator {
	
	protected void validate(Controller c) {
		if ("POST".equalsIgnoreCase(c.getRequest().getMethod().toUpperCase())) {
			validateRequiredString("password", "errorMsg", "请输入密码");
			validateRequiredString("username", "errorMsg", "请输入用户名");
		}
	}

	protected void handleError(Controller c) {
		c.setAttr("username", c.getPara("username", ""));
		c.forwardAction("/security/login");
		return;
	}
}