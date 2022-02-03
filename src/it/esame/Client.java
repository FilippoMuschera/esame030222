package it.esame;

import java.util.Scanner;

import static java.lang.System.*;

public class Client {
    public void logUser(){
      SingletonBankAccountClass bankAccount = SingletonBankAccountClass.getInstance();
        Scanner scanner = new Scanner(in);
        out.println("Enter your full name:");
        bankAccount.setFullName(scanner.nextLine());
    }

    public void requestTransaction(){
        Scanner scanner = new Scanner(in);
        SingletonBankAccountClass bankAccount = SingletonBankAccountClass.getInstance();
        out.println("What type of operation would you like to do? 1 = Outgoing, 2 = Incoming");
        int operation = scanner.nextInt();
        TransactionType type;
        if (operation == 1)
            type = TransactionType.OUTGOING;
        else if (operation == 2)
            type = TransactionType.INCOMING;
        else {
            err.println("Invalid operation");
            return;
        }
        out.println("Insert operation amount");
        double amount = scanner.nextDouble();
        if (amount < 0) {
            err.println("Invalid amount value");
            return;
        }
        bankAccount.doTransaction(type, amount);

    }

    public static void main(String[] args) {
        Client client = new Client();
        client.logUser();
        client.requestTransaction();
        SingletonBankAccountClass bankAccount = SingletonBankAccountClass.getInstance();
        bankAccount.printLastTransaction();
        /* Siccome l'istanza del bank account è Singleton, anche quando qui nel main prenderò l'istanza, sarà
        * quella su cui ha operato il client, e infatti il bilancio corrisponderà a quello dopo l'operazione eseguita */
        double balance = bankAccount.getBalance();
        out.println("Current balance is now: " + balance);
        client.requestTransaction();
        double balance2 = bankAccount.getBalance();
        out.println("Current balance is now: " + balance2);
    }

}



