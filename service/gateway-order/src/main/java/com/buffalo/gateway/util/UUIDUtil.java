package com.buffalo.gateway.util;

import java.util.Random;
import java.util.UUID;

/**
 * UUID工具类
 */
public class UUIDUtil {

	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}

	public static String getRandomNum(int digit) {
		StringBuilder str=new StringBuilder();//定义变长字符串
		Random random=new Random();
		for(int i=0;i<digit;i++){
			str.append(random.nextInt(10));
		}
		return str.toString();
	}

}
