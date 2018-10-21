package com.ztx.world.common.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

public class SpringApplicationContextUtil {
    private static final Logger log = LoggerFactory.getLogger(SpringApplicationContextUtil.class);

    private static ApplicationContext applicationContext;

    public static synchronized void setApplicationContext(ApplicationContext applicationContext) {
        SpringApplicationContextUtil.applicationContext = applicationContext;
        log.info("ApplicationContext Registered.");
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

    public static <T> T getBean(String name, Class<T> requiredType) {
        return applicationContext.getBean(name, requiredType);
    }
}
