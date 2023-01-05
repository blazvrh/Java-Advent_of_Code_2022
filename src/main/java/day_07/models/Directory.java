package day_07.models;

import java.util.HashMap;

public class Directory {

    private final String directoryName;
    private final Directory parentDirectory;
    private final HashMap<String, Directory> subDirectories = new HashMap<>();
    private final HashMap<String, File> files = new HashMap<>();
    private Integer dirSize = null;

    public Directory(String directoryName) {
        this.directoryName = directoryName;
        this.parentDirectory = null;
    }

    public Directory(String directoryName, Directory parentDirectory) {
        this.directoryName = directoryName;
        this.parentDirectory = parentDirectory;
    }

    public Directory getRoot() {
        if (this.parentDirectory == null) {
            return this;
        } else {
            return parentDirectory.getRoot();
        }

    }

    public Directory getParentDirectory() {
        return parentDirectory;
    }

    public Directory addSubDirectory(String name) {
        Directory subDirectory = new Directory(name, this);
        subDirectories.put(name, subDirectory);
        return subDirectory;
    }

    public boolean containsSubDirectory(String name) {
        return subDirectories.containsKey(name);
    }

    public Directory getSubDirectory(String name) {
        return subDirectories.get(name);
    }

    public void addFile(File file) {
        files.put(file.getName(), file);
    }

    public int getDirectorySize() {
        if (dirSize != null) {
            return dirSize;
        }

        int size = 0;
        for (File file : files.values()) {
            size += file.getFileSize();
        }

        for (Directory directory : subDirectories.values()) {
            size += directory.getDirectorySize();
        }

        dirSize = size;
        return size;
    }
}
