/*
 * Sarah Hall
 * CSCI 232
 * Part 2 of homework assignment 3
 */

package homework3;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Part2 implements Runnable {

    static ArrayList<Integer> ints = new ArrayList<Integer>();
    static int target = 1;
    int id;
    int random;
    Part2 next;

    public Part2(int thisId) {
        id = thisId;
    }

    public void setNext(Part2 newnext) {
        next = newnext;
    }

    @Override
    public void run() {
        while (target != id) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Part2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            random = (int) (Math.random() * 50 + 1);
            ints.add(random);
            System.out.println("Thread " + id + " inserted " + random);
            target = next.id;


}

public static void main(String[] args) throws InterruptedException {
        Part2 first = new Part2(1);
        Part2 second = new Part2(2);
        Part2 third = new Part2(3);
        Part2 fourth = new Part2(4);

        first.setNext(second);
        second.setNext(third);
        third.setNext(fourth);
        fourth.setNext(first);

        Thread firstT = new Thread(first);
        Thread secondT = new Thread(second);
        Thread thirdT = new Thread(third);
        Thread fourthT = new Thread(fourth);

        firstT.start();
        secondT.start();
        thirdT.start();
        fourthT.start();

    }
}
