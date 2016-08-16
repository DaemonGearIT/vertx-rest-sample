package com.daemongear.trackit.api.data.repository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by robertoosorio on 15-08-16.
 */
@Module(
        library = true
)
public class RepositoryModule {
    @Provides
    public UserRepository provideUserRepository() {
        return new MapUserRepository();
    }
}
