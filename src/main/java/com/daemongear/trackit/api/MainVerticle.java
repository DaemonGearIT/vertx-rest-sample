package com.daemongear.trackit.api;

import com.daemongear.trackit.api.commons.inject.InjectableAbstractVerticle;
import com.daemongear.trackit.api.commons.mapper.MapperModule;
import com.daemongear.trackit.api.data.repository.RepositoryModule;
import com.daemongear.trackit.api.usecase.auth.AuthUseCaseModule;
import com.daemongear.trackit.api.usecase.auth.RegisterUser;
import com.daemongear.trackit.api.usecase.auth.RegisterUserImpl;
import com.google.common.net.MediaType;
import dagger.ObjectGraph;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

/**
 * Created by robertoosorio on 15-08-16.
 */
public class MainVerticle extends InjectableAbstractVerticle {

    @Inject
    RegisterUser mRegisterUser;

    @Override
    public void start(Future<Void> startFuture) {
        Router router = Router.router(vertx);

        router.route().handler(BodyHandler.create());
        router.route("/").handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "text/html");
            response.end("<h1>Hello from TrackIt</h1></br><h5>powered by DaemonGear</h5>");
        });

        router.route(HttpMethod.POST, "/auth/register")
                .handler(mRegisterUser::registerUser);

        vertx
                .createHttpServer()
                .requestHandler(router::accept)
                .listen(
                        config().getInteger("http.port", 8080),
                        result -> {
                           if (result.succeeded()) {
                               startFuture.complete();
                           } else {
                               startFuture.fail(result.cause());
                           }
                        }
                );

    }

    @Override
    public List<Object> getModules() {
        return Arrays.asList(new MainModule(), new RepositoryModule(), new MapperModule(), new AuthUseCaseModule());
    }
}
