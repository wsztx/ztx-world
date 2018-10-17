package com.ztx.world.common.utils;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

/**
 * 拷贝实体类工具类
 * @author zhoutianxiang
 *
 */
public class BeanUtil extends BeanUtils {

	private static Logger log = LoggerFactory.getLogger(BeanUtil.class);

	/**
	 * 拷贝实体集合
	 * @param sourceList
	 * @param targetList
	 * @param clazz
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void copyPropertiesList(List sourceList, List targetList, Class clazz) {
		for (Object items : sourceList) {
			Object target = null;
			try {
				target = clazz.newInstance();
			} catch (InstantiationException e) {
				log.error(e.getMessage(), e);
			} catch (IllegalAccessException e) {
				log.error(e.getMessage(), e);
			}
			BeanUtils.copyProperties(items, target);
			targetList.add(target);
		}
	}
}
