
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
public class UserCourse extends AppCompatActivity {

    private TextView courseName;
    private TextView section;
    private TextView weekday;
    private TextView classRoom;
    private TextView hours;
    private Button delete;
    private Button update;

    private FirebaseFirestore database;
    private Intent intent;
    private Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_course);

        courseName = findViewById(R.id.courseName);
        section = findViewById(R.id.section);
        weekday = findViewById(R.id.weekday);
        classRoom = findViewById(R.id.classRoom);
        hours = findViewById(R.id.hours);

        database = FirebaseFirestore.getInstance();

        intent = getIntent();

        course = (Course) intent.getSerializableExtra("course");
        courseName.setText("  "+course.courseName);
        section.setText(course.section);
        weekday.setText(course.weekday);
        classRoom.setText(course.classRoom);
        hours.setText(course.hours);

        courseName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserCourse.this,CourseDetail.class);
                intent.putExtra("course",course);
                startActivity(intent);

            }
        });

    }

}