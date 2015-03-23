package com.minws.wechat.controller;

import com.jfinal.core.Controller;


public class MainController extends Controller{

	public void index() {
		render("front/index.htm");
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
