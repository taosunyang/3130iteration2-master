package com.kcb.timeTable;

/*
    Created by Jing Yang
 */
public class Model {
    String title;
    String desc;

    //constructor
    public Model(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }



    public String getTitle() {
        return this.title;
    }

    public String getDesc() {
        return this.desc;
    }


}