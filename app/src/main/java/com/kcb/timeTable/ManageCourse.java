package com.kcb.timeTable;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kcb.timeTable.model.Course;
import com.kcb.timeTable.viewholder.CourseViewHolder;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
//Created by Suyuan Li and Shaobo Liu

public class ManageCourse extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button addCourseButton;

    private FirebaseFirestore database;
    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_course);

        recyclerView = findViewById(R.id.courselist);
        addCourseButton = findViewById(R.id.addCourse);
        database = FirebaseFirestore.getInstance();

        adapter = setUpAdapter(database);
        setUpRecyclerView(recyclerView,adapter);

        addCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageCourse.this, AddCourse.class);
                startActivity(intent);
            }
        });


    }

    //Connects our recycler view with the viewholder (how we want to show our model[data])
    // and the firestore adapter
    private void setUpRecyclerView(RecyclerView rv, FirestoreRecyclerAdapter adapter)
    {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(manager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);
    }


    //Creates a Firestore adapter to be used with a Recycler view.
    //We will see adapter in more details after the midterm
    //More info on this: https://github.com/firebase/FirebaseUI-Android/blob/master/firestore/README.md
    private FirestoreRecyclerAdapter setUpAdapter(FirebaseFirestore db)
    {
        Query query = db.collection("courses").orderBy("courseName").limit(50);
        FirestoreRecyclerOptions<Course> options = new FirestoreRecyclerOptions.Builder<Course>()
                .setQuery(query,Course.class)
                .build();

        FirestoreRecyclerAdapter adapter = new FirestoreRecyclerAdapter<Course, CourseViewHolder>(options)
        {
            //For each item in the database connect it to the view
            @Override
            public void onBindViewHolder(CourseViewHolder holder, int position, final Course model)
            {
                holder.courseName.setText(model.courseName);
                holder.section.setText(model.section);

                //Set the on click for the button
                //I find this ugly :) but it is how you will see in most examples
                // You CAN use lambadas for the listeners
                // e.g. setOnClickListener ((View v) -> ....
                holder.detailsButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ManageCourse.this,UserCourse.class);
                        intent.putExtra("course",model);
                        startActivity(intent);

                    }
                });
            }

            @Override
            public CourseViewHolder onCreateViewHolder(ViewGroup group, int i)
            {
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.course_entry,group,false);
                return new CourseViewHolder(view);

            }
        };

        return adapter;

    }


    //Method called every time the activity starts.
    @Override
    protected void onStart() {
        super.onStart();
        //Tells the adapter to start listening for changes in the database
        adapter.startListening();
    }

    //Method called every time the activity stops
    @Override
    protected void onStop() {
        super.onStop();
        //Tells the adapter to stop listening since we are not using this activity
        //  anymore. Otherwise the adapter would still exist in the background draining battery
        //  with useful cycles...
        adapter.stopListening();
    }
}
