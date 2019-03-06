package com.kcb.timeTable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kcb.timeTable.model.Course;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

//Created by Suyuan Li and Shaobo Liu
public class AddCourse extends AppCompatActivity {

    private TextView courseName;
    private TextView section;
    private TextView hourshours;
    private TextView weekday;
    private TextView hours;
    private TextView classRoom;
    private TextView courseFlag;

    private Button addCourse;

    FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        courseName = findViewById(R.id.courseNameEdit);
        section = findViewById(R.id.sectionEdit);
        hours = findViewById(R.id.hoursEdit);
        weekday = findViewById(R.id.weekdayEdit);
        classRoom = findViewById(R.id.classRoomEdit);
        courseFlag = findViewById(R.id.courseFlagEdit);

        addCourse = findViewById(R.id.addNew);

        database = FirebaseFirestore.getInstance();

        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference ref = database.collection("courses").document();
                Course c = new Course(courseName.getText().toString(),section.getText().toString(),
                        hours.getText().toString(),  weekday.getText().toString(), classRoom.getText().toString(), courseFlag.getText().toString());
                //Here instead of adding directly we are first getting a reference so we save the ID;
                // this is not necessary but it will make life easier latter when editing/deleting.

                ref.set(c);

                //Finishes the acitivy and return to the parent one.
                finish();
            }
        });

    }
}
