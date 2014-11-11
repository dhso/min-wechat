/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.minws.wechat.sdk.weixin;

import com.minws.wechat.sdk.msg.in.InImageMsg;
import com.minws.wechat.sdk.msg.in.InLinkMsg;
import com.minws.wechat.sdk.msg.in.InLocationMsg;
import com.minws.wechat.sdk.msg.in.InTextMsg;
import com.minws.wechat.sdk.msg.in.InVideoMsg;
import com.minws.wechat.sdk.msg.in.InVoiceMsg;
import com.minws.wechat.sdk.msg.in.event.InFollowEvent;
import com.minws.wechat.sdk.msg.in.event.InLocationEvent;
import com.minws.wechat.sdk.msg.in.event.InMenuEvent;
import com.minws.wechat.sdk.msg.in.event.InQrCodeEvent;
import com.minws.wechat.sdk.msg.in.speech_recognition.InSpeechRecognitionResults;

/**
 * WeixinControllerAdapter
 */
public abstract class WeixinControllerAdapter extends WeixinController {
	
	protected abstract void processInFollowEvent(InFollowEvent inFollowEvent);
	
	protected abstract void processInTextMsg(InTextMsg inTextMsg);
	
	protected abstract void processInMenuEvent(InMenuEvent inMenuEvent);
	
	protected void processInImageMsg(InImageMsg inImageMsg) {
		
	}
	
	protected void processInVoiceMsg(InVoiceMsg inVoiceMsg) {
		
	}
	
	protected void processInVideoMsg(InVideoMsg inVideoMsg) {
		
	}
	
	protected void processInLocationMsg(InLocationMsg inLocationMsg) {
		
	}
	
	protected void processInLinkMsg(InLinkMsg inLinkMsg) {
		
	}
	
	protected void processInQrCodeEvent(InQrCodeEvent inQrCodeEvent) {
		
	}
	
	protected void processInLocationEvent(InLocationEvent inLocationEvent) {
		
	}
	
	protected void processInSpeechRecognitionResults(InSpeechRecognitionResults inSpeechRecognitionResults) {
		
	}
}


