/*
 *
 * Copyright Farmers WIFE S.L. All Rights Reserved.
 * 
 */

package com.farmerswife.cirkus.codetest.server.restlet;

import org.restlet.Request;

public class RestletHelper {
	public static String getCookieValue(Request req, String cookieName) {
		String token = (String) req.getCookies().getValues(cookieName);
		return token;
	}
}
