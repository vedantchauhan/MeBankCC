package com.bean;
import com.opencsv.bean.CsvBindByPosition;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Bean class containing the fixed position of the columns.
 *
 * @author Vedant Chauhan
 */
public class TransactionRecord {

    @CsvBindByPosition(position = 0)
    private String transactionId;
    @CsvBindByPosition(position = 1)
    private String fromAccountId;
    @CsvBindByPosition(position = 2)
    private String toAccountId;
    @CsvBindByPosition(position = 3)
    private String createAt;
    @CsvBindByPosition(position = 4)
    private String amount;
    @CsvBindByPosition(position = 5)
    private String transactionType;
    @CsvBindByPosition(position = 6)
    private String relatedTransaction;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(String fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public String getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(String toAccountId) {
        this.toAccountId = toAccountId;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getRelatedTransaction() {
        return relatedTransaction;
    }

    public void setRelatedTransaction(String relatedTransaction) {
        this.relatedTransaction = relatedTransaction;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
