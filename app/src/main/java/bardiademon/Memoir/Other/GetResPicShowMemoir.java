package bardiademon.Memoir.Other;

import bardiademon.Memoir.R;

public class GetResPicShowMemoir
{
    public int get (String subject)
    {
        final String
                STR_LOVE = "عشق",
                STR_LOVER = "عاشقانه",
                STR_BITTER = "تلخ",
                STR_NOVEL = "رمان",
                STR_MEMOIR = "خاطرات",
                STR_STORY = "داستان";
        final int
                RES_P_DEFAULT = R.drawable.background_show_memoir,
                RES_P_LOVE = R.drawable.lover,
                RES_P_DISLOVE = R.drawable.dislove,
                RES_P_DISLOVE2 = R.drawable.dislove2,
                RES_P_HUT = R.drawable.hut;

        if (subject.contains (STR_LOVE) || subject.contains (STR_LOVER))
        {
            if (subject.contains (STR_NOVEL)) return RES_P_HUT;
            else if (subject.contains (STR_STORY)) return RES_P_LOVE;
            else if (subject.contains (STR_MEMOIR)) return RES_P_LOVE;
            else return RES_P_DEFAULT;
        }
        else if (subject.contains (STR_BITTER))
        {
            if (subject.contains (STR_NOVEL)) return RES_P_DISLOVE;
            else if (subject.contains (STR_STORY)) return RES_P_DISLOVE2;
            else if (subject.contains (STR_MEMOIR)) return RES_P_DISLOVE2;
            else return RES_P_DEFAULT;
        }
        else return RES_P_DEFAULT;
    }
}
