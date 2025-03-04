package com.swang.introrxjava;

import io.reactivex.rxjava3.core.Observable;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class InspectionTest {

    @Test
    public void testAll() {
        Observable<Integer> values = Observable.create(o -> {
            o.onNext(0);
            o.onNext(10);
            o.onComplete();
        });

        values.all(i -> i < 5)
                .subscribe(
                        v -> System.out.println("All are less than 5? " + v),
                        e -> System.out.println("Error: " + e)
                );
    }

    @Test
    public void testError() {
        Observable<Integer> values = Observable.create(o -> {
            o.onNext(0);
            o.onNext(1);
            o.onError(new Exception("unknown"));
        });

        values.all(i -> i < 5)
                .subscribe(
                        v -> System.out.println("Any is greater than 5? " + v),
                        e -> System.out.println("Error: " + e)
                );
    }

    @Test
    public void testFalseComplete() {
        Observable<Integer> values = Observable.create(o -> {
            o.onNext(0);
            o.onError(new Exception("unknown"));
        });

        values.all(i->i==1)
                .subscribe(
                        v -> System.out.println("Contains the value 10? " + v),
                        e -> System.out.println("Error: " + e)
                );
    }

    @Test
    public void testExists() {
        Observable<Integer> values = Observable.range(0,2);

        values.any(i->i>2)
                .subscribe(v-> System.out.println(v), e-> System.out.println("Error: " + e));
    }

    @Test
    public void testIsEmpty() {
        Observable<Integer> values = Observable.range(0, 2);
        values.isEmpty()
                .subscribe(v-> System.out.println(v), e-> System.out.println("Error: " + e));
    }

    @Test
    public void testContains() throws IOException, InterruptedException {
        Observable<Long> values = Observable.interval(100, TimeUnit.MILLISECONDS);
        values.contains(2L)
                .subscribe(v-> System.out.println(v), e-> System.out.println("Error: " + e));

        Thread.sleep(500);
    }

    @Test
    public void testElementAt() throws IOException, InterruptedException {
        Observable<Integer> values = Observable.range(0, 5);
        values.elementAt(2)
                .subscribe(v-> System.out.println(v), e-> System.out.println("Error: " + e));
    }

    @Test
    public void testSequenceEqual() throws IOException, InterruptedException {
        Observable<String> stringObservable = Observable.just("1", "2", "3");
        Observable<Integer> integerObservable = Observable.just(1, 2, 3);

        Observable.sequenceEqual(stringObservable, integerObservable,
                        (s, i) -> s.equals(i.toString()))
                .subscribe(v-> System.out.println(v), e-> System.out.println("Error: " + e));
    }





}
