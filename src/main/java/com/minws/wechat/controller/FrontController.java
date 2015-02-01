package com.minws.wechat.controller;

import com.jfinal.core.Controller;


public class FrontController extends Controller{

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
