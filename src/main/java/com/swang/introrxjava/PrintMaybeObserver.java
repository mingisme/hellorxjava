package com.swang.introrxjava;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;

public class PrintMaybeObserver implements MaybeObserver {

    private final String name;

    public PrintMaybeObserver(String name) {
        this.name = name;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onSuccess(Object o) {
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
