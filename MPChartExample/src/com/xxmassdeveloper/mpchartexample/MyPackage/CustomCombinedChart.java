package com.xxmassdeveloper.mpchartexample.MyPackage;

import android.content.Context;
import android.util.AttributeSet;

import com.github.mikephil.charting.charts.CombinedChart;

/**
 * Created by e.konobeeva on 28.09.2016.
 */

public class CustomCombinedChart extends CombinedChart {
    public CustomCombinedChart(Context context) {
        super(context);
    }

    public CustomCombinedChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomCombinedChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void init() {
        super.init();
        mRenderer = new MyChartRender(this, mAnimator, mViewPortHandler);
    }
}
