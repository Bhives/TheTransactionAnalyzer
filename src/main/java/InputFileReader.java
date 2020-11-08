import Transactions.TransactionRecord;
import Transactions.TransactionTypes;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class InputFileReader {

    private File inputFile;
    private ArrayList<TransactionRecord> transactions;

    public InputFileReader(File inputFile) throws FileNotFoundException, ParseException {
        this.inputFile = inputFile;
        this.transactions = this.writeTransactionsToList(inputFile);
    }

    public ArrayList<TransactionRecord> writeTransactionsToList(File inputFile) throws FileNotFoundException, ParseException {
        Scanner fileScanner=new Scanner(inputFile);
        ArrayList<TransactionRecord> transactions=new ArrayList<TransactionRecord>();
        fileScanner.useDelimiter(",");
        DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        while(fileScanner.hasNext()){
            transactions.add(new TransactionRecord(fileScanner.next(), dateFormatter.parse(fileScanner.next()), fileScanner.nextFloat(), fileScanner.next(), TransactionTypes.valueOf(fileScanner.next()), fileScanner.next()));
        }
        return transactions;
    }

    public File getInputFile() {
        return inputFile;
    }

    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    public ArrayList<TransactionRecord> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<TransactionRecord> transactions) {
        this.transactions = transactions;
    }
}
