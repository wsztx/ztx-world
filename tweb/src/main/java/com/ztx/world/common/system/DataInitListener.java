package com.ztx.world.common.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.ztx.world.base.service.DataInitService;
import com.ztx.world.common.utils.PropertiesUtil;

/**
 * 数据初始化
 * @author ztx
 *
 */
@Component
public class DataInitListener implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
	private DataInitService dataInitService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
        // 防止重复执行。
        if(event.getApplicationContext().getParent() == null){
        	// 是否初始化缓存
        	String cacheInit = PropertiesUtil.get("init.cache");
        	if(cacheInit.equals("1")){
        		dataInitService.cacheInit();
        	}
        	
        	// 是否初始化数据库
        	String dbInit = PropertiesUtil.get("init.db");
        	if(dbInit.equals("1")){
        		dataInitService.dbInit();
        	}
        }
	}


}
