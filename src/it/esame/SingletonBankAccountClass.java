package it.esame;

import static java.lang.System.*;

public class SingletonBankAccountClass {

    private String fullName;
    private double balance = 3000;
    private TransactionType lastTransactionType = TransactionType.INCOMING;
    private double lastTransactionAmount = 3000;
    private static SingletonBankAccountClass instance = null;

    /* Utilizzando la keyword "synchronized" ci assicuriamo che questa istanza Singleton sia anche thread safe */
    public static synchronized SingletonBankAccountClass getInstance(){
        if (instance == null)
            instance = new SingletonBankAccountClass();
        return SingletonBankAccountClass.instance;
    }

    /* Costruttore privato per non permettere l'istanziazione della classe dall'esterno */
    private SingletonBankAccountClass(){
        out.println("COSTRUTTORE DELLA CLASSE SINGLETON ESEGUITO");
        /* Come si vedrà poi in fase di esecuzione, il costruttore stamperà questa stringa solo una volta
        * perché viene eseguito solo la prima volta (la prima volta che viene chiamata la getInstance()) */
    }

    //getter del bilancio
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
        if (type.equals(TransactionType.OUTGOING) && this.balance < amount){ //bilancio minore dell'operazione, che è in uscita,
                                                                             //quindi non può essere permessa
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
