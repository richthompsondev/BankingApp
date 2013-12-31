package se.emanuel.bank;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class Person {
    @Id
    @Column(name = "personid", nullable = false)
    private Long personid;
    @Basic
    @Column(name = "name", nullable = false, length = 45)
    private String name;
    @Basic
    @Column(name = "lastname", nullable = false, length = 45)
    private String lastname;
    @Basic
    @Column(name = "lastupdate", nullable = false)
    private Timestamp lastupdate;
    @Basic
    @Column(name = "balance", nullable = false, precision = 0)
    private double balance;
    @Basic
    @Column(name = "cardnumber", nullable = false)
    private int cardnumber;

    public Person(long personid, String name, String lastname, double balance, Timestamp lastupdate) {
        this.personid = personid;
        this.name = name;
        this.lastname = lastname;
        this.balance = balance;
        this.lastupdate = lastupdate;

    }

    public Person(double balance){

        this.balance = balance;
    }

    public Person() {

    }

    public int getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(int cardnumber) {
        this.cardnumber = cardnumber;
    }

    public Timestamp getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Timestamp lastupdate) {
        this.lastupdate = lastupdate;
    }

    public long getPersonid() {
        return personid;
    }

    public void setPersonid(long personid) {
        this.personid = personid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (personid != person.personid) return false;
        if (Double.compare(balance, person.balance) != 0) return false;
        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        if (lastname != null ? !lastname.equals(person.lastname) : person.lastname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        long result;
        long temp;
        result = personid;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        temp = Double.doubleToLongBits(balance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return (int) result;
    }

    public Person orElse(Object o) {
        return null;
    }
}
