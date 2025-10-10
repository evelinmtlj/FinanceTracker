package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
                    public static void main(String[] args) { //
                       // static Scanner scanner;
                       // static ArrayList<transaction> records;


        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

                   }





                   //create the arraylist that holds the transactions

       public  static  ArrayList<transaction> readRecordsFromFile() {
           //array list go with file reader use try/catch
           ArrayList<transaction> records = new ArrayList<>();

           try {
               FileReader fileReader = new FileReader("transactions.csv");
               BufferedReader bufferedReader = new BufferedReader(fileReader);

               String line;

               while ((line = bufferedReader.readLine()) != null) {
                   //split the file by parts
                   String[] parts = line.split("\\|");

                   String date = parts[0];
                   String time = parts[1];
                   String description = parts[2];
                   String vendor = parts[3];
                   double amount = Double.parseDouble(parts[4]);
                   // add this
                   transaction t = new transaction(date, time, description, vendor, amount);
                   records.add(t);
               }

           } catch (IOException e) {
               System.out.println("Transaction file not found! ");

           }

         return records;
       }

}