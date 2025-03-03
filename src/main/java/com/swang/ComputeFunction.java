package com.swang;

import io.reactivex.rxjava3.core.Observable;

import java.util.List;

public class ComputeFunction {
    public static void compute(Integer v) {
        try {
            System.out.println("compute integer v: " + v);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void compute2(List<Integer> v) {
        try {
            System.out.println("compute integer v: " + v);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void compute3(Observable<Integer> integerObservable) {
        integerObservable.subscribe(ComputeFunction::compute);
    }
}
