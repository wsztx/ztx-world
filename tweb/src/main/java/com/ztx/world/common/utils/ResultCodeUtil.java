package com.ztx.world.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResultCodeUtil {

	private static final String DEFAULT_ERRORCODE_FILE = "resultCode.properties";
	private static Properties errorCodes = new Properties();
	private static final Logger logger = LoggerFactory.getLogger(ResultCodeUtil.class);

	private static boolean isLoad;

	private static synchronized boolean init(){
		boolean ret = false;
		try(InputStream fis = ResultCodeUtil.class.getClassLoader()
				.getResourceAsStream("config/" + DEFAULT_ERRORCODE_FILE);){
			// 构造时获取到项目的物理根目录
			if (fis == null){
				logger.error("ResultCodeUtil-文件未找到。");
				return ret;
			}

			errorCodes.load(fis);
			isLoad = true;
			ret = true;
			
		} catch (IOException e){
			logger.error("ResultCodeUtil-初始化异常", e);
		}
		return ret;
	}

	public static String get(String key){
		try{
			if ("".equals(key)) {
				return "";
			}
			if (!isLoad){
				init();
			}
			String ret = errorCodes.getProperty(key);

			if (ret == null){
				return "";
			}

			return new String(ret.getBytes("ISO-8859-1"), "utf-8");
		} catch (Exception e){
			logger.error("ResultCodeUtil-字符转换异常", e);
			return "";
		}
	}
}