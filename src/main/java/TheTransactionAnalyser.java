import Transactions.Transaction;

import java.io.File;
import java.sql.SQLOutput;

public class TheTransactionAnalyser {

    public static void main(String[] args) {
        InputFileReader inputFileReader = new InputFileReader(new File("F:/GitHub/Java/The_Transaction_Analyser/src/main/resources/transactions.csv"));
        System.out.println(inputFileReader.getTransactions().toString());
    }
}
