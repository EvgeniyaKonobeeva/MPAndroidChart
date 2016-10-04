package com.xxmassdeveloper.mpchartexample.MyPackage;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;

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

    private Paint externalCirclePaint = new Paint();





    public DumbbellRenderer(CandleDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(chart, animator, viewPortHandler);
        externalCirclePaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void drawDataSet(Canvas c, ICandleDataSet idataSet) {
//        super.drawDataSet(c, dataSet);
        DumbbellDataSet dataSet = (DumbbellDataSet)idataSet;

        Transformer trans = mChart.getTransformer(dataSet.getAxisDependency());

        float phaseY = mAnimator.getPhaseY();
        float barSpace = dataSet.getBarSpace();

        mXBounds.set(mChart, dataSet);


        for (int j = mXBounds.min; j <= mXBounds.range + mXBounds.min; j++) {

            mRenderPaint.setStrokeWidth(dataSet.getShadowWidth());
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

//            mHighCircleBuffers[1] = mHighCircleBuffers[1]- dataSet.getBorderCircleRadius();
//            mLowCircleBuffers[1] = mLowCircleBuffers[1] + dataSet.getBorderCircleRadius();


//            mRenderPaint.setColor(dataSet.getStickColor());
            mRenderPaint.setShader(null);
            if(mRangeBuffers[1] > mRangeBuffers[3]){
                LinearGradient lg = new LinearGradient(0, mRangeBuffers[1], 0, mRangeBuffers[3], dataSet.getSystolicBorderColorId(), dataSet.getDiastolicBorderColor(), Shader.TileMode.MIRROR);
                mRenderPaint.setShader(lg);
            }else{
                LinearGradient lg = new LinearGradient(0, mRangeBuffers[3], 0, mRangeBuffers[1], dataSet.getDiastolicBorderColor(), dataSet.getSystolicBorderColorId(), Shader.TileMode.MIRROR);
                mRenderPaint.setShader(lg);
            }

            c.drawLine(
                    mRangeBuffers[0], mRangeBuffers[1],
                    mRangeBuffers[2], mRangeBuffers[3],
                    mRenderPaint);

            mRenderPaint.setShader(null);

//          Systolic pressure
            mRenderPaint.setStyle(Paint.Style.FILL);
            mRenderPaint.setColor(dataSet.getSystolicBorderColorId());
            c.drawCircle(mHighCircleBuffers[0], mHighCircleBuffers[1], dataSet.getBorderCircleRadius(), mRenderPaint);

//          Diastolic pressure
            mRenderPaint.setColor(dataSet.getDiastolicBorderColor());
            c.drawCircle(mLowCircleBuffers[0], mLowCircleBuffers[1], dataSet.getBorderCircleRadius(), mRenderPaint);

//          Center circle
            mRenderPaint.setColor(dataSet.getCentreCircleColor());
            c.drawCircle(mHighCircleBuffers[0], mHighCircleBuffers[1], dataSet.getCenterCircleRadius(), mRenderPaint);
            c.drawCircle(mLowCircleBuffers[0], mLowCircleBuffers[1],  dataSet.getCenterCircleRadius(), mRenderPaint);


            getPressureNorms((DumbbellEntry) e, dataSet, c);


        }


    }

    protected void drawExternalCircle(float pressure, float norma, Canvas c, DumbbellDataSet dataSet, boolean systolic){

        float strokeWidth = dataSet.getExternalCircleRadius() - dataSet.getBorderCircleRadius();
        externalCirclePaint.setStrokeWidth(strokeWidth*2);
        if(systolic) {
            if (pressure > norma) {
                externalCirclePaint.setColor(dataSet.getExternalHighCircleColor());
                c.drawCircle(mLowCircleBuffers[0], mLowCircleBuffers[1], dataSet.getExternalCircleRadius(), externalCirclePaint);
            } else if (pressure < norma) {
                externalCirclePaint.setColor(dataSet.getExternalLowCircleColor());
                c.drawCircle(mLowCircleBuffers[0], mLowCircleBuffers[1], dataSet.getExternalCircleRadius(), externalCirclePaint);
            }
        }else {
            if (pressure > norma) {
                externalCirclePaint.setColor(dataSet.getExternalHighCircleColor());/*Color.parseColor("#4ddf5f5d")*/
                c.drawCircle(mHighCircleBuffers[0], mHighCircleBuffers[1], dataSet.getExternalCircleRadius(), externalCirclePaint);
            } else if (pressure < norma) {
                externalCirclePaint.setColor(dataSet.getExternalLowCircleColor()); /*Color.parseColor("#4d7996de")*/
                c.drawCircle(mHighCircleBuffers[0], mHighCircleBuffers[1], dataSet.getExternalCircleRadius(), externalCirclePaint);
            }
        }
    }

    protected void getPressureNorms(DumbbellEntry entry, DumbbellDataSet dataSet, Canvas c){
        if(dataSet.isEnableDayNorms()){
            if(dataSet.isEnableNightDivision() && dataSet.isEnableNightNorms()){
                if(entry.getX() <  dataSet.getNightDividerPos()){ /* day*/
                    drawExternalCircle(entry.getHigh(), dataSet.getDayDiastNorm(), c, dataSet, false);
                    drawExternalCircle(entry.getLow(), dataSet.getDaySystNorm(), c, dataSet, true);
                }else{ /*night*/
                    drawExternalCircle(entry.getHigh(), dataSet.getNightDiastNorm(), c, dataSet, false);
                    drawExternalCircle(entry.getLow(), dataSet.getNightSystNorm(), c, dataSet, true);
                }
            }else {
                drawExternalCircle(entry.getHigh(), dataSet.getDayDiastNorm(), c, dataSet, false);
                drawExternalCircle(entry.getLow(), dataSet.getDaySystNorm(), c, dataSet, true);
            }
        }else if(dataSet.isEnableNightNorms()) {
            drawExternalCircle(entry.getHigh(), dataSet.getNightDiastNorm(), c, dataSet, false);
            drawExternalCircle(entry.getLow(), dataSet.getNightSystNorm(), c, dataSet, true);
        }
    }

}
