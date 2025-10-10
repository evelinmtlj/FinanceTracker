package com.pluralsight;

          public class transaction {

    private String date;
    private String time;
    private String description;
    private String vendor;
    private double amount;


              public transaction(String date, String time, String description, String vendor, double amount) {
                  this.date = date;
                  this.time = time;
                  this.description = description;
                  this.vendor = vendor;
                  this.amount = amount;
              }

              public String getDate() {
                  return date;
              }

              public String getDescription() {
                  return description;
              }

              public String getTime() {
                  return time;
              }

              public String getVendor() {
                  return vendor;
              }

              public double getAmount() {
                  return amount;
              }

              @Override
              public String toString() {
                  return "transaction{" +
                          "date='" + date + '\'' +
                          ", time='" + time + '\'' +
                          ", description='" + description + '\'' +
                          ", vendor='" + vendor + '\'' +
                          ", amount=" + amount +
                          '}';
              }
          }