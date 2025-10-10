package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static ArrayList<Transaction> ledger = readRecordsFromFile();

       public static void main(String[] args) {




























    }

    //create the arraylist that holds the transactions file
    public static ArrayList<Transaction> readRecordsFromFile() {
        //array list go with file reader use try/catch
        ArrayList<Transaction> records = new ArrayList<>();

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
                Transaction t = new Transaction(date,time, description, vendor, amount);
                records.add(t);
            }
            bufferedReader.close();
            fileReader.close();

        } catch (IOException e) {
            System.out.println("Transaction file not found! ");

        }

        return records;
    }


    //Home Screen Menu methods
    public static void showHomeScreen(){
        String homeScreen = """ 
                                    *************************************
                                    *         Welcome to Apex Bank       *
                                    *************************************
                    
                                    [D: Add Deposit]      [P:Make Payment]
                    
                                    [L: Ledger]           [X:   Exit     ]
                    
                    
                    """;
        System.out.println(homeScreen);


    }

    public static void displayOptions(){
        String choice = ConsoleHelper.promptForString("Enter your choice").toUpperCase();

        switch (choice) {
            case "D":
                addDeposit();
                break;

            case "P":
                //method
                break;

            case "L":
                //method
                break;

            case "X":
                System.out.println("Exiting bank.....");
                return; //use this to exit bank and exit loop


            default:
                System.out.println("Invalid choice please try again!");
                break;

        }

    }
    public static void addDeposit() {

        try {
            LocalDate date = ConsoleHelper.promptForDate("Enter date of deposit use format yyyy-MM-dd");
            LocalTime time = ConsoleHelper.promptForTime("Enter time of deposit use format HH:mm:ss");
            String description = ConsoleHelper.promptForString("Enter description of deposit");
            String vendor = ConsoleHelper.promptForString("Enter your name ");
            double amount = ConsoleHelper.promptForFloat("Enter amount");
            // create the deposit
            Transaction newDeposit = new Transaction(date, time, description, vendor, amount);
            // add it into the file
            ledger.add(newDeposit);

            //make sure to save that transaction to the file, too!

            System.out.println("A new deposit has been added!");

        } catch (Exception e) {
            System.out.println("Deposit could not be added! please try again");

        }
    }

    public static void makePayment(){

    }

    public static void ledgerScreen(){

    }


    //Ledger methods
    public static String ledgerMenu(){
        String ledgerMenu = """
                                     *************************************
                                    *    Welcome to the Ledger Menu      *
                                     *************************************
                    
                                    [A: All]                 [D: Deposits]
                    
                                    [P: Payments]            [R: Reports ]
                                    
                                    [H: Home]
                
                """;
        System.out.println(ledgerMenu);
        return ConsoleHelper.promptForString("Enter your choice").toUpperCase();
    }


}