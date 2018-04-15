package com.github.erikseguinte.sccCodeathon2018.backend;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.stream.Stream;

public class FileReader {


    public static HashSet<String> getClassesFromResource(String filename) {

        ClassLoader classLoader = FileReader.class.getClassLoader();

        HashSet<String> classes = null;
        // Try-with-resources
        try (Scanner scanner = new Scanner(classLoader.getResourceAsStream(filename))) {
            classes = scan(scanner);
        }

        return classes;
    }

    public static HashSet<String> scan(Scanner scanner) {

        HashSet<String> classes = new HashSet<>();

        while (scanner.hasNextLine()) {
            String thisClass = scanner.nextLine();

            if (thisClass.equalsIgnoreCase("A minimum of 6 units from the following:")) {
                break;
            }
            thisClass = cleanUpString(thisClass);

            if (thisClass.startsWith("CIS")) {
                classes.add(thisClass);
            }
        }

        return classes;

    }

    public static HashSet<String> getElectives(String filename) {

        ClassLoader classLoader = FileReader.class.getClassLoader();

        HashSet<String> classes = new HashSet<>();
        // Try-with-resources
        try (Scanner scanner = new Scanner(classLoader.getResourceAsStream(filename))) {

            boolean start = false;
            while (scanner.hasNextLine()) {
                String thisClass = scanner.nextLine();

                if (!start) {
                    if (thisClass.equalsIgnoreCase("A minimum of 6 units from the following:")){
                        start = true;
                    }
                } else {
                    thisClass = cleanUpString(thisClass);

                    if (thisClass.startsWith("CIS")) {
                        classes.add(thisClass);
                    }
                }
            }
        }

        return classes;
    }

    public static HashSet<String> getClassesfromPath(String filename) {
        HashSet<String> classes = null;

        // Try-with-resources
        try (Scanner scanner = new Scanner(new FileInputStream(filename))) {
            classes = scan(scanner);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return classes;
    }

    private static String cleanUpString(String string) {

        string = string.trim();
        string = string.toUpperCase();
        string = string.replaceAll("\\s", "");

        return string;
    }

    public static ArrayList<HashSet<String>> readPastSchedules() {

        HashSet<String> spring = new HashSet<>();
        HashSet<String> summer = new HashSet<>();
        HashSet<String> fall = new HashSet<>();


        try (Stream<Path> paths = Files.walk(Paths.get("src/main/resources/text/semesters"))) {
            paths
                    .filter(Files::isReadable)
                    .filter(Files::isRegularFile)
                    .forEach(path -> {
                        String fileName = path.getFileName().toString();

                        if (fileName.startsWith("cisp-sp")) {
                            spring.addAll(getClassesfromPath(path.toString()));
                        } else if (fileName.startsWith("cisp-su")) {
                            summer.addAll(getClassesfromPath(path.toString()));
                        } else {
                            fall.addAll(getClassesfromPath(path.toString()));
                        }

                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<HashSet<String>> semesters = new ArrayList<>();
        semesters.add(spring);
        semesters.add(summer);
        semesters.add(fall);


        return semesters;
    }
}
