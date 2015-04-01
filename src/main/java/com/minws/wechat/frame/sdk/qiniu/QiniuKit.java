package com.minws.wechat.frame.sdk.qiniu;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.qiniu.api.fop.ImageView;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.net.CallRet;
import com.qiniu.api.rs.EntryPath;
import com.qiniu.api.rs.RSClient;
import com.qiniu.api.rsf.ListItem;
import com.qiniu.api.rsf.ListPrefixRet;
import com.qiniu.api.rsf.RSFClient;

public class QiniuKit {
	private QiniuConfig qiniuConfig = null;

	public QiniuKit(String ak, String sk) {
		this.qiniuConfig = QiniuConfig.getInstance(ak, sk);
	}

	/**
	 * 上传文件
	 * 
	 * @param bucketName
	 * @param filePath
	 * @param is
	 * @return
	 */
	public PutRet put(String bucketName, String filePath, InputStream is) {
		String token = qiniuConfig.getToken(bucketName);
		return IoApi.Put(token, filePath, is, new PutExtra());
	}

	/**
	 * 上传文件
	 * 
	 * @param bucketName
	 * @param filePath
	 * @param file
	 * @return
	 */
	public PutRet put(String bucketName, String filePath, File file) {
		String token = qiniuConfig.getToken(bucketName);
		return IoApi.putFile(token, filePath, file, new PutExtra());
	}

	/**
	 * 批量获取文件列表
	 * 
	 * @param bucketName
	 * @return
	 */
	public List<ListItem> list(String bucketName) {
		return list(bucketName, 0);
	}

	/**
	 * 批量获取文件列表
	 * 
	 * @param bucketName
	 * @param count
	 * @return
	 */
	public List<ListItem> list(String bucketName, int count) {
		return list(bucketName, "", count);
	}

	/**
	 * 批量获取文件列表
	 * 
	 * @param bucketName
	 * @param directoryPath
	 * @return
	 */
	public List<ListItem> list(String bucketName, String directoryPath) {
		return list(bucketName, directoryPath, 0);
	}

	/**
	 * 批量获取文件列表
	 * 
	 * @param bucketName
	 * @param directoryPath
	 * @param count
	 * @return
	 */
	public List<ListItem> list(String bucketName, String directoryPath, int count) {
		if (directoryPath == null) {
			directoryPath = "";
		}
		if (count == 0) {
			count = Integer.MAX_VALUE;
		}
		RSFClient client = qiniuConfig.getRSFClient();

		ListPrefixRet list = client.listPrifix(bucketName, directoryPath, "", count);
		if (list == null)
			return null;

		List<ListItem> items = new ArrayList<ListItem>();
		items.addAll(list.results);

		return items;
	}

	/**
	 * 删除文件
	 * 
	 * @param bucketName
	 * @param filePath
	 */
	public void remove(String bucketName, String filePath) {
		RSClient client = qiniuConfig.getRSClient();
		client.delete(bucketName, filePath);
	}

	/**
	 * 批量删除文件
	 * 
	 * @param entryPath
	 */
	public void batchRemove(List<EntryPath> entryPath) {
		RSClient client = qiniuConfig.getRSClient();
		client.batchDelete(entryPath);
	}

	/**
	 * 预览文件 format = jpg
	 * 
	 * @param url
	 * @param height
	 * @param width
	 * @param quality
	 * @param mode
	 * @param format
	 * @return
	 */
	public CallRet preView(String url, int height, int width, int quality, int mode, String format) {
		ImageView iv = new ImageView();
		iv.height = height;
		iv.width = width;
		iv.quality = quality;
		iv.mode = mode;
		iv.format = format;
		return iv.call(url);
	}
}
