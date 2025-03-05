package com.swang.introrxjava;

import io.reactivex.rxjava3.core.Observable;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class LeavingTest {

    @Test
    public void testIterable() {
        Observable<Long> values = Observable.interval(500, TimeUnit.MILLISECONDS);
        Iterable<Long> longs = values.take(5).blockingIterable();
        for(long l: longs){
            System.out.println(l);
        }
    }

    @Test
    public void testFuture() throws InterruptedException, ExecutionException {
        Observable<Long> values = Observable.interval(500, TimeUnit.MILLISECONDS);
        Future<Long> future = values.toFuture();
        Thread.sleep(500);
        System.out.println(future.get());
    }
}
