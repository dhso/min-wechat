package com.minws.wechat.validator;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordKit {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String newPassword = new SimpleHash("md5", "user", ByteSource.Util.bytes("tps"), 2).toHex();
		ByteSource salt = ByteSource.Util.bytes("tps");
		System.out.println(newPassword);
		System.out.println(salt);
	}

}
