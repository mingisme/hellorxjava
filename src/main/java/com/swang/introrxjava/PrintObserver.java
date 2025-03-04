package com.swang.introrxjava;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DefaultObserver;


public class PrintObserver extends DefaultObserver {

    private final String name;

    public PrintObserver(String name) {
        this.name = name;
    }

    @Override
    public void onNext(Object o) {
        System.out.println(name + ":" + o);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        System.out.println(name + ": Error: " + e);
    }

    @Override
    public void onComplete() {
        System.out.println(name + ": Complete");
    }
}
