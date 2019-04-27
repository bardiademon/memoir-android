package bardiademon.Memoir.bardiademon.Class.Other.Math;

import android.content.res.Resources;
import android.util.TypedValue;

import java.util.Random;

import bardiademon.Memoir.bardiademon.Class.BeforeRunActivity.G;

public abstract class Math
{

    public static int CalculateThePercentOfANumber (int number, int percentage)
    {
        if (percentage < 1 || percentage > 99) return percentage;
        return (percentage * number) / 100;
    }

    public static int convertDPPX (int size)
    {
        Resources resources = G.getContext().getResources();
        size = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                size,
                resources.getDisplayMetrics()
        );
        return size;
    }

    public static Integer getRandom ()
    {
        Random random = new Random();
        return random.nextInt(999999999);
    }


    public static boolean isEven (int number)
    {
        return (number % 2 == 0);
    }

    public static boolean isOdd (int number)
    {
        return (number % 2 != 0);
    }

}
