package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Class to hold customer details
class Customer {
    String name;
    String id;
    ArrayList<String> transactionHistory;
    double balance;

    // Constructor to initialize customer details
    Customer(String name, String id) {
        this.name = name;
        this.id = id;
        this.transactionHistory = new ArrayList<>();
        this.balance = 0;
    }

    // Method to add a transaction to the history
    void addTransaction(String transaction) {
        transactionHistory.add(transaction);
    }

    // Method to display the transaction history
    void displayTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            System.out.println("Transaction History:");
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
        System.out.println();
    }
}

public class BankingApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Customer> customerMap = new HashMap<>();
        Customer currentCustomer = null;

        while (true) {  // Infinite loop for continuous customer handling
            if (currentCustomer == null) {
                // Get customer details
                System.out.print("Enter your name: ");
                String customerName = scanner.nextLine();

                System.out.print("Enter your ID: ");
                String customerId = scanner.nextLine();

                // Check if customer exists
                currentCustomer = customerMap.get(customerId);
                if (currentCustomer == null) {
                    // Create a new customer object if not found
                    currentCustomer = new Customer(customerName, customerId);
                    customerMap.put(customerId, currentCustomer);
                } else {
                    // Update name if necessary (optional)
                    currentCustomer.name = customerName;
                }

                System.out.println("\nWelcome back, " + currentCustomer.name);
                System.out.println("Your ID: " + currentCustomer.id);
                System.out.println();
            }

            char option;
            do {
                System.out.println("A. Check Balance");
                System.out.println("B. Deposit");
                System.out.println("C. Withdraw");
                System.out.println("D. Previous Transaction");
                System.out.println("E. Exit to New Customer");
                System.out.print("Enter an option: ");
                option = scanner.next().toUpperCase().charAt(0);
                scanner.nextLine(); // Consume the newline
                System.out.println();

                switch (option) {
                    case 'A':
                        System.out.println("Balance = $" + currentCustomer.balance);
                        System.out.println();
                        break;

                    case 'B':
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        if (depositAmount > 0) {
                            currentCustomer.balance += depositAmount;
                            currentCustomer.addTransaction("Deposited: $" + depositAmount);
                            System.out.println("Amount deposited: $" + depositAmount);
                        } else {
                            System.out.println("Please enter a positive amount.");
                        }
                        System.out.println();
                        break;

                    case 'C':
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        if (withdrawAmount > 0) {
                            if (currentCustomer.balance >= withdrawAmount) {
                                currentCustomer.balance -= withdrawAmount;
                                currentCustomer.addTransaction("Withdrawn: $" + withdrawAmount);
                                System.out.println("Amount withdrawn: $" + withdrawAmount);
                            } else {
                                System.out.println("Insufficient Balance.");
                            }
                        } else {
                            System.out.println("Please enter a positive amount.");
                        }
                        System.out.println();
                        break;

                    case 'D':
                        currentCustomer.displayTransactionHistory();
                        break;

                    case 'E':
                        System.out.println("Exiting to new customer session...");
                        System.out.println("Thank you, " + currentCustomer.name + ", for using our services.\n");
                        currentCustomer = null; // Reset current customer for the next session
                        break;

                    default:
                        System.out.println("Invalid option! Please try again.");
                        System.out.println();
                        break;
                }
            } while (option != 'E');
        }
    }
}