package banking;
import java.util.*;

import static banking.AccountPage.accountMenuPage;
import static banking.BuildConnection.closeConnection;
import static banking.LuhnAlgorythm.luhnAlgorythm;

public class HelperClass extends Main{

    private static int bin = 400000;

    public static void printMenu(){
        String[]menu={  "1. Create an account",
                        "2. Log into account",
                        "0. Exit"};

        for (String s : menu) {
            System.out.println(s);
        }
    }

    public static void chooseOption(){
        while(true) {
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    createAccount();
                    printMenu();
                    break;
                case 2:
                    logIn();
                    printMenu();
                    break;
                case 0:
                    closeConnection();
                    System.exit(0);
                    break;
                default:
                    System.out.println("You have entered an incorrect value, please choose from below!");
                    printMenu();
                    break;
            }
        }
    }

    public static void logIn() {
        System.out.println("\nEnter your card number: ");
        long cardNumber = scanner.nextLong();
        System.out.println("Enter your PIN: ");
        int pin = scanner.nextInt();
        loginCheck(cardNumber, pin);
    }

    public static void createAccount() {
        System.out.println("\nYour card has been created");
        System.out.println("Your card number:");
        int accountNumber = generateAccountNumber();
        Account a1 = newCard(bin, accountNumber, generateCheckSum(bin, accountNumber));
        System.out.println(a1);
        System.out.println("Your card PIN:");
        int pin = newPin();
        System.out.println(pin + "\n");
        BuildConnection.insertDetails(a1, pin);
    }
    public static void loginCheck(long cardNumber, int pinNumber) {
        boolean check = BuildConnection.loginCheck(cardNumber, pinNumber);
        if (check) {
            System.out.println("\nYou have successfully logged in! \n");
            accountMenuPage(cardNumber);
        } else {
            System.out.println("\nWrong card number or PIN! \n");
        }

    }

    public static int newPin(){
        Random random = new Random();
        return Integer.parseInt(String.format("%04d", random.nextInt(9000) + 1000));
    }
    public static Account newCard(int bin, int account, int checkSum){
        return new Account(bin, account, checkSum);
    }

    public static int generateAccountNumber() {
        Random random = new Random();
        return Integer.parseInt(String.format("%09d", random.nextInt(900000000) + 99999999));
    }


    public static int generateCheckSum(int bin, int accountNumber) {
        return luhnAlgorythm(bin, accountNumber);
    }
}
