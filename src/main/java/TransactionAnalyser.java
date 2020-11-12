import Transactions.Transaction;
import Transactions.TransactionTypes;
import com.opencsv.CSVReader;

import javax.xml.stream.util.StreamReaderDelegate;
import java.io.*;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Stream;

public class TransactionAnalyser {

    private File inputFile;
    private ArrayList<Transaction> transactions= new ArrayList<Transaction>();
    final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

    public TransactionAnalyser(File inputFile) {
        this.inputFile = inputFile;
        this.transactions=this.writeTransactionsToList(inputFile);
    }

    public ArrayList<Transaction> writeTransactionsToList(File inputFile) {
        CSVReader inputFileReader = null;
        try {
            inputFileReader = new CSVReader(new FileReader(inputFile), ',', '"', 1);
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        String[] currentLine = null;
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        try {
            while ((currentLine = inputFileReader.readNext()) != null) {
                transactions.add(new Transaction(currentLine[0], this.parseStringToDate(currentLine[1]), Float.parseFloat(currentLine[2]), currentLine[3], TransactionTypes.valueOf(currentLine[4]), currentLine[5]));
            }
            inputFileReader.close();
        } catch (IOException iOException) {
            iOException.printStackTrace();
        } finally {
            return transactions;
        }
    }

    public void analyzeTransactionsByMerchant(String merchantName, String dateFrom, String dateTo) {
        ArrayList<Transaction> foundTransactions=new ArrayList<Transaction>();
        Stream<Transaction> transactionStream = transactions.stream();
        transactionStream
                .filter(transaction -> merchantName.equals(transaction.getTransactionMerchant()))
                .filter(transaction -> transactions.stream().noneMatch(transaction1 -> transaction1.getRelatedTransactionId().equals(transaction.getTransactionId())))
                .filter(transaction-> transaction.getTransactionDate().after(this.parseStringToDate(dateFrom))
                        && transaction.getTransactionDate().before(this.parseStringToDate(dateTo)))
                .forEach(transaction -> foundTransactions.add(transaction));
        System.out.printf("Number of transactions = %s", foundTransactions.size());
    }

    public Date parseStringToDate(String currentDate){
        Date newDate=new Date();
        try {
            newDate=dateFormatter.parse(currentDate);
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }
        finally {
            return newDate;
        }
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
