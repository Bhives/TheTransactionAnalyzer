package Transactions;

import java.util.Date;

public class TransactionRecord {

    private int transactionId;
    private Date transactionDate;
    private int transactionAmount;
    private String transactionMerchant;
    private TransactionTypes transactionType;

    public TransactionRecord(int transactionId, Date transactionDate, int transactionAmount, String transactionMerchant, TransactionTypes transactionType) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.transactionAmount = transactionAmount;
        this.transactionMerchant = transactionMerchant;
        this.transactionType = transactionType;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(int transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionMerchant() {
        return transactionMerchant;
    }

    public void setTransactionMerchant(String transactionMerchant) {
        this.transactionMerchant = transactionMerchant;
    }

    public TransactionTypes getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionTypes transactionType) {
        this.transactionType = transactionType;
    }

    @Override
    public String toString(){
        return"Transacion №"+transactionId
                +"; date/time: "+transactionDate
                +"; value: "+transactionAmount
                +"; merchant name: "+transactionMerchant
                +"; transacion type: "+transactionType;
    }
}
