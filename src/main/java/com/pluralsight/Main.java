package com.pluralsight;

import java.io.*; //includes BufferedReader,FileReader,FileWrite,IO ex
import java.time.*; //includes LocalDate,LocalTime
import java.util.ArrayList;


public class Main {

    public static ArrayList<Transaction> ledger = readRecordsFromFile();

    public static void main(String[] args) {

        homeScreenMenu();

    }
    //create the arraylist that holds the transactions file
    public static ArrayList<Transaction> readRecordsFromFile() {
        //array list go with file reader use try/catch
        ArrayList<Transaction> transactions = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            //skips the first line
            bufferedReader.readLine();

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
    //--------Home Screen---------------
    public static void homeScreenMenu() {

        String homeScreen = """ 
                                *************************************
                                *         Welcome to Apex Bank       *
                                *************************************
                
                                [D: Add Deposit]      [P:Make Payment (debit)]
                
                                [L: Ledger]           [X:   Exit     ]
                
                
                """;

        while (true) { //keeps the loop going

            System.out.println(homeScreen);

            String choice = ConsoleHelper.promptForString("Enter your choice").toUpperCase().trim();

            switch (choice) {
                case "D":
                    addDeposit();
                    break;

                case "P":
                    makePayment();
                    break;

                case "L":
                    ledgerMenu();
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

    //******** HOME SCREEN METHODS *********
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
            System.out.println(newDeposit);

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
            System.out.println(newPayment);

        } catch (Exception e) {
            System.out.println("Payment unsuccessful ~ Verify your information and try again");
            System.out.println("Error: " + e.getMessage());
        }
    } // (debit) ex: paying for groceries or any expenses -money
    //--------Ledger--------------
    public static void ledgerMenu() {

        String ledgerScreen = """
                                    *************************************
                                    *         Welcome to ledger screen   *
                                    *************************************
                                      Choose what you would like to display
                
                                    [A: All]             [D: Deposits ]
                
                                    [P: Payments]         [R:  Reports ]
                                         [H: Back to home screen ]
                """;

        while (true) {

            System.out.println(ledgerScreen);
            String choice = ConsoleHelper.promptForString("Enter your choice").toUpperCase().trim();

            switch (choice) {
                case "A":
                    displayAll();
                    break;

                case "D":
                    displayDeposits();
                    break;

                case "P":
                    displayPayments();
                    break;

                case "R":
                    reportsMenu();
                    break;

                case "H":
                    System.out.println("Going back to home screen......");
                    return; //exits and goes back to hs

                default:
                    System.out.println("Invalid choice please try again!");
            }

        }
    }

    //******* LEDGER METHODS ************
    public static void displayAll()  {
        System.out.println("=======Displaying newest entries=======");
        for (int i = ledger.size()-1;i >=0;i--){
            System.out.println(ledger.get(i));
        }


    }

    public static void displayDeposits() {
        System.out.println("====== ...Displaying Deposits =======");
        int count = 0;

        for(int i = ledger.size()-1; i>=0;i--){
            Transaction t = ledger.get(i);

            if(t.getAmount()>0){
                System.out.println(t);
                deposits=true;
            }
        }

    }

    public static void displayPayments() {
        System.out.println("=========... Displaying Payments ========");

        int count = 0;

            if(t.getAmount()<0){
                System.out.println(t);
                payments=  true;
            }
        }


    }

     // ----------Reports--------------
    public static void reportsMenu() {
       String reportsScreen = """
                                   *************************************
                                    *     Welcome to reports screen    *
                                    *************************************
               
                                   [1: Month to date]       [2: Previous month]
                                   [3: Year to date]        [4: Previous year]
                                   [5: Search by vendor]    [0: Back to ledger menu]
               """;
        while (true) {
            System.out.println(reportsScreen);
            String choice = ConsoleHelper.promptForString("Enter your choice").toUpperCase().trim();


            switch (choice) {
                case "1":
                    displayMonthToDate();
                    break;
                case "2":
                    displayPreviousMonth();
                    break;
                case "3":
                    displayYearToDate();
                    break;
                case "4":
                    displayPreviousYear();
                    break;
                case "5":
                    searchByVendor();
                    break;
                case "0":
                    System.out.println("...Going back to ledger menu......");
                    return;
                default:
                    System.out.println("Invalid choice please try again!");
                    break;
            }

        }

    }

    // ********* REPORTS METHODS ***************

    private static void displayMonthToDate(){
        System.out.println("===== Month to date =======");

        LocalDate today = LocalDate.now(); //today's date
        LocalDate firstDay = today.withDayOfMonth(1); //gets first day of this month
        int count = 0; //keeps track of how many matches I found

        for(Transaction t : ledger){
            LocalDate date = t.getDate();

            //checks if transaction is from this month
            if(!date.isBefore(firstDay)){
                System.out.println(t);
                count++;
            }
        }

       if (count==0){
           System.out.println("No records were found for this month.");
       }
    }

    private static void displayPreviousMonth(){
        System.out.println("========= Previous month =========");

        YearMonth previousMonth = YearMonth.now().minusMonths(1); //goes back to last month

        int count = 0; //keep track of transactions found

        for(Transaction t : ledger){
            YearMonth transactionMonth = YearMonth.from(t.getDate());

            //checks it transaction happened in previous month
            if(transactionMonth.equals(previousMonth)) {
                System.out.println(t);
                 count++; //adds one transaction
            }
        }
        if (count ==0){
            System.out.println("No records from the previous month!");
        }


    }

    private static void displayYearToDate(){
        System.out.println("======= Year to date =========");

        //first day of the current year ex january 1,2025
        LocalDate startOfYear = LocalDate.of(LocalDate.now().getYear(),1,1);

        //keep track
        int count = 0;
        //loops through all transactions
        for(Transaction t: ledger){
            if(!t.getDate().isBefore(startOfYear)){
                System.out.println(t);
                count++;
            }
        }
        if (count==0){
            System.out.println("No records found for the current year");
        }
    }

    private static void displayPreviousYear(){
        System.out.println("======= Previous Year =======");
       int lastYear = LocalDate.now().getYear()-1;
       int count = 0;

       for(Transaction t: ledger) {
           if(t.getDate().getYear() == lastYear) {
               System.out.println(t);
               count++;
           }
       }
      if(count==0){
          System.out.println("No records found from previous year");
      }
    }

    private static void searchByVendor(){
        String vendor = ConsoleHelper.promptForString("Enter the name of vendor you wish to search for");
        System.out.println("Transactions matching: " + vendor);

        boolean found = false; //checks if it matches

        for(Transaction t : ledger){
            if(t.getVendor().toLowerCase().contains(vendor.toLowerCase())){
                System.out.println(t);
                found = true; //check if we found one match at least
            }
        }
            if (!found){
                System.out.println("No transactions found matching:" + vendor);
            }

    }

}


