package bardiademon.Memoir.bardiademon.Class.Other;

// Created by bardiademon on 12/03/2018_19:47.

import android.content.Intent;

import bardiademon.Memoir.bardiademon.Class.BeforeRunActivity.G;
import bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues;
import bardiademon.Memoir.bardiademon.Class.View.Icon;
import bardiademon.Memoir.bardiademon.Class.View.Toast;

public abstract class CloseTheApp
{

    public static void close ()
    {
        close(false);
    }

    public static void close (boolean showMessage)
    {
        if (showMessage)
            Toast.show(Icon.ICON_ERROR, GetValues.getString("toast__for_class__close_the_app"));
        G.getActivity().finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        G.getActivity().startActivity(intent);
    }
}
