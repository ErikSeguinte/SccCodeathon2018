package com.github.erikseguinte.sccCodeathon2018.backend;

import java.util.HashSet;

public class Goal {
    private HashSet<String> requiredClasses;
    private HashSet<String> electives;

    Goal(HashSet<String> requiredClasses) {
        this.requiredClasses = requiredClasses;
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
}
