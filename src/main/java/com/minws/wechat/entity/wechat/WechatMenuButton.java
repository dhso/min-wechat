package com.minws.wechat.entity.wechat;

import java.io.Serializable;
import java.util.List;

public class WechatMenuButton implements Serializable{

	private static final long serialVersionUID = 1651525560155147152L;
	
	private String type;
	
	private String name;
	
	private String url;
	
	private String key;
	
	private List<WechatMenuButton> sub_button;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<WechatMenuButton> getSub_button() {
		return sub_button;
	}

	public void setSub_button(List<WechatMenuButton> sub_button) {
		this.sub_button = sub_button;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	
}
