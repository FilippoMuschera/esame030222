package it.esame;

import static java.lang.System.*;

public class SingletonBankAccountClass {

    private String fullName;
    private double balance = 3000;
    private TransactionType lastTransactionType = TransactionType.INCOMING;
    private double lastTransactionAmount = 3000;
    private static final SingletonBankAccountClass ISTANCE = new SingletonBankAccountClass();

    public static SingletonBankAccountClass getInstance(){
        return SingletonBankAccountClass.ISTANCE;
    }

    /* Costruttore privato per non permettere l'istanziazione della classe dall'esterno */
    private SingletonBankAccountClass(){}

    public double getBalance() {
        return balance;
    }
    public void printLastTransaction(){
        String messageOutput = """
                Welcome %s, your last transaction was an %s transaction,
                for a total amount of %f.
                """;
        messageOutput = messageOutput.formatted(this.fullName, this.lastTransactionType, this.lastTransactionAmount);
        out.println(messageOutput);
    }

    public void doTransaction(TransactionType type, double amount){
        if (type.equals(TransactionType.OUTGOING) && this.balance < amount){
            err.println("Your balance is too low for this operation");
            return;
        }
        this.lastTransactionType = type;
        this.lastTransactionAmount = amount;
        if (type.equals(TransactionType.OUTGOING))
            balance = balance - amount;
        else
            balance = balance + amount;
        out.println("Transaction executed successfully");

    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
