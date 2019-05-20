/*
 *
 * Copyright Farmers WIFE S.L. All Rights Reserved.
 * 
 */

package com.farmerswife.cirkus.codetest.server;

import com.farmerswife.cirkus.codetest.server.guice.AppServletModule;
import com.farmerswife.cirkus.codetest.server.guice.DatabaseSetupModule;
import com.farmerswife.cirkus.codetest.server.guice.LoggingFacadeModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 * Configuring Guice modules. 
 */
public class AppServletContextListener extends GuiceServletContextListener {
	
	@Override
	protected Injector getInjector() {
		return Guice.createInjector(
			new LoggingFacadeModule(),
			new DatabaseSetupModule(),
			new AppServletModule());
	}
}
