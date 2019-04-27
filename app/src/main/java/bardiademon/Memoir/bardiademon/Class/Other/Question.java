package bardiademon.Memoir.bardiademon.Class.Other;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Map;

import bardiademon.Memoir.bardiademon.Interface.CallBack;

import bardiademon.Memoir.bardiademon.Class.BeforeRunActivity.G;
import bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues;
import bardiademon.Memoir.bardiademon.Class.Other.Math.Math;
import bardiademon.Memoir.bardiademon.Class.View.Title;
import bardiademon.Memoir.bardiademon.Interface.bardiademon;

import static bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues.getColor;
import static bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues.getString;

@bardiademon
public class Question
{

    public final static int YES_NO = 0, YES_NO_CANCEL = 1, YES_CANCEL = 2,
            CANCEL = 0, YES = 1, NO = 2;

    private int typeQuestion;
    private String message;

    private LinearLayout mainLayout, linearLayout;

    private Map<String, Button> btn;

    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    private CallBack _CallBack;
    private int answer;

    @bardiademon
    public Question (CallBack CallBack , String message)
    {
        this (CallBack , message , YES_NO);
    }

    @bardiademon
    public Question (CallBack callable , String message , int typeQuestion)
    {
        if (typeQuestion < 0 || typeQuestion > 2) return;
        this._CallBack = callable;
        this.message = message;
        this.typeQuestion = typeQuestion;
        RunClass ();
    }

    @bardiademon
    private void RunClass ()
    {
        new SetTools ();
        SetOnClick ();
        SetLayout ();
        Show ();
    }

    @bardiademon
    private void SetLayout ()
    {
        builder = new AlertDialog.Builder (G.getActivity ());
        builder.setView (mainLayout);
        builder.setCancelable (false);
        alertDialog = builder.create ();
    }

    @bardiademon
    private void Show ()
    {
        if (CheckBuilder () && !alertDialog.isShowing ()) alertDialog.show ();
    }

    @bardiademon
    private void Cancel ()
    {
        if (CheckBuilder () && alertDialog.isShowing ()) alertDialog.cancel ();
    }

    @bardiademon
    private boolean CheckBuilder ()
    {
        return (alertDialog != null && builder != null);
    }

    @bardiademon
    private void afterAnswer (int answer)
    {
        this.answer = answer;
        try
        {
            _CallBack.Call ();
        }
        catch (Exception e)
        {
            CloseTheApp.close (true);
        }
    }

    @bardiademon
    private void SetOnClick ()
    {
        setOnClickListener (btn.get ("yes") , YES);
        switch (typeQuestion)
        {
            case YES_NO:
                setOnClickListener (btn.get ("no") , NO);
                break;

            case YES_CANCEL:
                setOnClickListener (btn.get ("cancel") , CANCEL);
                break;

            case YES_NO_CANCEL:
                setOnClickListener (btn.get ("no") , NO);
                setOnClickListener (btn.get ("cancel") , CANCEL);
                break;
        }
    }

    @bardiademon
    private void setOnClickListener (Button btn , final int type)
    {
        btn.setOnClickListener (v ->
        {
            afterAnswer (type);
            Cancel ();
        });
    }

    @bardiademon
    public int getAnswer ()
    {
        return answer;
    }

    @bardiademon
    public boolean clickYes ()
    {
        return (getAnswer () == YES);
    }

    @bardiademon
    public boolean clickNo ()
    {
        return (getAnswer () == NO);
    }

    @bardiademon
    public boolean clickCancel ()
    {
        return (getAnswer () == CANCEL);
    }

    @bardiademon
    class SetTools
    {
        @bardiademon
        SetTools ()
        {
            makeLayout ();
        }

        @bardiademon
        private void makeLayout ()
        {
            int margin = 10;
            mainLayout = new LinearLayout (G.getContext ());
            mainLayout.setBackgroundColor (getColor ("COLOR_WHITE"));

            mainLayout.setOrientation (LinearLayout.VERTICAL);
            CardView cardView = new CardView (G.getContext ());
            cardView.setBackgroundColor (getColor ("COLOR_WHITE"));

            linearLayout = new LinearLayout (G.getContext ());
            linearLayout.setOrientation (LinearLayout.VERTICAL);
            linearLayout.setBackgroundColor (getColor ("COLOR_WHITE"));

            LinearLayout.LayoutParams paramsMain = new LinearLayout.LayoutParams (LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);
            mainLayout.setLayoutParams (paramsMain);
            mainLayout.setGravity (Gravity.CENTER);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams (LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins (margin , margin , margin , margin);

            cardView.setLayoutParams (params);
            linearLayout.setLayoutParams (params);

            title (linearLayout);

            makeButton ();
            cardView.addView (linearLayout);
            mainLayout.addView (cardView);
        }

        @bardiademon
        private void title (LinearLayout layout)
        {
            Title title = new Title ();
            title.setMainLayout (layout);
            title.setCurvedMargin (true);
            title.setText (message);
            title.apply ();
        }

        @bardiademon
        private void makeButton ()
        {
            int margin = Math.convertDPPX (5);
            LinearLayout llButton = new LinearLayout (G.getContext ());
            LinearLayout llButtonYesNo = new LinearLayout (G.getContext ());
            LinearLayout llButtonCancel = new LinearLayout (G.getContext ());
            llButton.setBackgroundColor (getColor ("COLOR_WHITE"));
            llButtonYesNo.setBackgroundColor (getColor ("COLOR_WHITE"));
            llButtonCancel.setBackgroundColor (getColor ("COLOR_WHITE"));

            LinearLayout.LayoutParams paramsButton = new LinearLayout.LayoutParams (LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);
            paramsButton.setMargins (margin , margin , margin , margin);
            llButton.setLayoutParams (paramsButton);
            llButton.setOrientation (LinearLayout.HORIZONTAL);
            llButton.setGravity (Gravity.CENTER);

            LinearLayout.LayoutParams paramsButtonCancel = new LinearLayout.LayoutParams (0 , LinearLayout.LayoutParams.WRAP_CONTENT);
            paramsButtonCancel.weight = 5;
            llButtonCancel.setLayoutParams (paramsButtonCancel);

            LinearLayout.LayoutParams paramsButtonYesNo = new LinearLayout.LayoutParams (0 , LinearLayout.LayoutParams.WRAP_CONTENT);
            paramsButtonYesNo.weight = 5;
            llButtonYesNo.setOrientation (LinearLayout.HORIZONTAL);
            llButtonYesNo.setLayoutParams (paramsButtonYesNo);

            LinearLayout llYes = new LinearLayout (G.getContext ());
            LinearLayout llNo = new LinearLayout (G.getContext ());
            LinearLayout.LayoutParams paramsLlYesNo = new LinearLayout.LayoutParams (0 , LinearLayout.LayoutParams.WRAP_CONTENT);
            paramsLlYesNo.weight = 5;
            llYes.setLayoutParams (paramsLlYesNo);
            llNo.setLayoutParams (paramsLlYesNo);
            llButtonYesNo.addView (llNo);
            llButtonYesNo.addView (llYes);

            btn = new HashMap<> ();
            LinearLayout.LayoutParams paramsBtn = new LinearLayout.LayoutParams (LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT);
            switch (typeQuestion)
            {
                case YES_NO:
                {
                    Button btnNo = makeButtonNo (paramsBtn);
                    llButtonYesNo.addView (btnNo);
                    btn.put ("no" , btnNo);
                    break;
                }
                case YES_NO_CANCEL:
                {
                    Button btnNo = makeButtonNo (paramsBtn);
                    llButtonYesNo.addView (btnNo);
                    btn.put ("no" , btnNo);

                    Button btnCancel = makeButtonCancel (paramsBtn);
                    btn.put ("cancel" , btnCancel);
                    llButtonCancel.addView (btnCancel);
                    break;
                }
                case YES_CANCEL:
                {
                    Button btnCancel = makeButtonCancel (paramsBtn);
                    llButtonCancel.addView (btnCancel);
                    btn.put ("cancel" , btnCancel);
                    break;
                }
            }

            Button btnYes = makeButtonYes (paramsBtn);
            llButtonYesNo.addView (btnYes);

            llButton.addView (llButtonCancel);
            llButton.addView (llButtonYesNo);
            linearLayout.addView (llButton);
        }

        @bardiademon
        private Button makeButtonYes (LinearLayout.LayoutParams params)
        {
            Button btnYes = new Button (G.getContext ());
            btnYes.setText (getString ("btn_txt__question__yes"));
            btn.put ("yes" , btnYes);
            btnYes.setLayoutParams (params);
            btnYes.setBackgroundColor (getColor ("COLOR_WHITE"));
            btnYes.setTypeface (setTypeface ());
            setTextColorButton (btnYes);
            return btnYes;
        }

        @bardiademon
        private Button makeButtonNo (LinearLayout.LayoutParams params)
        {
            Button btnNo = new Button (G.getContext ());
            btnNo.setText (getString ("btn_txt__question__no"));
            btnNo.setLayoutParams (params);
            btnNo.setBackgroundColor (getColor ("COLOR_WHITE"));
            setTextColorButton (btnNo);
            btnNo.setTypeface (setTypeface ());
            return btnNo;
        }

        @bardiademon
        private Button makeButtonCancel (LinearLayout.LayoutParams params)
        {
            Button btnCancel = new Button (G.getContext ());
            btnCancel.setText (getString ("btn_txt__question__cancel"));
            btnCancel.setLayoutParams (params);
            btnCancel.setBackgroundColor (getColor ("COLOR_WHITE"));
            setTextColorButton (btnCancel);
            btnCancel.setTypeface (setTypeface ());
            return btnCancel;
        }

        @bardiademon
        private void setTextColorButton (Button btn)
        {
            btn.setTextColor (getColor ("clr_btn_ques"));
        }

        @bardiademon
        private Typeface setTypeface ()
        {
            return Typeface.createFromAsset (G.getAssetsManager () , "iranian_sans.ttf");
        }

    }

    @bardiademon
    public static abstract class QuestionReady
    {
        @SuppressLint("StaticFieldLeak")
        private static Question question;

        @bardiademon
        public static abstract class Exit
        {
            @bardiademon
            private static void questionExitFromThePage (final CallBack afterClickYes)
            {
                question = new Question (() ->
                {
                    if (question.clickYes ()) afterClickYes.Call ();
                } , getString ("question_ready__exit_from_the_page"));
                System.gc ();
            }

            @bardiademon
            public static void EXIT_ACTIVITY (final Class<?> where)
            {
                questionExitFromThePage (() -> new ActiveSwitching (where));
                System.gc ();
            }

            @bardiademon
            public static void EXIT_ACTIVITY (final Class<?> where , CallBack afterClickYes)
            {
                questionExitFromThePage (() ->
                {
                    afterClickYes.Call ();
                    if (where != null) new ActiveSwitching (where);
                });
                System.gc ();
            }

            @bardiademon
            public static void EXIT_ALERT_DIALOG (final android.support.v7.app.AlertDialog alertDialog)
            {
                questionExitFromThePage (alertDialog::cancel);
                System.gc ();
            }
        }

        @bardiademon
        public abstract static class RECS
        {
            private static CallBack afterClickYes;
            private static String messageQuestion;

            @bardiademon
            public static void REMOVE (CallBack afterClickYes)
            {
                setAfterClickYes (afterClickYes);
                setMessageQuestion ("string__question_ready__remove");
                set ();
            }

            @bardiademon
            public static void CHANGE (CallBack afterClickYes)
            {
                setAfterClickYes (afterClickYes);
                setMessageQuestion ("string__question_ready__change");
                set ();
            }

            @bardiademon
            public static void ADD (CallBack afterClickYes)
            {
                setAfterClickYes (afterClickYes);
                setMessageQuestion ("string__question_ready__add");
                set ();
            }

            @bardiademon
            public static void SET (CallBack afterClickYes)
            {
                setAfterClickYes (afterClickYes);
                setMessageQuestion ("string__question_ready__set");
                set ();
            }

            @bardiademon
            private static void setAfterClickYes (CallBack afterClickYes)
            {
                RECS.afterClickYes = afterClickYes;
            }

            @bardiademon
            private static void setMessageQuestion (String messageQuestion)
            {
                RECS.messageQuestion = GetValues.getString (messageQuestion);
            }

            @bardiademon
            private static void set ()
            {
                question = new Question (() ->
                {
                    if (question.clickYes ())
                        afterClickYes.Call ();
                    clear ();
                } , messageQuestion);
            }

            @bardiademon
            private static void clear ()
            {
                question = null;
                afterClickYes = null;
                messageQuestion = null;
            }
        }
    }
}
