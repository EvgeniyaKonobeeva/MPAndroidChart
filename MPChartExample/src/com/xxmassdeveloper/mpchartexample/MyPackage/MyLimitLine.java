package com.xxmassdeveloper.mpchartexample.MyPackage;

import com.github.mikephil.charting.components.LimitLine;

/**
 * Created by e.konobeeva on 30.09.2016.
 */

public class MyLimitLine extends LimitLine {

    private float beginPos = -1f;
    private float endPos = -1f;

    public MyLimitLine(float limit, float beginPos, float endPos){
        super(limit);
        this.beginPos = beginPos;
        this.endPos = endPos;
    }

    public float getEndPos() {
        return endPos;
    }


    public float getBeginPos() {
        return beginPos;
    }

    public void setBeginPos(float beginPos) {
        this.beginPos = beginPos;
    }

    public void setEndPos(float endPos) {
        this.endPos = endPos;
    }

}
