package com.xxmassdeveloper.mpchartexample.MyPackage;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.interfaces.dataprovider.CandleDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;
import com.github.mikephil.charting.renderer.CandleStickChartRenderer;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

/**
 * Created by e.konobeeva on 28.09.2016.
 */

public class DumbbellRenderer extends CandleStickChartRenderer {

    private float[] mRangeBuffers = new float[4];
    private float[] mHighCircleBuffers = new float[2];
    private float[] mLowCircleBuffers = new float[2];

    private int highColorId = Color.parseColor("#df5f5d");
    private int lowColorId = Color.parseColor("#7a96de");
    private int stickColorId = Color.parseColor("#f5aac0");
    private float radius = 10;

    public DumbbellRenderer(CandleDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(chart, animator, viewPortHandler);
    }

    @Override
    protected void drawDataSet(Canvas c, ICandleDataSet dataSet) {
//        super.drawDataSet(c, dataSet);

        Transformer trans = mChart.getTransformer(dataSet.getAxisDependency());

        float phaseY = mAnimator.getPhaseY();
        float barSpace = dataSet.getBarSpace();

        mXBounds.set(mChart, dataSet);
        mRenderPaint.setStrokeWidth(dataSet.getShadowWidth());

        for (int j = mXBounds.min; j <= mXBounds.range + mXBounds.min; j++) {

            // get the entry
            CandleEntry e = dataSet.getEntryForIndex(j);

            if (e == null)
                continue;

            float low = e.getLow();
            float xPos = e.getX();
            float high = e.getHigh();

            mRangeBuffers[0] = xPos;
            mRangeBuffers[1] = high;
            mRangeBuffers[2] = xPos;
            mRangeBuffers[3] = low;

            mHighCircleBuffers[0] = xPos;
            mHighCircleBuffers[1] = high;

            mLowCircleBuffers[0] = xPos;
            mLowCircleBuffers[1] = low;


            trans.pointValuesToPixel(mRangeBuffers);
            trans.pointValuesToPixel(mLowCircleBuffers);
            trans.pointValuesToPixel(mHighCircleBuffers);


            mRenderPaint.setColor(stickColorId);


            c.drawLine(
                    mRangeBuffers[0], mRangeBuffers[1],
                    mRangeBuffers[2], mRangeBuffers[3],
                    mRenderPaint);

            mRenderPaint.setStyle(Paint.Style.STROKE);
            mRenderPaint.setStrokeWidth(3);
            mRenderPaint.setColor(highColorId);
            c.drawCircle(mHighCircleBuffers[0], mHighCircleBuffers[1]- radius, radius, mRenderPaint);

            mRenderPaint.setColor(lowColorId);
            c.drawCircle(mLowCircleBuffers[0], mLowCircleBuffers[1] + radius, radius, mRenderPaint);

        }


    }

    public void setDesignParams(int highColorId, int lowColorId, int stickColorId){
        this.highColorId = highColorId;
        this.lowColorId = lowColorId;
        this.stickColorId = stickColorId;
    }
}
