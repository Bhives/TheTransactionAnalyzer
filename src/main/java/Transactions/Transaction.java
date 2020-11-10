package Transactions;

import java.util.Date;

public class Transaction {

    private String transactionId;
    private Date transactionDate;
    private float transactionAmount;
    private String transactionMerchant;
    private TransactionTypes transactionType;
    private String relatedTransactionId;

    public Transaction(String transactionId, Date transactionDate, float transactionAmount, String transactionMerchant, TransactionTypes transactionType, String relatedTransactionId) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.transactionAmount = transactionAmount;
        this.transactionMerchant = transactionMerchant;
        this.transactionType = transactionType;
        this.relatedTransactionId = relatedTransactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public float getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(float transactionAmount) {
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

    public String getRelatedTransactionId() {
        return relatedTransactionId;
    }

    public void setRelatedTransactionId(String relatedTransactionId) {
        this.relatedTransactionId = relatedTransactionId;
    }

    @Override
    public boolean equals(Object comparableObject) {
        if (this == comparableObject) return true;
        if (comparableObject==null||!(comparableObject instanceof Transaction)) return false;
        Transaction transaction = (Transaction ) comparableObject;
        return transactionId==transaction.getTransactionId()
                &&transactionDate==transaction.getTransactionDate()
                &&transactionAmount==transaction.getTransactionAmount()
                &&transactionMerchant==transaction.getTransactionMerchant()
                &&transactionType==transaction.getTransactionType()
                &&relatedTransactionId==transaction.getRelatedTransactionId();
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int result = 1;
        result = prime * result + transactionId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Transaction ID: " + transactionId
                + "; date/time: " + transactionDate
                + "; value: " + transactionAmount
                + "; merchant name: " + transactionMerchant
                + "; transaction type: " + transactionType
                + "; related transaction ID: " + relatedTransactionId;
    }
}
