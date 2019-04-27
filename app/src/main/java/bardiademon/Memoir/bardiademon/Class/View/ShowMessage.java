package bardiademon.Memoir.bardiademon.Class.View;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import bardiademon.Memoir.R;
import bardiademon.Memoir.bardiademon.Class.BeforeRunActivity.G;
import bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues;
import bardiademon.Memoir.bardiademon.Class.Other.Math.Math;
import bardiademon.Memoir.bardiademon.Interface.Title;

@SuppressLint("StaticFieldLeak")
public abstract class ShowMessage
{
    private static AlertDialog.Builder builder;
    private static AlertDialog alertDialog;
    private static LinearLayout mainLayout;
    private static SetTools setTools;
    private static final int GRAVITY = Gravity.END;

    public static void show (String title , String message)
    {
        show (title , message , Icon.NO_ICON , GRAVITY , null);
    }

    public static void show (String title , String message , int icon)
    {
        show (title , message , icon , GRAVITY , null);
    }

    public static void show (String title , String message , int icon , AfterClick AfterClickOk)
    {
        show (title , message , icon , GRAVITY , AfterClickOk);
    }

    public static void show (String title , String message , int icon , int gravityMessage)
    {
        show (title , message , icon , gravityMessage , null);
    }

    public static void show (String title , String message , int icon , int gravityMessage , AfterClick AfterClickOk)
    {
        setTools = new SetTools (title , message , icon , gravityMessage , AfterClickOk);
        mainLayout = setTools.getMainLayout ();
        set (icon);
    }

    private static void set (int icon)
    {
        SetLayout ();
        sound (icon);
        Show ();
    }

    private static void SetLayout ()
    {
        builder = new AlertDialog.Builder (G.getActivity ());
        builder.setView (mainLayout);
        builder.setCancelable (false);
        alertDialog = builder.create ();
    }

    private static void Show ()
    {
        if (CheckBuilder () && !alertDialog.isShowing ()) alertDialog.show ();
    }

    private static void Cancel ()
    {
        if (CheckBuilder () && alertDialog.isShowing ()) alertDialog.cancel ();
        clean ();
    }

    private static void clean ()
    {
        builder = null;
        alertDialog = null;
        mainLayout = null;
        setTools.clean ();
        setTools = null;
        System.gc ();
    }

    private static boolean CheckBuilder ()
    {
        return (builder != null && alertDialog != null);
    }

    private static void sound (int icon)
    {
        switch (icon)
        {
            case Icon.ICON_ERROR:
                playSound (R.raw.error);
                break;

            case Icon.ICON_WARNING:
                playSound (R.raw.warning);
                break;

            case Icon.ICON_INFORMATION:
                playSound (R.raw.successful);
                break;

            case Icon.ICON_SUCCESSFUL:
                playSound (R.raw.successful);
                break;
        }
    }

    private static void playSound (int sound)
    {
        MediaPlayer mediaPlayer = MediaPlayer.create (G.getActivity () , sound);
        mediaPlayer.start ();
    }

    private static class SetTools implements Title
    {
        private TextView btnOk;
        private bardiademon.Memoir.bardiademon.Class.View.Title viewTitleAndMessage;
        private LinearLayout mainLayout;
        private AfterClick callable;
        private String title, message;
        private int gravity;
        private boolean setIcon = false;
        private SetIconMessage setIconMessage;
        private LinearLayout layoutShowMessageAndIcon, layoutMessage, layoutFinalMessageAndIcon;
        private ScrollView scrollView;

        SetTools (String title , String message , Integer icon , int gravityMessage , AfterClick AfterClickOk)
        {
            this.title = title;
            this.message = message;
            callable = AfterClickOk;
            this.gravity = gravityMessage;
            if (!icon.equals (Icon.NO_ICON) && (icon.equals (0) || icon.equals (Icon.ICON_INFORMATION) || icon.equals (Icon.ICON_SUCCESSFUL) || icon.equals (Icon.ICON_WARNING) || icon.equals (Icon.ICON_ERROR)))
            {
                setIcon = true;
                setIconMessage = new SetIconMessage (icon);
            }
            SetTools ();
            set ();
            setOnClickBtnOk ();
        }

        private void SetTools ()
        {
            btnOk = new Button (G.getActivity () , null , android.R.attr.buttonBarButtonStyle);
            btnOk.setBackgroundColor (GetValues.getColor ("COLOR_WHITE"));

            mainLayout = new LinearLayout (G.getActivity ());
            mainLayout.setBackgroundColor (GetValues.getColor ("COLOR_WHITE"));

            layoutFinalMessageAndIcon = new LinearLayout (G.getActivity ());
            scrollView = new ScrollView (G.getActivity ());
            layoutFinalMessageAndIcon.setOrientation (LinearLayout.HORIZONTAL);

            viewTitleAndMessage = new bardiademon.Memoir.bardiademon.Class.View.Title ();
            if (isSetIcon ())
            {
                layoutMessage = new LinearLayout (G.getActivity ());
                layoutMessage.setBackgroundColor (GetValues.getColor ("COLOR_WHITE"));

                layoutShowMessageAndIcon = new LinearLayout (G.getActivity ());
                layoutShowMessageAndIcon.setBackgroundColor (GetValues.getColor ("COLOR_WHITE"));
            }
        }

        private void set ()
        {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams (LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.MATCH_PARENT);
            mainLayout.setLayoutParams (params);
            mainLayout.setOrientation (LinearLayout.VERTICAL);

            LinearLayout.LayoutParams paramsLayoutFinalMessageNdIcon = new LinearLayout.LayoutParams (LinearLayout.LayoutParams.MATCH_PARENT , 0);
            paramsLayoutFinalMessageNdIcon.weight = 0.9f;
            layoutFinalMessageAndIcon.setLayoutParams (paramsLayoutFinalMessageNdIcon);
            LinearLayout.LayoutParams paramsScrollView = new LinearLayout.LayoutParams (LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);
            scrollView.setLayoutParams (paramsScrollView);

            Title ();
            if (isSetIcon ())
            {
                layoutShowMessageAndIcon.addView (getLayoutIcon () , 0);
                layoutShowMessageAndIcon.addView (layoutMessage , 1);

                scrollView.addView (layoutShowMessageAndIcon);
                layoutFinalMessageAndIcon.addView (scrollView);

                mainLayout.addView (layoutFinalMessageAndIcon , 1);
            }
            setBtnOk ();
        }

        @Override
        public void Title ()
        {
            viewTitleAndMessage.setMainLayout (mainLayout);
            viewTitleAndMessage.setText (title);
            viewTitleAndMessage.setIndex (bardiademon.Memoir.bardiademon.Class.View.Title.indexTop);
            viewTitleAndMessage.setGravity (Gravity.END);
            viewTitleAndMessage.apply ();

            viewTitleAndMessage.setText (message);
            viewTitleAndMessage.setGravity (gravity);
            viewTitleAndMessage.setBackgroundColor (GetValues.getColor ("COLOR_WHITE"));
            viewTitleAndMessage.setTextColor (GetValues.getColor ("COLOR_BLACK"));
            int margin = Math.convertDPPX (15);
            if (isSetIcon ())
            {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams (LinearLayout.LayoutParams.MATCH_PARENT , 0);
                params.weight = 0.8f;
                params.setMargins (margin , margin , margin , margin);
                layoutShowMessageAndIcon.setLayoutParams (params);
                layoutShowMessageAndIcon.setOrientation (LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams paramsLayoutShowMessage = new LinearLayout.LayoutParams (0 , LinearLayout.LayoutParams.WRAP_CONTENT);
                paramsLayoutShowMessage.weight = 0.8f;
                layoutMessage.setLayoutParams (paramsLayoutShowMessage);
                layoutMessage.setOrientation (LinearLayout.HORIZONTAL);
                layoutMessage.setGravity (gravity);
                viewTitleAndMessage.setMainLayout (layoutMessage);
            }
            else
            {
                viewTitleAndMessage.setMargin (margin);
                viewTitleAndMessage.setIndex (bardiademon.Memoir.bardiademon.Class.View.Title.indexAfterAbout);
                viewTitleAndMessage.setCurvedMargin (false);
            }
            viewTitleAndMessage.apply ();
        }

        private void setBtnOk ()
        {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams (LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);
            int margin = Math.convertDPPX (5);
            int padding = Math.convertDPPX (10);

            params.setMargins (margin , margin , margin , margin);
            btnOk.setLayoutParams (params);
            btnOk.setPadding (padding , padding , padding , padding);
            btnOk.setText (GetValues.getString ("btn_ok"));
            btnOk.setTextColor (GetValues.getColor ("COLOR_BLACK"));
            btnOk.setTypeface (Typeface.createFromAsset (G.getAssetsManager () , "iranian_sans.ttf"));
            btnOk.setGravity (Gravity.CENTER);

            mainLayout.addView (btnOk , 2);
        }

        private void setOnClickBtnOk ()
        {
            btnOk.setOnClickListener (v ->
            {
                if (callable != null) callable.AfterClickOk ();
                Cancel ();
            });
        }

        void clean ()
        {
            title = null;
            message = null;
            callable = null;
            viewTitleAndMessage = null;
            btnOk = null;
            mainLayout = null;
            if (isSetIcon ())
            {
                layoutMessage = null;
                layoutShowMessageAndIcon = null;
            }
        }

        LinearLayout getMainLayout ()
        {
            return mainLayout;
        }

        private boolean isSetIcon ()
        {
            return setIcon;
        }

        private LinearLayout getLayoutIcon ()
        {
            if (setIconMessage != null) return setIconMessage.getLayout ();

            return null;
        }

        private class SetIconMessage
        {
            private int icon;
            private LinearLayout linearLayout;
            private ImageView imgShowIcon;

            SetIconMessage (Integer icon)
            {
                if (icon.equals (0)) icon = Icon.ICON_INFORMATION;
                this.icon = icon;
                RunClass ();
            }

            private void RunClass ()
            {
                SetTools ();
                set ();
            }

            private void SetTools ()
            {
                linearLayout = new LinearLayout (G.getActivity ());
                linearLayout.setBackgroundColor (GetValues.getColor ("COLOR_WHITE"));

                imgShowIcon = new ImageView (G.getActivity ());
                imgShowIcon.setBackgroundColor (GetValues.getColor ("COLOR_WHITE"));
            }

            private void set ()
            {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams (0 , LinearLayout.LayoutParams.WRAP_CONTENT);
                params.weight = 0.2f;
                linearLayout.setOrientation (LinearLayout.HORIZONTAL);
                linearLayout.setGravity (Gravity.CENTER);
                linearLayout.setLayoutParams (params);

                int wh = Math.convertDPPX (50);
                params = new LinearLayout.LayoutParams (wh , wh);
                imgShowIcon.setLayoutParams (params);
                Glide.with (G.getActivity ()).load (icon).error (Glide.with (G.getActivity ()).load (Icon.ICON_ERROR)).into (imgShowIcon);

                linearLayout.addView (imgShowIcon);
            }

            public LinearLayout getLayout ()
            {
                return linearLayout;
            }
        }
    }

    public interface AfterClick
    {
        void AfterClickOk ();
    }
}
