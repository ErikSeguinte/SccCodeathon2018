package com.github.erikseguinte.sccCodeathon2018.backend;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;

public class CliApp {

    public static void main(String[] args) {
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        HashSet<String> classesTaken = FileReader.getClassesFromResource("text/Students/w1234567.txt");
        Goal goal = new Goal(FileReader.getClassesFromResource("text/Students/w1235813.txt"));

        HashSet<String> classes = goal.compareToRequirements(classesTaken);

        for (String thisClass:classes) {
            System.out.println(thisClass);
        }

    }
}
