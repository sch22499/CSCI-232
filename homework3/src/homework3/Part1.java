/*
 * Sarah Hall
 * CSCI 232
 * Part 1 of homework 3
 */

package homework3;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Part1 implements Runnable{
    
    int seconds;
    int count;
    String spacing = "";
    String category;
    
    public Part1(int newseconds){
        seconds = newseconds;
        category = Integer.toString(seconds);
    }
    

    @Override
    public void run() {
        while(true){
            try {
                count++;
                if("3".equals(category)){
                    spacing = "    ";
                }else{
                    spacing = "";
                }
                Thread.sleep(seconds * 1000);
                System.out.println(spacing + count);
            } catch (InterruptedException ex) {
                Logger.getLogger(Part1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public static void main(String[] args) {
        Part1 first = new Part1(1);
        Part1 second = new Part1(3);
        Thread t1 = new Thread(first);
        Thread t2 = new Thread(second);
        
        t1.start();
        t2.start();
    }
}


