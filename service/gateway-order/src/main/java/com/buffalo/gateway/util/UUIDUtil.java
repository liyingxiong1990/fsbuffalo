package com.buffalo.gateway.util;

import java.util.UUID;

/**
 * UUID工具类
 */
public class UUIDUtil {

	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}


}
