package com.minws.wechat.frame.sdk.ueditor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;

import com.minws.wechat.frame.sdk.ueditor.define.ActionMap;
import com.minws.wechat.frame.sdk.ueditor.define.AppInfo;
import com.minws.wechat.frame.sdk.ueditor.define.BaseState;
import com.minws.wechat.frame.sdk.ueditor.define.State;
import com.minws.wechat.frame.sdk.ueditor.hunter.FileManager;
import com.minws.wechat.frame.sdk.ueditor.hunter.ImageHunter;
import com.minws.wechat.frame.sdk.ueditor.upload.Uploader;

public class UeditorKit {

	private HttpServletRequest request = null;
	private UeditorConfig ueditorConfig = null;
	private String actionType = null;

	public UeditorKit(HttpServletRequest request) {
		this.request = request;
		this.actionType = request.getParameter("action");
		this.ueditorConfig = UeditorConfig.getInstance();
	}

	public String exec() throws JSONException {
		String callbackName = this.request.getParameter("callback");
		if (callbackName != null) {
			if (!validCallbackName(callbackName)) {
				return new BaseState(false, AppInfo.ILLEGAL).toJSONString();
			}
			return callbackName + "(" + this.invoke() + ");";
		} else {
			return this.invoke();
		}
	}

	public String invoke() throws JSONException {
		if (actionType == null || !ActionMap.mapping.containsKey(actionType)) {
			return new BaseState(false, AppInfo.INVALID_ACTION).toJSONString();
		}
		if (ueditorConfig == null || !ueditorConfig.valid()) {
			return new BaseState(false, AppInfo.CONFIG_ERROR).toJSONString();
		}
		State state = null;
		Map<String, Object> conf = null;
		int actionCode = ActionMap.getType(actionType);
		switch (actionCode) {
		case ActionMap.CONFIG:
			return ueditorConfig.getAllConfig().toString();
		case ActionMap.UPLOAD_IMAGE:
		case ActionMap.UPLOAD_SCRAWL:
		case ActionMap.UPLOAD_VIDEO:
		case ActionMap.UPLOAD_FILE:
			conf = ueditorConfig.getConfig(actionCode);
			state = new Uploader(request, conf).doExec();
			break;
		case ActionMap.CATCH_IMAGE:
			conf = ueditorConfig.getConfig(actionCode);
			String[] list = request.getParameterValues((String) conf.get("fieldName"));
			state = new ImageHunter(conf).capture(list);
			break;
		case ActionMap.LIST_IMAGE:
		case ActionMap.LIST_FILE:
			conf = ueditorConfig.getConfig(actionCode);
			int start = this.getStartIndex();
			state = new FileManager(conf).listFile(start);
			break;
		}
		return state.toJSONString();
	}

	public int getStartIndex() {
		String start = this.request.getParameter("start");
		try {
			return Integer.parseInt(start);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * callback参数验证
	 */
	public boolean validCallbackName(String name) {
		if (name.matches("^[a-zA-Z_]+[\\w0-9_]*$")) {
			return true;
		}
		return false;
	}
}