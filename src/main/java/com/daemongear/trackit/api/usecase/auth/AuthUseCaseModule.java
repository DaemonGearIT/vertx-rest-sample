package com.daemongear.trackit.api.usecase.auth;

import com.daemongear.trackit.api.commons.mapper.Mapper;
import com.daemongear.trackit.api.data.entity.User;
import com.daemongear.trackit.api.data.repository.UserRepository;
import com.daemongear.trackit.api.domain.model.UserModel;
import dagger.Module;
import dagger.Provides;

/**
 * Created by robertoosorio on 15-08-16.
 */
@Module(
        complete = false,
        library = true
)
public class AuthUseCaseModule {

    @Provides
    public RegisterUser provideRegisterUserUseCase(UserRepository userRepository, Mapper<UserModel, User> userMapper) {
        return new RegisterUserImpl(userRepository, userMapper);
    }
}
