package day_10.models;

import java.util.HashMap;

public class Cpu {

    private int cycle = 0;
    private int xValue = 1;

    private HashMap<Integer, Integer> cycleMap = new HashMap<>();

    public void executeInstruction(Instruction instruction) {
        cycle++;
        cycleMap.put(cycle, xValue);

        if (instruction.isNoop()) {
            return;
        }

        cycle++;
        cycleMap.put(cycle, xValue);
        xValue += instruction.getAddX();
    }

    public int getSignalSum() {
        int signalStrength = 0;
        for (int i = 20; i < cycleMap.size(); i += 40) {
            signalStrength += cycleMap.get(i) * i;
        }
        return signalStrength;
    }

    public String renderImage() {
        StringBuilder output = new StringBuilder("\n\t\t");
        for (int i = 0; i < cycleMap.size() / 40; i++) {
            output.append(renderRow(i)).append("\n\t\t");
        }
        return output.toString();
    }

    private String renderRow(int rowIndex) {
        StringBuilder output = new StringBuilder();

        for (int pixelIndex = 0; pixelIndex < 40; pixelIndex++) {
            cycle = 40 * rowIndex + pixelIndex + 1;
            int spritePosition = cycleMap.get(cycle);

            output.append(getPixelType(pixelIndex, spritePosition));
        }

        return output.toString();
    }

    private String getPixelType(int pixelIndex, int spritePosition) {
        if (spritePosition - 1 <= pixelIndex && spritePosition + 1 >= pixelIndex) {
            return "#";
        } else {
            return ".";
        }
    }
}
