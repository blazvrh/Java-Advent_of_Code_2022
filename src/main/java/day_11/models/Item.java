package day_11.models;

import java.util.ArrayList;

public class Item {

    private long value;

    public Item(String itemValue) {
        this.value = Long.parseLong(itemValue);
    }

    public long getItemValue(String[] operations, boolean useRelief, long lcm) throws Exception {
        long firstValue;
        long secondValue;
        String mathOperation = operations[1];

        if (operations[0].equals("old")) {
            firstValue = value;
        } else {
            firstValue = Long.parseLong(operations[0]);
        }

        if (operations[2].equals("old")) {
            secondValue = value;
        } else {
            secondValue = Long.parseLong(operations[2]);
        }

        if (mathOperation.equals("*")) {
            value = firstValue * secondValue;
        } else if (mathOperation.equals("+")) {
            value = firstValue + secondValue;
        } else {
            throw new Exception("Unhandled operation: '" + mathOperation + "'");
        }

        if (useRelief) {
            value = value / 3;
        } else {
            value = value % lcm;
        }

        return value;
    }

}
