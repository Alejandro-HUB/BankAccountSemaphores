import java.util.concurrent.*;

public class SemaphoreBank implements Bank {
    //The account balance starts out at 0
    private int accountBalance = 0;
    //The credit balance starts out at 1500
    private int creditBalance = 1500;
    //The credit debt starts out at 0
    private int creditDebt = 0;
    //Semaphore for handling Consumer Threads
    static Semaphore consumerThread = new Semaphore(0);
    //Semaphore for handling Producer Threads
    static Semaphore producerThread = new Semaphore(1);

    //Withdraw method that is called by Consumer.java
    public void withdraw(int value, String threadName, int count) {
        try {
            consumerThread.acquire(); //Acquire the semaphore for withdraw
            if(accountBalance - value < 0) { //If there is not enough money
                printState("", "Consumer " + threadName + " withdraws $" + value, "Withdraw Blocked: Insufficient Funds"); //Deny the withdrawal
                if((creditBalance - value < 0) && (creditDebt != 0)) //If there are no funds in the credit and the credit debt is not 0
                {
                    printState("", "Consumer " + threadName + " Credit $" + value, "Withdraw Blocked: Insufficient Credit"); //Deny the withdrawal
                }
                else
                {
                    creditBalance -= value; //Now you have less credit balance to spent
                    creditDebt += value; //Add the payment to the credit debt
                    printState("", "Consumer " + threadName + " Credit $" + value, "Balance: $" + accountBalance); //Show new balance after credit payment
                    System.out.println("                                                            Credit left: $" + creditBalance + //Display amount left in credit card to be spent
                            ", Credit owed: $" + creditDebt); //Display credit owed
                }
                producerThread.release(); //Release the semaphore for withdraw
                return;
            }
            accountBalance -= value; //If there is balance in the account, withdraw money
            printState("", "Consumer " + threadName + " withdraws $" + value, "Balance: $" + accountBalance); //Show new balance after withdrawal
            producerThread.release(); //Release the semaphore for withdraw
        }
        catch(InterruptedException e) { //If the program reaches the exception
            System.out.println("InterruptedException caught");
        }
    }

    //deposit method that is called by Producer.java
    public void deposit(int value, String threadName, int count) {
        try {
            producerThread.acquire(); //Acquire the semaphore for deposit
            accountBalance += value; //Add deposit to the account balance
            printState("Producer " + threadName + " deposits $" + value, "" , "Balance: $" + accountBalance); //Display new account balance
            consumerThread.release(); //Release the semaphore for deposit
        } catch(InterruptedException e) { //If the program reaches the exception
            System.out.println("InterruptedException caught");
        }
    }

    //studentLoan method that is called by Producer.java
    public void studentLoan(int value, String threadName, int count)
    {
        if ((count == 1) || (count == 4) || (count == 8)) { // Each semester starts within 4 months in the year. At month 1, month 4, and month 8.
            try {
                producerThread.acquire(); //Acquire the semaphore for studentLoan
                accountBalance += value; //Add studentLoan to the account balance
                printState("Student Loan " + threadName + " deposits $" + value, "" , "Balance: $" + accountBalance); //Display new account balance
                consumerThread.release(); //Release the semaphore for studentLoan
            } catch(InterruptedException e) { //If the program reaches the exception
                System.out.println("InterruptedException caught");
            }
        }
    }

    //Tuition method that is called by Consumer.java
    public void Tuition(int value, String threadName, int count) {
        if ((count == 1) || (count == 4) || (count == 8)) { // Each semester starts within 4 months in the year. At month 1, month 4, and month 8.

            try {
                consumerThread.acquire(); //Acquire the semaphore for Tuition
                if (accountBalance - value < 0) { //If there is not enough money
                    printState("", "Tuition " + threadName + " withdraws $" + value, "Withdraw Blocked: Insufficient Funds"); //Deny the withdrawal
                    if((creditBalance - value < 0) && (creditDebt != 0)) //If there are no funds in the credit and the credit debt is not 0
                    {
                        printState("", "Consumer " + threadName + " Credit $" + value, "Withdraw Blocked: Insufficient Credit"); //Deny the withdrawal
                    }
                    else
                    {
                        creditBalance -= value; //Now you have less credit balance to spent
                        creditDebt += value; //Add the payment to the credit debt
                        printState("", "Consumer " + threadName + " Credit $" + value, "Balance: $" + accountBalance); //Show new balance after credit payment
                        System.out.println("                                                            Credit left: $" + creditBalance + //Display amount left in credit card to be spent
                                ", Credit owed: $" + creditDebt); //Display credit owed
                    }
                    producerThread.release(); //Release semaphore for Tuition
                    return;
                }
                accountBalance -= value; //If there is balance in the account, withdraw Tuition
                printState("", "Tuition " + threadName + " withdraws $" + value, "Balance: $" + accountBalance); //Show new balance after withdraw of Tuition
                producerThread.release(); //Release semaphore for Tuition

            } catch (InterruptedException e) { //If the program reaches the exception
                System.out.println("InterruptedException caught");
            }

        }
    }


    //Housing and Food method that is called by Consumer.java
    public void HousingAndFood(int value, String threadName, int count) {

                try {
                    consumerThread.acquire(); //Acquire the semaphore for Housing
                    if (accountBalance - value < 0) { //If there is not enough money
                        printState("", "Housing " + threadName + " withdraws $" + value, "Withdraw Blocked: Insufficient Funds"); //Deny the withdrawal
                        if((creditBalance - value < 0) && (creditDebt != 0)) //If there are no funds in the credit and the credit debt is not 0
                        {
                            printState("", "Consumer " + threadName + " Credit $" + value, "Withdraw Blocked: Insufficient Credit"); //Deny the withdrawal
                        }
                        else
                        {
                            creditBalance -= value; //Now you have less credit balance to spent
                            creditDebt += value; //Add the payment to the credit debt
                            printState("", "Consumer " + threadName + " Credit $" + value, "Balance: $" + accountBalance); //Show new balance after credit payment
                            System.out.println("                                                            Credit left: $" + creditBalance + //Display amount left in credit card to be spent
                                    ", Credit owed: $" + creditDebt); //Display credit owed
                        }
                        producerThread.release(); //Release semaphore for Housing
                        return;
                    }
                    accountBalance -= value; //If there is balance in the account, withdraw Housing
                    printState("", "Housing " + threadName + " withdraws $" + value, "Balance: $" + accountBalance); //Show new balance after withdraw of Housing
                    producerThread.release(); //Release semaphore for Housing

                } catch (InterruptedException e) { //If the program reaches the exception
                    System.out.println("InterruptedException caught");
                }

        }

    //Job: deposit method that is called by Producer.java
    public void Job(int value, String threadName, int count) {
        try {
            producerThread.acquire(); //Acquire the semaphore for deposit
            accountBalance += value; //Add deposit to the account balance
            printState("Job " + threadName + " deposits $" + value, "" , "Balance: $" + accountBalance); //Display new account balance
            consumerThread.release(); //Release the semaphore for deposit
        } catch(InterruptedException e) { //If the program reaches the exception
            System.out.println("InterruptedException caught");
        }
    }

    //Bills: withdraw method that is called by Consumer.java
    public void Bill(int value, String threadName, int count) {
        try {
            consumerThread.acquire(); //Acquire the semaphore for Bills
            if (accountBalance - value < 0) { //If there is not enough money
                printState("", "Bill " + threadName + " withdraws $" + value, "Withdraw Blocked: Insufficient Funds"); //Deny the withdrawal
                if((creditBalance - value < 0) && (creditDebt != 0)) //If there are no funds in the credit and the credit debt is not 0
                {
                    printState("", "Consumer " + threadName + " Credit $" + value, "Withdraw Blocked: Insufficient Credit"); //Deny the withdrawal
                }
                else
                {
                    creditBalance -= value; //Now you have less credit balance to spent
                    creditDebt += value; //Add the payment to the credit debt
                    printState("", "Consumer " + threadName + " Credit $" + value, "Balance: $" + accountBalance); //Show new balance after credit payment
                    System.out.println("                                                            Credit left: $" + creditBalance + //Display amount left in credit card to be spent
                                       ", Credit owed: $" + creditDebt); //Display credit owed
                }
                producerThread.release(); //Release semaphore for Bills
                return;
            }
            accountBalance -= value; //If there is balance in the account, withdraw Bills
            printState("", "Bill " + threadName + " withdraws $" + value, "Balance: $" + accountBalance); //Show new balance after withdraw of Bills
            producerThread.release(); //Release semaphore for Bills

        } catch (InterruptedException e) { //If the program reaches the exception
            System.out.println("InterruptedException caught");
        }
    }

    //PayCredit method that is called by Producer.java
    public void PayCredit(int value, String threadName, int count)
    {
        int creditTwenty = (creditDebt * 20)/100; //Get 20% of the credit debt
        int creditTemp = creditDebt - creditTwenty; //Store credit debt - 20% of credit debt

            try {
                producerThread.acquire(); //Acquire the semaphore for studentLoan
                if((accountBalance - creditTwenty < 0) && (creditDebt != 0)) //Check if there are not enough funds to pay Credit
                {
                    printState("Pay Credit 20% " + threadName + " $" + creditTwenty,"" ,"Payment Blocked: Insufficient Funds"); //Deny the payment
                }
                else if (creditDebt == 0) //If there is no debt to pay do nothing
                {

                }
                else
                {
                    accountBalance -= creditTwenty; //Subtract 20% of the credit debt from the account balance
                    creditDebt = creditTemp; //20% of credit debt is subtracted from credit debt full
                    printState("Pay Credit 20% " + threadName + " $" + creditTwenty, "", "CreditOwed: $" + creditDebt); //Show new balance after Credit Payment
                }
                consumerThread.release(); //Release the semaphore for studentLoan
            } catch(InterruptedException e) { //If the program reaches the exception
                System.out.println("InterruptedException caught");
            }

    }


    //Formatting the output in the form of a grid
    public void printState(String depositString, String withdrawString, String balance)
    {
        System.out.printf("%-30s%-30s%-30s\n", depositString, withdrawString, balance); //Declare the format for each field
    }
}