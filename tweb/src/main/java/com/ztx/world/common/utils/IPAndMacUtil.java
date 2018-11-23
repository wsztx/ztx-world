package com.ztx.world.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description 获取IP地址和mac地址工具类
 * @author zhoutianxiang 2018年11月12日 上午11:53:27
 * @since 2.1
 */
public class IPAndMacUtil {
	
	private static Logger log = LoggerFactory.getLogger(IPAndMacUtil.class);
	
	/**
	 * 根据请求获取IP地址
	 * @author zhoutianxiang 2018年11月12日 下午2:34:07
	 * @param request
	 * @return
	 * @throws Exception
	 * @since 2.1
	 */
	public static String getIP(HttpServletRequest request) throws Exception {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip != null) {
			if (!ip.isEmpty() && !"unKnown".equalsIgnoreCase(ip)) {
				int index = ip.indexOf(",");
				if (index != -1) {
					return ip.substring(0, index);
				} else {
					return ip;
				}
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (ip != null) {
			if (!ip.isEmpty() && !"unKnown".equalsIgnoreCase(ip)) {
				return ip;
			}
		}
		ip = request.getHeader("Proxy-Client-IP");
		if (ip != null) {
			if (!ip.isEmpty() && !"unKnown".equalsIgnoreCase(ip)) {
				return ip;
			}
		}
		ip = request.getHeader("WL-Proxy-Client-IP");
		if (ip != null) {
			if (!ip.isEmpty() && !"unKnown".equalsIgnoreCase(ip)) {
				return ip;
			}
		}
		ip = request.getRemoteAddr();
		return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}
	
	/**
	 * 获取IP对应的mac地址
	 * @author zhoutianxiang 2018年11月12日 下午2:35:43
	 * @return
	 * @since 2.1
	 */
	public static String getMACAddress(String ip) {
		String mac = "";
		String os = System.getProperty("os.name");
		if (os.startsWith("win") || os.startsWith("Win")) {
			mac = getMacWindows(ip);
		} else {
			mac = getMacLinux(ip);
		}
		return mac.toUpperCase();
	}
	
	/**
	 * 根据ip获取mac地址
	 * @author zhoutianxiang 2018年11月12日 下午2:33:12
	 * @param ip
	 * @return
	 * @since 2.1
	 */
	public static String getMacWindows(String ip) {
		String mac = "";
		BufferedReader bufferedReader = null;
		Process process = null;
		try {
			process = Runtime.getRuntime().exec("nbtstat -A " + ip);
			// windows系统都是GBK编码,不加GBK读出的中文是乱码
			bufferedReader = new BufferedReader(new InputStreamReader(
					process.getInputStream(), "GBK")); 
			String line = null;
			int index = -1;
			while ((line = bufferedReader.readLine()) != null) {
 				index = line.toLowerCase().indexOf("mac 地址 =");
				
				if (index != -1) {
					index = line.indexOf("=");
					if (index != -1) {
						mac = line.substring(index + 1).trim();
					}
					break;
				}
			}
		} catch (IOException e) {
			if (log.isErrorEnabled()) {
				log.error(e.getMessage(), e);
			}
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			bufferedReader = null;
			process = null;
		}
		return mac;
	}
	
	/**
	 * 根据ip获取mac地址
	 * @author zhoutianxiang 2018年11月12日 下午2:33:29
	 * @param ip
	 * @return
	 * @since 2.1
	 */
	public static String getMacLinux(String ip) {
		String mac = "";
		BufferedReader bufferedReader = null;
		Process process = null;
		try {
			process = Runtime.getRuntime().exec("ifconfig eth0");
			bufferedReader = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String line = null;
			int index = -1;
			while ((line = bufferedReader.readLine()) != null) {
				index = line.toLowerCase().indexOf("硬件地址");
				if (index != -1) {
					mac = line.substring(index + 4).trim();
					break;
				}
			}
		} catch (IOException e) {
			if (log.isErrorEnabled()) {
				log.error(e.getMessage(), e);
			}
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			bufferedReader = null;
			process = null;
		}

		return mac;
	}
	
	public static void main(String[] args) {
		System.out.println("ssssssssssssssssssss");
		System.out.println(IPAndMacUtil.getMACAddress("10.16.85.101"));
		System.out.println("eeeeeeeeeeeeeeeeeeee");
	}
}
