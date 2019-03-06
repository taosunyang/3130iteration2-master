package com.kcb.timeTable;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
//Created by Yang Liu and Sunyang Tao
public class MainActivity extends AppCompatActivity {
    private LinearLayout llWeekNames;
    private LinearLayout llSections;
    private LinearLayout Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday;

    List<LinearLayout> WeeklyView=new ArrayList<>();
    private int CourseHeight;
    private int maxCourse = 10;
    private RecyclerView rv;
    private List<CourseModel.DataBean> currentDay=new ArrayList<>();
    private TodayCourseAdapter adapter;
    private List<CourseModel.DataBean> listAll=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView pic = (ImageView) findViewById(R.id.picture);

        initView();
        CourseHeight = getResources().getDimensionPixelSize(R.dimen.sectionHeight);
        initWeekNameView();
        initSectionView();
        initWeekCourseView();
        TextView tt=(TextView) findViewById(R.id.totayTotal);
        tt.setText(""+getTodayEvent());

        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserProfile.class);
                startActivity(intent);
            }
        });

    }

    private void initView() {
        rv= (RecyclerView) findViewById(R.id.DailyEvents);
        llWeekNames= (LinearLayout) findViewById(R.id.Weekday);
        llSections= (LinearLayout) findViewById(R.id.ll_sections);
        Monday= (LinearLayout) findViewById(R.id.Monday);
        Tuesday= (LinearLayout) findViewById(R.id.Tuesday);
        Wednesday= (LinearLayout) findViewById(R.id.Wednesday);
        Thursday= (LinearLayout) findViewById(R.id.Thursday);
        Friday= (LinearLayout) findViewById(R.id.Friday);
        Saturday= (LinearLayout) findViewById(R.id.Saturday);
        Sunday= (LinearLayout) findViewById(R.id.Sunday);

        WeeklyView.add(Monday);
        WeeklyView.add(Tuesday);
        WeeklyView.add(Wednesday);
        WeeklyView.add(Thursday);
        WeeklyView.add(Friday);
        WeeklyView.add(Saturday);
        WeeklyView.add(Sunday);

        listAll=getList();

        currentDay=getWeekCourse(getWeekDay());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(linearLayoutManager);
        adapter=new TodayCourseAdapter(this,currentDay);
        rv.setAdapter(adapter);
    }

    /**
     * The layout from Monday to Sunday
     **/
    private void initWeekNameView() {

        for (int i = 0; i < WeeklyView.size() + 1; i++) {
            TextView tvWeekName = new TextView(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
            if (i != 0) {
                lp.weight = 1;
                tvWeekName.setText("" + intToWeek(i));
                if (i == getWeekDay()) {
                    tvWeekName.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    tvWeekName.setTextColor(Color.parseColor("#4A4A4A"));
                }
            } else {
                lp.weight = 0.8f;
                Date date= new Date();
                SimpleDateFormat day=new SimpleDateFormat("dd");
                String d= day.format(date);
                tvWeekName.setText(inToMonth()+d);
            }
            tvWeekName.setGravity(Gravity.CENTER_HORIZONTAL);
            tvWeekName.setLayoutParams(lp);
            llWeekNames.addView(tvWeekName);
        }
    }

    /** 2/22/2019 new added function
     *get the total number of today's courses
     */
    private int getTodayEvent(){
        int i=getList().size()-1;
        int today=getWeekDay();
        int count=0;
        while(i>0){
            if(this.getList().get(i).getweekday()==today){
                count++;
            }
            i--;
        }
        return count;
    }


    /**
     * set the max section number as 10 (8:00 to 18:00, one hour one section)
     */
    private void initSectionView() {
        for (int i = 1; i <= maxCourse; i++) {
            TextView tvSection = new TextView(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, getResources().getDimensionPixelSize(R.dimen.sectionHeight1));
            lp.gravity = Gravity.CENTER;
            tvSection.setGravity(Gravity.CENTER);
            tvSection.setText(initToSection(i));
            tvSection.setLayoutParams(lp);
            View view=new View(this);
            LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, getResources().getDimensionPixelSize(R.dimen.lineHeight));
            lp1.gravity = Gravity.BOTTOM;
            view.setBackgroundResource(R.color.item_divider);
            view.setLayoutParams(lp1);

            llSections.addView(tvSection);
            llSections.addView(view);
        }

    }

    /**
     * initiate the timetable (bottom part)
     */
    private void initWeekCourseView() {
        for (int i = 0; i < WeeklyView.size(); i++) {
            initWeekPanel(WeeklyView.get(i), getWeekCourse(i+1));
        }
    }

    public List<CourseModel.DataBean> getWeekCourse(int i){
        List<CourseModel.DataBean> weekList=new ArrayList<>();
        weekList.clear();
        for (CourseModel.DataBean dataBean : listAll){
            if (dataBean.getweekday()==i){
                weekList.add(dataBean);
            }
        }
        return weekList;
    }


    public void initWeekPanel(LinearLayout ll, List<CourseModel.DataBean> data) {

        if (ll == null || data == null || data.size() < 1)
            return;
        CourseModel.DataBean firstCourse = data.get(0);
        for (int i = 0; i < data.size(); i++) {
            final CourseModel.DataBean courseModel = data.get(i);

            if (courseModel.getSection() == 0 || courseModel.gethours() == 0)
                return;
            FrameLayout frameLayout = new FrameLayout(this);
            //shape corners
            CornerTextView tv = new CornerTextView(this,
                    ColorUtils.getCourseBgColor(courseModel.getCourseFlag()),
                    dip2px(this, 3));
            LinearLayout.LayoutParams frameLp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    CourseHeight * courseModel.gethours());
            LinearLayout.LayoutParams tvLp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);

            if (i == 0) {
                frameLp.setMargins(0, (courseModel.getSection() - 1) * CourseHeight, 0, 0);
            } else {
                frameLp.setMargins(0, (courseModel.getSection() - (firstCourse.getSection() + firstCourse.gethours())) * CourseHeight, 0, 0);
            }
            tv.setLayoutParams(tvLp);
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(12);
            tv.setTextColor(Color.parseColor("#FFFFFF"));
            tv.setText(courseModel.getCourseName() + "\n @" + courseModel.getClassRoom());

            frameLayout.setLayoutParams(frameLp);
            frameLayout.addView(tv);
            frameLayout.setPadding(2, 2, 2, 2);
            ll.addView(frameLayout);
            firstCourse = courseModel;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this,courseModel.getCourseName(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private String  initToSection(int i) {
        switch (i){
            case 1:
                return "8:00";
            case 2:
                return "9:00";
            case 3:
                return "10:00";
            case 4:
                return "11:00";
            case 5:
                return "13:00";
            case 6:
                return "14:00";
            case 7:
                return "15:00";
            case 8:
                return "16:00";
            case 9:
                return "17:00";
            case 10:
                return "18:00";
            default:
                return "";
        }
    }


    private String intToWeek(int i) {
        switch (i){
            case 1:
                return "Mon.";
            case 2:
                return "Tue.";
            case 3:
                return "Wed.";
            case 4:
                return "Thu.";
            case 5:
                return "Fri.";
            case 6:
                return "Sat.";
            case 7:
                return "Sun.";
            default:
                return "";
        }

    }

    private String inToMonth() {
        int i=getMonth();
        switch (i){
            case 1:
                return "Jan.";
            case 2:
                return "Feb.";
            case 3:
                return "Mar.";
            case 4:
                return "Apr.";
            case 5:
                return "May.";
            case 6:
                return "Jun.";
            case 7:
                return "Jul.";
            case 8:
                return "Aug.";
            case 9:
                return "Sep.";
            case 10:
                return "Oct";
            case 11:
                return "Nov";
            case 12:
                return "Dec";
            default:
                return "";
        }

    }


    /**
     * Current week
     */
    public int getWeekDay() {
        int w = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
        if (w <= 0) {
            w = 7;
        }
        return w;
    }

    /**
     * Current month
     */
    public int getMonth() {
        int w = Calendar.getInstance().get(Calendar.MONTH) + 1;
        return w;
    }


    public static String getJson(String fileName,Context context) {
        //Turn the data from json into strings
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //Get assert manager
            AssetManager assetManager = context.getAssets();
            //Open file through the manager and read it
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) !=null ) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return stringBuilder.toString();
    }

    private List<CourseModel.DataBean> getList(){
        String json=getJson("course.json",this);
        Gson gson=new Gson();
        CourseModel courseModel = gson.fromJson(json,CourseModel.class);
        List<CourseModel.DataBean> courseList=courseModel.getData();
        return courseList;
    }


}
