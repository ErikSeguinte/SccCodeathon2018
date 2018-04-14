package com.github.erikseguinte.sccCodeathon2018.backend;

import java.util.HashSet;

public class Goal {
    private HashSet<String> requiredClasses;
    private HashSet<String> electives;

    Goal(HashSet<String> requiredClasses) {
        this.requiredClasses = requiredClasses;
    }

    public void setRequiredClasses(HashSet<String> requiredClasses) {
        if (requiredClasses.contains("cisp400cisp401")){
            requiredClasses.remove("cisp400cisp401");
            requiredClasses.add("cisp400");
            requiredClasses.add("cisp401");
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
        return classesRemaining;

    }

    public void printGoal(){
        System.out.println("required");
        requiredClasses.forEach(System.out::println);

        System.out.println("Elective");
        electives.forEach(System.out::println);
    }
}
