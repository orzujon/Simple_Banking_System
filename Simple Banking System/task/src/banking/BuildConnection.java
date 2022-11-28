package banking;

import org.sqlite.SQLiteDataSource;

import java.sql.*;

import static java.sql.DriverManager.getConnection;

public class BuildConnection {

    private static Connection con;

    public static Connection establishConnection(String url, SQLiteDataSource dataSource) {
        try {
            con = getConnection(url);

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return con;
    }

    public static void createTable(String url, SQLiteDataSource dataSource) {
        con = establishConnection(url, dataSource);
        try {
            PreparedStatement pst = con.prepareStatement("CREATE TABLE IF NOT EXISTS card(id INTEGER PRIMARY KEY, number VARCHAR, pin VARCHAR, balance INTEGER)");

            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertDetails(Account accountNumber, int pin) {
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO card(number, pin, balance) VALUES (?,?,?)");
            pst.setString(1, String.valueOf(accountNumber));
            pst.setString(2, String.valueOf(pin));
            pst.setInt(3, 0);

            int i = pst.executeUpdate();
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public static boolean loginCheck(long cardNumber, int pin) {
        boolean check = false;
        try {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM card WHERE number = ? and pin = ?");
            pst.setString(1, String.valueOf(cardNumber));
            pst.setString(2, String.valueOf(pin));
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                check = true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return check;
    }

    public static void showBalance(long card) {
        long balance = 0;
        try {
            PreparedStatement pst = con.prepareStatement("SELECT balance FROM card WHERE number = ?");
            pst.setLong(1, card);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                balance = rs.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\nBalance:" + balance + "\n");
    }

    public static void addIncome(long card, int depositAmount) {
        try {
            PreparedStatement pst = con.prepareStatement("UPDATE card SET balance = balance + ? WHERE number = ?");
            pst.setLong(2, card);
            pst.setInt(1, depositAmount);
            int i = pst.executeUpdate();
            if (i > 0) {
                System.out.println("Income was added!");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public static boolean checkAccountExist(long card) {
        boolean found = false;
        try {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM card WHERE number = ?");
            pst.setLong(1, card);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                found = true;
            }
        }  catch (SQLException e) {
            System.out.println(e);
        }
        return found;
    }

    public static void doTransfer(long senderCard, long receiverCard, int amount) {

        try {
            con.setAutoCommit(false);
            Savepoint savepoint = con.setSavepoint();
            PreparedStatement pst = con.prepareStatement("UPDATE card SET balance = balance - ? WHERE number = ?");
            pst.setLong(2, senderCard);
            pst.setInt(1, amount);
            int i = pst.executeUpdate();

            PreparedStatement pst2 = con.prepareStatement("UPDATE card SET balance = balance + ? WHERE number = ?");
            pst2.setLong(2, receiverCard);
            pst2.setInt(1, amount);
            int x = pst2.executeUpdate();

            PreparedStatement pst3 = con.prepareStatement("SELECT balance FROM card WHERE number = ?");
            pst3.setLong(1, senderCard);
            ResultSet rs = pst3.executeQuery();
            while(rs.next()) {
                if (rs.getInt(1) < 0) {
                    System.out.println("Not enough money! \n");
                    con.rollback(savepoint);
                } else {
                    System.out.println("Success!");
                    con.commit();
                }
            }
            } catch (SQLException e) {
                System.out.println(e);
        }
    }

    public static void closeAccount(long card) {
        try {
            con.setAutoCommit(false);
            Savepoint savepoint = con.setSavepoint();

            PreparedStatement pst = con.prepareStatement("DELETE FROM card WHERE number = ?");
            pst.setLong(1, card);
            int i = pst.executeUpdate();
            if (i > 0) {
                System.out.println("The account has been closed! \n");
                con.commit();
            } else {
                con.rollback(savepoint);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void closeConnection() {
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
