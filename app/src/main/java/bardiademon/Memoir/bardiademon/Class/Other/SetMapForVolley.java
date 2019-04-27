package bardiademon.Memoir.bardiademon.Class.Other;

import java.util.HashMap;
import java.util.Map;

public abstract class SetMapForVolley
{
    public static Map<String, String> set (String[][] info)
    {
        if (info == null) return null;

        Map<String, String> param = new HashMap<>();

        for (String[] value : info)
            param.put(value[0] , value[1]);


        return param;
    }
}
