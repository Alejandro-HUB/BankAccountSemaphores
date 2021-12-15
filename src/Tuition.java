
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

//Thread for Tuition
public class Tuition extends Thread implements Runnable {
    int speed, max; //Speed for the semaphores, max amount that can be withdrawn, number of months
    Bank account; //Create an instance of a bank account
    String threadName = ""; //Name "#" for each thread
    int count = 1; //Variable that counts the amount of months
    private static Random generator = new Random(); //Random amount of money withdrawn

    public Tuition(Bank account, String threadName, int speed, int max, int count) { //Consumer constructor to execute
        this.speed = speed; //Speed for the semaphores
        this.max = max; //Max amount that can be withdrawn
        this.account = account; //Create an instance of a bank account
        this.threadName = threadName; //Name "#" for each thread
        this.count = count; //Counts number of months
    }

    @Override
    public void run() { //Method to run a thread and its functions
        try {
            Thread.sleep(speed); //Wait using the semaphores using the speed
            account.Tuition(1500, threadName, count); //Tuition gets withdrawn from the balance
            account.HousingAndFood(200, threadName, count); //Housing and Food withdraws from Balance
        }
        //The exception that signals the app to shut down and the thread to stop
        catch(InterruptedException ex) {
            ex.printStackTrace();
            Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}