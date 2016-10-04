package com.xxmassdeveloper.mpchartexample.MyPackage;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.xxmassdeveloper.mpchartexample.R;
import com.xxmassdeveloper.mpchartexample.notimportant.DemoBase;

import java.util.ArrayList;

/**
 * Created by e.konobeeva on 27.09.2016.
 */

public class LineChartViaDumbbell extends DemoBase{
    private CombinedChart chart;
    private int itemcount = 11;
    private float XValues[];
    private float YValues[];

    private float dayDiastLimit = 15;
    private float daySystLimit = 8;

    private float nightDiastLimit = 10;
    private float nightSystLimit = 5;

    private float nightPos = 7;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_lined_dumbbell_chart);

        chart = (CustomCombinedChart)findViewById(R.id.line_dumbbell);
        chart.getDescription().setEnabled(false);
        chart.setBackgroundColor(Color.WHITE);
        chart.setDrawGridBackground(false);
        chart.setDrawBarShadow(false);
        chart.setHighlightFullBarEnabled(false);

        chart.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.CANDLE, CombinedChart.DrawOrder.LINE
        });

        setYAxis();

        Legend l = chart.getLegend();
        l.setEnabled(false);

        XValues = new float[itemcount];
        YValues = new float[itemcount];
        CombinedData combinedData = new CombinedData();
        combinedData.setData(generateLineData());
        combinedData.setData(generateCandleData());

        setYAxis();
        setXAxis().setAxisMaximum(combinedData.getXMax() + 1f);

        chart.setData(combinedData);
        chart.invalidate();

    }

    private LineData generateLineData() {

        LineData d = new LineData();

        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int index = 0; index < itemcount; index++) {
            YValues[index] = getRandom(15, 5);
            XValues[index] = index + 0.5f;
            entries.add(new Entry(XValues[index], YValues[index]));
        }

        LineDataSet set = new LineDataSet(entries, "Line DataSet");
        set.setColor(Color.rgb(0,191,255));
        set.setLineWidth(1f);
        set.setCircleColor(Color.rgb(0,191,255));
        set.setDrawCircleHole(false);
        set.setCircleRadius(2f);
        set.setFillColor(Color.rgb(0,191,255));
        set.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        set.setDrawValues(false);

        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        d.addDataSet(set);

        return d;
    }
    private CandleData generateCandleData(){
        ArrayList<CandleEntry> candleEntries = new ArrayList<>();

        for(int i = 0; i < itemcount; i++){
            float close = YValues[i] + 1.5f;
            float high = close + 1;
            float low = close - 1;
            DumbbellEntry dumbbellEntry = new DumbbellEntry(XValues[i], high, low,20f, 10f );
            candleEntries.add(dumbbellEntry);
        }

        DumbbellDataSet dataSet = new DumbbellDataSet(candleEntries, "candle entries");
        dataSet.setShadowWidth(1f);
        dataSet.setDrawValues(false);
        dataSet.enableDayNorms(true);
        dataSet.enableNightNorms(true);
        dataSet.setDayDiastNorm(dayDiastLimit);
        dataSet.setDaySystNorm(daySystLimit);
        dataSet.setNightDiastNorm(nightDiastLimit);
        dataSet.setNightSystNorm(nightSystLimit);
        dataSet.setNightDividerPos(nightPos);

        dataSet.setSystolicBorderColor("#bbea77");
        dataSet.setDiastolicBorderColor("#ffb536");

        return new CandleData(dataSet);



    }

    private XAxis setXAxis(){
        LimitLine horizontalLineLimit = new LimitLine(nightPos);
        horizontalLineLimit.setLineWidth(2f);
        horizontalLineLimit.enableDashedLine(5f, 10f, 0f);
        horizontalLineLimit.setLineColor(Color.parseColor("#ed998e"));

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(0f);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new DefaultAxisValueFormatter(0));
        xAxis.setDrawLimitLinesBehindData(true);
        xAxis.addLimitLine(horizontalLineLimit);
        return xAxis;
    }

    private void setYAxis(){

        LimitLine systDayLineLimit = new MyLimitLine(daySystLimit, 0f, nightPos);
        systDayLineLimit.setLineWidth(1f);
        systDayLineLimit.enableDashedLine(5f, 10f, 0f);
        systDayLineLimit.setLineColor(Color.BLUE);

        LimitLine diastDayLineLimit = new MyLimitLine(dayDiastLimit, 0f, nightPos);
        diastDayLineLimit.setLineWidth(1f);
        diastDayLineLimit.enableDashedLine(5f, 10f, 0f);
        diastDayLineLimit.setLineColor(Color.GREEN);


        LimitLine systNightLineLimit = new MyLimitLine(nightSystLimit, nightPos, 11f);
        systNightLineLimit.setLineWidth(1.5f);
        systNightLineLimit.enableDashedLine(5f, 10f, 0f);
        systNightLineLimit.setLineColor(Color.DKGRAY);

        LimitLine diastNightLineLimit = new MyLimitLine(nightDiastLimit, nightPos, 11f);
        diastNightLineLimit.setLineWidth(1.5f);
        diastNightLineLimit.enableDashedLine(5f, 10f, 0f);
        diastNightLineLimit.setLineColor(Color.GRAY);

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawLimitLinesBehindData(true);

        leftAxis.addLimitLine(systDayLineLimit);
        leftAxis.addLimitLine(diastDayLineLimit);
        leftAxis.addLimitLine(diastNightLineLimit);
        leftAxis.addLimitLine(diastNightLineLimit);
    }

}
