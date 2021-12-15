public interface Bank {
    public void withdraw(int value, String threadName, int count); //method to withdraw money: Consumer
    public void deposit(int value, String threadName, int count); //method to deposit money: Producer
    public void studentLoan(int value, String threadName, int count); //method to deposit student loan: Producer
    public void Tuition(int value, String threadName, int count); //method to withdraw tuition: Consumer
    public void HousingAndFood(int value, String threadName, int count); //method to withdraw Housing and Food: Consumer
    public void Job(int value, String threadName, int count); //method to deposit Job Money: Producer
    public void Bill(int value, String threadName, int count); //method to withdraw Bills: Consumer
    public void PayCredit(int value, String threadName, int count); //method to pay Credit: Producer
}