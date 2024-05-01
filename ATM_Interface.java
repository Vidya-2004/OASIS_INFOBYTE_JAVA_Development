// Task 03 -> ATM Interface

import java.io.*;
class CustomerInformation {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    protected double balance;
    protected String name;
    protected long accountNumber;
    CustomerInformation() {
        try {
            System.out.print("Enter Name: ");
            name = in.readLine();
            System.out.print("Enter account Number: ");
            accountNumber = Long.parseLong(in.readLine());
            System.out.print("Enter Balance in account: ");
            balance = Double.parseDouble(in.readLine());
        } catch(Exception e) {
            System.out.println("Invalid Input.");
        }
    }
}
public class Atm extends CustomerInformation {
    void checkBalance() {
        System.out.println("Name: " + name);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Current Balance: " + balance);
    }
    void withdrawMoney()throws IOException {
        System.out.print("PLs Enter the amount to be withdrawn: ");
        double amt = Integer.parseInt(in.readLine());

        if(amount <= balance) {
            balance = balance - amount;
            System.out.println("Money Withdrawn: " + amount);
            System.out.println("New Balance: " + balance);
            System.out.println("Transction Success");
        } else {
            System.out.println("Soory Insufficient Balance");
            System.out.println("Transction Failed");
        }
    }
    void DepositMoney()throws IOException {
        System.out.print("PLs Enter the amount to be Deposited: ");
        double amt = Integer.parseInt(in.readLine());

        balance = balance + amount;
        System.out.println("Money Depositd: " + amount);
        System.out.println("New Balance: " + balance);
        System.out.println("Transction Success");
    }
    public static void main(String args[]) {
        Atm obj = new Atm();
        while(true) {
            System.out.println("Enter 1 -> checkBalance\nEnter 2 -> withdraw\nEnter 3 -> deposit\nEnter 4 -> To Exit");
            try{
                int choice = Integer.parseInt(in.readLine());
                if(choice == 1) {
                    obj.checkBalance();
                } else if(choice == 2) {
                    obj.withdrawMoney();
                } else if(choice == 3) {
                    obj.DepositMoney();
                } else if(choice == 4) {
                    System.out.println("Thanks for using our ATM.");
                    break;
                } else {
                    System.out.println("Pls Enter correct choice.");
                }
            } catch (Exception e) {
                System.out.println("Invalid Input! Try Again.");
            }
            System.out.println("-------------------");
        }
    }
}
