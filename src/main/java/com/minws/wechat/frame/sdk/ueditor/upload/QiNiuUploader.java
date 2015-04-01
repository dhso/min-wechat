package com.minws.wechat.frame.sdk.ueditor.upload;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jfinal.ext.plugin.config.ConfigKit;
import com.minws.wechat.frame.sdk.qiniu.QiniuKit;
import com.minws.wechat.frame.sdk.ueditor.define.AppInfo;
import com.minws.wechat.frame.sdk.ueditor.define.BaseState;
import com.minws.wechat.frame.sdk.ueditor.define.FileType;
import com.minws.wechat.frame.sdk.ueditor.define.State;
import com.qiniu.api.io.PutRet;

public class QiNiuUploader {

	public static final State save(HttpServletRequest request, Map<String, Object> conf) {
		FileItem fileItem = null;

		boolean isAjaxUpload = request.getHeader("X_Requested_With") != null;

		if (!ServletFileUpload.isMultipartContent(request)) {
			return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
		}

		ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());

		if (isAjaxUpload) {
			upload.setHeaderEncoding("UTF-8");
		}

		try {
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem item : items) {
				if (!item.isFormField()) {
					fileItem = item;
					break;
				}
				fileItem = null;
			}

			if (fileItem == null) {
				return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
			}

			String originFileName = fileItem.getName();
			long originFileSize = fileItem.getSize();

			String suffix = FileType.getSuffixByFilename(originFileName);
			if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
				return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
			}

			long maxSize = ((Long) conf.get("maxSize")).longValue();
			if (!validSize(originFileSize, maxSize)) {
				return new BaseState(false, AppInfo.MAX_SIZE);
			}

			InputStream is = fileItem.getInputStream();

			PutRet putRet = new QiniuKit(ConfigKit.getStr("wx.qiniu.ak"), ConfigKit.getStr("wx.qiniu.sk")).put(ConfigKit.getStr("wx.qiniu.bucket"), originFileName, is);
			State state = new BaseState(true);
			is.close();
			if (putRet.ok()) {
				String key = putRet.getKey();
				state.putInfo("size", originFileSize);
				state.putInfo("title", originFileName);
				state.putInfo("url", ConfigKit.getStr("wx.qiniu.url") + key);
				state.putInfo("type", suffix);
				state.putInfo("original", "");
				return state;
			} else {
				return new BaseState(false, AppInfo.IO_ERROR);
			}
		} catch (FileUploadException e) {
			return new BaseState(false, AppInfo.PARSE_REQUEST_ERROR);
		} catch (IOException e) {
		}
		return new BaseState(false, AppInfo.IO_ERROR);
	}

	private static boolean validType(String type, String[] allowTypes) {
		List<String> list = Arrays.asList(allowTypes);

		return list.contains(type);
	}

	private static boolean validSize(long size, long length) {
		return size <= length;
	}
}
