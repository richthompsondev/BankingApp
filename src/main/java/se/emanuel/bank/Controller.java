/*
@Emanuel sleyman
2024-02-23
*/
package se.emanuel.bank;


import java.util.Scanner;

public class Controller {

    static Scanner input = new Scanner(System.in);
    Main main;

    public Controller(Main main) {
        this.main = main;
    }

    private void getOptions() {
        System.out.println(Color.CYAN + Color.UNDERLINE + Color.BOLD + "Menu:");
        System.out.println("1. DEPOSIT");
        System.out.println("2. WITHDRAW");
        System.out.println("3. SHOW ACCOUNT INFO");
        System.out.println("4. ADD A BANK CUSTOMER");
        System.out.println("5. SHOW TRANSACTIONS");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: " + Color.RESET);
    }

    public void controller() {

        String choice;

        do {
            getOptions();
            choice = input.nextLine();

            switch (choice) {
                case "0" -> {
                    System.out.println("GOODBYE...");
                    System.exit(0);
                }

                case "1"-> main.deposit();

                case "2" -> main.withdraw();

                case "3" -> main.showAccountInfo();

                case "4"-> main.addCustomer();

                case "5" -> main.showTransactions();

                default-> System.out.println(Color.RED + Color.BOLD + "Invalid choice. Please try again." + Color.RESET);

            }

        } while (true);
    }
}