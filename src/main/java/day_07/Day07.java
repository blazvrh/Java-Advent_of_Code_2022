package day_07;

import InputFileReader.InputFileReader;
import day_07.models.Directory;
import day_07.models.File;

import java.util.ArrayList;

public class Day07 {

    public static void main(String[] args) throws Exception {
        ArrayList<String> input_lines = new InputFileReader(7, false).readLines();
        ArrayList<Directory> directories = getDirectories(input_lines);

        System.out.println("Day 07");
        System.out.println("\tPart 01: " + part01(directories));
        System.out.println("\tPart 02: " + part02(directories));
    }

    private static ArrayList<Directory> getDirectories(ArrayList<String> inputLines) {
        ArrayList<Directory> directories = new ArrayList<>();

        Directory currentDirectory = null;
        for (String line : inputLines) {
            if (line.startsWith("$ ls")) {
                continue;
            }

            if (line.startsWith("$ cd")) {
                currentDirectory = mapDirectory(line, currentDirectory, directories);
            } else if (line.startsWith("dir")) {
                assert currentDirectory != null;
                mapSubDirectory(line, currentDirectory, directories);
            } else {
                assert currentDirectory != null;
                mapFile(line, currentDirectory);
            }
        }
        return directories;
    }

    private static Directory mapDirectory(String line, Directory currentDirectory, ArrayList<Directory> directories) {
        String directoryName = line.substring(5);
        Directory directory;

        if (directoryName.equals("/")) {
            directory = new Directory(directoryName);
            directories.add(directory);
            return directory;
        } else if (directoryName.equals("..")) {
            assert currentDirectory != null;
            return currentDirectory.getParentDirectory();
        } else {
            assert currentDirectory != null;
            if (currentDirectory.containsSubDirectory(directoryName)) {
                return currentDirectory.getSubDirectory(directoryName);
            } else {
                directory = currentDirectory.addSubDirectory(directoryName);
                directories.add(directory);
                return directory;
            }
        }
    }

    private static void mapSubDirectory(String line, Directory currentDirectory, ArrayList<Directory> directories) {
        String directoryName = line.substring(4);

        if (currentDirectory.containsSubDirectory(directoryName)) {
            return;
        }
        Directory directory = currentDirectory.addSubDirectory(directoryName);
        directories.add(directory);
    }

    public static void mapFile(String line, Directory currentDirectory) {
        String[] parsedLine = line.split(" ");
        int fileSize = Integer.parseInt(parsedLine[0]);
        String fileName = parsedLine[1];

        File file = new File(fileSize, fileName);
        currentDirectory.addFile(file);
    }

    private static int part01(ArrayList<Directory> directories) {
        int size = 0;
        for (Directory directory : directories) {
            int dirSize = directory.getDirectorySize();
            if (dirSize <= 100000) {
                size += dirSize;
            }
        }

        return size;
    }


    private static int part02(ArrayList<Directory> directories) {
        Directory root = directories.get(0).getRoot();

        int requiredSpace = 30_000_000;
        int totalSpace = 70_000_000;
        int freeSpace = totalSpace - root.getDirectorySize();
        int minFolderSize = requiredSpace - freeSpace;

        Directory selectedDirectory = null;

        for (Directory directory : directories) {
            if (directory.getDirectorySize() < minFolderSize) {
                continue;
            }

            if (selectedDirectory == null) {
                selectedDirectory = directory;
            } else if (selectedDirectory.getDirectorySize() > directory.getDirectorySize()) {
                selectedDirectory = directory;
            }

        }

        assert selectedDirectory != null;
        return selectedDirectory.getDirectorySize();
    }

}
