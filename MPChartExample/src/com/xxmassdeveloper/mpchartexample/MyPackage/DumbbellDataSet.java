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

    private int externalHighCircleColor = Color.parseColor("#4ddf5f5d");
    private int externalLowCircleColor = Color.parseColor("#4d7996de");

    private float borderCircleRadius = 15f;
    private float centerCircleRadius = 10f;
    private float externalCircleRadius = 19f;

    private float dayDiastNorm;
    private float daySystNorm;
    private float nightDiastNorm;
    private float nightSystNorm;

    private float nightDividerPos;

    private boolean enableDayNorms;
    private boolean enableNightNorms;

    private boolean enableNightDivision = false;



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

    public void setExternalHighCircleColor(String parsableColor){
        externalHighCircleColor = Color.parseColor(parsableColor);
    }

    public void setExternalLowCircleColor(String parsableColor) {
        this.externalLowCircleColor = Color.parseColor(parsableColor);
    }

    public void setDayNorms(float systNorm, float diastNorm){
        this.daySystNorm = systNorm;
        this.dayDiastNorm = diastNorm;
    }

    public void setNightNorms(float systNorm, float diastNorm){
        this.nightSystNorm = systNorm;
        this.nightDiastNorm = diastNorm;
    }

    public void setNightDividerPos(float nightDividerPos) {
        this.nightDividerPos = nightDividerPos;
        enableNightDivision = true;
    }

    public void setSystolicBorderColorId(int systolicBorderColorId) {
        this.systolicBorderColorId = systolicBorderColorId;
    }

    public void setDaySystNorm(float daySystNorm) {
        this.daySystNorm = daySystNorm;
    }

    public void setDayDiastNorm(float dayDiastNorm) {
        this.dayDiastNorm = dayDiastNorm;
    }

    public void setNightDiastNorm(float nightDiastNorm) {
        this.nightDiastNorm = nightDiastNorm;
    }

    public void setNightSystNorm(float nightSystNorm) {
        this.nightSystNorm = nightSystNorm;
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

    public int getExternalHighCircleColor() {
        return externalHighCircleColor;
    }

    public int getExternalLowCircleColor() {
        return externalLowCircleColor;
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

    public float getDayDiastNorm() {
        return dayDiastNorm;
    }

    public float getDaySystNorm() {
        return daySystNorm;
    }

    public float getNightDiastNorm() {
        return nightDiastNorm;
    }

    public float getNightSystNorm() {
        return nightSystNorm;
    }

    public float getNightDividerPos() {
        return nightDividerPos;
    }

    public void enableDayNorms(boolean enable){
        enableDayNorms = enable;
    }
    public void enableNightNorms(boolean enable){
        enableNightNorms = enable;
    }

    public boolean isEnableDayNorms() {
        return enableDayNorms;
    }

    public boolean isEnableNightNorms() {
        return enableNightNorms;
    }

    public boolean isEnableNightDivision() {
        return enableNightDivision;
    }
}
