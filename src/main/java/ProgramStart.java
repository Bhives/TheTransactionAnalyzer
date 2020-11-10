import java.io.File;
import java.util.Scanner;

public class ProgramStart {

    public static void main(String[] args) throws NullPointerException {
        TransactionAnalyser transactionAnalyser = new TransactionAnalyser(new File("F:/GitHub/Java/The_Transaction_Analyser/src/main/resources/transactions.csv"));
        System.out.println(transactionAnalyser.getTransactions().toString());
        System.out.println("Total number of transactions: "+transactionAnalyser.getTransactions().size());
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the name of merchant:");
        transactionAnalyser.analyzeTransactionsByMerchant(input.next());
    }
}