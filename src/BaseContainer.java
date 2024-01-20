package src;

import java.util.*;

/**
 * BaseContainer class contains number in desired base
 */
public class BaseContainer {
    private final int base;
    private final Map<Integer, Character> letterMap;
    private final boolean isNegative;
    private List<Integer> integerPart;
    private List<Integer> decimalPart;

    public BaseContainer(int base, int inputValue) {
        if (base < 2) throw new IllegalArgumentException("Base must be greater than 1");
        isNegative = inputValue < 0;
        inputValue = Math.abs(inputValue);
        integerPart = new ArrayList<>();
        decimalPart = new ArrayList<>();
        this.base = base;
        letterMap = new HashMap<>();
        createMapping();

        convertInteger(inputValue);
    }

    public BaseContainer(int base, double inputValue) {
        if (base < 2) throw new IllegalArgumentException("Base must be greater than 1");

        isNegative = inputValue < 0;
        inputValue = Math.abs(inputValue);
        integerPart = new ArrayList<>();
        decimalPart = new ArrayList<>();
        this.base = base;
        letterMap = new HashMap<>();

        createMapping();
        int intPart = (int) inputValue;
        convertInteger(intPart);
        convertDecimal(inputValue - intPart);
    }

    private void createMapping() {
        for (int i = 10; i < base + 10; ++i) {
            char letter = (char) ('A' + i - 10);
            letterMap.put(i, letter);
        }

    }

    private void convertInteger(int inputValue) {
        do {
            integerPart.add(inputValue % base);
            inputValue /= base;
        } while (inputValue != 0);

        Collections.reverse(integerPart);
    }

    private void convertDecimal(double inputValue) {
        while (inputValue != 0) {
            inputValue *= base;
            decimalPart.add((int) inputValue);
            inputValue -= (int) inputValue;
        }
    }
    @Override
    public String toString() {
        StringBuilder resultStringBuilder = new StringBuilder("(");
        if (isNegative) resultStringBuilder.append("1|");
        for (int each : integerPart) {
            if (each > 9) resultStringBuilder.append(letterMap.get(each));
            else resultStringBuilder.append(each);
        }
        if (!(decimalPart.isEmpty())) {
            resultStringBuilder.append("|");
            for (int each : decimalPart) {
                if (each > 9) resultStringBuilder.append(letterMap.get(each));
                else resultStringBuilder.append(each);
            }
        }
        resultStringBuilder.append(")").append(base);

        return resultStringBuilder.toString();
    }

    public int convertToBaseTenInteger() {
        int result = 0;
        for (int i = 0; i < integerPart.size(); ++i) {
            result += integerPart.get(i) * (int) Math.pow(base, integerPart.size() - i - 1);
        }
        return isNegative ? -result : result;
    }

    public double convertToBaseTenDouble() {
        double result = Math.abs(convertToBaseTenInteger());
        for (int i = 0; i < decimalPart.size(); i++) {
            result += decimalPart.get(i) * Math.pow(base, -(i + 1));
        }
        return isNegative ? -result : result;
    }
}
