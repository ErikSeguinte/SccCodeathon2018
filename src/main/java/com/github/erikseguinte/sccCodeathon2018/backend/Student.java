package com.github.erikseguinte.sccCodeathon2018.backend;

import java.util.EnumSet;
import java.util.HashSet;

enum OopClasses{
    CISP400, CISP401
};

public class Student {
    private HashSet<String> classesTaken;
    private Goal goal;
    private int electiveUnits;
    private EnumSet<OopClasses> oop;

    public void setClassesTaken(HashSet<String> classesTaken) {
        this.classesTaken = classesTaken;

        oop = EnumSet.noneOf(OopClasses.class);

        if (classesTaken.contains("cisp400")){
            oop.add(OopClasses.CISP400);
        }

        if (classesTaken.contains("cisp401")){
            oop.add(OopClasses.CISP401);
        }
    }

    public EnumSet<OopClasses> getOop() {
        return oop;
    }

    public HashSet<String> getClassesTaken() {
        return classesTaken;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }
}
