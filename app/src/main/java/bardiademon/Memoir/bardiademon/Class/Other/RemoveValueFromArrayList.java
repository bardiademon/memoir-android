package bardiademon.Memoir.bardiademon.Class.Other;

// bardiademon@gmail.com

import java.util.ArrayList;

public class RemoveValueFromArrayList
{
    private ArrayList<?> arrayList;
    private Object value;
    private boolean removed;
    private boolean all;

    public RemoveValueFromArrayList (ArrayList arrayList, Object value, boolean all)
    {
        this.arrayList = arrayList;
        this.value = value;
        this.all = all;
        remove();
    }

    private void remove ()
    {
        if (new SearchToArrayList(arrayList, value).isFound())
        {
            for (int i = 0, len = arrayList.size(); i < len; i++)
            {
                if (String.valueOf(arrayList.get(i)).equals(String.valueOf(value)))
                {
                    arrayList.remove(i);
                    removed = true;
                }

                if (removed && !all) return;
            }
        }
        else removed = false;
    }

    public boolean isRemoved ()
    {
        return removed;
    }

    public ArrayList<?> getArrayList ()
    {
        return arrayList;
    }
}
