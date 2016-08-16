package com.daemongear.trackit.api.usecase.auth;

import com.daemongear.trackit.api.commons.mapper.Mapper;
import com.daemongear.trackit.api.data.entity.User;
import com.daemongear.trackit.api.data.repository.UserRepository;
import com.daemongear.trackit.api.domain.model.UserModel;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

/**
 * Created by robertoosorio on 15-08-16.
 */
public class RegisterUserImpl implements RegisterUser {

    UserRepository mUserRepository;
    Mapper<UserModel, User> mUserModelToUserMapper;

    public RegisterUserImpl(UserRepository mUserRepository, Mapper<UserModel, User> mUserModelToUserMapper) {
        this.mUserRepository = mUserRepository;
        this.mUserModelToUserMapper = mUserModelToUserMapper;
    }

    @Override
    public void registerUser(RoutingContext routingContext) {
        System.out.println("UseCase - RegisterUser");
        String body = routingContext.getBodyAsString();
        System.out.println(String.format("Decode value : %s", body));
        UserModel userModel = Json.decodeValue(body, UserModel.class);
        System.out.println("Mapping UserModel to User");
        User user = mUserModelToUserMapper.map(userModel);
        if (user.getId() == null) {
            Long id =  mUserRepository.save(user);
            if (id != null) {
                System.out.println("Create User successful");
                routingContext
                        .response()
                        .setStatusCode(HttpResponseStatus.CREATED.code())
                        .putHeader("content-type", "application/json")
                        .end(Json.encodePrettily(user));
            } else {
                System.out.println("Can't create user");
                routingContext.response().setStatusCode(HttpResponseStatus.INTERNAL_SERVER_ERROR.code()).end();
            }
        } else {
            System.out.println("Can't create user");
            routingContext.response().setStatusCode(HttpResponseStatus.INTERNAL_SERVER_ERROR.code()).end();
        }
    }
}
