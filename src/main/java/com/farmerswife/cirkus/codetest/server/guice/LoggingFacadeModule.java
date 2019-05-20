/*
 *
 * Copyright Farmers WIFE S.L. All Rights Reserved.
 * 
 */

package com.farmerswife.cirkus.codetest.server.guice;

import org.restlet.engine.Engine;
import org.restlet.ext.slf4j.Slf4jLoggerFacade;
import org.slf4j.bridge.SLF4JBridgeHandler;

import com.google.inject.AbstractModule;

public class LoggingFacadeModule extends AbstractModule {

	@Override
	protected void configure() {
		
		// Setup logger facade for Restlet services
		System.setProperty("org.restlet.engine.loggerFacadeClass", "org.restlet.ext.slf4j.Slf4jLoggerFacade");
		SLF4JBridgeHandler.install();
		Slf4jLoggerFacade loggerFacade = new Slf4jLoggerFacade();
		Engine.getInstance().setLoggerFacade(loggerFacade);
	}
}
