package custom;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class CustomArrayList<T> implements Collection<T> {

    Object[] array;
    int size;

    private static final int DEFAULT_CAPACITY = 10;

    private int index;

    public CustomArrayList() {
        array = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        index = 0;
        for (var i = 0; i < size; i++) {
            if (o.equals(array[i])) {
                index = i;
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public <T1> T1[] toArray(IntFunction<T1[]> generator) {
        return Collection.super.toArray(generator);
    }

    public boolean add(T element) {
        if (size == array.length) {
            increaseCapacity();
        }
        array[size++] = element;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        rangeCheck(index);
        for (var i = index; i < size; i++) {
            array[i] = array[i + 1];
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Object [] arr = c.toArray();
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeIf(Predicate<? super T> filter) {
        return Collection.super.removeIf(filter);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        size = 0;
        array = new Object[10];
    }

    @Override
    public Spliterator<T> spliterator() {
        return Collection.super.spliterator();
    }

    @Override
    public Stream<T> stream() {
        return Collection.super.stream();
    }

    @Override
    public Stream<T> parallelStream() {
        return Collection.super.parallelStream();
    }

    private void increaseCapacity() {
        var newCapacity = 2 * array.length;
        array = Arrays.copyOf(array, newCapacity);
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        rangeCheck(index);
        return (T) array[index];
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("invalid index or index is out of range"+ index);
        }
    }

    @SuppressWarnings("unchecked")
    public T remove(int index) {
        rangeCheck(index);
        T returnVal = (T) array[index];
        for (var i = index; i < size; i++) {
            array[i] = array[i + 1];
        }
        return returnVal;
    }

    public int indexOf(Object o) {
        var idx = -1;
        if (contains(o)) {
            idx = index;

        }
        index = 0;
        return idx;
    }

    public int lastIndexOf(Object o) {
        var idx = -1;
        if (contains(o)) {
            var count = Arrays.stream(array).filter(var -> var == o).count();
            if (count == 1) {
                idx = index;
            } else {
                for (var i = size - 1; i > index; i--) {
                    if (array[i].equals(o)) {
                        idx = i;
                        break;
                    }
                }

            }
        }
        return idx;
    }


}
