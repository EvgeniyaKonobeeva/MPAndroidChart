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

    private DumbbellDataSet dataSet;



    public DumbbellRenderer(CandleDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(chart, animator, viewPortHandler);
    }

    @Override
    protected void drawDataSet(Canvas c, ICandleDataSet idataSet) {
//        super.drawDataSet(c, dataSet);
        this.dataSet = (DumbbellDataSet)idataSet;

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

            float systolic = e.getLow();
            float xPos = e.getX();
            float diastolic = e.getHigh();
            float normSyst = e.getOpen();
            float normDiast = e.getClose();

            mRangeBuffers[0] = xPos;
            mRangeBuffers[1] = diastolic;
            mRangeBuffers[2] = xPos;
            mRangeBuffers[3] = systolic;

            mHighCircleBuffers[0] = xPos;
            mHighCircleBuffers[1] = diastolic;

            mLowCircleBuffers[0] = xPos;
            mLowCircleBuffers[1] = systolic;


            trans.pointValuesToPixel(mRangeBuffers);
            trans.pointValuesToPixel(mLowCircleBuffers);
            trans.pointValuesToPixel(mHighCircleBuffers);

            float highCenterY = mHighCircleBuffers[1]- dataSet.getBorderCircleRadius();
            float lowCenterY = mLowCircleBuffers[1] + dataSet.getBorderCircleRadius();


//            mRenderPaint.setColor(dataSet.getStickColor());
            mRenderPaint.setShader(null);
            if(diastolic > systolic){
                LinearGradient lg = new LinearGradient(mRangeBuffers[0], mRangeBuffers[1], mRangeBuffers[2], mRangeBuffers[3], dataSet.getSystolicBorderColorId(), dataSet.getDiastolicBorderColor(), Shader.TileMode.CLAMP);
                mRenderPaint.setShader(lg);
            }else{
                LinearGradient lg = new LinearGradient(mRangeBuffers[0], mRangeBuffers[3], mRangeBuffers[2], mRangeBuffers[1], dataSet.getDiastolicBorderColor(), dataSet.getSystolicBorderColorId(), Shader.TileMode.MIRROR);
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
            c.drawCircle(mHighCircleBuffers[0], highCenterY, dataSet.getBorderCircleRadius(), mRenderPaint);

//          Diastolic pressure
            mRenderPaint.setColor(dataSet.getDiastolicBorderColor());
            c.drawCircle(mLowCircleBuffers[0], lowCenterY, dataSet.getBorderCircleRadius(), mRenderPaint);

//          Center circle
            mRenderPaint.setColor(dataSet.getCentreCircleColor());
            c.drawCircle(mHighCircleBuffers[0], highCenterY, dataSet.getCenterCircleRadius(), mRenderPaint);
            c.drawCircle(mLowCircleBuffers[0], lowCenterY,  dataSet.getCenterCircleRadius(), mRenderPaint);

            if(diastolic > normDiast){
                mRenderPaint.setColor(dataSet.getExternalBiggerCircleColor());
                c.drawCircle(mLowCircleBuffers[0], lowCenterY,  dataSet.getExternalCircleRadius(), mRenderPaint);
            }else if(diastolic < normDiast){
                mRenderPaint.setColor(dataSet.getExternalSmallerCircleColor());
                c.drawCircle(mLowCircleBuffers[0], lowCenterY,  dataSet.getExternalCircleRadius(), mRenderPaint);
            }

            mRenderPaint.setStyle(Paint.Style.STROKE);
            if(systolic > normSyst){
                mRenderPaint.setColor(dataSet.getExternalBiggerCircleColor());
                c.drawCircle(mHighCircleBuffers[0], highCenterY,  dataSet.getExternalCircleRadius(), mRenderPaint);
            }else if(systolic < normSyst){
                mRenderPaint.setColor(dataSet.getExternalSmallerCircleColor());
                c.drawCircle(mHighCircleBuffers[0], highCenterY,  dataSet.getExternalCircleRadius(), mRenderPaint);
            }


        }


    }

}
