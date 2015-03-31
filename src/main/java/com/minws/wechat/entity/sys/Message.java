package com.minws.wechat.entity.sys;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = 1651525560155147152L;

	private String MsgCode;

	private String MsgType;

	private String MsgDesc;

	public String getMsgCode() {
		return MsgCode;
	}

	public String getMsgType() {
		return MsgType;
	}

	public String getMsgDesc() {
		return MsgDesc;
	}

	public void setMsgCode(String msgCode) {
		MsgCode = msgCode;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public void setMsgDesc(String msgDesc) {
		MsgDesc = msgDesc;
	}

	public Message() {

	}

	/**
	 * Message
	 * 
	 * @param msgCode
	 * @param msgType
	 * @param msgDesc
	 */
	public Message(String msgCode, String msgType, String msgDesc) {
		this.MsgCode = msgCode;
		this.MsgType = msgType;
		this.MsgDesc = msgDesc;
	}
}
