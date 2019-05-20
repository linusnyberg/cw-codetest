/*
 *
 * Copyright Farmers WIFE S.L. All Rights Reserved.
 * 
 */

package com.farmerswife.cirkus.codetest.server.guice;

import com.farmerswife.cirkus.codetest.server.restlet.RestletServlet;
import com.google.inject.servlet.ServletModule;

public class AppServletModule extends ServletModule {
	
	@Override
	@SuppressWarnings("all")
	protected void configureServlets() {

		// REST API setup
		serve("/api/v1/*").with(RestletServlet.class);
	}
}
