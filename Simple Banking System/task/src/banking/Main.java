package banking;

import org.sqlite.SQLiteDataSource;

import java.util.Scanner;

import static banking.HelperClass.chooseOption;
import static banking.HelperClass.printMenu;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        String url = "jdbc:sqlite:" + args[1];
        BuildConnection.createTable(url, dataSource);
        printMenu();
        chooseOption();
    }
    /*4000003107606184*/
}