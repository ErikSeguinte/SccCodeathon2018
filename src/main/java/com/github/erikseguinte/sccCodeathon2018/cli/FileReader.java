package com.github.erikseguinte.sccCodeathon2018.cli;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {

    public static ArrayList<String> getClasses(String filename){

        ArrayList<String> classes = new ArrayList<>();
        ClassLoader classLoader = FileReader.class.getClassLoader();

        // Try-with-resources
        try(Scanner scanner = new Scanner(classLoader.getResourceAsStream(filename))) {

            while(scanner.hasNextLine()){

                String thisClass = scanner.nextLine().trim();

                //lower Case
                thisClass = thisClass.toLowerCase();

                // remove internal spaces
                thisClass = thisClass.replaceAll("\\s","");


                classes.add(thisClass);
            }
        }

        return classes;
    }
}
