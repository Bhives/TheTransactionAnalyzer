import Transactions.Transaction;
import Transactions.TransactionTypes;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class InputFileReader {

    private File inputFile;
    private ArrayList<Transaction> transactions= new ArrayList<Transaction>();

    public InputFileReader(File inputFile) {
        this.inputFile = inputFile;
        this.transactions=this.writeTransactionsToList(inputFile);
    }

    public ArrayList<Transaction> writeTransactionsToList(File inputFile) {
        try (Scanner fileScanner = new Scanner(inputFile)) {
            fileScanner.useLocale(Locale.ENGLISH);
            fileScanner.nextLine();
            ArrayList<Transaction> transactions = new ArrayList<Transaction>();
            fileScanner.useDelimiter(",");
            DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            while (fileScanner.hasNext()) {
                //System.out.println(fileScanner.next());
                transactions.add(new Transaction(fileScanner.next(), dateFormatter.parse(fileScanner.next()), fileScanner.nextFloat(), fileScanner.next(), TransactionTypes.valueOf(fileScanner.next()), fileScanner.next()));
            }
            return transactions;
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        } catch (InputMismatchException inputMismatchException) {
            inputMismatchException.printStackTrace();
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
