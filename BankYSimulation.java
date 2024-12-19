import java.io.*;
import java.util.*;

class Account {
    private String accountNumber;
    private String accountHolder;
    private double balance;

    public Account(String accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
            return true;
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
            return false;
        }
    }

    public boolean transfer(Account toAccount, double amount) {
        if (this.withdraw(amount)) {
            toAccount.deposit(amount);
            System.out.println("Transferred: " + amount + " to " + toAccount.getAccountNumber());
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "AccountNumber: " + accountNumber + ", AccountHolder: " + accountHolder + ", Balance: " + balance;
    }
}

class BankY {
    private Map<String, Account> accounts;

    public BankY() {
        accounts = new HashMap<>();
    }

    public void createAccount(String accountNumber, String accountHolder, double initialBalance) {
        if (accounts.containsKey(accountNumber)) {
            System.out.println("Account already exists.");
        } else {
            accounts.put(accountNumber, new Account(accountNumber, accountHolder, initialBalance));
            System.out.println("Account created: " + accountNumber);
        }
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public void listAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
        } else {
            System.out.println("Accounts:");
            for (Account account : accounts.values()) {
                System.out.println(account);
            }
        }
    }
}

public class BankYSimulation {
    public static void main(String[] args) {
        BankY bank = new BankY();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nBankY - Main Menu");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer Funds");
            System.out.println("5. List Accounts");
            System.out.println("6. Exit");

            System.out.print("Choose an option (1-6): ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter account number: ");
                    String accountNumber = scanner.nextLine();
                    System.out.print("Enter account holder name: ");
                    String accountHolder = scanner.nextLine();
                    System.out.print("Enter initial balance: ");
                    double initialBalance = Double.parseDouble(scanner.nextLine());
                    bank.createAccount(accountNumber, accountHolder, initialBalance);
                    break;
                case "2":
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextLine();
                    Account account = bank.getAccount(accountNumber);
                    if (account != null) {
                        System.out.print("Enter amount to deposit: ");
                        double amount = Double.parseDouble(scanner.nextLine());
                        account.deposit(amount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case "3":
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextLine();
                    account = bank.getAccount(accountNumber);
                    if (account != null) {
                        System.out.print("Enter amount to withdraw: ");
                        double amount = Double.parseDouble(scanner.nextLine());
                        account.withdraw(amount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case "4":
                    System.out.print("Enter source account number: ");
                    String fromAccountNumber = scanner.nextLine();
                    Account fromAccount = bank.getAccount(fromAccountNumber);
                    if (fromAccount != null) {
                        System.out.print("Enter destination account number: ");
                        String toAccountNumber = scanner.nextLine();
                        Account toAccount = bank.getAccount(toAccountNumber);
                        if (toAccount != null) {
                            System.out.print("Enter amount to transfer: ");
                            double amount = Double.parseDouble(scanner.nextLine());
                            fromAccount.transfer(toAccount, amount);
                        } else {
                            System.out.println("Destination account not found.");
                        }
                    } else {
                        System.out.println("Source account not found.");
                    }
                    break;
                case "5":
                    bank.listAccounts();
                    break;
                case "6":
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
