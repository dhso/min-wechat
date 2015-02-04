package com.minws.wechat.frame.sdk.alipay.ctrl;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;

import com.jfinal.core.Controller;
import com.minws.wechat.frame.sdk.alipay.config.AlipayConfig;
import com.minws.wechat.frame.sdk.alipay.util.AlipayNotify;

public class AlipayController extends Controller {

	public void index() {
		renderJsp("index.jsp");
	}

	public void alipayApi() {
		renderJsp("alipayapi.jsp");
	}

	public void notifyUrl() throws Exception {
		// 获取支付宝GET过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = getParaMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			params.put(name, new String(valueStr.getBytes("ISO-8859-1"), "utf-8"));
		}
		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//

		// RSA签名解密
		if (AlipayConfig.sign_type.equals("0001")) {
			params = AlipayNotify.decrypt(params);
		}
		// XML解析notify_data数据
		Document doc_notify_data = DocumentHelper.parseText(params.get("notify_data"));
		// 商户订单号
		String out_trade_no = doc_notify_data.selectSingleNode("//notify/out_trade_no").getText();
		// 支付宝交易号
		String trade_no = doc_notify_data.selectSingleNode("//notify/trade_no").getText();
		// 交易状态
		String trade_status = doc_notify_data.selectSingleNode("//notify/trade_status").getText();
		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

		if (AlipayNotify.verifyNotify(params)) {// 验证成功
			// 请在这里加上商户的业务逻辑程序代码
			// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			if (trade_status.equals("TRADE_FINISHED")) {
				// 判断该笔订单是否在商户网站中已经做过处理
				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				// 如果有做过处理，不执行商户的业务程序

				// 注意：
				// 该种交易状态只在两种情况下出现
				// 1、开通了普通即时到账，买家付款成功后。
				// 2、开通了高级即时到账，从该笔交易成功时间算起，过了签约时的可退款时限（如：三个月以内可退款、一年以内可退款等）后。

				renderText("success"); // 请不要修改或删除
			} else if (trade_status.equals("TRADE_SUCCESS")) {
				// 判断该笔订单是否在商户网站中已经做过处理
				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				// 如果有做过处理，不执行商户的业务程序

				// 注意：
				// 该种交易状态只在一种情况下出现——开通了高级即时到账，买家付款成功后。

				renderText("success"); // 请不要修改或删除
			}
		} else {// 验证失败
			renderText("fail");
		}
	}

	public void callBackUrl() throws Exception {
		// 获取支付宝GET过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = getParaMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			params.put(name, new String(valueStr.getBytes("ISO-8859-1"), "utf-8"));
		}

		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		// 商户订单号

		String out_trade_no = new String(getPara("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

		// 支付宝交易号

		String trade_no = new String(getPara("trade_no").getBytes("ISO-8859-1"), "UTF-8");

		// 交易状态
		String result = new String(getPara("result").getBytes("ISO-8859-1"), "UTF-8");

		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

		// 计算得出通知验证结果
		boolean verify_result = AlipayNotify.verifyReturn(params);

		if (verify_result) {// 验证成功
			// ////////////////////////////////////////////////////////////////////////////////////////
			// 请在这里加上商户的业务逻辑程序代码

			// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

			// 判断该笔订单是否在商户网站中已经做过处理
			// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
			// 如果有做过处理，不执行商户的业务程序

			// 该页面可做页面美工编辑
			renderText("验证成功<br />");
			// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

			// ////////////////////////////////////////////////////////////////////////////////////////
		} else {
			// 该页面可做页面美工编辑
			renderText("验证失败");
		}
	}

	public void backedUrl() {
		redirect("shop/index");
	}

}
