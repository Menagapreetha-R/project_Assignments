package com.hcl.traings.UserResource;

import java.util.ArrayList;
import java.util.List;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;


public class UserResource {
	@SuppressWarnings("unused")
	public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        List<User> users = new ArrayList<User>();

		Vertx vertx = Vertx.vertx();

		HttpServer server = vertx.createHttpServer();

		Router router = Router.router(vertx);
		Route postHandler = router.post("/addUser").handler(BodyHandler.create()).handler(routingContext -> {
			final User user = Json.decodeValue(routingContext.getBody(), User.class);
			HttpServerResponse serverResponse = routingContext.response();
			serverResponse.setChunked(true);
			users.add(user);
			serverResponse.end(users.size() + " user added successfully...");
		});
		Route getHandler = router.get("/getUsers").produces("*/json").handler(routingContext -> {
			routingContext.response().setChunked(true).end(Json.encodePrettily(users));
		});
		Route getFilterHandler = router.get("/getUser/:name").produces("*/json").handler(routingContext -> {
			String name = routingContext.request().getParam("name");
			routingContext.response().setChunked(true).end(Json
					.encodePrettily(users.stream().filter(use -> use.getName().equals(name)).findFirst().get()));
		});

		server.requestHandler(router::accept).listen(9091);


    }

}
