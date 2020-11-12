import Transactions.Transaction;
import Transactions.TransactionTypes;
import com.opencsv.CSVReader;

import java.io.*;
import java.rmi.NoSuchObjectException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class TransactionAnalyser {

    private File inputFile;
    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

    public TransactionAnalyser(File inputFile) {
        this.inputFile = inputFile;
        this.transactions = this.writeTransactionsToList(inputFile);
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
                transactions.add(new Transaction(currentLine[0], this.parseStringToDate(currentLine[1]), Double.parseDouble(currentLine[2]), currentLine[3], TransactionTypes.valueOf(currentLine[4]), currentLine[5]));
            }
            inputFileReader.close();
        } catch (IOException iOException) {
            iOException.printStackTrace();
        } finally {
            return transactions;
        }
    }

    public void analyzeTransactionsByMerchant(String merchantName, String dateFrom, String dateTo) {
        Date parsedDateFrom = parseStringToDate(dateFrom);
        Date parsedDateTo = parseStringToDate(dateTo);
        if (parsedDateFrom.after(parsedDateTo)){
            throw new DateTimeException("Error! Incorrect date(s) value!");
        }
        ArrayList<Transaction> foundTransactions = new ArrayList<Transaction>();
        try {
            transactions.stream()
                    .filter(transaction -> merchantName.equals(transaction.getTransactionMerchant()))
                    .filter(transaction -> transactions.stream().noneMatch(transaction1 -> transaction1.getRelatedTransactionId().equals(transaction.getTransactionId())))
                    .filter(transaction -> transaction.getTransactionDate().after(parsedDateFrom)
                            && transaction.getTransactionDate().before(parsedDateTo))
                    .forEach(transaction -> foundTransactions.add(transaction));

        //System.out.println(foundTransactions.toString());
        System.out.printf("Number of transactions = %s\n", foundTransactions.size());
        System.out.printf("Average Transaction Value = %.2f", foundTransactions.stream().mapToDouble(Transaction::getTransactionAmount).average().getAsDouble());
        }
        catch (NoSuchElementException noSuchElementException){
            noSuchElementException.printStackTrace();
        }
    }

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
