package com.example.t_008.thread;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public class CustomThread<T extends CustomObserver> {

    private Handler handler;
    private Object o;
    private T observable;

    public void subscribe(T observableT) {
        this.observable = observableT;

        handler = new Handler(Looper.getMainLooper());

        Thread thread = new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                o = observable.doOnThread();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (observable != null) {
                            observable.callOnSuccess(o);
                        }
                    }
                });
                Looper.loop();
            }
        };

        thread.start();
    }

    public void unsubscribe() {
            observable = null;
    }
}
