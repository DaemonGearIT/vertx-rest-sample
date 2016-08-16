package com.daemongear.trackit.api.commons.inject;

import dagger.ObjectGraph;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Vertx;

import java.util.List;

/**
 * Created by robertoosorio on 15-08-16.
 */
public abstract class InjectableAbstractVerticle extends AbstractVerticle implements Injectable {

    private ObjectGraph mObjectGraph;

    @Override
    public void init(Vertx vertx, Context context) {
        super.init(vertx, context);
        mObjectGraph = ObjectGraph.create(getModules().toArray());
        inject(this);
    }

    @Override
    public void inject(Object object) {
        mObjectGraph.inject(object);
    }

    @Override
    public ObjectGraph getObjectGraph() {
        return mObjectGraph;
    }
}
