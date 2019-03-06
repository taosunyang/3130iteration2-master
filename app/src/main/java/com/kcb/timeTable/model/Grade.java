package com.kcb.timeTable.model;

import java.io.Serializable;

// Created by Ziyu Wang
public class Grade implements Serializable
{

    public String id;//used for the mapping in other classes
    public String name;
    public String grade;


    public Grade()
    {
        // Default constructor
    }

    public Grade(String id, String name, String grade)
    {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String toString()
    {
        return "Name: " + name + " grade: " + grade;
    }
}

