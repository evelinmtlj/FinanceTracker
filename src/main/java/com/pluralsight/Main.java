package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static ArrayList<Transaction> ledger = readRecordsFromFile();

    public static void main(String[] args) {

        homeScreen();
        displayOptions();


    }

    //create the arraylist that holds the transactions file
    public static ArrayList<Transaction> readRecordsFromFile() {
        //array list go with file reader use try/catch
        ArrayList<Transaction> transactions = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                //split the file by parts
                String[] parts = line.split("\\|");

                LocalDate date = LocalDate.parse(parts[0]);
                LocalTime time = LocalTime.parse(parts[1]);
                String description = parts[2];
                String vendor = parts[3];
                double amount = Double.parseDouble(parts[4]);
                // add this
                Transaction t = new Transaction(date, time, description, vendor, amount);
                transactions.add(t);
            }
            bufferedReader.close();
            fileReader.close();

        } catch (IOException e) {
            System.out.println("Transaction file not found! ");

        }

        return transactions;
    }

    // create a method to store it into files
    private static void addToFile(Transaction transaction) {
        try {
            FileWriter fw = new FileWriter("transactions.csv", true);//append true means it adds it to the end of file
            fw.write(transaction.toString() + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Could not save to file");
        }

    }


    //Method do display HS and options
    public static void homeScreen() {
        String homeScreen = """ 
                                *************************************
                                *         Welcome to Apex Bank       *
                                *************************************
                
                                [D: Add Deposit]      [P:Make Payment]
                
                                [L: Ledger]           [X:   Exit     ]
                
                
                """;
        System.out.println(homeScreen);


    }

    public static void displayOptions() {

        while (true) {
            String choice = ConsoleHelper.promptForString("Enter your choice").toUpperCase().trim();

            switch (choice) {
                case "D":
                    addDeposit();
                    break;

                case "P":
                    makePayment();
                    break;

                case "L":
                    ledgerScreen();
                    break;

                case "X":
                    System.out.println("Goodbye! Come back soon to Apex Bank.");
                    return;//use this to exit bank and exit loop


                default:
                    System.out.println("Invalid choice please try again!");
                    break;
            }
        }

    }

    //Home screen methods
    public static void addDeposit() {

        try {

            String description = ConsoleHelper.promptForString("Enter description of deposit (ex: paycheck, refunds, cash deposit) ");
            String vendor = ConsoleHelper.promptForString("Where is this deposit from (ex: employer,friend, refund from store)");
            double amount = ConsoleHelper.promptForFloat("Enter amount $");
            // Make sure amount is positive
            if (amount <= 0) {
                System.out.println("Please make sure your payment amount is above zero!");
                return;
            }
            //get current time and date

            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now();

            // create the deposit
            //here amount is positive since we are adding a deposit
            Transaction newDeposit = new Transaction(date, time, description, vendor, amount);
            // add it into the file
            ledger.add(newDeposit);
            addToFile(newDeposit);

            System.out.println("A new deposit has been added!");

        } catch (Exception e) {
            System.out.println("Deposit could not be added! please try again");
            System.out.println("Error:" + e.getMessage());

        }
    } //it's like you get paid from work and add it into your bank +money

    public static void makePayment() {
        try {
//making a payment is like negative amounts vs making deposits is like positive amounts
            String description = ConsoleHelper.promptForString("Enter description of payment (ex: rent,movie tickets,groceries) ");
            String vendor = ConsoleHelper.promptForString("Enter payee name ");
            double amount = ConsoleHelper.promptForFloat("Enter amount $");

            // check that deposit is always positive
            if (amount <= 0) {
                System.out.println("Please make sure your payment amount is above zero!");
                return;
            }

            //this will get current time instead of asking
            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now();

            // create the payment
            Transaction newPayment = new Transaction(date, time, description, vendor, -amount);
            // add it into the file
            ledger.add(newPayment);
            //save to file
            addToFile(newPayment);

            System.out.println("Payment confirmed!");

        } catch (Exception e) {
            System.out.println("Payment unsuccessful ~ Verify your information and try again");
            System.out.println("Error: " + e.getMessage());
        }
    } // (debit) ex: paying for groceries or any expenses -money


    //Ledger screen display and options
    public static void ledgerScreen() {
        String ledgerScreen = """
                                    *************************************
                                    *         Welcome to ledger screen   *
                                    *************************************
                
                                    [A: All ]             [D: Deposits ]
                
                                    [P: Payments]         [R:  Reports ]
                                         [H: Back to home screen ]
                """;
        System.out.println(ledgerScreen);

    }

    public static String ledgerMenu() {
        while (true) {
            String choice = ConsoleHelper.promptForString("Enter your choice").toUpperCase().trim();
            switch (choice) {
                case "A":
                    //method
                    break;

                case "D":
                    //method
                    break;

                case "P":
                    //method
                    break;

                case "R":
                    //method
                    break;

                case "H":
                    System.out.println("Going back to home screen......");
                    return;

                default:
                    System.out.println("Invalid choice please try again!");
                    break;
            }

        }
    }


    //methods for ledger menu
    public static void all() {

    }

    public static void deposits() {

    }

    public static void payments() {

    }

    public static void reports() {

    }

}


