package bardiademon.Memoir.bardiademon.Class.View;

// Created by bardiademon on 24/02/2018_22:32.

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import bardiademon.Memoir.R;
import bardiademon.Memoir.bardiademon.Class.BeforeRunActivity.G;

import static bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues.getString;

public class Toast extends android.widget.Toast
{

    private int idIcon = 0;
    private String text;
    private Activity activity;
    private int duration = LENGTH_LONG;
    private static Toast toast;

    public static final int LENGTH_LONG = 0, LENGTH_SHORT = 1;

    private View view = null;

    @Deprecated
    public Toast ()
    {
        super (G.getActivity ());
        this.activity = G.getActivity ();
    }

    @Deprecated
    public Toast (String text)
    {
        super (G.getActivity ());
        this.activity = G.getActivity ();
        this.setText (text);
    }

    @Deprecated
    public void successful ()
    {
        idIcon = Icon.ICON_SUCCESSFUL;
    }

    @Deprecated
    public void error ()
    {
        idIcon = Icon.ICON_ERROR;
    }

    @Deprecated
    public void information ()
    {
        idIcon = Icon.ICON_INFORMATION;
    }

    @Deprecated
    public void warning ()
    {
        idIcon = Icon.ICON_WARNING;
    }

    public void setText (String text)
    {
        this.text = text;
    }

    public void setIcon (Integer icon)
    {
        if (!icon.equals (Icon.ICON_INFORMATION) && !icon.equals (Icon.ICON_ERROR) && !icon.equals (Icon.ICON_SUCCESSFUL) && !icon.equals (Icon.ICON_WARNING))
            icon = Icon.ICON_INFORMATION;

        idIcon = icon;
    }

    public String getText ()
    {
        return text;
    }

    public static void show (String msg)
    {
        show (Icon.ICON_INFORMATION, msg, LENGTH_LONG);
    }

    public static void show (Integer icon, String msg)
    {
        show (icon, msg, LENGTH_LONG);
    }

    public static void show (Integer icon, String msg, int duration)
    {
        if (toast == null) toast = new Toast ();
        toast.setIcon (icon);
        toast.setText (msg);
        toast.set_duration (duration);
        toast.show ();
        toast = null;
        System.gc ();
    }

    public void LENGTH_LONG ()
    {
        this.duration = 1;
    }

    public void LENGTH_SHORT ()
    {
        this.duration = 0;
    }

    public void set_duration (Integer duration)
    {
        if (!duration.equals (LENGTH_SHORT) && !duration.equals (LENGTH_LONG))
            duration = LENGTH_LONG;

        this.duration = duration;
    }

    @Override
    public void setView (View view)
    {
        this.view = view;
    }

    @SuppressLint ({"WrongConstant", "InflateParams"})
    private void set ()
    {
        if (view == null)
        {
            LayoutInflater layoutInflater = activity.getLayoutInflater ();
            view = layoutInflater.inflate (R.layout.xml_my_toast, null);
        }
        super.setView (view);

        if (idIcon == 0)
            idIcon = R.drawable.information;

        super.setDuration (duration);

        SetTools ();

        icon.setImageResource (idIcon);
        txtView.setText (text);

        super.setGravity (Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    public void show ()
    {
        set ();
        super.show ();
    }

    private ImageView icon;
    private TextView txtView;

    private void SetTools ()
    {
        icon = view.findViewById (R.id.icon);
        txtView = view.findViewById (R.id.text);
    }


    public abstract static class ToastReady
    {
        public static void PLEASE_ENTER_INFO ()
        {
            show (Icon.ICON_WARNING, getString ("toast__please_enter_info"));
        }

        public static void NOT_FOUND ()
        {
            show (Icon.ICON_INFORMATION, getString ("not_found"));
        }

        public static void ERROR ()
        {
            show (Icon.ICON_ERROR, getString ("error"));
        }

        public static void DONE ()
        {
            show (Icon.ICON_SUCCESSFUL, getString ("done"));
        }

        public static void CHANGED ()
        {
            show (Icon.ICON_SUCCESSFUL, getString ("changed"));
        }

        public static void DELETED ()
        {
            show (Icon.ICON_SUCCESSFUL, getString ("deleted"));
        }

        public static void ADDED ()
        {
            show (Icon.ICON_SUCCESSFUL, getString ("added"));
        }

        public static void WAS_SET ()
        {
            show (Icon.ICON_SUCCESSFUL, getString ("was_set"));
        }

        private static void show (int icon, String msg)
        {
            Toast.show (icon, msg);
            System.gc ();
        }
    }
}
