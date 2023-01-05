package day_07.models;

public class File {
    private final int size;
    private final String name;

    public File(int size, String name) {
        this.size = size;
        this.name = name;
    }

    public int getFileSize() {
        return size;
    }

    public String getName() {
        return name;
    }
}
