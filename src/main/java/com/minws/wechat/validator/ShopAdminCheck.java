/**
 * 
 */
/**
 * @author Administrator
 *
 */
package com.minws.wechat.validator;

import java.util.Arrays;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.minws.wechat.entity.sys.Message;
import com.minws.wechat.model.sys.Config;

public class ShopAdminCheck implements Interceptor {

	public void intercept(ActionInvocation ai) {
		String uid = ai.getController().getPara("aid");
		String shopAdminOpenId = Config.dao.getValueByKey("shop_admin_openId");
		if (Arrays.asList(shopAdminOpenId.split(",")).contains(uid)) {
			ai.invoke();
		} else {
			ai.getController().renderJson(new Message("600", "error", "没有管理权限！"));
			return;
		}
	}
}