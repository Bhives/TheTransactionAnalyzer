import Transactions.Transaction;
import Transactions.TransactionTypes;
import com.opencsv.CSVReader;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class InputFileReader {

    private File inputFile;
    private ArrayList<Transaction> transactions= new ArrayList<Transaction>();

    public InputFileReader(File inputFile) {
        this.inputFile = inputFile;
        this.transactions=this.writeTransactionsToList(inputFile);
    }

    public ArrayList<Transaction> writeTransactionsToList(File inputFile) {
        CSVReader inputFileReader = null;
        try {
            inputFileReader = new CSVReader(new FileReader(inputFile), ',', '"', 1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] currentLine = null;
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        while (true) {
            try {
                if (!((currentLine = inputFileReader.readNext()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                transactions.add(new Transaction(currentLine[0],
                        dateFormatter.parse(currentLine[1]),
                        Float.parseFloat(currentLine[2]),
                        currentLine[3],
                        TransactionTypes.valueOf(currentLine[4]),
                        currentLine[5]));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return transactions;
    }

    public File getInputFile() {
        return inputFile;
    }

    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }
}
