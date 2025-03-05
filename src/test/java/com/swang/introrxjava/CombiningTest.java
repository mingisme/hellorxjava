package com.swang.introrxjava;

import io.reactivex.rxjava3.core.Observable;
import org.junit.Test;

public class CombiningTest {

    @Test
    public void testConcat() {
        Observable.concat(
                Observable.just("A", "B", "C"),
                Observable.just(1, 2, 3))
                .subscribe(System.out::println);
    }

}
