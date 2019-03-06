package com.kcb.timeTable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
// Created by Yang Liu and Sunyang Tao
public class UserProfile extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        Button userName = (Button) findViewById(R.id.UserName);
        Button userFaculty = (Button) findViewById(R.id.UserFaculty);
        Button email = (Button) findViewById(R.id.Email);
        Button password = (Button) findViewById(R.id.Password);
        Button timetable = (Button) findViewById(R.id.timetable);
        Button registration = (Button) findViewById(R.id.Registration);
        Button courseTree = (Button) findViewById(R.id.CourseTree);


        Button pic2 = (Button) findViewById(R.id.timetable);
        pic2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfile.this, activitySecond.class);
                startActivity(intent);
            }
        });

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfile.this, ManageCourse.class);
                startActivity(intent);
            }
        });


        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfile.this, GradeMain.class);
                startActivity(intent);
            }
        });

    }



}
