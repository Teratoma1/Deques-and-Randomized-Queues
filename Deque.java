/* *****************************************************************************

 **************************************************************************** */


import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last = null;
    private int count = 0;

    private class Node {
        Node nextFront;
        Node nextBack;
        Item item;
    }

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null && last == null;
    }

    // return the number of items on the deque
    public int size() {
        return count;
    }

    // add the item to the front
    public void addFirst(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.nextFront = oldfirst;
        count++;
        if (count == 1) {
            last = first;
            last.item = item;
        }

    }

    // add the item to the back
    public void addLast(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.nextBack = oldlast;
        count++;
        if (count == 1) {
            first = last;
            first.item = item;
        }

    }

    /*
    // remove and return the item from the front
    public Item removeFirst()

    // remove and return the item from the back
    public Item removeLast()
*/

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    public class ListIterator implements Iterator<Item> {
        private Node current = first;
        private Node current2 = last;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("don't work son");
        }

        public Item next() {
            if (current.nextFront == null) {
                if (current2.nextBack == null) {
                    throw new NoSuchElementException("nema se");
                }
                Item item = current2.item;
                current2 = current2.nextBack;
                return item;
            }
            Item item = current.item;
            current = current.nextFront;
            return item;
        }

    }


    // unit testing (required)
    public static void main(String[] args) {

        Deque<Integer> deck = new Deque<>();
        deck.addFirst(5);
        deck.addLast(-5);
        deck.addLast(-25);
        deck.addFirst(25);
        deck.addFirst(50);
        deck.addLast(-50);

        System.out.println(deck.size());
        for (int i : deck) {
            System.out.println(i);
        }

/*
        String s = StdIn.readString();
        while (!s.isEmpty()) {

        }
        */

    }
}
