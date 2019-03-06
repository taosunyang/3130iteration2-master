package com.kcb.timeTable.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kcb.timeTable.R;
//Created by Suyuan Li and Shaobo Liu
public class CourseViewHolder extends RecyclerView.ViewHolder  {

    public TextView courseName;
    public TextView section;
    public Button detailsButton;

    public CourseViewHolder(View view)
    {
        super(view);
        courseName = view.findViewById(R.id.courseName);
        section = view.findViewById(R.id.courseSection);

        detailsButton = view.findViewById(R.id.goDetails);

    }




}

