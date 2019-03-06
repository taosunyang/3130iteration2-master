package com.kcb.timeTable.model;
import java.io.Serializable;


//Created by Suyuan Li and Shaobo Liu
public class Course implements Serializable {

    public String id;
    public String courseName;
    public String section;
    public String hours;
    public String weekday;
    public String classRoom;
    public String courseFlag;

    public  Course()
    {

    }

    public Course(String courseName, String section, String hours, String weekday, String classRoom, String courseFlag)
    {
        this.courseName = courseName;
        this.section = section;
        this.hours = hours;
        this.weekday = weekday;
        this.classRoom = classRoom;
        this.courseFlag = courseFlag;
    }





    @Override
    public String toString()
    {
        return "Name: " + courseName + " section: " + section;
    }
}
