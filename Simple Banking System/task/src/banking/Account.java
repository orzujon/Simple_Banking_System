package banking;

public class Account {
    private int bin;
    private int account;
    private int checkDigit;

    public Account(int bin, int account, int checkDigit) {
        this.bin = bin;
        this.account = account;
        this.checkDigit = checkDigit;
    }

    public int getBin() {
        return bin;
    }

    public void setBin(int bin) {
        this.bin = bin;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public int getCheckDigit() {
        return checkDigit;
    }

    public void setCheckDigit(int checkDigit) {
        this.checkDigit = checkDigit;
    }

    @Override
    public String toString() {
        return bin + "" + account + "" +
                checkDigit + "";
    }
}
