package com.suad.venttome;

import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.android.material.card.MaterialCardView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import static android.content.ContentValues.TAG;


public class HomeFragment extends Fragment {

    //Pie chart stuff
    int ela;
    int div;
    int alim;
    int lux;
    int poup;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    ImageButton elatedBtn;
    ImageButton happyBtn;
    ImageButton ehBtn;
    ImageButton sadBtn;
    Button angryBtn;
    private MaterialCardView elatedCard;
    private MaterialCardView happyCard;
    private MaterialCardView mehCard;
    private MaterialCardView sadCard;
    private MaterialCardView angryCard;
    private Button elatedDialogButton;
    private Button happyDialogButton;
    private Button mehDialogButton;
    private Button sadDialogButton;
    private Button angryDialogButton;
    GraphView graphView;
    private int elatedTimes;
    private int happyTimes;
    private int mehTimes;
    private int sadTimes;
    private int angryTimes;
    private SharedPreferences sharedpreferences;
    private int elatedNewValue;
    private int happyNewValue;
    private int mehNewValue;
    private int sadNewValue;
    private int angryNewValue;
    private PieChart pieChart;
    private float[] yData;
    private String[] xData;
    int allMoods;
    float a;
    float b;
    float c;
    float d;
    float e;
    private AnimatorSet mAnimationSet;
    public LinearLayout linearLay;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment getInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }


//OnViewCreated method

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //IRRELEVANT/USELESS
        elatedBtn = view.findViewById(R.id.elated_btn);
        happyBtn = view.findViewById(R.id.happy_btn);
        ehBtn = view.findViewById(R.id.eh_btn);
        sadBtn = view.findViewById(R.id.sad_btn);
        angryBtn = view.findViewById(R.id.angry_btn);

//initializing UI elements
        elatedCard = view.findViewById(R.id.homeElatedCard);
        happyCard = view.findViewById(R.id.homeHappyCard);
        mehCard = view.findViewById(R.id.homeMehCard);
        sadCard = view.findViewById(R.id.homeSadCard);
        angryCard = view.findViewById(R.id.homeAngryCard);
        linearLay = view.findViewById(R.id.LinearLay);
        graphView = view.findViewById(R.id.graph_id);
        pieChart = view.findViewById(R.id.chart_id);
        SharedPreferences preferences = requireActivity().getSharedPreferences("MY_PREFERENCES", Context.MODE_PRIVATE);
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




//Angry Button Press

        AnimationDrawable animationDrawable = (AnimationDrawable) linearLay.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();

        angryCard.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                angryBtn.setBackgroundResource(R.drawable.gradient_anim);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        Intent intent = new Intent(getActivity(), RecordAMood.class);
                        startActivity(intent);
                    }
                }, 150);
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