package com.daemongear.trackit.api.commons.mapper;

import com.daemongear.trackit.api.data.entity.User;
import com.daemongear.trackit.api.domain.model.UserModel;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by robertoosorio on 15-08-16.
 */
public class UserModelToUserMapper implements Mapper<UserModel, User> {

    @Override
    public User map(UserModel input) {
        User user = new User();
        user.setId(input.getId());
        user.setUsername(input.getUsername());
        user.setPassword(input.getPassword());
        return user;
    }

    @Override
    public List<User> map(List<UserModel> inputs) {
        List<User> users = new ArrayList<>();
        for (UserModel input :
                inputs) {
            users.add(map(input));
        }
        return users;
    }
}
