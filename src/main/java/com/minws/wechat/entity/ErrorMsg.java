package com.minws.wechat.entity;

import java.io.Serializable;

public class ErrorMsg implements Serializable {

	private static final long serialVersionUID = 1651525560155147152L;

	private String errType;

	private String errMessage;

	public String getErrType() {
		return errType;
	}

	public void setErrType(String errType) {
		this.errType = errType;
	}

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	public ErrorMsg(String errType, String errMessage) {
		this.errType = errType;
		this.errMessage = errMessage;
	}

}
