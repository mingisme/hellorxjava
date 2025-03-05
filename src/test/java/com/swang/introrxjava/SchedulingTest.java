package com.swang.introrxjava;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.junit.Test;

public class SchedulingTest {
    @Test
    public void testScheduling() {
        System.out.println("Main thread: " + Thread.currentThread().getName());
        Observable.just("Hello world")
                .doOnNext(x -> System.out.println("Thread 1: " + Thread.currentThread().getName()))
                .observeOn(Schedulers.io())
                .doOnNext(x -> System.out.println("Thread 2: " + Thread.currentThread().getName()))
                .subscribe(x -> System.out.println("Thread 3: " + Thread.currentThread().getName()));
    }
}
