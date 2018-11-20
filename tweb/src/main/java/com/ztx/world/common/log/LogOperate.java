package com.ztx.world.common.log;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface LogOperate {
	
	/**
	 * @param 模块名字
	 */
	String model() default "";

	/**
	 * @param 操作类型
	 */
	String type() default "";
	
	/**
	 * @param 操作对象
	 */
	String object() default "";
	
	/**
	 * @param 描述
	 */
	String description() default "";
}
