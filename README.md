Finance Tracker

Finance Tracker is a simple console-based application that helps users track deposits, payments, and view financial reports. It saves all transactions to a CSV file and allows for easy viewing of transaction history
Home Screen

The home screen gives users the following options. The application continues running until the user chooses to exit:
D) Add Deposit – Prompts the user for deposit information and saves it to the CSV file.
P) Make Payment (Debit) – Prompts the user for debit information and saves it to the CSV file.
L) Ledger – Displays the ledger screen with all transaction history.
X) Exit – Exits the application.

Ledger

The ledger displays transactions with the newest entries first. Users have multiple options to filter and view transactions:
A) All – Displays all transactions.
D) Deposits – Displays only deposits (positive entries).
P) Payments – Displays only payments (negative entries).
R) Reports – Opens the reports screen to run predefined reports or custom searches:
1) Month To Date – Shows all transactions from the current month.
2) Previous Month – Shows all transactions from the previous month.
3) Year To Date – Shows all transactions from the current year.
4) Previous Year – Shows all transactions from the previous year.
5) Search by Vendor – Prompts the user for a vendor name and displays all transactions associated with that vendor.
0) Back – Returns to the Ledger page.
H) Home – Returns to the Home Screen.

Usage
Run the application.

Choose an option from the Home Screen.

Follow prompts to add deposits, make payments, or view transaction history.

Navigate through the ledger and reports to analyze your financial data.

Choose X) Exit to close the application.


created the file that holds the transactions. also created another class that holds the constructor and getters
created an arraylist that will hold the transaction and split it into appropriate parts
<img width="1470" height="956" alt="image" src="https://github.com/user-attachments/assets/99c42eda-634d-49aa-aef4-243671063966" />
added localdate into ConsoleHelper
screenshots of <img width="1470" height="956" alt="image" src="https://github.com/user-attachments/assets/d3d55c38-8258-4644-945e-d8279911a1e6" />
<img width="1470" height="956" alt="image" src="https://github.com/user-attachments/assets/746f7302-c730-4b87-94ad-2935ae36bc2c" /> <--- here i worked on a method that could catch any errors also used the same method to making a payment
<img width="1470" height="956" alt="image" src="https://github.com/user-attachments/assets/321af9c7-9ef8-4631-9cd8-2cbc1a1cd97a" />
^ here i fixed to format of time since it was saving with extra numbers after the seconds/ also fixed the way it was saving the amount with extra numbers after the decimal
<img width="1470" height="956" alt="image" src="https://github.com/user-attachments/assets/c2af713d-5027-48bc-8e20-9adc7f5f8d41" />
^ also made some improvements to be more specifit when asking the user about deposits
<img width="1470" height="956" alt="image" src="https://github.com/user-attachments/assets/61a6a657-b389-40e1-a8b2-293e58f776aa" />
^ used the same method with deposits and payments but chaged some words and the amount type
<img width="1470" height="956" alt="image" src="https://github.com/user-attachments/assets/ca7f2ef9-9866-45ee-a87e-a82e97c75397" />
^ created  a method to display recent transactions used array list since that is where my file is in
for (int i = transactions.size() - 1; i >= 0; i--) {
    System.out.println(transactions.get(i));
this means int i = transactions.size()-1 starts at the last item in the file thanks to -1, i >= 0 it will keep going in a loop displaying until it get to the last transaction 0, i-- means it decreases and shows the transactions moving backward
<img width="1470" height="956" alt="image" src="https://github.com/user-attachments/assets/90062dcb-a067-41fe-8105-f4a2bb8cd8d9" />
^ here i created another method similar to get displays but this one is for display payments, since payments are negative i had to modify the code 
<img width="1470" height="956" alt="image" src="https://github.com/user-attachments/assets/60360e14-6eb5-4c07-a288-1db01399d706" />
method to display transactions for month
<img width="1470" height="956" alt="image" src="https://github.com/user-attachments/assets/0a3f6972-6b86-4243-b5ba-71a51b99da93" />
^ created methods to be similar to this one to print previous month,year
