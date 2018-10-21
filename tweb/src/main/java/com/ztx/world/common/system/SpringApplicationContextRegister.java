package com.ztx.world.common.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringApplicationContextRegister implements ApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(SpringApplicationContextRegister.class);

    public SpringApplicationContextRegister() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringApplicationContextUtil.setApplicationContext(applicationContext);
        log.info("Register ApplicationContext.");
    }
}
