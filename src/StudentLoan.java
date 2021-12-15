import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

//Thread for StudentLoan
public class StudentLoan extends Thread implements Runnable{
    int speed, max; //Speed for the semaphores, max amount that can be deposited
    Bank account; //Create an instance of a bank account
    String threadName = ""; //Name "#" for each thread
    int count = 1; //Variable that counts the amount of months
    private static Random generator = new Random(); //Random amount of money deposited

    public StudentLoan(Bank account, String threadName, int speed, int max, int count) { //StudentLoan constructor to execute
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
            account.studentLoan(1500, threadName, count); //Using Producer's student Loan to deposit to Balance
            account.PayCredit(1 + generator.nextInt(max), threadName, count); //Pay credit through Producer
        }
        //The exception that signals the app to shut down and the thread to stop
        catch(InterruptedException ex) {
            ex.printStackTrace();
            Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}