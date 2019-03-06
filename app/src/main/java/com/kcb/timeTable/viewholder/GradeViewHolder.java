package com.kcb.timeTable.viewholder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kcb.timeTable.R;

// Created by Ziyu Wang
public class GradeViewHolder extends RecyclerView.ViewHolder {

    public TextView name;
    public TextView grade;

    public Button detailsButton;

    public GradeViewHolder(View view) {
        super(view);
        name = view.findViewById(R.id.gradeName);
        grade = view.findViewById(R.id.gradeFinal);

        detailsButton = view.findViewById(R.id.goDetails);

    }
}
