package se.emanuel.bank;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "transaction", schema = "bank", catalog = "")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transactionid")
    private int transactionid;
    @Basic
    @Column(name = "personid", nullable = false, updatable = false)
    private Long personid;
    @Basic
    @Column(name = "deposit", nullable = false, updatable = true)
    private Double deposit;
    @Basic
    @Column(name = "withdraw", nullable = false, updatable = true)
    private Double withdraw;
    @Basic
    @Column(name = "transactiontime", nullable = false)
    private Timestamp transactiontime;

    public Transaction(long personid, Timestamp transactiontime, Double withdraw) {
        this.personid = personid;
        this.transactiontime = transactiontime;
        this.withdraw = withdraw;
    }

    public Transaction(long personid, Double deposit, Timestamp transactiontime) {
        this.personid = personid;
        this.transactiontime = transactiontime;
        this.deposit = deposit;
    }


    public Transaction() {

    }

    public Double getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(Double withdraw) {
        this.withdraw = withdraw;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double balance) {
        this.deposit = balance;
    }

    public Timestamp getTransactiontime() {
        return transactiontime;
    }

    public void setTransactiontime(Timestamp transactiontime) {
        this.transactiontime = transactiontime;
    }

    public int getId() {
        return transactionid;
    }

    public void setId(int id) {
        this.transactionid = id;
    }

    public long getPersonid() {
        return personid;
    }

    public void setPersonid(long personid) {
        this.personid = personid;
    }

    public double getAmount() {
        return deposit;
    }

    public void setAmount(double amount) {
        this.deposit = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (transactionid != that.transactionid) return false;
        if (personid != that.personid) return false;
        if (Double.compare(deposit, that.deposit) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        long result;
        long temp;
        result = transactionid;
        result = 31 * result + personid;
        temp = Double.doubleToLongBits(deposit);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return (int) result;
    }
}
