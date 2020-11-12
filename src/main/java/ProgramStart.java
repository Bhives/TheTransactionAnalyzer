import java.io.File;

public class ProgramStart {

    //An input parameters could be changed here

    public static String inputName = "DimitriPetrenko";
    public static String inputDateFrom = "11/09/2020 13:00:00";
    public static String inputDateTo = "11/09/2020 15:00:00";
    public static String fileName = "./src/main/resources/transactions.csv";

    public static void main(String[] args) {
        TransactionAnalyser transactionAnalyser = new TransactionAnalyser(new File(fileName));
        System.out.printf("Analysis of transactions made by %s, from %s to %s:\n", inputName, inputDateFrom, inputDateTo);
        transactionAnalyser.analyzeTransactionsByMerchant(inputName, inputDateFrom, inputDateTo);
    }
}