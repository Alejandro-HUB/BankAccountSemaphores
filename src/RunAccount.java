import java.util.concurrent.*;
import java.util.Scanner;

public class RunAccount {
    public static void main(String[] args)
    {
        //Number of months
        int months = 12;
        //Number of Universities
        int u = 1;
        //Number of consumers
        int c = 2;
        //Number of producers
        int p = 2;
        //Withdrawal speed
        int wSpeed = 1;
        //Maximum withdrawal amount
        int wMax = 150;
        //Deposit speed
        int dSpeed = 1;
        //Maximum deposit amount
        int dMax = 200;

        //Check if number of producers equals number of consumers
        if(c != p)
        {
            System.out.println("Error: Number of Producers must be equal to number of Consumers" + "\nExiting....");
            System.exit(0);
        }

        //Grid to organize output
        System.out.println("");
        System.out.println("Producer:                     Consumer:                     Balance: ");
        System.out.println("---------                     ---------                     -------- ");

        String threadname = ""; //The index of each threat. There can be more than one producer or consumer threat
        Bank account; // Initialize bank account: withdraw, deposit, tuition, student loan


        account = new SemaphoreBank();

        for(int k = 1; k <= months; k++) //Loops 12 times for the number of months
        {
            ExecutorService app = Executors.newFixedThreadPool(2+c); //Execute with this number of threads

            if((k == 1) || (k == 4) || (k == 8)) //Show that a semester started
            {
                System.out.println("------------------------------------------------------------------------------------------------"
                        + "\nMonth: " + k + " - Semester Started"//Show month number and new Semester
                        + "\n------------------------------------------------------------------------------------------------");
            }
            else
            {
                System.out.println("------------------------------------------------------------------------------------------------"
                        + "\nMonth: " + k //Show month number
                        + "\n------------------------------------------------------------------------------------------------");
            }
            try {
                //Get University Threads
                for (int l = 1; l <= u; l++) {
                    threadname = Integer.toString(l); //Number of threads for University
                    app.execute(new StudentLoan(account, threadname, dSpeed, dMax, k)); //Get each thread for Student Loans
                    app.execute(new Tuition(account, threadname, dSpeed, dMax, k)); //Get each thread for Tuition
                }
                //Get Producer Threads
                for (int j = 1; j <= p; j++) {
                    threadname = Integer.toString(j); //Number of threads for Producers
                    app.execute(new Producer(account, threadname, dSpeed, dMax, k)); //Get each thread for Producers
                }
                //Get Consumer Threads
                for (int i = 1; i <= c; i++) {
                    threadname = Integer.toString(i); //Number of threads for Consumers
                    app.execute(new Consumer(account, threadname, wSpeed, wMax, k)); //Get each thread for Consumers
                }
            }
            //Shut down the app if it reaches the exception
            catch(Exception ex) {ex.printStackTrace();}
                app.shutdown();
                while(!app.isTerminated())
                {

                }

        }


    }
}