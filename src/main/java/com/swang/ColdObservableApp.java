package com.swang;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.io.IOException;

public class ColdObservableApp {
    public static void main(String[] args) throws IOException {
        Observable.range(1, 1_000_000)
                .observeOn(Schedulers.computation())
                .subscribe(ComputeFunction::compute);

        System.in.read();
    }
}
