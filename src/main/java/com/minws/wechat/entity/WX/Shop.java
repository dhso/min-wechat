package com.minws.wechat.entity.WX;

import java.io.Serializable;

public class Shop implements Serializable {

	private static final long serialVersionUID = 1651525560155147152L;

	private String name;

	private String notification;

	public String getName() {
		return name;
	}

	public String getNotification() {
		return notification;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	public Shop(String name, String notification) {
		this.name = name;
		this.notification = notification;
	}

}
