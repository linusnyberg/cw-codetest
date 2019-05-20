/*
 *
 * Copyright Farmers WIFE S.L. All Rights Reserved.
 * 
 */

package com.farmerswife.cirkus.codetest.server.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import com.farmerswife.cirkus.codetest.entities.User;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * This class handles all calls to the MongoDB database related to a user collection.
 */
@Singleton
public class UserDAO {
	@Inject Datastore datastore;

	public User getUserForId(ObjectId id) {
		if (id == null) return null;
		User result = datastore.get(User.class, id);
		return result;
	}
	
	public User getUserForCredentials(String username, String passwordAsPlainText) {
		Query<User> query = datastore.createQuery(User.class);
		query.field("username").equal(username);
		query.field("password").equal(passwordAsPlainText);
		return query.get();
	}
}
