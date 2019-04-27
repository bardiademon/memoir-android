package bardiademon.Memoir.bardiademon.Class.Other;

import java.util.Map;

public class SearchMap
{
    private static Object index;

    public static boolean SearchMap (Map<?, ?> map, Object what)
    {
        String valueSearch = String.valueOf(what);
        for (Map.Entry<?, ?> entry : map.entrySet())
        {
            if (valueSearch.equals(String.valueOf(entry.getValue())))
            {
                index = entry.getKey();
                return true;
            }
        }
        return false;
    }

    public static Object getIndex ()
    {
        return index;
    }

    public static String getIndexString ()
    {
        return String.valueOf(index);
    }

    public static Integer getIndexInteger ()
    {
        return Integer.parseInt(getIndexString());
    }
}
