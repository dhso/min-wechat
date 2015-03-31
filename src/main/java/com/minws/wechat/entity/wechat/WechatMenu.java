package com.minws.wechat.entity.wechat;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WechatMenu implements Serializable{

	private static final long serialVersionUID = 1651525560155147152L;
	
	private List<WechatMenuButton> button;

	public List<WechatMenuButton> getButton() {
		return button;
	}

	public void setButton(List<WechatMenuButton> button) {
		this.button = button;
	}
	
	
}
