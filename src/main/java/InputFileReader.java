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
        try (CSVReader inputFileReader = new CSVReader(new FileReader(inputFile), ',', '"', 1)) {
            String[] currentLine = null;
            ArrayList<Transaction> transactions = new ArrayList<Transaction>();
            DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            while ((currentLine = inputFileReader.readNext()) != null) {
                //System.out.println(fileScanner.next());
                transactions.add(new Transaction(currentLine[0],
                        dateFormatter.parse(currentLine[1]),
                        Float.parseFloat(currentLine[2]),
                        currentLine[3],
                        TransactionTypes.valueOf(currentLine[4]),
                        currentLine[5]));
            }
            return transactions;
        } catch (IOException iOException) {
            iOException.printStackTrace();
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        //} catch (InputMismatchException inputMismatchException) {
        //    inputMismatchException.printStackTrace();
        } finally {
            System.out.println("Sorry, an error has occurred!");
            return null;
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
