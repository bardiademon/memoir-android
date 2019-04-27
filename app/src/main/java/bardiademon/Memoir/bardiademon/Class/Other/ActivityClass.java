package bardiademon.Memoir.bardiademon.Class.Other;

import bardiademon.Memoir.bardiademon.Class.BeforeRunActivity.G;
import bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues;

public abstract class ActivityClass
{

    public static String getSimpleName ()
    {
        return G.getActivityClass().getSimpleName();
    }

    public static String getName ()
    {
        return GetValues.getString(getSimpleName());
    }
}
