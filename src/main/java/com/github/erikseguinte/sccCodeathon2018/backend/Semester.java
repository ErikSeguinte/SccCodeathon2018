package com.github.erikseguinte.sccCodeathon2018.backend;

import java.util.HashSet;

public class Semester {
    private HashSet<String> classesOffered;

    public void addClasses(HashSet<String>classesThisSemester){

        classesOffered.addAll(classesThisSemester);

    }

    public boolean isOffered(String thisClass){
        return classesOffered.contains(thisClass);
    }
}
