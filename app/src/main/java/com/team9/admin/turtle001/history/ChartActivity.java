package com.team9.admin.turtle001.history;



import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.team9.admin.turtle001.ActivityCollector;
import com.team9.admin.turtle001.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;
import me.itangqi.waveloadingview.WaveLoadingView;

//历史记录

public class ChartActivity extends AppCompatActivity {
    private LineChartView lineChart;
    private WaveLoadingView mWaveLoadingView;

    Calendar calendar = Calendar.getInstance();
    //获取系统的日期
//年
    int year = calendar.get(Calendar.YEAR);
    //月
    int month = calendar.get(Calendar.MONTH)+1;
    //日
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    int mday,myear,mmonth;
    private Switch mswitch;
    private int mlength = 7;


    String[] date = new String[30];//X轴的标注
    int[] score = new int[30]; //图表的数据点
    private List<PointValue> mPointValues = new ArrayList<PointValue>();
    private List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();
    private Historydb historydb;
    private SQLiteDatabase dbReader;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        lineChart = (LineChartView)findViewById(R.id.line_chart);
        init();
        display();

        Button back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

    }


    private void display(){

        int rel = score[29];
        String rel1 = String.valueOf(rel);
        mWaveLoadingView = (WaveLoadingView) findViewById(R.id.waveLoadingView2);
        mWaveLoadingView.setShapeType(WaveLoadingView.ShapeType.CIRCLE);
        mWaveLoadingView.setCenterTitle(rel1);
        mWaveLoadingView.setProgressValue(rel);
        mWaveLoadingView.setAmplitudeRatio(70);
        mWaveLoadingView.setAnimDuration(2000);
        mWaveLoadingView.startAnimation();
        mWaveLoadingView.setCenterTitleColor(Color.WHITE);
        mWaveLoadingView.setCenterTitleStrokeColor(Color.GRAY);
    }
    protected void onResume() {
        super.onResume();
        mswitch = (Switch) findViewById(R.id.switch1);
        mswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    mlength = 30;
                }
                else{
                    mlength = 7;
                }
                mPointValues.clear();
                mAxisXValues.clear();
                init();
            }
        });
    }
    public void init(){
        getArray();
        getAxisXLables();//获取x轴的标注
        getAxisPoints();//获取坐标点
        initLineChart();//初始化
    }


    public void getArray(){
        int i = 29;
        historydb = new Historydb(this);
        dbReader = historydb.getReadableDatabase();
        Cursor cursor = dbReader.rawQuery("select month,day,content from history order by _id desc",null);
        if (cursor.moveToFirst()){
            do{

                String mdata =cursor.getString(cursor.getColumnIndex("month"))+"."+cursor.getString(cursor.getColumnIndex("day"));
                String mscore = cursor.getString(cursor.getColumnIndex("content"));
                date[i] = mdata;
                score[i] = Integer.parseInt(mscore);
                i--;
            }while(cursor.moveToNext()&&i>0);
        }
        cursor.close();
        if (i > 0 ){
            setDay(i);
        }

    }

    public void setDay(int i){
        historydb = new Historydb(this);
        dbReader = historydb.getWritableDatabase();
        Cursor cursor = dbReader.rawQuery("select day,month,year from history limit 1",null);
        if( cursor.moveToFirst()){
            mday = Integer.parseInt(cursor.getString(cursor.getColumnIndex("day")));
            myear = Integer.parseInt(cursor.getString(cursor.getColumnIndex("year")));
            mmonth = Integer.parseInt(cursor.getString(cursor.getColumnIndex("month")));
            }else {
            mday = day;myear = year;mmonth = month;
        }
        setDate(i);
    }

    public void setDate(int i){
        for (int j = i;j >= 0;j--){
            if (mday != 1){
                mday--;
            }else{
                if (mmonth == 3){
                    mmonth--;
                    if ((myear % 4 ==0 && myear % 100!=0)||myear % 400 == 0){
                        mday = 29;
                    }else{
                        mday = 28;
                    }
                }else{
                    switch (mmonth){
                        case 5:
                        case 7:
                        case 10:
                        case 12:
                            mday = 30;
                            mmonth--;
                            break;
                        case 1:
                            mday = 31;
                            mmonth = 12;
                            myear = myear--;
                            break;
                        default:
                            mday = 31;
                            mmonth--;
                    }
                }
            }
            date[j] = String.valueOf(mmonth)+"."+String.valueOf(mday);
        }
    }

    /**
     * 设置X 轴的显示
     */
    private void getAxisXLables() {
        for (int i = 0; i < mlength; i++) {
            mAxisXValues.add(new AxisValue(i).setLabel(date[30-mlength+i]));
        }
    }

    /**
     * 图表的每个点的显示
     */
    private void getAxisPoints() {
        for (int i = 0; i < mlength; i++) {
            mPointValues.add(new PointValue(i, score[30-mlength+i]));
        }
    }

    private void initLineChart() {
        Line line = new Line(mPointValues).setColor(Color.parseColor("#FFCD41"));  //折线的颜色（橙色）
        List<Line> lines = new ArrayList<Line>();
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
        line.setCubic(false);//曲线是否平滑，即是曲线还是折线
        line.setFilled(true);//是否填充曲线的面积
        line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
        line.setPointRadius(3);// 设置节点半径
        line.setPointColor(0xff56caaf);// 设置节点颜色

        line.setColor(0xff56caaf);// 设置折线颜色
        line.setStrokeWidth(3);// 设置折线宽度


        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(false);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextColor(Color.GRAY);  //设置字体颜色
        axisX.setTextSize(14);//设置字体大小
        axisX.setMaxLabelChars(8); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
        axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部
        axisX.setHasLines(true); //x 轴分割线


        // Y轴是根据数据的大小自动设置Y轴上限(在下面我会给出固定Y轴数据个数的解决方案)
        Axis axisY = new Axis();  //Y轴
        axisY.setName("");//y轴标注
        axisY.setTextSize(10);//设置字体大小
        data.setAxisYLeft(axisY);  //Y轴设置在左边
        axisY.setTextColor(Color.GRAY);// 设置Y轴文字颜色
        //设置行为属性，支持缩放、滑动以及平移
        lineChart.setInteractive(true);
        lineChart.setZoomType(ZoomType.HORIZONTAL);
        lineChart.setMaxZoom((float) 1);//最大方法比例
        lineChart.setLineChartData(data);
        lineChart.setVisibility(View.VISIBLE);

        Viewport v = new Viewport(lineChart.getMaximumViewport());
        v.left = 0;
        v.right = 7;
        lineChart.setCurrentViewport(v);

    }


    @Override
    protected void onStart() {
        super.onStart();
        //开始
        mWaveLoadingView.resumeAnimation();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束
        mWaveLoadingView.pauseAnimation();
    }
}
