import java.io.File;

public class ProgramStart {

    public static String inputName="DimitriPetrenko";
    public static String inputDateFrom= "11/09/2020 13:00:00";
    public static String inputDateTo= "11/09/2020 14:00:00";

    public static void main(String[] args) throws NullPointerException {
        TransactionAnalyser transactionAnalyser = new TransactionAnalyser(new File("./src/main/resources/transactions.csv"));
        //System.out.println(transactionAnalyser.getTransactions().toString());
        System.out.printf("Analysis of transactions made by %s, from %s to %s:\n", inputName, inputDateFrom, inputDateTo);
        transactionAnalyser.analyzeTransactionsByMerchant(inputName, inputDateFrom, inputDateTo);
    }
}