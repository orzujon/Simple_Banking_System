package banking;

import java.util.Arrays;

public class LuhnAlgorythm extends HelperClass{

    public static int luhnAlgorythm(int bin, int account) {
        String numberOne = Long.toString(bin);
        String numberTwo = Long.toString(account);
        String fullNumber = numberOne + numberTwo;
        long mergedNumber = Long.parseLong(fullNumber);

        return findCheckNumber(sumOfArray(subtractNine(doubleThirdIndex(convertToArray(mergedNumber)))));
    }

    public static int[] convertToArray(long number) {
        String temp = Long.toString(number);
        int[] numberArray = new int[temp.length()];
        for (int i = 0; i < temp.length(); i++) {
            numberArray[i] = temp.charAt(i) - '0';
        }
        return numberArray;
    }

    public static int[] doubleThirdIndex(int[] convertToArray) {
        int index = 1;
        for (int i = 0; i < convertToArray.length; i++) {
            if (index % 2 != 0) {
                convertToArray[i] = convertToArray[i] * 2;
                index++;
            } else {
                index++;
                continue;
            }
        }
        return convertToArray;
    }

    public static int[] subtractNine(int[] doubleThirdIndex) {
        for (int i = 0; i < doubleThirdIndex.length; i++){
            if (doubleThirdIndex[i] > 9) {
                doubleThirdIndex[i] = doubleThirdIndex[i] - 9;
            }
        }
        return doubleThirdIndex;
    }

    public static int sumOfArray(int[] subtractNine) {
        int sum = 0;
        for (int i = 0; i < subtractNine.length; i++) {
            sum = sum + subtractNine[i];
        }
        return sum;
    }

    public static int findCheckNumber(int sumOfArray) {
        if (sumOfArray % 10 == 0) {
            return 0;
        } else {
            int checkSum = 10 - (sumOfArray % 10);
            return checkSum;
        }
    }

    public static int[] removeLastNumber(int[] convertToArray) {
        int[] newArray = Arrays.copyOf(convertToArray, convertToArray.length-1);
        return newArray;
    }

    public static boolean checkLuhnAlgorythm(long card) {
        int checkSum = findCheckNumber(sumOfArray(subtractNine(doubleThirdIndex(removeLastNumber(convertToArray(card))))));
        int[] temp = convertToArray(card);
        int lastNumber = temp[temp.length-1];
        if (checkSum == lastNumber) {
            return true;
        } else {
            return false;
        }
    }

}

