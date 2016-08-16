package com.daemongear.trackit.api.commons.mapper;

import com.daemongear.trackit.api.data.entity.User;
import com.daemongear.trackit.api.domain.model.UserModel;
import dagger.Module;
import dagger.Provides;

/**
 * Created by robertoosorio on 15-08-16.
 */
@Module(
        library = true
)
public class MapperModule {
    @Provides
    public Mapper<UserModel, User> providesUserModelToUserMapper() {
        return new UserModelToUserMapper();
    }
}
