package com.swang.introrxjava;

import io.reactivex.rxjava3.core.Observable;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class TransformTest {

    @Test
    public void testOfType() {
        Observable.just(1, 2, "3")
                .ofType(Integer.class)
                .subscribe(System.out::println);
    }

    @Test
    public void testCast() {
        Observable.just(1L, 2L, 3L)
                .cast(Long.class)
                .subscribe(System.out::println,t-> System.out.println(t));
    }

    @Test
    public void testTimestamp() {
        Observable.interval(100, TimeUnit.MILLISECONDS)
                .timestamp()
                .subscribe(System.out::println);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTimeInterval() {
        Observable.interval(100, TimeUnit.MILLISECONDS)
                .timeInterval()
                .subscribe(System.out::println);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMaterialize() {
        Observable<Long> values = Observable.interval(100, TimeUnit.MILLISECONDS);
        values.take(3)
                .materialize()
                .subscribe(System.out::println);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testFlatMap() {
        Observable.just(1, 2, 3)
                .flatMap(i -> Observable.range(0, i))
                .subscribe(System.out::println);
    }

    @Test
    public void testFlatMapIterable() {
        Observable.range(0,1)
                .flatMapIterable(i -> Arrays.asList(1,2,3,4))
                .subscribe(System.out::println);
    }
}
