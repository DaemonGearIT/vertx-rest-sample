package com.daemongear.trackit.api.usecase.auth;

import com.daemongear.trackit.api.domain.model.UserModel;
import io.vertx.ext.web.RoutingContext;

/**
 * Created by robertoosorio on 15-08-16.
 */
public interface RegisterUser {
    void registerUser(RoutingContext routingContext);
}
