package com.github.erikseguinte.sccCodeathon2018.backend;

import java.io.Serializable;
import java.util.HashSet;

public class Semester implements Serializable {
    private HashSet<String> classesOffered;

    Semester(HashSet<String> classesOffered) {
        this.classesOffered = new HashSet<>();
        addClasses(classesOffered);
    }

    public void addClasses(HashSet<String> classesThisSemester){

        this.classesOffered.addAll(classesThisSemester);

    }

    public boolean isOffered(String thisClass){
        return classesOffered.contains(thisClass);
    }
}
