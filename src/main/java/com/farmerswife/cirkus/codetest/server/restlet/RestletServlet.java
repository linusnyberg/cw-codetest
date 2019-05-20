/*
 *
 * Copyright Farmers WIFE S.L. All Rights Reserved.
 * 
 */

package com.farmerswife.cirkus.codetest.server.restlet;

import org.restlet.Application;
import org.restlet.Context;
import org.restlet.ext.servlet.ServerServlet;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

@Singleton
public class RestletServlet extends ServerServlet {
	private static final long serialVersionUID = -3245047404820790725L;

	@Inject Injector injector;
	
	@Override
	protected Application createApplication(Context parentContext) {
		return injector.getInstance(RestletApplication.class);
	}
}
