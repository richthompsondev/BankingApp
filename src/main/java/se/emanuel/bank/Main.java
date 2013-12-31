/*
@Emanuel sleyman
2024-02-23
*/
package se.emanuel.bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class Main implements CommandLineRunner {
    @Autowired
    public TransRepo transRepo;
    @Autowired
    public PersonRepo personRepo;

    Scanner input = new Scanner(System.in);

    static int[] intArray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    public Main() {

    }


    Controller controller = new Controller(this);

    public void run(String[] args) {
        controller.controller();
    }

    public void showTransactions() {
        System.out.print(Color.PURPLE + Color.BOLD + "ENTER YOUR PERSON-NUMBER: ");
        int personId = input.nextInt();
        input.nextLine();

        List<Transaction> overview = transRepo.findTransactionByPersonid(personId);

        if (!overview.isEmpty()) {
            for (Transaction s : overview) {
                System.out.println("TRANSACTION-ID:" + s.getId());
                System.out.println("DEPOSITS:" + s.getDeposit() + "KR");
                System.out.println("WITHDRAWS:" + s.getWithdraw() + "KR");
                System.out.println("TIME:" + s.getTransactiontime());
                System.out.println("_________________________________");
            }
        } else {
            System.out.println("SOMETHING WENT WRONG");
            return;
        }
    }

    public void deposit() {
        try {
            System.out.print(Color.PURPLE + Color.BOLD + "ENTER YOUR CARD-NUMBER: ");
            int cardid = input.nextInt();
            input.nextLine();

            Optional<Person> depositAccount = personRepo.findPersonByCardnumber(cardid);

            if (depositAccount.isPresent()) {
                Person person = depositAccount.get();

                    System.out.println("How much would you like to deposit?");
                    System.out.print("Amount: " + Color.RESET);
                    double amount = input.nextDouble();
                    input.nextLine();

                    double newBalance = person.getBalance() + amount;

                    Person p = new Person();
                    p.setName(person.getName());
                    p.setLastname(person.getLastname());
                    p.setCardnumber(person.getCardnumber());
                    p.setPersonid(person.getPersonid());
                    p.setBalance(newBalance);
                    p.setLastupdate(Timestamp.valueOf(LocalDateTime.now()));
                    personRepo.save(p);

                    Transaction t = new Transaction();
                    t.setWithdraw(0.0);
                    t.setDeposit(amount);
                    t.setPersonid(person.getPersonid());
                    t.setTransactiontime(Timestamp.valueOf(LocalDateTime.now()));
                    transRepo.save(t);

                    System.out.println("Transaction successful. Updated balance: " + newBalance);
            } else {
                System.out.println("ENTERED CARD IS NOT" + cardid + "VALID");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void withdraw() {

        try {
            System.out.print(Color.PURPLE + Color.BOLD + "ENTER YOUR CARD-NUMBER: ");
            int cardid = input.nextInt();
            input.nextLine();

            Optional<Person> depositAccount = personRepo.findPersonByCardnumber(cardid);

            if (depositAccount.isPresent()) {
                Person person = depositAccount.get();

                    System.out.println("How much would you like to withdraw?");
                    System.out.print("Amount: " + Color.RESET);
                    double amount = input.nextDouble();
                    input.nextLine();

                    double newBalance = person.getBalance() - amount;

                    Person p = new Person();
                    // Update the balance and last update time of the existing Person object
                    p.setBalance(newBalance);
                    p.setLastupdate(Timestamp.valueOf(LocalDateTime.now()));
                    p.setCardnumber(person.getCardnumber());
                    p.setPersonid(person.getPersonid());
                    p.setName(person.getName());
                    p.setLastname(person.getLastname());

                    // Save the updated Person object
                    personRepo.save(p);

                    // Create and save the Transaction object
                    Transaction t = new Transaction();
                    t.setWithdraw(0.0);
                    t.setDeposit(amount);
                    t.setPersonid(person.getPersonid());
                    t.setTransactiontime(Timestamp.valueOf(LocalDateTime.now()));
                    transRepo.save(t);

                    System.out.println("Transaction successful. Updated balance: " + newBalance);
                } else {
                    System.out.println("Entered card is not valid: " + cardid);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void addCustomer() {

        System.out.println("WHATS YOUR PERSON-ID?");
        System.out.print(">");
        int personId = input.nextInt();
        input.nextLine();

        List<Person> id = personRepo.findAll();
        if (id.equals(personId)) {
            System.out.println("PERSON ALREADY EXISTS");
        } else {
            System.out.print("YOUR NAME: ");
            String name = input.nextLine();
            System.out.print("YOUR LASTNAME: ");
            String lastname = input.nextLine();

            System.out.print("HOW MUCH WOULD YOU LIKE TO DEPOSIT?: ");
            double deposit = input.nextDouble();
            input.nextLine();

            Person p = new Person();

            p.setName(name);
            p.setLastname(lastname);
            p.setBalance(deposit);
            p.setPersonid(personId);
            p.setLastupdate(Timestamp.valueOf(LocalDateTime.now()));
            p.setCardnumber(createCardNumber());

            System.out.println("YOU HAVE BEEN ADDED INTO DATABSE");
            personRepo.save(p);
        }

    }

    private int createCardNumber() {
        int generatedNumber = 0;
        int[] uniqueNumber = generateUniqueCardNumber(intArray, 4);
        for (int i = 0; i < uniqueNumber.length; i++) {
            generatedNumber = generatedNumber * 10 + uniqueNumber[i];
        }
        System.out.println(Color.CYAN + Color.BOLD + "HERE IS YOUR CARDNUMBER DONT LOSE IT! " + generatedNumber + Color.RESET);

        return generatedNumber;
    }

    private int[] generateUniqueCardNumber(int[] intArray, int counter) {
        if (intArray.length < counter) {
            throw new IllegalArgumentException();
        }
        int[] result = new int[counter];
        Random random = new Random();
        int index = 0;

        while (index < counter) {
            int randomindex = random.nextInt(intArray.length);
            int randomNum = intArray[randomindex];

            boolean alreadyExist = false;
            for (int i = 0; i < index; i++) {
                if (result[i] == index) {
                    alreadyExist = true;
                    break;
                }
            }
            if (!alreadyExist) {
                result[index] = randomNum;
                index++;
            }
        }
        return result;
    }

    public void showAccountInfo() {
        System.out.print("Enter your personID: ");
        int id = input.nextInt();
        input.nextLine();
        List<Person> info = personRepo.findPersonByPersonid(id);
        if (info != null) {
            for (Person p : info) {
                System.out.println(Color.PURPLE + "NAME: " + p.getName());
                System.out.println("LASTNAME: " + p.getLastname());
                System.out.println("PERSON-ID: " + p.getPersonid());
                System.out.println("ACCOUNT BALANCE: " + p.getBalance() + " KR" + Color.RESET);

            }
        } else {
            System.out.println("SOMETHING WENT WRONG");
            return;
        }
    }
}

