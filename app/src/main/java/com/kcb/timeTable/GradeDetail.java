package com.kcb.timeTable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.kcb.timeTable.model.Grade;

// Created by Ziyu Wang
public class GradeDetail extends AppCompatActivity
{
    private TextView name;
    private TextView fgrade;

    private Grade grade;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grade_detail);

        name = findViewById(R.id.nameEdit);
        fgrade = findViewById(R.id.gradeEdit);

        intent = getIntent();

        grade = (Grade) intent.getSerializableExtra("grade");
        name.setText(grade.name);
        fgrade.setText(grade.grade);

    }
}
