package com.xxmassdeveloper.mpchartexample.MyPackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.renderer.YAxisRenderer;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.List;

/**
 * Created by e.konobeeva on 30.09.2016.
 */

public class MyYAxisRenderer extends YAxisRenderer {
    public MyYAxisRenderer(ViewPortHandler viewPortHandler, YAxis yAxis, Transformer trans) {
        super(viewPortHandler, yAxis, trans);
    }

    @Override
    public void renderLimitLines(Canvas c) {
//        super.renderLimitLines(c);
        List<LimitLine> limitLines = mYAxis.getLimitLines();

        if (limitLines == null || limitLines.size() <= 0)
            return;

        float[] pts = mRenderLimitLinesBuffer;
        pts[0] = 0;
        pts[1] = 0;
        Path limitLinePath = mRenderLimitLines;
        limitLinePath.reset();

        for (int i = 0; i < limitLines.size(); i++) {

            MyLimitLine line = (MyLimitLine) limitLines.get(i);

            if (!line.isEnabled())
                continue;

            int clipRestoreCount = c.save();
            mLimitLineClippingRect.set(mViewPortHandler.getContentRect());
            mLimitLineClippingRect.inset(0.f, -line.getLineWidth() / 2.f);
            c.clipRect(mLimitLineClippingRect);

            mLimitLinePaint.setStyle(Paint.Style.STROKE);
            mLimitLinePaint.setColor(line.getLineColor());
            mLimitLinePaint.setStrokeWidth(line.getLineWidth());
            mLimitLinePaint.setPathEffect(line.getDashPathEffect());

            pts[1] = line.getLimit();
            float xPosBuff[] = new float[2];
            float xPosBuff2[] = new float[2];

            xPosBuff[0] = line.getBeginPos();
//            xPosBuff[1] = /*line.getEndPos() == 0 ? mViewPortHandler.contentRight() : */line.getEndPos();
            xPosBuff2[0] = line.getEndPos();
//            xPosBuff2[1] = /*line.getEndPos() == 0 ? mViewPortHandler.contentRight() : */line.getEndPos();

            mTrans.pointValuesToPixel(pts);
            mTrans.pointValuesToPixel(xPosBuff);
            mTrans.pointValuesToPixel(xPosBuff2);

            limitLinePath.moveTo(xPosBuff[0], pts[1]);
            limitLinePath.lineTo(xPosBuff2[0], pts[1]);


            c.drawPath(limitLinePath, mLimitLinePaint);
            limitLinePath.reset();
            // c.drawLines(pts, mLimitLinePaint);

            c.restoreToCount(clipRestoreCount);
        }
    }
}
