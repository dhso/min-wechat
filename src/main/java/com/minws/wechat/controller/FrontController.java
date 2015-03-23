package com.minws.wechat.controller;

import com.jfinal.core.Controller;

public class FrontController extends Controller {

	public void index() {
		render("index.htm");
	}

	public void blog() {
		render("blog.htm");
	}

	public void page() {
		render("page.htm");
	}

	public void pageTypography() {
		render("page-typography.htm");
	}

	public void pageElements() {
		render("page-elements.htm");
	}

	public void work() {
		render("work.htm");
	}

	public void contact() {
		render("contact.htm");
	}
}
