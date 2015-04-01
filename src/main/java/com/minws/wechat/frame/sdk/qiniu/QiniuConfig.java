/**
 * @author hadong
 *
 */
package com.minws.wechat.frame.sdk.qiniu;

import org.json.JSONException;

import com.jfinal.log.Logger;
import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.rs.PutPolicy;
import com.qiniu.api.rs.RSClient;
import com.qiniu.api.rsf.RSFClient;

public class QiniuConfig {
	private static final Logger logger = Logger.getLogger(QiniuConfig.class);

	private String ak = null;
	private String sk = null;

	private QiniuConfig(String ak, String sk) {
		this.ak = ak;
		this.sk = sk;
	}

	public static QiniuConfig getInstance(String ak, String sk) {
		try {
			return new QiniuConfig(ak, sk);
		} catch (Exception e) {
			return null;
		}
	}

	public String getToken(String bucketName) {
		Mac mac = new Mac(this.ak, this.sk);
		PutPolicy putPolicy = new PutPolicy(bucketName);
		try {
			return putPolicy.token(mac);
		} catch (JSONException e) {
			logger.error("qiniu config get token", e);
		} catch (AuthException e) {
			logger.error("qiniu config get token", e);
		}
		return null;
	}

	public RSFClient getRSFClient() {
		Mac mac = new Mac(this.ak, this.sk);
		RSFClient client = new RSFClient(mac);
		return client;
	}

	public RSClient getRSClient() {
		Mac mac = new Mac(this.ak, this.sk);
		RSClient client = new RSClient(mac);
		return client;
	}

}