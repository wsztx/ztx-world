package com.ztx.world.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesUtil {

	private static final String DEFAULT_ERRORCODE_FILE = "config.properties";
	private static Properties errorCodes = new Properties();
	private static final Logger log = LoggerFactory.getLogger(PropertiesUtil.class);

	private static boolean isLoad;

	private static synchronized boolean init(){
		boolean ret = false;
		try(InputStream fis = ResultCodeUtil.class.getClassLoader()
				.getResourceAsStream("config/" + DEFAULT_ERRORCODE_FILE);){
			// 构造时获取到项目的物理根目录
			if (fis == null){
				log.error("PropertiesUtil error,文件未找到.");
				return ret;
			}

			errorCodes.load(fis);
			isLoad = true;
			ret = true;
			
		} catch (IOException e){
			log.error("PropertiesUtil error,文件初始化异常.", e);
		}
		return ret;
	}

	public static String get(Integer key){
		try{
			if(key == null){
				return "";
			}
			if (!isLoad){
				init();
			}
			String ret = errorCodes.getProperty(String.valueOf(key));

			if (ret == null){
				return "";
			}

			return new String(ret.getBytes("ISO-8859-1"), "utf-8");
		} catch (Exception e){
			log.error("PropertiesUtil error,字符转换异常.", e);
			return "";
		}
	}
	
}
