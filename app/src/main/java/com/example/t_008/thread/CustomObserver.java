package com.example.t_008.thread;

public interface CustomObserver<E extends Object> {

    void callOnSuccess(E e);

    E doOnThread();
}
