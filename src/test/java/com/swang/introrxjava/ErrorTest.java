package com.swang.introrxjava;

import io.reactivex.rxjava3.core.Observable;
import org.junit.Test;

import java.util.Random;

public class ErrorTest {

    @Test
    public void testRetry() {
        Random random = new Random();
        Observable.create(emitter -> {
            emitter.onNext(random.nextInt()%20);
            emitter.onNext(random.nextInt()%20);
            emitter.onError(new Exception());
        }).retry(1).subscribe(System.out::println, System.out::println);
    }

    @Test
    public void testRetryWhen() {
        Random random = new Random();
        Observable.create(emitter -> {
            emitter.onNext(random.nextInt()%20);
            emitter.onNext(random.nextInt()%20);
            emitter.onError(new Exception());
        }).retryWhen(throwableObservable -> throwableObservable.take(2)).subscribe(System.out::println, System.out::println);
    }

    @Test
    public void testUsing(){
        Observable<Object> values = Observable.using(
                () -> "Connection",
                connection -> Observable.create(emitter -> {
                    for(Character c : connection.toCharArray()){
                        emitter.onNext(c);
                    }
                    emitter.onComplete();
                }),
                connection -> System.out.println("Closing " + connection)
        );

        values.subscribe(System.out::println, System.out::println);


    }
}
