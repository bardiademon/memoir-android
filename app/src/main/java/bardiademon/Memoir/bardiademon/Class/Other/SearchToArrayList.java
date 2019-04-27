package bardiademon.Memoir.bardiademon.Class.Other;

import java.util.ArrayList;

// bardiademon@gmail.com

public class SearchToArrayList
{
    private ArrayList<?> arrayList;
    private Object what;
    private boolean found;
    private int index = -1;

    public SearchToArrayList (ArrayList<?> arrayList, Object what)
    {
        this.arrayList = arrayList;
        this.what = what;
        search();
    }

    private void search ()
    {
        for (int i = 0, len = arrayList.size(); i < len; i++)
        {
            Object value = arrayList.get(i);
            if (String.valueOf(value).equals(String.valueOf(what)))
            {
                index = i;
                found = true;
                return;
            }
        }
        found = false;
    }

    public boolean isFound ()
    {
        return found;
    }

    public int getIndex ()
    {
        return index;
    }
}
