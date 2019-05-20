/*
 *
 * Copyright Farmers WIFE S.L. All Rights Reserved.
 * 
 */

package com.farmerswife.cirkus.codetest.server.utils;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonParser {
	public static Map<String, Object> jsonToMap(String json) {
		Gson gson = new Gson();
		return gson.fromJson(json, new TypeToken<Map<String, Object>>(){}.getType());
	}
}
