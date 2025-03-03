package com.swang;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class BasicRxJavaTest {

    @Test
    public void testObservable() {
        final String[] result = new String[1];
        Observable<String> observable = Observable.just("Hello", "World");
        observable.subscribe(s -> result[0] = s);

        Assert.assertEquals("World", result[0]);
    }

    @Test
    public void testOnXXX() {
        final String[] result = new String[]{""};
        Observable<String> observable = Observable.fromArray("a", "b", "c");
        observable.subscribe(
                s -> result[0] += s,
                Throwable::printStackTrace,
                () -> result[0] += "_completed"
        );

        Assert.assertEquals("abc_completed", result[0]);
    }


    @Test
    public void testMap() {
        final String[] result = new String[]{""};
        Observable<String> observable = Observable.fromArray("a", "b", "c");
        observable
                .map(String::toUpperCase)
                .subscribe(s -> result[0] += s);

        Assert.assertEquals("ABC", result[0]);
    }

    @Test
    public void testFlatMap() {
        final String[] result = new String[]{""};
        Observable<String> observable = Observable.fromArray("a", "b", "c");
        observable
                .flatMap(s -> Observable.fromArray(s.toUpperCase()))
                .subscribe(s -> result[0] += s);

        Assert.assertEquals("ABC", result[0]);
    }

    @Test
    public void testScan() {
        final String[] result = new String[]{""};
        Observable<String> observable = Observable.fromArray("a", "b", "c");
        observable
                .scan((x, y) -> x + y)
                .subscribe(s -> result[0] += s);

        Assert.assertEquals("aababc", result[0]);
    }

    @Test
    public void testGroupBy() {
        final String[] even = new String[]{""};
        final String[] odd = new String[]{""};
        Observable<Integer> observable = Observable.fromArray(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        observable
                .groupBy(n -> 0 == (n % 2) ? "EVEN" : "ODD")
                .subscribe(group ->
                        group.subscribe(n -> {
                                    if (group.getKey().equals("EVEN")) {
                                        even[0] += n;
                                    } else {
                                        odd[0] += n;
                                    }
                                }
                        )
                );
        Assert.assertEquals("246810", even[0]);
    }

    @Test
    public void testFilter() {
        final String[] result = new String[]{""};
        Observable<Integer> observable = Observable.fromArray(1, 2, 3, 4, 5, 6, 7, 8, 9);
        observable
                .filter(n -> 0 == (n % 2))
                .subscribe(s -> result[0] += s);

        Assert.assertEquals("2468", result[0]);
    }

    @Test
    public void testCondition() {
        final String[] result = new String[]{""};
        Observable<Integer> observable = Observable.fromArray(1, 2, 3, 4, 5, 6, 7, 8, 9);
        observable.takeWhile(n -> n < 5)
                .subscribe(s -> result[0] += s);

        Assert.assertEquals("1234", result[0]);
    }


    @Test
    public void testCollectableObservable() throws InterruptedException {
        final String[] result = new String[]{""};
        ConnectableObservable<Long> connectableObservable = Observable.interval(200, TimeUnit.MILLISECONDS).publish();
        connectableObservable.subscribe(n -> result[0] += n);
        Assert.assertNotEquals("01", result[0]);

        connectableObservable.connect();
        Thread.sleep(500);
        Assert.assertEquals("01", result[0]);

    }

    @Test
    public void testSingle() {
        final String[] result = new String[]{""};
        Observable<String> observable = Observable.just("a");
        observable
                .single("z")
                .doOnSuccess(n -> result[0] += n)
                .doOnError(err -> err.printStackTrace())
                .subscribe();


        Assert.assertEquals("a", result[0]);
    }


    @Test
    public void testSubject() {
        final String[] result1 = new String[]{""};
        final String[] result2 = new String[]{""};
        PublishSubject<String> subject = PublishSubject.create();
        subject.subscribe(s -> result1[0] += s);
        subject.onNext("a");
        subject.onNext("b");
        subject.onNext("c");
        subject.subscribe(s -> result2[0] += s);
        subject.onNext("d");

        Assert.assertEquals("abcd", result1[0]);
        Assert.assertEquals("d", result2[0]);
    }
}
