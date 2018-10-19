package com.ztx.world.common.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

import com.ztx.world.common.constants.ConfigConstants;

public class MD5Util {
	
	// 进行shiro加密，返回加密后的结果
	public static String md5(String pass) {
		String hashAlgorithmName = "MD5";
		Object salt = new Md5Hash(ConfigConstants.SALT_SOURCE);
		int hashIterations = 1024;
		Object result = new SimpleHash(hashAlgorithmName, pass, salt, hashIterations);
		String password = result.toString();
		return password;
	}
}
