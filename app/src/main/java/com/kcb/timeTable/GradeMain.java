package com.kcb.timeTable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.kcb.timeTable.model.Grade;
import com.kcb.timeTable.viewholder.GradeViewHolder;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

// Created by Ziyu Wang
public class GradeMain extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button makefakedata;

    private FirebaseFirestore database;
    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grade_main);

        makefakedata = findViewById(R.id.update);
        recyclerView = findViewById(R.id.contactlist);

        database = FirebaseFirestore.getInstance();

        adapter = setUpAdapter(database);
        setUpRecyclerView(recyclerView, adapter);

        makefakedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Grade a = new Grade("0","CSCI 1100","A+");
                Grade b = new Grade("1","CSCI 1101","A+");
                Grade c = new Grade("2","CSCI 2110","A");
                Grade d = new Grade("3","CSCI 2112","A-");
                Grade e = new Grade("4","CSCI 2121","B+");
                Grade f = new Grade("5","CSCI 2132","A-");
                Grade g = new Grade("6","CSCI 2141","A");
                Grade h = new Grade("7","CSCI 2100","C");
                Grade i = new Grade("8","ECON 1101","A");
                Grade j = new Grade("9","ECON 1102","A");
                Grade k = new Grade("10","MATH 1000","A-");
                Grade l = new Grade("11","MATH 1010","B-");
                DocumentReference ref1 = database.collection("grades").document();
                DocumentReference ref2 = database.collection("grades").document();
                DocumentReference ref3 = database.collection("grades").document();
                DocumentReference ref4 = database.collection("grades").document();
                DocumentReference ref5 = database.collection("grades").document();
                DocumentReference ref6 = database.collection("grades").document();
                DocumentReference ref7 = database.collection("grades").document();
                DocumentReference ref8 = database.collection("grades").document();
                DocumentReference ref9 = database.collection("grades").document();
                DocumentReference ref10 = database.collection("grades").document();
                DocumentReference ref11= database.collection("grades").document();
                DocumentReference ref12= database.collection("grades").document();
                ref1.set(a);
                ref2.set(b);
                ref3.set(c);
                ref4.set(d);
                ref5.set(e);
                ref6.set(f);
                ref7.set(g);
                ref8.set(h);
                ref9.set(i);
                ref10.set(j);
                ref11.set(k);
                ref12.set(l);

            }
        });

    }

    private void setUpRecyclerView(RecyclerView rv, FirestoreRecyclerAdapter adapter)
    {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(manager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);
    }

    private FirestoreRecyclerAdapter setUpAdapter(FirebaseFirestore db)
    {
        Query query = db.collection("grades").orderBy("name").limit(50);
        FirestoreRecyclerOptions<Grade> options = new FirestoreRecyclerOptions.Builder<Grade>()
                .setQuery(query,Grade.class)
                .build();

        FirestoreRecyclerAdapter adapter = new FirestoreRecyclerAdapter<Grade,GradeViewHolder>(options)
        {
            //For each item in the database connect it to the view
            @Override
            public void onBindViewHolder(GradeViewHolder holder, int position, final Grade model)
            {
                holder.name.setText(model.name);
                holder.grade.setText(model.grade);
                holder.detailsButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(GradeMain.this,GradeDetail.class);
                        intent.putExtra("grades",model);
                        startActivity(intent);

                    }
                });
            }

            @Override
            public GradeViewHolder onCreateViewHolder(ViewGroup group, int i)
            {
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.grade_entry,group,false);
                return new GradeViewHolder(view);

            }
        };

        return adapter;

    }

    //Method called every time the activity starts.
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    //Method called every time the activity stops
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
