/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private final StackForRandomizer<Item> stack = new StackForRandomizer<>();

    private Node first;

    private int count = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
    }

    private class Node {
        Node next;
        Item item;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return first == null;

    }

    // return the number of items on the randomized queue
    public int size() {
        return count;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("can't be null");
        }
        Node novo = new Node();
        novo.item = item;
        count++;
        /*
        if (count == 1) {
            last = novo;
            last.item = item;
        }
        else {
            first.prev = novo;
        }*/
        novo.next = first;
        first = novo;
    }

    // temporary transfer
    private void pusher() {
        int broj = StdRandom.uniform(count);
        while (broj > 0) {

            Item item = first.item;
            first = first.next;

            stack.push(item);
            count--;
            broj--;
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("queue is empty");
        }

        pusher();
        Item item = first.item;
        first = first.next;


        // while (!queue.isEmpty()) {}
        for (Item i : stack) {
            enqueue(i);
            stack.pop();
        }
        count--;

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("queue is empty");
        }

        pusher();
        Item item = first.item;

        // while (!queue.isEmpty()) {}
        for (Item i : stack) {
            enqueue(i);
            stack.pop();
        }

        return item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("not supported");
        }

        public Item next() {
            if (current == null) {
                throw new java.util.NoSuchElementException("no more items");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

    }

    private class StackForRandomizer<Item2> implements Iterable<Item2> {
        private Node2 first = null;

        private class Node2 {
            Item2 item;
            Node2 next;
        }

        public boolean isEmpty() {
            return first == null;
        }

        public void push(Item2 item) {
            Node2 oldfirst = first;
            first = new Node2();
            first.item = item;
            first.next = oldfirst;
        }

        public Item2 pop() {
            Item2 item = first.item;
            first = first.next;
            return item;
        }

        public Iterator<Item2> iterator() {
            return new ListIterator();
        }

        private class ListIterator implements Iterator<Item2> {
            private Node2 current = first;

            public boolean hasNext() {
                return current != null;
            }

            public void remove() { /* not supported */ }

            public Item2 next() {
                if (current == null) {
                    throw new java.util.NoSuchElementException("no more items");
                }

                Item2 item = current.item;
                current = current.next;
                return item;
            }
        }
    }


    public static void main(String[] args) {


        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();

        while (!StdIn.isEmpty()) {
            String a = StdIn.readString();

            if (a.equals("-")) {
                StdOut.println(queue.dequeue());
            }
            else if (a.equals("!")) {
                StdOut.println(queue.sample());
            }
            else {
                queue.enqueue(Integer.parseInt(a));
            }


        }

    }

}
