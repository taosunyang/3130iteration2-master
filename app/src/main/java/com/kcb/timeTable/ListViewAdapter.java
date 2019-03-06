package com.kcb.timeTable;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/*
    Created by Jing Yang
 */
public class ListViewAdapter extends BaseAdapter{

    Context mContext;
    LayoutInflater inflater;
    List<Model> modellist;
    ArrayList<Model> arrayList;

    //constructor
    public ListViewAdapter(Context context, List<Model> modellist) {
        mContext = context;
        this.modellist = modellist;
        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<Model>();
        this.arrayList.addAll(modellist);
    }

    public class ViewHolder{
        TextView mTitleTv, mDescTv;
    }

    @Override
    public int getCount() {
        return modellist.size();
    }

    @Override
    public Object getItem(int i) {
        return modellist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int postition, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view==null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.row, null);

            holder.mTitleTv = view.findViewById(R.id.mainTitle);
            holder.mDescTv = view.findViewById(R.id.mainDesc);

            view.setTag(holder);

        }
        else {
            holder = (ViewHolder)view.getTag();
        }
        holder.mTitleTv.setText(modellist.get(postition).getTitle());
        holder.mDescTv.setText(modellist.get(postition).getDesc());



        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (modellist.get(postition).getTitle().equals("Computer Science")){

                    Intent intent = new Intent(mContext, NewActivity.class);
                    //intent.putExtra("actionBarTitle", "Computer Science");
                    intent.putExtra("contentTv", "CSCI3130");
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getTitle().equals("Commerce")){
                    Intent intent = new Intent(mContext, NewActivity.class);
                    //intent.putExtra("actionBarTitle", "Commerce");
                    intent.putExtra("contentTv", "COMM3203");
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getTitle().equals("Economics")){
                    Intent intent = new Intent(mContext, NewActivity.class);
                    //intent.putExtra("actionBarTitle", "Economics");
                    intent.putExtra("contentTv", "ECON1101");
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getTitle().equals("Engineering")){
                    Intent intent = new Intent(mContext, NewActivity.class);
                    //intent.putExtra("actionBarTitle", "Engineering");
                    intent.putExtra("contentTv", "ENGI1203");
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getTitle().equals("Mathematics")){
                    Intent intent = new Intent(mContext, NewActivity.class);
                    //intent.putExtra("actionBarTitle", "Mathematics");
                    intent.putExtra("contentTv", "MATH1000");
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getTitle().equals("Actuarial Science")){

                    Intent intent = new Intent(mContext, NewActivity.class);
                    //intent.putExtra("actionBarTitle", "Actuarial Science");
                    intent.putExtra("contentTv", "ACSC3703");
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getTitle().equals("English")){
                    Intent intent = new Intent(mContext, NewActivity.class);
                   // intent.putExtra("actionBarTitle", "English");
                    intent.putExtra("contentTv", "ENGL1015");
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getTitle().equals("Law")){
                    Intent intent = new Intent(mContext, NewActivity.class);
                    //intent.putExtra("actionBarTitle", "Law");
                    intent.putExtra("contentTv", "LAWS2000");
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getTitle().equals("Music")){
                    Intent intent = new Intent(mContext, NewActivity.class);
                    //intent.putExtra("actionBarTitle", "Music");
                    intent.putExtra("contentTv", "MUSC1081");
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getTitle().equals("Physiology")){
                    Intent intent = new Intent(mContext, NewActivity.class);
                   // intent.putExtra("actionBarTitle", "Physiology");
                    intent.putExtra("contentTv", "PHYL2044");
                    mContext.startActivity(intent);
                }

            }
        });


        return view;
    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        modellist.clear();
        if (charText.length()==0){
            modellist.addAll(arrayList);
        }
        else {
            for (Model model : arrayList){
                if (model.getTitle().toLowerCase(Locale.getDefault())
                        .contains(charText)){
                    modellist.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }

}