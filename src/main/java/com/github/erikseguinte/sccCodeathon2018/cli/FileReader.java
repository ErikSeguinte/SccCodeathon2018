package com.github.erikseguinte.sccCodeathon2018.cli;

import java.util.HashSet;
import java.util.Scanner;

public class FileReader {

    public static HashSet<String> getClassesFromResource(String filename){

        HashSet<String> classes = new HashSet<>();
        ClassLoader classLoader = FileReader.class.getClassLoader();

        // Try-with-resources
        try(Scanner scanner = new Scanner(classLoader.getResourceAsStream(filename))) {

            while(scanner.hasNextLine()){
                String thisClass = scanner.nextLine();

                thisClass = cleanUpString(thisClass);

                classes.add(thisClass);
            }
        }

        return classes;
    }

    private static String cleanUpString(String string){

        string = string.trim();

        //lower Case
        string = string.toLowerCase();

        // remove internal spaces
        string = string.replaceAll("\\s","");

        return string;
    }
}
