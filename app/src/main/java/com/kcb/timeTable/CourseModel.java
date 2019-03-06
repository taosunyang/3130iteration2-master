package com.kcb.timeTable;

import java.util.List;

/**
 * Created by Yang Liu and Sunyang Tao
 *Course object
 * includes course information: courseName section classroom hours
 */
public class CourseModel {
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * example
         * id : 0
         * courseName : CSCI 3130
         * section : 1
         * hours : 1
         * weekday : 1
         * classRoom : LSC#201
         * courseFlag : 1
         */

        private int id;
        private String courseName;
        private int section;
        private int hours;
        private int weekday;
        private String classRoom;
        private int courseFlag;

        public DataBean(int id, String courseName, int section, int hours, int weekday, String classRoom, int courseFlag) {
            this.id = id;
            this.courseName = courseName;
            this.section = section;
            this.hours = hours;
            this.weekday = weekday;
            this.classRoom = classRoom;
            this.courseFlag = courseFlag;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public int getSection() {
            return section;
        }

        public void setSection(int section) {
            this.section = section;
        }

        public int gethours() {
            return hours;
        }

        public void sethours(int hours) {
            this.hours = hours;
        }

        public int getweekday() {
            return weekday;
        }

        public void setweekday(int weekday) {
            this.weekday = weekday;
        }

        public String getClassRoom() {
            return classRoom;
        }

        public void setClassRoom(String classRoom) {
            this.classRoom = classRoom;
        }

        public int getCourseFlag() {
            return courseFlag;
        }

        public void setCourseFlag(int courseFlag) {
            this.courseFlag = courseFlag;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", courseName='" + courseName + '\'' +
                    ", section=" + section +
                    ", hours=" + hours +
                    ", weekday=" + weekday +
                    ", classRoom='" + classRoom + '\'' +
                    ", courseFlag=" + courseFlag +
                    '}';
        }
    }
}