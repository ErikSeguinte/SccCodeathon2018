package com.github.erikseguinte.sccCodeathon2018.backend;


import java.util.EnumSet;
import java.util.HashSet;

enum OopClasses {
    CISP400, CISP401
}

public class Student {
    private HashSet<String> classesTaken;
    private HashSet<String> classesStillNeeded;
    private HashSet<String> electivesAvailable;
    private Goal goal;
    private int electiveUnits;
    private int electiveUnitsRequired;
    private EnumSet<OopClasses> oop;

    public Student(){
        oop = EnumSet.noneOf(OopClasses.class);
        electiveUnits = 0;
    }
    public EnumSet<OopClasses> getOop() {
        return oop;
    }

    public HashSet<String> getClassesTaken() {
        return classesTaken;
    }

    public void setClassesTaken(HashSet<String> classesTaken) {
        this.classesTaken = classesTaken;


    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public void processCisp400And401(){
        if (classesTaken.contains("CISP400")) {
            oop.add(OopClasses.CISP400);
        }

        if (classesTaken.contains("CISP401")) {
            oop.add(OopClasses.CISP401);
        }

        if (oop.contains(OopClasses.CISP400) && oop.contains(OopClasses.CISP401)) {
            electiveUnits += 3;
        }
    }

    public void processElectives() {
        HashSet<String> processedClasses = (HashSet<String>) classesTaken.clone();
        processedClasses.remove("CISP400");
        processedClasses.remove("CISP401");
        electivesAvailable = new HashSet<>();

        for (String electiveClass : goal.getElectives()) {
            if (processedClasses.contains(electiveClass)) {
                electiveUnits += 3;
            } else {
                electivesAvailable.add(electiveClass);
            }
        }
    }

    public void process() {
        classesStillNeeded = goal.compareToRequirements(classesTaken);
        processCisp400And401();
        processElectives();

    }

    public HashSet<String> getClassesStillNeeded() {
        return classesStillNeeded;
    }

    public HashSet<String> getElectivesAvailable() {
        if (electiveUnits >= electiveUnitsRequired){
            return new HashSet<>();
        } else
        return electivesAvailable;
    }
}
