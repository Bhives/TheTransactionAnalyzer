import Transactions.Transaction;
import Transactions.TransactionTypes;
import com.opencsv.CSVReader;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;

public class TransactionAnalyser {

    private File inputFile;
    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

    public TransactionAnalyser(File inputFile) {
        this.inputFile = inputFile;
        this.transactions = this.writeTransactionsToList(inputFile);
    }

    /*
        This method reads .csv-file
        and parses it's data to list of objects
     */
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
                transactions.add(new Transaction(currentLine[0], this.parseStringToDate(currentLine[1]), Double.parseDouble(currentLine[2]), currentLine[3], TransactionTypes.valueOf(currentLine[4]), currentLine[5]));
            }
        } catch (IOException | NullPointerException exception) {
            exception.printStackTrace();
        } finally {
            try {
                inputFileReader.close();
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
            return transactions;
        }
    }

    /*
        Main analyzer method to find certain transactions with our input search data
        and make an output with total number of found transactions and it's average amount
        + ignores transactions that were reversed
     */

    public void analyzeTransactionsByMerchant(String merchantName, String dateFrom, String dateTo) {
        ArrayList<Transaction> foundTransactions = new ArrayList<Transaction>();
        try {
            transactions.stream()
                    .filter(transaction -> merchantName.equals(transaction.getTransactionMerchant()))
                    .filter(transaction -> transactions.stream()
                            .noneMatch(transactionIfRelated -> transactionIfRelated
                                    .getRelatedTransactionId().equals(transaction.getTransactionId())))
                    .filter(transaction -> transaction.getTransactionDate().after(parseStringToDate(dateFrom))
                            && transaction.getTransactionDate().before(parseStringToDate(dateTo)))
                    .forEach(transaction -> foundTransactions.add(transaction));
            System.out.printf("Number of transactions = %s\n", foundTransactions.size());
            System.out.printf("Average transaction value = %.2f", foundTransactions.stream().mapToDouble(Transaction::getTransactionAmount).average().getAsDouble());
        } catch (NoSuchElementException | DateTimeException exception) {
            exception.printStackTrace();
        }
    }

    /*
        This method is managed to simplify parsing operations for strings with date to Date object -
        to make less exception handling operations throughout the code
     */

    public Date parseStringToDate(String inputDate) {
        Date parsedDate = new Date();
        try {
            parsedDate = dateFormatter.parse(inputDate);
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        } finally {
            return parsedDate;
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
