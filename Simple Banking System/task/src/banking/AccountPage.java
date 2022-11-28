package banking;

import static banking.BuildConnection.checkAccountExist;
import static banking.BuildConnection.closeConnection;
import static banking.HelperClass.chooseOption;
import static banking.HelperClass.printMenu;
import static banking.LuhnAlgorythm.checkLuhnAlgorythm;
import static banking.Main.scanner;

public class AccountPage {
    public static void accountMenuPage(long card) {
        String[] options = {    "1. Balance ",
                "2. Add income",
                "3. Do transfer",
                "4. Close account",
                "5. Log out",
                "0. Exit"
        };
        for (String s: options){
            System.out.println(s);
        }
        chooseAccountOption(card);
    }

    public static void chooseAccountOption(long card) {
        while (true) {
            int option = scanner.nextInt();
            switch(option) {
                case 1:
                    balance(card);
                    accountMenuPage(card);
                    break;
                case 2:
                    //Add Income
                    addIncome(card);
                    accountMenuPage(card);
                    break;
                case 3:
                    //Do transfer
                    doTransfer(card);
                    accountMenuPage(card);
                    break;
                case 4:
                    //Close an account
                    closeAccount(card);
                    printMenu();
                    break;
                case 5:
                    //Logout
                    logOut();
                    break;
                case 0:
                    //Exit
                    closeConnection();
                    exit();
                    break;
                default:
                    System.out.println("Illegal input! Please choose an option below");
            }
        }
    }

    public static void balance(long card) {
        BuildConnection.showBalance(card);
    }

    public static boolean checkLuhn(long card){
        return checkLuhnAlgorythm(card);
    }
    public static void transferMoney(long senderCard, long receiverCard, int amount){
        BuildConnection.doTransfer(senderCard, receiverCard, amount);
    }

    public static void logOut() {
        System.out.println("\nYou have successfully logged out! \n");
        printMenu();
        chooseOption();
    }
    public static void exit() {
        System.out.println("\nBye! \n");
        System.exit(0);
    }
    public static void addIncome(long card) {
        System.out.println("Enter income: ");
        int income = scanner.nextInt();
        BuildConnection.addIncome(card, income);
    }

    public static void doTransfer(long card) {
        System.out.println("Transfer");
        System.out.println("Enter card number:");
        checkTransferCondition(card);
    }

    public static void closeAccount(long card) {
        BuildConnection.closeAccount(card);
    }
    public static boolean accountExists(long card) {
        return checkAccountExist(card);
    }
    public static void checkTransferCondition(long card) {
        long cardNumber = scanner.nextLong();
        if (card != cardNumber) {
            if (checkLuhn(cardNumber) && accountExists(cardNumber)) {
                System.out.println("Enter how much money you want to transfer:");
                int transferAmount = scanner.nextInt();
                transferMoney(card, cardNumber, transferAmount);
            } else if (!checkLuhn(cardNumber)) {
                System.out.println("Probably you made a mistake in the card number. Please try again! \n");
                checkTransferCondition(card);
            } else if (!accountExists(cardNumber)) {
                System.out.println("Such a card does not exist. \n");
            }
        } else {
            System.out.println("You can't transfer money to the same account! \n");
        }
    }
}
