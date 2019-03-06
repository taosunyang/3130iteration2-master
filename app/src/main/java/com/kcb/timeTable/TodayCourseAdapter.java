package com.kcb.timeTable;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sunyang Tao and Yang Liu
 */

public class TodayCourseAdapter extends RecyclerView.Adapter<TodayCourseAdapter.MyViewHolder>{
    private Context context;
    private List<CourseModel.DataBean> list;
    private LayoutInflater inflater;
    public TodayCourseAdapter(Context context, List<CourseModel.DataBean> list) {
        this.context = context;
        this.list = list;
        inflater=LayoutInflater.from(context);
    }

    public void setData(List<CourseModel.DataBean> list){
        this.list=list;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.rv_item_today_course,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    //calculate and display the begin time and end time of the course
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        int start = 8+list.get(position).getSection();
        int end= start+list.get(position).gethours();
        holder.tvCourse.setText(list.get(position).getCourseName()+"\n"+list.get(position).getClassRoom()+"\n"+
        start+":00 - "+end+":00");
        list.get(position);
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvCourse;
        //TextView tvTotal;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvCourse= (TextView) itemView.findViewById(R.id.tv_course);
            //tvTotal=(TextView) itemView.findViewById(R.id.totayTotal);
            //tvTotal.setText(list.size());
        }

    }


}
