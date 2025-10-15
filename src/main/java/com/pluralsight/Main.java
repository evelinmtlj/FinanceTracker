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
                    displayAll(ledger);
                    break;

                case "D":
                    displayDeposits(ledger);
                    break;

                case "P":
                    displayPayments(ledger);
                    break;

                case "R":
                    reports();
                    break;

                case "H":
                    System.out.println("Going back to home screen......");
                    return; //exits and goes back to hs

                default:
                    System.out.println("Invalid choice please try again!");
            }

        }
    }

    //methods for ledger menu
    public static void displayAll(ArrayList<Transaction> transactions)  {
        System.out.println("=======Displaying newest entries=======");
        for (int i = transactions.size()-1;i >=0;i--){
            System.out.println(transactions.get(i));
        }


    }

    public static void displayDeposits(ArrayList<Transaction>transactions) {
        System.out.println("====== ...Displaying Deposits =======");
        boolean deposits = false;

        for(int i = transactions.size()-1; i>=0;i--){
            Transaction t = transactions.get(i);

            if(t.getAmount()>0){
                System.out.println(t);
                deposits=true;
            }
        }

    }

    public static void displayPayments(ArrayList<Transaction>transactions) {
        System.out.println("=========... Displaying Payments ========");
        boolean payments = false;

        for(int i = transactions.size()-1; i>=0; i--){
            Transaction t = transactions.get(i);

            if(t.getAmount()<0){
                System.out.println(t);
                payments=  true;
            }
        }


    }

    public static void reports() {
       String reportsScreen = """
                                   *************************************
                                    *     Welcome to reports screen    *
                                    *************************************
               
                                   [1: Month to date]       [2: Previous month]
                                   [3: Year to date]        [4: Previous year]
                                   [5: Search by vendor]    [0: Back to ledger menu]
               """;

        String choice = ConsoleHelper.promptForString("Enter your choice").toUpperCase().trim();
        switch (choice){
            case "1":
               displayMonthToDate(ledger);
                break;
            case "2":
                displayPreviousMonth(ledger);
                break;
            case"3":
                displayYearToDate(ledger);
                break;
            case "4":
                //method
                break;
            case "5":
                searchByVendor(ledger);
                break;
            case "0":
                System.out.println("...Going back to ledger menu......");
                return;
            default:
                System.out.println("Invalid choice please try again!");
                break;
        }



    }

    //methods for reports menu

    private static void displayMonthToDate(ArrayList<Transaction>transactions){
        System.out.println("===== Month to date =======");

        LocalDate today = LocalDate.now(); //today's date
        LocalDate firstDay = today.withDayOfMonth(1); //gets first day of this month
        int count = 0; //keeps track of how many matches I found

        for(int i = transactions.size() -1; i>=0; i--){
            Transaction t = transactions.get(i);
            LocalDate date = t.getDate();

            //checks between the first of the month and today
            if(!date.isBefore(firstDay) && !date.isAfter(today)){
                System.out.println(t);
                count++;
            }
        }

       if (count==0){
           System.out.println("No records were found for this month.");
       }
    }

    private static void displayPreviousMonth(ArrayList<Transaction>transactions){
        System.out.println("========= Previous months =========");

        YearMonth currentMonth = YearMonth.now(); //gets current year and month
        YearMonth previousMonth = currentMonth.minusMonths(1); //goes back to last month

        int count = 0; //keep track of transactions found

        for(int i = transactions.size()-1; i>=0; i--){  //keeps looping backwards
            Transaction t = transactions.get(i); //gets one transaction
            LocalDate date = t.getDate(); // gets the date

            //converts transaction to format year month
            YearMonth transactionMonth = YearMonth.from(date);
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

    private static void displayYearToDate(ArrayList<Transaction>transactions){
        LocalDate today = LocalDate.now();
        //first day of the current year
        LocalDate startOfYear = LocalDate.of(today.getYear(), 1,1);

        //keep track
        int count = 0;
        for(int i = transactions.size()-1;i>=0; i--){
            Transaction t = transactions.get(i);

            LocalDate date = t.getDate();
            //check if transactions are between start of year and todays
        }
    }

    private static void displayPreviousYear(){

    }

    private static void searchByVendor(ArrayList<Transaction>transactions){
        String vendor = ConsoleHelper.promptForString("Enter the name of vendor you wish to search for");
        System.out.println("Transactions matching: " + vendor);

        boolean found = false; //checks if it matches

        for(Transaction t : transactions){
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


