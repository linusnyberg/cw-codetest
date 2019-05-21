# farmerswife code test for Java developers

### Approximate time of completion: 4-6 hours

---

## Introduction

The goal for this code test is to develop a simple yet complete backend application for a Blog in Java, to be run as a web service application. Data should be consumed through a RESTful API and stored in a MongoDB database. A MongoDB "mock framework" is pre-configured in the project so no separate MongoDB database installation is required.

Users can own multiple Blogs. Each Blog has a title and zero or more Blog Entries. A Blog Entry has a title, content and a date of creation. Everyone can view all Blogs but only the User owning the Blog can modify it.

### Example of a Blog

Blog: Jonas development Blog<br/>
Blog owner: Jonas Nyberg

Entries:

2015-12-11 - "A weird bug found in the framework"<br/>
Some text here explaining the bug and how to work around it.

2015-12-08 - "New release to production server"<br/>
Some text here about the release process or something.

## Prerequisite

### The project comes with out of the box support for following technologies:

<b>Eclipse IDE</b> (contains .project and .classpath)

> In the root folder, you have a file called <i>server_RunJettyRun.launch</i>. This is a config file that can be used using an Eclipse plugin called <i>RunJettyRun</i>. Installing this plugin will enable you to start an embedded Jetty server from within Eclipse in one click.
See: https://marketplace.eclipse.org/content/run-jetty-run

<b>Apache Maven</b> - Build automation tool and dependency manager<br/>
[https://maven.apache.org/](https://maven.apache.org/)

<b>Restlet Framework</b> - RESTful web API framework<br/>
[http://restlet.com/](http://restlet.com/)

<b>Google Guice 3.0</b> - Depencency Injection framework<br/>
[https://github.com/google/guice](https://github.com/google/guice)

<b>Morphia</b> - MongoDB ORM framework<br/>
[https://github.com/mongodb/morphia](https://github.com/mongodb/morphia)

<b>Fongo</b> - MongoDB mock framework<br/>
[https://github.com/fakemongo/fongo](https://github.com/fakemongo/fongo)

> Since the project comes configured with a MongoDB mock framework (Fongo), no actual MongoDB database server is required to be setup on the development computer. All data created will by default be saved in memory and deleted when the server stops.

### Pre-configured users:

When the server starts, MongoDB will be populated with three users in the default Users collection:

#### (Username / Password)

* john / 1111
* jane / 2222
* frank / 3333

## Implementation Details

Each of the features described below should be implemented all the way from setting up appropriate URL route (using best practices for a well-defined REST based web service) to proper server side authentication and data fetching/manipulation in the database.

### Required API features to be implemented:

#### Available to anyone (does not require being logged in):

* Be able to log in using any of the pre-defined users

* Be able to list all available Blogs

#### Available to logged in users (requires being logged in):

* Be able to create a new Blog

* Be able to update the name of a Blog owned by this user

* Get current logged in user

* Logout

### Optional API features to be implemented:

#### Available to anyone (does not require being logged in):

* Be able to list all Blog **Entries** for a Blog

* Be able to view a specific Blog **Entry**

#### Available to logged in users (requires being logged in):

* Be able to post new Blog **Entries** to a Blog owned by this user

* Be able to update the content of a Blog **Entry** for a Blog owned by this user

* Be able to delete a Blog **Entry** for a Blog owned by this user

## Some basics regarding Restlet, Guice and MongoDB:

### Restlet > URL routes

Every unique URL, e.g.

* api/v1/some/path
* api/v1/books/{bookId}
* api/v1/books/{bookId}/chapter/{chapterId}

is mapped to it's own Restlet resource that extends the ServerResource class.
See <b>com.farmerswife.cirkus.codetest.server.restlet.resources.MyExampleResource</b> for an example of a Restlet resource.

> In the class com.farmerswife.cirkus.codetest.server.restlet.RestletApplication in the code base you set up your routes.

### Restlet > Method signatures

All GET, POST, PUT and DELETE methods in a ServerResource are formed in exactly the same way:

#### GET methods
```
	@Get
	public Representation myGetMethod() {
		/* No method argument because GET methods are not allowed to have a request body, according to the HTTP specification. */
	}
	
```

#### POST methods
```
	@Post("json")
	public Representation myPostMethod(Representation entity) {
		/* The method argument hold the request body */
	}
```

#### PUT methods
```
	@Put("json")
	public Representation myPutMethod(Representation entity) {
		/* The method argument hold the request body */
	} 
```

#### DELETE methods
```
	@Delete
	public Representation myDeleteMethod() {
		/* No method argument because DELETE methods are not allowed to have a request body, according to the HTTP specification. */
	}
```

### Restlet > Parsing request body and send response

To parse out a request body from the "Representation" object in the method signature and convert it to a java.util.Map, you can call this method:

```
	Map<String, Object> requestBodyAsMap = JsonParser.jsonToMap(representationObject.getText());
```

To send a response back to the client, you can use the <b>JsonRepresentation</b> class, or use <b>EmptyRepresentation</b> if you want to return an empty response.

```
	Map<String, Object> responseAsMap = new HashMap<String, Object>();
	// ...
	return new JsonRepresentation(responseAsMap);
```

### Restlet > Handling request attributes, query values, headers and cookies

#### For parsing a request attribute value from the URL, eg. <b>/api/v1/books/{bookId}/chapters/{chapterId}</b>

```
	Object bookId = getRequestAttributes().get("bookId");
	Object chapterId = getRequestAttributes().get("chapterId");
```

So calling the URL /api/v1/books/123/chapters/456 would result in ->
bookId = "123", chapterId = "456"

#### For parsing a query value from the URL, eg. <b>/api/v1/search?searchQuery=123</b>

```
	String searchQueryValue = getQueryValue("searchQuery"); // Will get the value "123"
```

#### Request headers

Get request headers:

```
	Series<Parameter> requestHeaders = (Series<Parameter>) getRequest().getAttributes().get("org.restlet.http.headers");
```

Set response header:

```
	Series<Header> responseHeaders = (Series<Header>) getResponse().getAttributes().get("org.restlet.http.headers");	
	responseHeaders.add(new Header(headerName, headerValue));		
```

#### Cookies

Getting a cookie value:

```
	String cookieValue = (String) getRequest().getCookies().getValues(cookieName);
```

Creating a basic response cookie:

```
	CookieSetting cookieSetting = new CookieSetting(cookieName, cookieValue);
	cookieSetting.setPath("/");
	getResponse().getCookieSettings().add(cookieSetting);
```

### Guice > Injections

To get access to the DAO-layer from another class, you can just do this:
```
	@Inject MyDAO myDAO;
```
And that's it, you now have access to that DAO from within that class.

Note however, that the class injecting the DAO must itself be injected from the Restlet Resource to be part of the "Guice context".

It's easiest to see it as an injection chain of classes.

### MongoDB/Morphia > Datatstore reference

All Guice configurations are already done, so each DAO class can just use:
```
	@Inject Datastore datastore;
```
to get access to the MongoDB datastore reference (Morphia ORM interface).

### MongoDB > Using pure MongoDB java driver

If you want to use pure MongoDB with Java, without Morphia ORM, you can call:
```
	DB db = datastore.getDB();
```
which will give you a reference to a com.mongodb.DB object.


## Beyond having just a working implementation, your code will be assessed in the following areas:

* Being able to separate concerns well

* Writing extendable, readable and robust code

* Good understanding of appropriate design patterns and best practices 

* Good naming of classes/methods/variables etc. 
