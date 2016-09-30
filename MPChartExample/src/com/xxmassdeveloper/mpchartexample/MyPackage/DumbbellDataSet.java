package com.xxmassdeveloper.mpchartexample.MyPackage;

import android.graphics.Color;

import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;

import java.util.List;

/**
 * Created by e.konobeeva on 30.09.2016.
 */

public class DumbbellDataSet extends CandleDataSet {

    private int systolicBorderColorId = Color.parseColor("#df5f5d");
    private int diastolicBorderColor = Color.parseColor("#7a96de");
    private int stickColor = Color.parseColor("#f5aac0");
    private int centreCircleColor = Color.parseColor("#fafafa");

    private int externalBiggerCircleColor = Color.BLUE;
    private int externalSmallerCircleColor = Color.RED;

    private float borderCircleRadius = 10f;
    private float centerCircleRadius = 6f;
    private float externalCircleRadius = 13f;


    public DumbbellDataSet(List<CandleEntry> yVals, String label) {
        super(yVals, label);
    }

    public void setSystolicBorderColor(String parsableColor){
        systolicBorderColorId = Color.parseColor(parsableColor);
    }
    public void setDiastolicBorderColor(String parsableColor){
        diastolicBorderColor = Color.parseColor(parsableColor);
    }

    public void setStickColor(String parsableColor){
        stickColor = Color.parseColor(parsableColor);
    }
    public void setCentreCircleColor(String parsableColor){
        centreCircleColor = Color.parseColor(parsableColor);
    }

    public void setBorderCircleRadius(float radiusPx){
        borderCircleRadius = radiusPx;
    }

    public void setExternalCircleRadius(float radiusPx){
        externalCircleRadius = radiusPx;
    }

    public void setCenterCircleRadius(float radiusPx){
        centerCircleRadius = radiusPx;
    }

    public void setExternalBiggerCircleColor(String parsableColor){
        externalBiggerCircleColor = Color.parseColor(parsableColor);
    }

    public void setExternalSmallerCircleColor(String parsableColor) {
        this.externalSmallerCircleColor = Color.parseColor(parsableColor);
    }

    public int getSystolicBorderColorId() {
        return systolicBorderColorId;
    }

    public int getDiastolicBorderColor() {
        return diastolicBorderColor;
    }

    public int getStickColor() {
        return stickColor;
    }

    public int getCentreCircleColor() {
        return centreCircleColor;
    }

    public int getExternalBiggerCircleColor() {
        return externalBiggerCircleColor;
    }

    public int getExternalSmallerCircleColor() {
        return externalSmallerCircleColor;
    }

    public float getBorderCircleRadius() {
        return borderCircleRadius;
    }

    public float getCenterCircleRadius() {
        return centerCircleRadius;
    }

    public float getExternalCircleRadius() {
        return externalCircleRadius;
    }
}
