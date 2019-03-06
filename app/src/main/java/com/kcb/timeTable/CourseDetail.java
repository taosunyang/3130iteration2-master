
package com.kcb.timeTable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kcb.timeTable.model.Course;
import com.google.firebase.firestore.FirebaseFirestore;

//Created by Suyuan Li and Shaobo Liu
public class CourseDetail extends AppCompatActivity {

    private TextView courseName;
    private TextView section;
    private TextView weekday;
    private TextView classRoom;
    private TextView hours;
    private TextView courseFlag;
    private Button delete;
    private Button update;

    private FirebaseFirestore database;
    private Intent intent;
    private Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_detail);

        courseName = findViewById(R.id.courseNameEdit);
        section = findViewById(R.id.sectionEdit);
        weekday = findViewById(R.id.weekdayEdit);
        classRoom = findViewById(R.id.classRoomEdit);
        hours = findViewById(R.id.hoursEdit);
        courseFlag = findViewById(R.id.courseFlagEdit);
        delete = findViewById(R.id.deleteButton);
        update = findViewById(R.id.updateButton);

        database = FirebaseFirestore.getInstance();

        intent = getIntent();

        course = (Course)intent.getSerializableExtra("course");
        courseName.setText(course.courseName);
        section.setText(course.section);
        weekday.setText(course.weekday);
        classRoom.setText(course.classRoom);
        hours.setText(course.hours);
        courseFlag.setText(course.courseFlag);


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCourse();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCourse();
            }
        });



    }

    //TODO: add the logic for updating an entry
    private void updateCourse()
    {

        database.document("courses/" + course.id).update("courseName",courseName.getText().toString(),"section",section.getText().toString(),"weekday",weekday.getText().toString( ),
                "hours", hours.getText().toString(), "classRoom",classRoom.getText().toString(), "courseFlag", courseFlag.getText().toString());

        finish();
    }

    //TODO: add the logic for deleting an entry
    private void deleteCourse()
    {
        database.document("courses/" + course.id).delete();


        //finishes the activity
        finish();

    }
}