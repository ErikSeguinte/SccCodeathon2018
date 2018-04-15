package com.github.erikseguinte.sccCodeathon2018.backend;

import java.util.HashSet;

public class Goal {
    private HashSet<String> requiredClasses;
    private HashSet<String> electives;


    public Goal(HashSet<String> requiredClasses) {
        this.requiredClasses = requiredClasses;
        processCisp400And401();
    }

    public HashSet<String> getElectives() {
        return electives;
    }

    public void processCisp400And401() {
        if (requiredClasses.contains("CISP400CISP401")){
            requiredClasses.remove("CISP400CISP401");
            requiredClasses.add("CISP400");
            requiredClasses.add("CISP401");
        }
    }

    public void setElectives(HashSet<String> electives) {
        this.electives = electives;
    }

    public HashSet<String> compareToRequirements(HashSet<String> classesTaken) {

        HashSet<String> classesRemaining = new HashSet<>();


        for (String thisClass : requiredClasses) {
            if (!classesTaken.contains(thisClass)) {
                classesRemaining.add(thisClass);
            }
        }

        if (classesTaken.contains("CISP400") || classesTaken.contains("CISP401")){
            classesRemaining.remove("CISP400");
            classesRemaining.remove("CISP401");
        }
        return classesRemaining;

    }

    public void printGoal(){
        System.out.println("required");
        requiredClasses.forEach(System.out::println);

        System.out.println("Elective");
        electives.forEach(System.out::println);
    }
}
