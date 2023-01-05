package InputFileReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class InputFileReader {

    private final boolean readTestFile;
    private final String filePath;

    public InputFileReader(int day) {
        this.readTestFile = false;
        this.filePath = this.getFilePath(day);
    }

    public InputFileReader(int day, boolean read_test_file) {
        this.readTestFile = read_test_file;
        this.filePath = this.getFilePath(day);
    }

    private String getFilePath(int day) {
        String packagePath = "src/main/java/day_" + String.format("%02d", day);
        if (readTestFile) {
            return packagePath + "/input_test.txt";
        } else {
            return packagePath + "/input.txt";
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public ArrayList<String> readLines() throws Exception {
        File file = new File(this.filePath);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));) {
            ArrayList<String> lines = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }

            return lines;
        } catch (Exception e) {
            System.out.println("Failed to read input file");
            throw e;
        }
    }
}

class Main {
    public static void main(String[] args) throws Exception {
        InputFileReader fileReader = new InputFileReader(1, true);

        ArrayList<String> lines = fileReader.readLines();

        System.out.println(lines);
    }
}