package com.xxmassdeveloper.mpchartexample.MyPackage;

import com.github.mikephil.charting.data.CandleEntry;

/**
 * Created by e.konobeeva on 30.09.2016.
 */

public class DumbbellEntry extends CandleEntry {

    public DumbbellEntry(float x, float shadowH, float shadowL, float open, float close, Object data) {
        super(x, shadowH, shadowL, open, close, data);
    }
    public DumbbellEntry(float x, float systolic, float diastolic){
        super(x, systolic, diastolic, 0, 0);
    }
    public DumbbellEntry(float x, float systolic, float diastolic, float normSyst, float normDiast){
        super(x, systolic, diastolic, normSyst, normDiast);
    }


}
