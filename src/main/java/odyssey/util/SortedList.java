package odyssey.util;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class SortedList<T extends Comparable<T>> implements Iterable<T> {

    private static final int INIT_SIZE = 16;

    // TODO: switch to custom array impl ??
    private ArrayList<T> backingList;

    public SortedList() {
        this(INIT_SIZE);
    }

    public SortedList(int initSize) {
        this.backingList = new ArrayList<>(initSize);
    }

    public void add(T element) {
        int i = Collections.binarySearch(backingList, element);
        if(i < 0) {
            i = ~i;
        }
        backingList.add(i, element);
    }

    public T get(int i) {
        return backingList.get(i);
    }

    // Returns true if element was removed, or false if element didn't exist in list
    public boolean remove(T element) {
        int i = Collections.binarySearch(backingList, element);
        if(i < 0) {
            return false;
        }
        backingList.remove(i);
        return true;
    }

    public int size() {
        return backingList.size();
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return backingList.iterator();
    }
}
