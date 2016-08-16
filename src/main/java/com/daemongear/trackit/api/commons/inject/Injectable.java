package com.daemongear.trackit.api.commons.inject;

import dagger.ObjectGraph;

import java.util.List;

/**
 * Created by robertoosorio on 15-08-16.
 */
public interface Injectable {
    List<Object> getModules();
    void inject(Object object);
    ObjectGraph getObjectGraph();
}
