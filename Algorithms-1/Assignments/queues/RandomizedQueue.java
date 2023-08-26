import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private Item[] s;

    // construct an empty randomized queue
    public RandomizedQueue() {
        size = 0;
        s = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    private void resize(int newSize) {
        if (newSize < 1) return;
        Item[] newS = (Item[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            newS[i] = s[i];
            s[i] = null;
        }
        s = newS;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size == s.length) {
            resize(s.length * 2);
        }
        s[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        int randInt = StdRandom.uniformInt(size);
        Item value = s[randInt];
        if (randInt != size - 1) {
            s[randInt] = s[size - 1];
        }
        s[--size] = null;

        if (size > 0 && size <= s.length / 4) {
            resize(s.length / 2);
        }
        return value;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        int randInt = StdRandom.uniformInt(size);
        return s[randInt];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int iN;
        private Item[] sCopy;

        public RandomizedQueueIterator() {
            iN = size;
            sCopy = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                sCopy[i] = s[i];
            }
        }

        public boolean hasNext() {
            return iN > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            Item item;
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int randInt = StdRandom.uniformInt(iN);
            item = sCopy[randInt];
            if (randInt != iN - 1) {
                sCopy[randInt] = sCopy[iN - 1];
            }
            sCopy[--iN] = null;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        // int k = Integer.parseInt(args[1]);
        // RandomizedQueue rq = new RandomizedQueue();
        // while (!StdIn.isEmpty()) {
        //     StdIn.readString();
        //     rq.enqueue();
    }

}
