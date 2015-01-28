/**
 * 
 */
/**
 * @author Administrator
 *
 */
package com.minws.wechat.frame.sdk.simsimi;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.minws.wechat.frame.kit.StringKit;
import com.minws.wechat.sdk.kit.HttpKit;

public class SimsimiSdk {

	/**
	 * 问小黄鸡
	 * @param ask
	 * @return
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	public static String askSimsimi(String ask) throws JsonProcessingException, IOException {
		String url = "http://www.simsimi.com/func/reqN?lc=ch&ft=0.0&req=" + URLEncoder.encode(ask, "UTF-8") + "&fl=http%3A%2F%2Fwww.simsimi.com%2Ftalk.htm";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("USER_AGENT", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.154 Safari/537.36");
		headers.put("Cookie", "simsimi_uid=59406430");
		String result = HttpKit.get(url, null, headers);
		if (StringKit.isNotBlank(result)) {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readTree(result); // 读取Json
			result = rootNode.path("sentence_resp").asText();
		}
		return result;
	}

}
