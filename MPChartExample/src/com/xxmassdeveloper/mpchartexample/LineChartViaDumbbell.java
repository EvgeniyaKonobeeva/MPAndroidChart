package com.xxmassdeveloper.mpchartexample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.xxmassdeveloper.mpchartexample.notimportant.DemoBase;

import java.util.ArrayList;

/**
 * Created by e.konobeeva on 27.09.2016.
 */

public class LineChartViaDumbbell extends DemoBase{
    private CombinedChart chart;
    private int itemcount = 10;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_lined_dumbbell_chart);

        chart = (CombinedChart)findViewById(R.id.line_dumbbell);
        chart.getDescription().setEnabled(false);
        chart.setBackgroundColor(Color.WHITE);
        chart.setDrawGridBackground(false);
        chart.setDrawBarShadow(false);
        chart.setHighlightFullBarEnabled(false);

        chart.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.LINE
        });

        LimitLine lowerDayLineLimit = new LimitLine(0.5f);
        lowerDayLineLimit.setLineWidth(2f);
        lowerDayLineLimit.enableDashedLine(5f, 10f, 0f);
        lowerDayLineLimit.setLineColor(Color.BLUE);

        LimitLine horizontalLineLimit = new LimitLine(7f);
        horizontalLineLimit.setLineWidth(2f);
        horizontalLineLimit.enableDashedLine(5f, 10f, 0f);
        horizontalLineLimit.setLineColor(Color.GRAY);

        LimitLine upperNightLineLimit = new LimitLine(10f);
        upperNightLineLimit.setLineWidth(2f);
        upperNightLineLimit.enableDashedLine(5f, 10f, 0f);

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        leftAxis.setDrawLimitLinesBehindData(true);
        leftAxis.addLimitLine(lowerDayLineLimit);
        leftAxis.addLimitLine(upperNightLineLimit);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new DefaultAxisValueFormatter(0));
        xAxis.setDrawLimitLinesBehindData(true);
        xAxis.addLimitLine(horizontalLineLimit);

        Legend l = chart.getLegend();
        l.setEnabled(false);

        CombinedData combinedData = new CombinedData();
        combinedData.setData(generateLineData());

//        xAxis.setAxisMaximum(combinedData.getXMax() + 0.25f);

        chart.setData(combinedData);
        chart.invalidate();

    }

    private LineData generateLineData() {

        LineData d = new LineData();

        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int index = 0; index < itemcount; index++)
            entries.add(new Entry(index + 0.5f, getRandom(15, 5)));

        LineDataSet set = new LineDataSet(entries, "Line DataSet");
        set.setColor(Color.rgb(0,191,255));
        set.setLineWidth(2.5f);
        set.setCircleColor(Color.rgb(0,191,255));
        set.setDrawCircleHole(false);
        set.setCircleRadius(4.5f);
        set.setFillColor(Color.rgb(0,191,255));
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setDrawValues(false);

        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        d.addDataSet(set);

        return d;
    }

}
