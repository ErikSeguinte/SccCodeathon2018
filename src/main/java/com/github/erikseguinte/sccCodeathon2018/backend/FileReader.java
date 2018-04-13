package com.github.erikseguinte.sccCodeathon2018.backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;

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

    public static HashSet<String> getClassesfromPath(String filename){

        HashSet<String> classes = new HashSet<>();

        // Try-with-resources
        try(Scanner scanner = new Scanner(new FileInputStream(filename))) {

            while(scanner.hasNextLine()){
                String thisClass = scanner.nextLine();

                thisClass = cleanUpString(thisClass);

                classes.add(thisClass);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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

    public static ArrayList<HashSet<String>> readPastSchedules(){

        HashSet<String> spring = new HashSet<>();
        HashSet<String> summer = new HashSet<>();
        HashSet<String> fall = new HashSet<>();


        try (Stream<Path> paths = Files.walk(Paths.get("src/main/resources/text/semesters"))) {
            paths
                    .filter(Files::isReadable)
                    .filter(Files::isRegularFile)
                    .forEach(path -> {
                        String fileName = path.getFileName().toString();

                        if (fileName.startsWith("cisp-sp")){
                          spring.addAll(getClassesfromPath(path.toString()));
                        } else if ( fileName.startsWith("cisp-su")) {
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
