import java.io.File;

public class ProgramStart {

    public static void main(String[] args) throws NullPointerException {
        TransactionAnalyser transactionAnalyser = new TransactionAnalyser(new File("F:/GitHub/Java/The_Transaction_Analyser/src/main/resources/transactions.csv"));
        System.out.println(transactionAnalyser.getTransactions().toString());

    }
}
