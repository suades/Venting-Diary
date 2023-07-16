package com.suad.venttome;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static android.content.ContentValues.TAG;

public class MoodTracker extends AppCompatActivity {
    //Pie chart stuff
    int ela;
    int div;
    int alim;
    int lux;
    int poup;
    private PieChart pieChart;
    private float[] yData;
    private String[] xData;
    int allMoods;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private GraphView graphView;
    private int elatedTimes;
    private int happyTimes;
    private int mehTimes;
    private int sadTimes;
    private int angryTimes;
    private int elatedNewValue;
    private int happyNewValue;
    private int mehNewValue;
    private int sadNewValue;
    private int angryNewValue;
    private ImageButton toHomeBtn;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mood_tracker);
        graphView = findViewById(R.id.graph_id);
        pieChart = findViewById(R.id.chart_id);
        SharedPreferences preferences = getSharedPreferences("MY_PREFERENCES", Context.MODE_PRIVATE);
        //Pie chart stuff 2
        ela = preferences.getInt("Elated", elatedNewValue);
        div = preferences.getInt("Happy", happyNewValue);
        alim = preferences.getInt("Meh", mehNewValue);
        lux = preferences.getInt("Sad", sadNewValue);
        poup = preferences.getInt("Angry", angryNewValue);
        allMoods = ela+div+alim+lux+poup;
        float b = (div*100)/(float) allMoods;
        float c = (alim*100)/(float) allMoods;
        float d = (lux*100)/(float) allMoods;
        float e = (poup*100)/(float) allMoods;
        float a = (ela*100)/(float) allMoods;

        yData = new float[]{a,b,c,d,e};
        xData = new String[]{"Elated", "Happy", "Meh", "Sad", "Angry"};

//Making the Graph



        BarGraphSeries series1 = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(1, preferences.getInt("Elated", elatedNewValue))
        });

        BarGraphSeries series2 = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(2, preferences.getInt("Happy", happyNewValue))
        });

        BarGraphSeries series3 = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(3, preferences.getInt("Meh", mehNewValue))
        });

        BarGraphSeries series4 = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(4, preferences.getInt("Sad", sadNewValue))
        });

        BarGraphSeries series5 = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(5, preferences.getInt("Angry", angryNewValue))
        });


        BarGraphSeries series6 = new BarGraphSeries<>(new DataPoint[]{
                new DataPoint(0,0),
                new DataPoint(6, 0)
        });

        graphView.addSeries(series1);
        graphView.addSeries(series2);
        graphView.addSeries(series3);
        graphView.addSeries(series4);
        graphView.addSeries(series5);
        graphView.addSeries(series6);

// styling
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graphView);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"","Elated", "Happy", "Meh", "Sad", "Angry",""});
        graphView.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
        graphView.getGridLabelRenderer().setHighlightZeroLines(true);
        graphView.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.NONE);
        graphView.getViewport().setBackgroundColor(Color.TRANSPARENT);
        graphView.getGridLabelRenderer().setGridColor(Color.WHITE);

        series1.setColor(Color.rgb(239,8,192));
        series1.setSpacing(5);
        series1.setAnimated(true);
        series2.setColor(Color.rgb(4,208,79));
        series2.setSpacing(5);
        series2.setAnimated(true);
        series3.setColor(Color.rgb(188,111,9));
        series3.setSpacing(5);
        series3.setAnimated(true);
        series4.setColor(Color.rgb(51,127,242));
        series4.setSpacing(5);
        series4.setAnimated(true);
        series5.setColor(Color.rgb(251,0,17));
        series5.setSpacing(5);
        series5.setAnimated(true);
        series6.setSpacing(15);
        graphView.getGridLabelRenderer().setLabelsSpace(-1);
        graphView.getGraphContentHeight();
        graphView.getViewport().setMinY(0);
        graphView.getViewport().setYAxisBoundsManual(true);
        graphView.getGridLabelRenderer().setHorizontalLabelsColor(Color.WHITE);
        graphView.getGridLabelRenderer().setVerticalLabelsColor(Color.WHITE);

        // draw values on top
        series1.setValuesOnTopSize(30);
        series2.setValuesOnTopSize(30);
        series3.setValuesOnTopSize(30);
        series4.setValuesOnTopSize(30);
        series5.setValuesOnTopSize(30);
        series1.setDrawValuesOnTop(true);
        series2.setDrawValuesOnTop(true);
        series3.setDrawValuesOnTop(true);
        series4.setDrawValuesOnTop(true);
        series5.setDrawValuesOnTop(true);
        series6.setDrawValuesOnTop(false);
        series1.setValuesOnTopColor(Color.WHITE);
        series2.setValuesOnTopColor(Color.WHITE);
        series3.setValuesOnTopColor(Color.WHITE);
        series4.setValuesOnTopColor(Color.WHITE);
        series5.setValuesOnTopColor(Color.WHITE);


        //PIE CHART

        pieChart.setRotationEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setCenterTextColor(Color.WHITE);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Mood Tracker" + "\n"+ "(In %)");
        pieChart.setCenterTextSize(10);
        pieChart.setHoleColor(Color.TRANSPARENT);
        pieChart.setBackgroundColor(Color.TRANSPARENT);
        //More options just check out the documentation!

        addDataSet();

        toHomeBtn = findViewById(R.id.to_home_button);

        toHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MoodTracker.this, RealHomePage.class);
                startActivity(intent);
            }
        });

    }


    private void addDataSet() {
        Log.d(TAG, "addDataSet started");
        ArrayList<PieEntry>  yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for(int i = 0; i < yData.length; i++){
            yEntrys.add(new PieEntry(yData[i], i));
        }

        for(int i = 1; i < xData.length; i++){
            xEntrys.add((xData[i]));
        }

        // create data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(14);

        //add colors
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.rgb(239,8,192));
        colors.add(Color.rgb(4,208,79));
        colors.add(Color.rgb(188,111,9));
        colors.add(Color.rgb(51,127,242));
        colors.add(Color.rgb(251,0,17));

        pieDataSet.setColors(colors);

        // add legend
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);

        // create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.setUsePercentValues(true);
        pieChart.invalidate();



    }

    @Override
    public void onPause() {
        super.onPause();


    }

}
