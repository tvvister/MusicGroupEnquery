package com.maintwister.musicgroupfile.model;

/**
 * Created by Andrey on 04.04.2016.
 */
public interface ICallback<T> {
    void handle(T t);
}
