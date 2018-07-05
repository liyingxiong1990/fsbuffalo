package com.buffalo.gateway.authentication.util;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {

	/**
	 * HTTP Response Info
	 * @param httpStatus
	 * @param statusText
	 * @param data
	 * @param <T>
	 * @return
	 */
	public static <T> Map<String, T> result(HttpStatus httpStatus, String statusText, T data) {
		Map<String, T> map = new HashMap<String, T>();
		map.put("data", data);
		map.put("statusText", (T) statusText);
		map.put("status", (T) httpStatus.toString());
		return map;
	}

	/**
	 * HTTP Response Info
	 * @param httpStatus
	 * @param statusText
	 * @param <T>
	 * @return
	 */
	public static <T> Map<String, T> result(HttpStatus httpStatus, String statusText) {
		Map<String, T> map = new HashMap<String, T>();
		map.put("statusText", (T) statusText);
		map.put("status", (T)httpStatus.toString());
		return map;
	}
	
}
