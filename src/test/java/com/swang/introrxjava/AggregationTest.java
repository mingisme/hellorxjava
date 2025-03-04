package com.swang.introrxjava;

import io.reactivex.rxjava3.core.Observable;
import org.junit.Test;

import javax.crypto.spec.PSource;
import java.util.ArrayList;
import java.util.HashMap;

public class AggregationTest {
    @Test
    public void testCount() {
        Observable<Integer> values = Observable.range(0, 3);
        values.subscribe(new PrintObserver("Values"));
        values.count().subscribe(new PrintSingleObserver("Count"));
    }

    @Test
    public void testFirst() {
        Observable<Integer> values = Observable.range(0, 5);
        values.first(2)
                .subscribe(new PrintSingleObserver("First"));
    }

    @Test
    public void testSingle() {
        Observable<Integer> values = Observable.range(0, 5);
        values.single(1).subscribe(new PrintSingleObserver("Single2"));
    }

    @Test
    public void testReduce() {
        Observable<Integer> values = Observable.range(0, 5);

        values.reduce((x, y) -> x + y)
                .subscribe(new PrintMaybeObserver("Sum"));

        values.reduce((x, y) -> (x > y) ? y : x)
                .subscribe(new PrintMaybeObserver("Min"));
    }

    @Test
    public void testToList() {
        Observable<Integer> values = Observable.range(0, 5);

        values.toList().subscribe(v-> System.out.println(v));
    }

    @Test
    public void testToSortedList() {
        Observable<Integer> values = Observable.range(0, 5);

        values.toSortedList((x,y)->y-x).subscribe(v-> System.out.println(v));
    }

    @Test
    public void testToMap() {
        Observable<Person> values = Observable.just(new Person("Will", 35),
                new Person("Nick", 40),
                new Person("Saul", 35));

        values.toMap(p->p.name)
                .subscribe(v-> System.out.println(v));
    }

    @Test
    public void testToMap2() {
        Observable<Person> values = Observable.just(new Person("Will", 35),
                new Person("Nick", 40),
                new Person("Saul", 35));

        values.toMap(p->p.name, p->p.age)
                .subscribe(v-> System.out.println(v));
    }

    @Test
    public void testToMultiMap() {
        Observable<Person> values = Observable.just(new Person("Will", 35),
                new Person("Nick", 40),
                new Person("Saul", 35));

        values.toMultimap(p->p.age, p->p.name, ()->new HashMap<>(), key-> new ArrayList<>())
                .subscribe(v-> System.out.println(v));
    }

    @Test
    public void testGroupBy() {
        Observable<String> values = Observable.just("first", "second", "third", "forth", "fifth", "sixth");
        values.groupBy(s->s.charAt(0))
                .subscribe(v-> v.last("").subscribe(o-> System.out.println(v.getKey() + ":" + o)));
    }

    @Test
    public void testGroupBy2() {
        Observable<String> values = Observable.just("first", "second", "third", "forth", "fifth", "sixth");
        values.groupBy(s->s.charAt(0))
                .flatMap(g->g.lastElement()
                        .toObservable()
                        .map(v->g.getKey() +":" +v))
                .subscribe(v-> System.out.println(v));
    }


    @Test
    public void testNest() {
        Observable.just(Observable.range(0,3))
                .subscribe(v->v.subscribe(o-> System.out.println(o)));

    }

}
