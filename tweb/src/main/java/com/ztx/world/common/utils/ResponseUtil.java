package com.ztx.world.common.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class ResponseUtil {
	
	private static Logger log = LoggerFactory.getLogger(ResponseUtil.class);

	public static void writeJson(HttpServletResponse response, Object object){
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragrma", "no-cache");
        response.setDateHeader("Expires", 0);
        try {
			response.getWriter().write(JSONObject.toJSONString(object));
		} catch (IOException e) {
			log.error("Return data error.");
		}
	}
}
