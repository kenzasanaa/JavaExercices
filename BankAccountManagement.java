// Write a Java program to create a class called "Bank" with a collection of accounts and methods to add and remove accounts, and to deposit and withdraw money. Also define a class called "Account" to maintain account details of a particular customer.
import java.util.ArrayList;

public class Main{
    public static void main(String[] args){
        Bank bank = new Bank();
        Account account1 = new Account("Peter Irmgard",5000.00,11005);
        Account account2 = new Account("Katja Ruedi",4500.00, 12109);
        Account account3 = new Account("Marcella Gebhard", 20000.00, 22267);
        // Add the three accounts to the bank
        bank.addAccount(account1);
        bank.addAccount(account2);
        bank.addAccount(account3);

        ArrayList<Account> accounts = bank.getAccounts();

        for (Account account: accounts) {
            System.out.println(account.getAccountInfo());
        }
        System.out.println("\n After depositing 1000 into account1:");
        bank.depositMoney(account1, 1000);
        System.out.println(account1.getAccountInfo());
        System.out.println("No transaction in account2:");
        System.out.println(account2.getAccountInfo());
        System.out.println("After withdrawing 5000 from account3:");
        bank.withdrawMoney(account3, 5000);
        System.out.println(account3.getAccountInfo());
    }
}


 class Account {
    private String name;
    private double money;
    private int accountNumber;
    public Account(String name, double money,int accountNumber) {
        this.name = name;
        this.money = money;
        this.accountNumber = accountNumber;
    }
    
    public String getName(){return name;}
    public double getMoney(){return money;}
    public int getAccountNumber(){return accountNumber;}

    public void setName(String newName) {
        this.name = newName;
    }
    public void setAccountNumber(int newAccountNumber) {
        this.accountNumber = newAccountNumber;
    }
    public void deposit(double amount) {
        money+= amount;
  }
    public void withdraw(double amount) {
        if (money >= amount) {money-= amount;} 
        else { System.out.println("your balance is not enough");}
  }
   public String getAccountInfo() {
        return "Name: " + name + ", Account Number: " + accountNumber + ", Balance: " + money;
    }
}

 class Bank{
    private ArrayList<Account> accounts;
    public Bank() {
    accounts = new ArrayList<Account>();
  }
  public void addAccount(Account account){
    accounts.add(account);
  }
  public void removeAccount(Account account){
    accounts.remove(account);
  }
  public void depositMoney(Account account, double amount){
    account.deposit(amount);
  }
  public void withdrawMoney(Account account, double amount){
    account.withdraw(amount);
  }
  public ArrayList<Account> getAccounts() {
    return accounts;
  }
    
}