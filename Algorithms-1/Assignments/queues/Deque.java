import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int size = 0;
    private Node head = new Node();
    private Node tail = new Node();

    // construct an empty deque
    public Deque() {
        head.next = tail;
        tail.prev = head;
    }

    private class Node {
        private Item value;
        private Node next;
        private Node prev;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item item cannot be null");
        }
        Node n = new Node();
        n.value = item;
        n.next = head.next;
        n.next.prev = n;
        n.prev = head;
        head.next = n;
        ++size;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item item cannot be null");
        }
        Node n = new Node();
        n.value = item;
        n.prev = tail.prev;
        n.prev.next = n;
        n.next = tail;
        tail.prev = n;
        ++size;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        Item value = head.next.value;
        head.next = head.next.next;
        head.next.prev = head;
        --size;
        return value;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        Item value = tail.prev.value;
        tail.prev = tail.prev.prev;
        tail.prev.next = tail;
        --size;
        return value;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = head.next;

        public boolean hasNext() {
            return current != tail;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No further elements");
            }
            Item item = current.value;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        // testing add/remove from start/end
        System.out
                .println("********** Testing Sequence Start @ First **********");
        for (int i = 1; i <= 5; i++) {
            deque.addFirst(i);
            deque.addLast(-i);
        }
        System.out.println("Expect 5 to -5");
        while (deque.size() > 0)
            System.out.print(String.format("%d ", deque.removeFirst()));
        System.out.println("");
        System.out
                .println("********** Testing Sequence Start @ Last **********");
        for (int i = 1; i <= 5; i++) {
            deque.addLast(-i);
            deque.addFirst(i);
        }
        System.out.println("Expect -5 to 5");
        while (deque.size() > 0)
            System.out.print(String.format("%d ", deque.removeLast()));
        System.out.println("");
        // testing iterator
        System.out
                .println("********** Testing ForEach Iterator **********");
        for (int i = 1; i <= 5; i++) {
            deque.addFirst(i);
            deque.addLast(-i);
        }
        System.out.println("Expect 5 to -5");
        for (int value : deque)
            System.out.print(String.format("%d ", value));
        // testing iterator
        System.out.println("");
        System.out
                .println("********** Testing Parralel Iterators **********");
        Iterator<Integer> iterator1 = deque.iterator();
        Iterator<Integer> iterator2 = deque.iterator();
        while (iterator1.hasNext())
            System.out.println(String.format("%1$d %2$d", iterator1.next(),
                                             iterator2.next()));
        System.out.println("");
    }

}
