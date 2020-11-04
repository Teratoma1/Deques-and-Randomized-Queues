/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {

        RandomizedQueue<String> ispis = new RandomizedQueue<>();

        int k = Integer.parseInt(args[0]);

        while (!StdIn.isEmpty()) {
            ispis.enqueue(StdIn.readString());
        }
        for (int i = 0; i <= k; i++) {
            System.out.println(ispis.dequeue());
        }
    }
}
