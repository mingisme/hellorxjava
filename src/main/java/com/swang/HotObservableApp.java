package com.swang;

import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;

import java.io.IOException;
import java.util.stream.IntStream;

public class HotObservableApp {
    public static void main(String[] args) throws IOException {
        PublishSubject<Integer> source = PublishSubject.<Integer>create();

        source.observeOn(Schedulers.computation())
                .subscribe(ComputeFunction::compute, Throwable::printStackTrace);

        IntStream.range(1, 1_000_000).forEach(source::onNext);

        System.in.read();
    }
}
