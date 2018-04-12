package com.github.erikseguinte.sccCodeathon2018.cli;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;

public class CliApp {

    public static void main(String[] args) {
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current relative path is: " + s);
        HashSet<String> classes = FileReader.getClassesFromResource("text/goals/ascs.txt");

        for (String thisClass:classes) {
            System.out.println(thisClass);
        }

    }
}
