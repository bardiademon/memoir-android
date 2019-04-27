package bardiademon.Memoir.bardiademon.Class.Other.GetFromValue;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;

import bardiademon.Memoir.bardiademon.Class.BeforeRunActivity.G;
import bardiademon.Memoir.bardiademon.Class.Other.CloseTheApp;

public abstract class GetValues
{

    public static final int GET_STRING__WITH_SPACE = 0, GET_STRING__WITH_COMMA = 1, GET_STRING__WITH_COMMA_SPACE = 2, GET_STRING__WITH_SPACE_COMMA = 3, GET_STRING__NEW_LINE = 4;

    @NonNull
    public static String getString (String name)
    {
        String string;
        try
        {
            string = (getResources().getString(getIdentifier(name, "string")));
        }
        catch (Exception e)
        {
            try
            {
                string = getString("bardiademon");
            }
            catch (Exception e1)
            {
                string = "Error: String not found!";
            }
        }
        return string;
    }

    public static String[] getStringArray (String name)
    {
        String[] string;
        try
        {
            string = (getResources().getStringArray(getIdentifier(name, "array")));
        }
        catch (Exception e)
        {
            string = null;
        }
        return string;
    }

    public static String getStringArray (Integer withWhat, String name)
    {
        String with = withWhat(withWhat);
        StringBuilder result = new StringBuilder();
        String[] stringArray = getStringArray(name);
        for (int i = 0, len = stringArray.length; i < len; i++)
        {
            result.append(stringArray[i]);
            if (i + 1 < len) result.append(with);
        }
        return result.toString();
    }

    public static String getString (String... name)
    {
        StringBuilder result = new StringBuilder();
        for (String str : name)
            result.append(getString(str));

        return result.toString();
    }

    public static String getString (Integer withWhat, String... name)
    {
        String with = withWhat(withWhat);
        StringBuilder result = new StringBuilder();
        for (int i = 0, len = name.length; i < len; i++)
        {
            result.append(getString(name[i]));
            if (i + 1 < len) result.append(with);
        }
        return result.toString();
    }

    private static String withWhat (Integer withWhat)
    {
        switch (withWhat)
        {
            case GET_STRING__WITH_SPACE:
                return " ";
            case GET_STRING__WITH_COMMA:
                return ",";
            case GET_STRING__WITH_COMMA_SPACE:
                return ", ";
            case GET_STRING__WITH_SPACE_COMMA:
                return " ,";
            case GET_STRING__NEW_LINE:
                return "\n";
            default:
                return "";
        }
    }

    public static int getColor (String name)
    {
        int color;
        try
        {
            color = (getResources().getColor(getIdentifier(name, "color")));
        }
        catch (Exception e)
        {
            color = Color.BLACK;
        }
        return color;
    }

    public static <T extends View> T getTools (String idName)
    {
        try
        {
            int id = getIdentifier(idName, "id");
            return G.getView().findViewById(id);
        }
        catch (Exception e)
        {
            CloseTheApp.close(true);
        }
        return null;
    }

    public static View getView (String nameLayout)
    {
        View view = null;
        try
        {
            XmlResourceParser layout = getResources().getLayout(getIdentifier(nameLayout, "layout"));
            view = G.getActivity().getLayoutInflater().inflate(layout, null);
        }
        catch (Exception ignored)
        {
        }
        return view;
    }

    public static View getView (int resourcesId)
    {
        View view = null;
        try
        {
            view = G.getActivity().getLayoutInflater().inflate(resourcesId, null);
        }
        catch (Exception ignored)
        {
        }
        return view;
    }

    private static int getIdentifier (String name, String defType)
    {
        int identifier = 0;
        try
        {
            identifier = getResources().getIdentifier(name, defType, G.getContext().getPackageName());
        }
        catch (Exception e)
        {
            CloseTheApp.close();
        }
        return identifier;
    }

    private static Resources getResources ()
    {
        Resources resources = null;
        try
        {
            resources = G.getContext().getResources();
        }
        catch (Exception ignored)
        {
        }
        return resources;
    }
}