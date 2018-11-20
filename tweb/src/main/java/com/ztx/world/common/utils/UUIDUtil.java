package com.ztx.world.common.utils;

import java.util.UUID;

public class UUIDUtil {

	/**
	 * 去掉UUID中的"-"
	 * @param uuid
	 * @return
	 */
	public static String replaceUUID(String uuid) {
		uuid = uuid.replaceAll("\\-", "");
		return uuid;
	}
	
	/**
	 * 获取UUID,无"-"
	 * @return
	 */
	public static String getUUIDA() {
		String uuid = UUID.randomUUID().toString();
		return uuid.replaceAll("\\-", "");
	}
	
	/**
	 * 获取UUID
	 * @return
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}
}
