package com.minws.wechat.frame.sdk.qiniu;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.rs.RSClient;
import com.qiniu.api.rsf.ListItem;
import com.qiniu.api.rsf.ListPrefixRet;
import com.qiniu.api.rsf.RSFClient;

public class QiniuKit {
	static QiniuConfig mConfig = new QiniuConfig();

	static void init(QiniuConfig config) {
		mConfig = config;
	}

	public static PutRet put(String bucketName, String filePath, InputStream is) {
		String token = mConfig.getToken(bucketName);
		return IoApi.Put(token, filePath, is, new PutExtra());
	}

	public static PutRet put(String bucketName, String filePath, File file) {
		String token = mConfig.getToken(bucketName);
		return IoApi.putFile(token, filePath, file, new PutExtra());
	}

	public static List<ListItem> list(String bucketName) {
		return list(bucketName, 0);
	}

	public static List<ListItem> list(String bucketName, int count) {
		return list(bucketName, "", count);
	}

	public static List<ListItem> list(String bucketName, String directoryPath) {
		return list(bucketName, directoryPath, 0);
	}

	public static List<ListItem> list(String bucketName, String directoryPath, int count) {
		if (directoryPath == null) {
			directoryPath = "";
		}
		if (count == 0) {
			count = Integer.MAX_VALUE;
		}
		RSFClient client = mConfig.getRSFClient();

		ListPrefixRet list = client.listPrifix(bucketName, directoryPath, "", count);
		if (list == null)
			return null;

		List<ListItem> items = new ArrayList<ListItem>();
		items.addAll(list.results);

		return items;
	}

	public static void remove(String bucketName, String filePath) {
		RSClient client = mConfig.getRSClient();
		client.delete(bucketName, filePath);
	}
}
