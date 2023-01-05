package day_11.models;

import java.util.ArrayList;

public class Monkey {

    private final String monkeyIndex;
    private final ArrayList<Item> items;
    String[] operations;
    private final Long divisibleTestNumber;
    private final int monkeyIndexTestSuccess;
    private final int monkeyIndexTestFailure;

    private Monkey monkeyTestSuccess;
    private Monkey monkeyTestFailure;

    private long itemInspectCount = 0;
    private long lcm;

    public Monkey(ArrayList<String> lines) {
        monkeyIndex = lines.get(0).split(" ")[1].split(":")[0];
        items = getStartingItems(lines.get(1).split("Starting items: ")[1]);
        String operation = lines.get(2).split("Operation: new = ")[1];
        operations = operation.split(" ");
        divisibleTestNumber = Long.parseLong(lines.get(3).split("Test: divisible by ")[1]);
        monkeyIndexTestSuccess = Integer.parseInt(lines.get(4).split("If true: throw to monkey ")[1]);
        monkeyIndexTestFailure = Integer.parseInt(lines.get(5).split("If false: throw to monkey ")[1]);
    }

    private ArrayList<Item> getStartingItems(String itemsAsString) {
        ArrayList<Item> startingItems = new ArrayList<>();

        String[] unparsedItems = itemsAsString.split(", ");
        for (String item : unparsedItems) {
            startingItems.add(new Item(item));
        }
        return startingItems;
    }

    public void playRound(boolean useRelief) throws Exception {
        for (Item item : items) {
            itemInspectCount++;

            long itemValue = item.getItemValue(operations, useRelief, lcm);

            if (itemValue < 0) {
                throw new Exception("Number to high for this script!");
            }

            Monkey targetMonkey;
            if (itemValue % divisibleTestNumber == 0) {
                targetMonkey = monkeyTestSuccess;
            } else {
                targetMonkey = monkeyTestFailure;
            }
            targetMonkey.addItem(item);
        }

        items.clear();
    }

    private void addItem(Item item) {
        items.add(item);
    }

    public int getMonkeyIndexTestSuccess() {
        return monkeyIndexTestSuccess;
    }

    public int getMonkeyIndexTestFailure() {
        return monkeyIndexTestFailure;
    }

    public long getItemInspectCount() {
        return itemInspectCount;
    }

    public Long getDivisibleTestNumber() {
        return divisibleTestNumber;
    }

    public void setMonkeyTestSuccess(Monkey monkeyTestSuccess) {
        this.monkeyTestSuccess = monkeyTestSuccess;
    }

    public void setMonkeyTestFailure(Monkey monkeyTestFailure) {
        this.monkeyTestFailure = monkeyTestFailure;
    }

    public void setLcm(long lcm) {
        this.lcm = lcm;
    }
}
