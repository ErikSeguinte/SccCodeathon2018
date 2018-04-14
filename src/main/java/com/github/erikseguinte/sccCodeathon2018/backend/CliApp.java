package com.github.erikseguinte.sccCodeathon2018.backend;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;

public class CliApp {

    static ArrayList<Semester> semesters;

    public static void main(String[] args) {
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        HashSet<String> classesTaken = FileReader.getClassesFromResource("text/Students/w1234567.txt");
        Goal goal = new Goal(FileReader.getClassesFromResource("text/goals/ascs.txt"));

        HashSet<String> classes = goal.compareToRequirements(classesTaken);


        goal.setElectives(FileReader.getElectives("text/goals/ascs.txt"));


        // Only used to process the historical data files.
        // not used once jarred
        //writeSemesterObjects();

        readSemesterObjects();

       Student student = new Student();
       student.setClassesTaken(classesTaken);
       student.setGoal(goal);

    }

    public static void writeSemesterObjects() {
        semesters = new ArrayList<>();

        ArrayList<HashSet<String>> semesterSets = FileReader.readPastSchedules();

        for (HashSet<String> set : semesterSets) {
            Semester semester = new Semester(set);
            semesters.add(semester);

        }

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("src/main/resources/data/semesters"))) {
            outputStream.writeObject(semesters);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readSemesterObjects() {

        ClassLoader classLoader = CliApp.class.getClassLoader();

        try (ObjectInputStream inputStream = new ObjectInputStream(classLoader.getResourceAsStream("data/semesters"))) {
            semesters = (ArrayList<Semester>) inputStream.readObject();

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

    }


}
