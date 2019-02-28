package com.example.t_008.feature_thread;

public interface CustomObserver<E extends Object> {

    void callOnSuccess(E e);

    E doOnThread();
}
