package com.example.t_008.thread;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class CustomThread<T extends CustomObserver> {

    private Handler handler;
    private Object o;
    private volatile List<T> observableList = new ArrayList<>();

    public void subscribe(T observableT) {
        this.observableList.add(observableT);
        Thread thread = new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                if (observableList != null) {
                    for (final T observable : observableList) {
                        o = observable.doOnThread();
                        handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                observable.callOnSuccess(o);
                            }
                        });
                    }
                }
                Looper.loop();
            }
        };
        thread.start();
    }

    public void unsubscribe() {
        observableList = null;
    }
}
