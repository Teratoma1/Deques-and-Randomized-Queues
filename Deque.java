/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last;

    private int count = 0;

    private class Node {
        Node next;
        Node prev;

        Item item;
    }

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null || last == null;
    }

    // return the number of items on the deque
    public int size() {
        return count;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("can't be null");
        }

        Node novo = new Node();
        novo.item = item;
        count++;
        if (count == 1) {
            last = novo;
            last.item = item;
        }
        else {
            first.prev = novo;
        }
        novo.next = first;
        first = novo;

         /*
        Node oldfirst = first; //50
        first = new Node(); //25
        first.item = item;
        first.next = null;
        count++;
        if (count == 1) {
            first = last;
        }
        else {
            first.prev = oldfirst;
            oldfirst.next = first; //50

        }

         */
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("can't be null");
        }

        Node novo = new Node();
        novo.item = item;
        count++;
        if (count == 1) {
            first = novo;
            first.item = item;
        }
        else {
            last.next = novo;
            novo.prev = last;
        }
        last = novo;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (first == null) {
            throw new java.util.NoSuchElementException("empty");
        }
        Item item = first.item;
        if (first.next == null) {
            last = null; // ako je zadnji u dequeu - druga strana postaje takoder null
        }
        else {
            first.next.prev = null; // ako zadnji ispred nije zadnji u dequeu, pokazuje na null
        }
        first = first.next; // node pokazuje na samog sebe umjesto zadnjeg ispred
        count--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (last == null) {
            throw new java.util.NoSuchElementException("empty");
        }
        Item item = last.item;

        if (last.prev == null) {
            first = null; // ako zadnji straga nije zadnji u dequeu, pokazuje na null
        }
        else {
            last.prev.next = null;
        }

        last = last.prev; // node pokazuje na samog sebe umjesto zadnjeg straga
        count--;
        return item;
    }


    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // return an iterator over items in order from front to back
    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("unsupported operation");
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


    public static void main(String[] args) {
        Deque<Integer> deck = new Deque<>();
        deck.addFirst(5);
        deck.addLast(-5);
        deck.addFirst(15);
        deck.addFirst(25);
        deck.addLast(-50);
        deck.addFirst(30);
    }
}
