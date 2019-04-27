package bardiademon.Memoir.bardiademon.Class.View;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.Paint;
import android.graphics.drawable.shapes.RoundRectShape;

import bardiademon.Memoir.bardiademon.Class.Other.Math.Math;

public class GetShapeDrawable
{
    public static ShapeDrawable get (int color, float[] margin)
    {
        for (int i = 0; i < margin.length; i++)
            margin[i] = Math.convertDPPX((int) margin[i]);

        RoundRectShape rectShape = new RoundRectShape(margin, null, null);

        ShapeDrawable shapeDrawable = new ShapeDrawable(rectShape);
        shapeDrawable.getPaint().setColor(color);
        shapeDrawable.getPaint().setStyle(Paint.Style.FILL);
        shapeDrawable.getPaint().setAntiAlias(true);
        shapeDrawable.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG);
        return shapeDrawable;
    }
}
