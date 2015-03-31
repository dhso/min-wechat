package com.minws.wechat.controller.sys;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.minws.wechat.validator.SigninValidator;

public class SecurityController extends Controller {

	// 默认登录页面
	public void index() {
		render("login.htm");
	}

	// 登录页面
	public void login() {
		render("login.htm");
	}

	// 登录Action
	@Before(SigninValidator.class)
	public void signin() {
		if ("GET".equalsIgnoreCase(this.getRequest().getMethod().toUpperCase())) {
			forwardAction("/security/login");
		} else if ("POST".equalsIgnoreCase(this.getRequest().getMethod().toUpperCase())) {
			String username = getPara("username");
			String password = getPara("password");
			Boolean rememberMe = "on".equalsIgnoreCase(getPara("rememberMe", "off"));
			Subject currentUser = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
			try {
				currentUser.login(token);
				redirect("/back/index");
			} catch (Exception e) {
				// 登录失败
				String esn = e.getClass().getSimpleName();
				if ("IncorrectCredentialsException".equalsIgnoreCase(esn)) {
					setAttr("errorMsg", "用户名或者密码不正确！");
				} else if ("UnknownAccountException".equalsIgnoreCase(esn)) {
					setAttr("errorMsg", "用户名错误！");
				} else if ("LockedAccountException".equalsIgnoreCase(esn)) {
					setAttr("errorMsg", "用户已锁定！");
				} else if ("AuthenticationException".equalsIgnoreCase(esn)) {
					setAttr("errorMsg", "用户认证失败！");
				} else if ("ExcessiveAttemptsException".equalsIgnoreCase(esn)) {
					setAttr("errorMsg", "用户登录错误次数过多，10分钟内禁止登录！");
				} else if ("DisabledAccountException".equalsIgnoreCase(esn)) {
					setAttr("errorMsg", "用户已禁用！");
				} else if ("ExpiredCredentialsException".equalsIgnoreCase(esn)) {
					setAttr("errorMsg", "用户已过期！");
				} else {
					setAttr("errorMsg", "未知错误！");
				}
				setAttr("username", username);
				setAttr("rememberMe", rememberMe);
				forwardAction("/security/login");
			}
		}

	}

	// 登出Action
	public void signout() {
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated()) {
			currentUser.logout();
		}
		forwardAction("/security/login");
	}

	public void err401() {
		setAttr("msg", "401 Unauthorized");
		setAttr("success", false);
		renderJson();
	}

	public void err403() {
		setAttr("msg", "403 Forbidden");
		setAttr("success", false);
		renderJson();
	}

	public void err404() {
		render("error/404.htm");
	}

	public void err500() {
		render("error/500.htm");
	}
}
