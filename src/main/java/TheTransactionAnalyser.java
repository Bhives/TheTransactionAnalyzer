import Transactions.Transaction;

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.text.ParseException;

public class TheTransactionAnalyser {

    public static void main(String[] args) throws NullPointerException {
        InputFileReader inputFileReader = new InputFileReader(new File("F:/GitHub/Java/The_Transaction_Analyser/src/main/resources/transactions.csv"));
        System.out.println(inputFileReader.getTransactions().toString());
    }
}
