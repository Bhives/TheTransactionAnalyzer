import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;

public class TheTransactionAnalyser {

    public static void main(String[] args) {
            InputFileReader inputFileReader = new InputFileReader(new File("F:/GitHub/Java/The_Transaction_Analyser/src/main/resources/transactions.csv"));
            System.out.println(inputFileReader.getTransactions().toString());
    }
}
