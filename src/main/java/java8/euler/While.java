package java8.euler;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by esLion on 3/23/14.
 */
public class While<E> implements Iterator<E> {
    private final Iterator<E> delegate;
    private final Predicate<E> predicate;

    private volatile boolean hasNext = true;
    private volatile E next;

    public While(Stream<E> delegate, Predicate<E> predicate) {
        this.delegate = delegate.iterator();
        this.predicate = predicate;
    }

    @Override
    public boolean hasNext() {
        boolean hn = hasNext;
        if (!hn) {
            return false;
        }
        if (next != null) {
            return true;
        }
        if (delegate.hasNext()) {
            next = delegate.next();
            hn = predicate.test(next);
            if (!hn){
                next = null;
            }
            hasNext = hn;
        }
        return next != null;
    }

    @Override
    public E next() {
        if (next == null && !hasNext()) {
            throw new NoSuchElementException();
        }
        E aux = next;
        next = null;
        return aux;
    }

    public static <X> Stream<X> stream(Stream<X> s, Predicate<X> p) {
        While<X> wh = new While<>(s, p);
        Spliterator it = Spliterators.spliterator(wh, Integer.MAX_VALUE, Spliterator.ORDERED);
        return StreamSupport.stream(it, false);
    }
}
