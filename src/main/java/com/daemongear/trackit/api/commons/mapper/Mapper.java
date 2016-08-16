package com.daemongear.trackit.api.commons.mapper;

import java.util.List;

/**
 * Created by robertoosorio on 15-08-16.
 */
public interface Mapper<I,O> {
    O map(I input);
    List<O> map(List<I> inputs);
}
