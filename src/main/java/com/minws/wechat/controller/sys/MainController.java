package com.minws.wechat.controller.sys;

import com.jfinal.core.Controller;

public class MainController extends Controller {

	public void index() {
		redirect("/cms/index");
	}

	public void mapByBus() {
		render("application/MapByBus.htm");
	}

	public void mapByCar() {
		render("application/MapByCar.htm");
	}

	public void mapByWalk() {
		render("application/MapByWalk.htm");
	}

}
