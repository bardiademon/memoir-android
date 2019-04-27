package bardiademon.Memoir.bardiademon.Class.View.Form;

import java.util.Map;

public class SearchMap
{
    private static Integer index;
    private static int index2;

    public static boolean SearchMap (Map<?, ?> map, Object what)
    {
        if (map == null) return false;
        String valueSearch = String.valueOf(what);
        for (Map.Entry<?, ?> entry : map.entrySet())
        {
            if (valueSearch.equals(String.valueOf(entry.getValue())))
            {
                index = (Integer) entry.getKey();
                return true;
            }
        }
        return false;
    }

    public static boolean SearchMap2 (Map<?, ?> map, Object what)
    {
        if (map == null) return false;
        for (Map.Entry<?, ?> entry : map.entrySet())
        {
            if (entry.getValue() instanceof Map<?, ?>)
            {
                if (SearchMap2((Map<?, ?>) entry.getValue(), what))
                {
                    index2 = (Integer) entry.getKey();
                    return true;
                }
            }
            else
                return SearchMap(map, what);
        }
        return false;
    }

    public static Integer getIndexInstanceofMap ()
    {
        return index2;
    }

    public static Integer getIndex ()
    {
        return index;
    }

}
