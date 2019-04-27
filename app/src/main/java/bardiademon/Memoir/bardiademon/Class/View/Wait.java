package bardiademon.Memoir.bardiademon.Class.View;

import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;


import bardiademon.Memoir.bardiademon.Class.BeforeRunActivity.G;
import bardiademon.Memoir.bardiademon.Class.Other.ActiveSwitching;
import bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues;
import bardiademon.Memoir.bardiademon.Class.Other.Math.Math;
import bardiademon.Memoir.bardiademon.Interface.Title;

public class Wait
{
    public static final int PROGRESS_NORMAL = 0, PROGRESS_HORIZONTAL_INDETERMINATE = 1, PROGRESS_HORIZONTAL = 2;

    public static final String DEFAULT_MESSAGE = "0";

    public static final int DEFAULT_MAX = 100;

    private int max;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    private String title, message;

    private int stateOfProgress;
    private int progress;
    private boolean btnCancel;
    private SetTools setTools;
    private Callable<Void> afterClickCancel;
    private boolean closeAfterClickCancel;
    private boolean setJustProgress = false;
    private static Wait wait;
    private boolean cancelable = false;
    private static Timer timer;

    public Wait ()
    {
        setTitle(null);
        setMessage(DEFAULT_MESSAGE);
        setStateOfProgress(PROGRESS_NORMAL);
        setMax(DEFAULT_MAX);
    }

    public Wait (String title , String message)
    {
        setTitle(title);
        setMessage(message);
        setStateOfProgress(PROGRESS_NORMAL);
        set();
    }

    public void setJustProgress (boolean setJustProgress)
    {
        this.setJustProgress = setJustProgress;
    }

    public void setStartProgress (int progress)
    {
        this.progress = progress;
    }

    public static void Set ()
    {
        Set(true);
    }

    public static void Set (boolean show)
    {
        Set(null , DEFAULT_MESSAGE , PROGRESS_NORMAL , null , false , show);
    }

    public static void Set (String message , boolean show)
    {
        Set(null , message , PROGRESS_NORMAL , null , false , show);
    }

    public static void Set (String title , String message , boolean show)
    {
        Set(title , message , PROGRESS_NORMAL , null , false , show);
    }

    public static void Set (String title , String message , int stateOfProgress , boolean show)
    {
        Set(title , message , stateOfProgress , null , false , show);
    }

    public static void Set (String message , int stateOfProgress , boolean show)
    {
        Set(null , message , stateOfProgress , null , false , show);
    }

    public static void Set (String title , String message , int stateOfProgress , Callable<Void> afterClickCancel , boolean show)
    {
        Set(title , message , stateOfProgress , afterClickCancel , true , show);
    }

    public static void Set (String message , int stateOfProgress , Callable<Void> afterClickCancel , boolean show)
    {
        Set(null , message , stateOfProgress , afterClickCancel , true , show);
    }

    public static void Set (String message , int stateOfProgress , Callable<Void> afterClickCancel , boolean close , boolean show)
    {
        Set(null , message , stateOfProgress , afterClickCancel , close , show);
    }

    public static void Set (String title , String message , int stateOfProgress , Callable<Void> afterClickCancel , boolean close , boolean show)
    {
        if (wait != null)
        {
            CloseWait();
            wait = null;
        }
        wait = new Wait();
        wait.setTitle(title);
        wait.setMessage(message);
        wait.setStateOfProgress(stateOfProgress);
        wait.btnCancel(afterClickCancel , close);
        if (show) wait.set();
    }

    public void setMax (int max)
    {
        this.max = max;
    }

    public int getMax ()
    {
        return max;
    }

    public static void Set (int stateOfProgress , ViewGroup mainGroup)
    {
        if (mainGroup != null)
        {
            Wait wait = new Wait();
            wait.setJustProgress(true);
            wait.setStateOfProgress(stateOfProgress);
            wait.set(mainGroup);
        }
    }

    public void setCancelable (boolean cancelable)
    {
        this.cancelable = cancelable;
    }

    public static void CloseItAfterASpecifiedTime (int Second)
    {
        CloseItAfterASpecifiedTime(Second , null , null);
    }

    public static void CloseItAfterASpecifiedTime (int Second , String Message)
    {
        CloseItAfterASpecifiedTime(Second , Message , null);
    }

    public static void CloseItAfterASpecifiedTime (int Second , CallBack.AfterClose AfterClose)
    {
        CloseItAfterASpecifiedTime(Second , null , AfterClose);
    }

    public static void CloseItAfterASpecifiedTime (int Second , String Message , CallBack.AfterClose AfterClose)
    {
        timer = new Timer();
        timer.schedule(new TimerTask()
        {
            @Override
            public void run ()
            {
                G.getActivity().runOnUiThread(new TimerTask()
                {
                    @Override
                    public void run ()
                    {
                        timer.cancel();
                        Wait.CloseWait();
                        if (Message != null && !Message.equals(""))
                            ShowMessage.show(GetValues.getString("error") , Message , Icon.ICON_ERROR);


                        if (AfterClose != null) AfterClose.CallBack();
                    }
                });
            }
        } , Second , Second);
    }

    public static void CloseWait ()
    {
        if (wait != null) wait.close();
    }

    public static void Cancel ()
    {
        wait.close();
    }

    public static Wait getWait ()
    {
        return wait;
    }

    public void setTitle (String title)
    {
        if (title != null && title.equals(DEFAULT_MESSAGE))
            title = GetValues.getString("please_wait");
        this.title = title;
    }

    public void setProgressAdd (int progress)
    {
        progress += getProgress();
        if (progress > max) progress = max;

        setProgress(progress);
    }

    public void setProgress (int progress)
    {
        if (setTools != null)
        {
            if (progress > max) progress = max;
            this.progress = progress;
            setTools.setProgress();
        }
    }

    public int getProgress ()
    {
        return progress;
    }

    public void setMessage (String message)
    {
        if (message == null || message.equals(DEFAULT_MESSAGE))
            message = GetValues.getString("please_wait");
        this.message = message;
    }

    public void btnCancel (Callable<Void> afterClickCancel , boolean close)
    {
        this.btnCancel = (afterClickCancel != null || close);
        this.afterClickCancel = afterClickCancel;
        closeAfterClickCancel = close;
    }

    public void btnCancel (Callable<Void> afterClickCancel)
    {
        btnCancel(afterClickCancel , true);
    }

    public void btnCancel (boolean close)
    {
        btnCancel(null , close);
    }

    public void btnCancel ()
    {
        btnCancel(null , true);
    }

    public boolean isBtnCancel ()
    {
        return btnCancel;
    }

    public void setStateOfProgress (int stateOfProgress)
    {
        if (stateOfProgress != PROGRESS_NORMAL && stateOfProgress != PROGRESS_HORIZONTAL && stateOfProgress != PROGRESS_HORIZONTAL_INDETERMINATE)
            stateOfProgress = PROGRESS_NORMAL;

        this.stateOfProgress = stateOfProgress;
    }

    public Integer getStateOfProgress ()
    {
        return stateOfProgress;
    }

    public void set ()
    {
        set(null);
    }

    public void set (ViewGroup mainLayout)
    {
        setTools = new SetTools();
        if (!isSetJustProgress())
        {
            SetLayout();
            Show();
        }
        if (mainLayout != null) setMainLayout(mainLayout);
    }

    public boolean isSetJustProgress ()
    {
        return setJustProgress;
    }

    private void SetLayout ()
    {
        builder = new AlertDialog.Builder(G.getActivity());
        builder.setView(setTools.getMainLayout());
        builder.setCancelable(cancelable);
        alertDialog = builder.create();
    }

    public ViewGroup getView ()
    {
        if (isSetJustProgress()) return setTools.getMainLayout();
        return null;
    }

    public void setMainLayout (ViewGroup mainLayout)
    {
        if (isSetJustProgress()) mainLayout.addView(getView());
    }

    private void Show ()
    {
        if (CheckBuilder() && !alertDialog.isShowing()) alertDialog.show();
    }

    public void close ()
    {
        if (CheckBuilder() && alertDialog.isShowing())
        {
            alertDialog.cancel();
            if (timer != null) timer.cancel();
            clear();
        }
    }

    public void clear ()
    {
        if (setTools != null) setTools.clear();
        builder = null;
        alertDialog = null;
        title = null;
        message = null;
        setTools = null;
        afterClickCancel = null;
        wait = null;
        timer = null;
    }

    private boolean CheckBuilder ()
    {
        return (builder != null && alertDialog != null);
    }

    private class SetTools implements Title
    {
        private LinearLayout mainLayout, layoutTitle, layoutMessage, layoutProgressBar, layoutMessageAndProgressBar, layoutButton, layoutProgressAndProgressInt;
        private ProgressBar progressBar;
        private Button btnCancel;
        private LinearLayout.LayoutParams params;
        private TextView txtShowProgress;

        SetTools ()
        {
            SetTools();
            if (!isSetJustProgress()) Title();
            addView();
        }

        void setProgress ()
        {
            if (getStateOfProgress().equals(PROGRESS_HORIZONTAL) && txtShowProgress != null)
            {
                int progress = getProgress();
                progressBar.setProgress(progress);
                setTxtShowProgress(progress);
            }
        }

        private void SetTools ()
        {
            params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);

            mainLayout = new LinearLayout(G.getActivity());
            mainLayout.setOrientation(LinearLayout.VERTICAL);
            mainLayout.setGravity(Gravity.CENTER);
            int padding = Math.convertDPPX(10);
            mainLayout.setPadding(padding , padding , padding , padding);

            layoutMessageAndProgressBar = new LinearLayout(G.getActivity());

            mainLayout.setLayoutParams(params);
            if (!isSetJustProgress())
            {
                layoutMessage = new LinearLayout(G.getActivity());
                if (title != null)
                {
                    layoutTitle = new LinearLayout(G.getActivity());
                    layoutTitle.setLayoutParams(params);
                }

            }
            layoutMessageAndProgressBar.setLayoutParams(params);
            layoutProgressBar = new LinearLayout(G.getActivity());
            layoutProgressBar.setGravity(Gravity.CENTER);

            layoutMessageAndProgressBar.setGravity(Gravity.CENTER);

            if (isBtnCancel())
            {
                btnCancel = new Button(G.getActivity());
                layoutButton = new LinearLayout(G.getActivity());
                layoutButton.setLayoutParams(params);
                setButton();
            }
            if (getStateOfProgress().equals(PROGRESS_HORIZONTAL))
            {
                layoutProgressAndProgressInt = new LinearLayout(G.getActivity());
                layoutProgressAndProgressInt.setOrientation(LinearLayout.HORIZONTAL);
                layoutMessageAndProgressBar.setOrientation(LinearLayout.HORIZONTAL);
                txtShowProgress = new TextView(G.getActivity());
            }
            setProgressBar();
        }

        private void setProgressBar ()
        {
            LinearLayout.LayoutParams paramsLayoutMessage, paramProgressBar;

            Integer progress = getStateOfProgress();
            if (progress.equals(PROGRESS_NORMAL))
            {
                paramsLayoutMessage = new LinearLayout.LayoutParams(0 , LinearLayout.LayoutParams.WRAP_CONTENT);
                paramProgressBar = new LinearLayout.LayoutParams(0 , LinearLayout.LayoutParams.WRAP_CONTENT);

                paramsLayoutMessage.weight = 0.8f;
                paramProgressBar.weight = 0.2f;
                layoutMessageAndProgressBar.setOrientation(LinearLayout.HORIZONTAL);

                progressBar = new ProgressBar(G.getActivity());
                progressBar.setLayoutParams(params);
                layoutProgressBar.addView(progressBar);
            }
            else if (progress.equals(PROGRESS_HORIZONTAL_INDETERMINATE))
            {
                paramsLayoutMessage = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);
                paramProgressBar = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);
                setProgressHorizontal(true);
            }
            else
            {
                paramsLayoutMessage = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);
                paramProgressBar = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);
                setProgressHorizontal(false);
            }
            if (!isSetJustProgress()) layoutMessage.setLayoutParams(paramsLayoutMessage);
            layoutProgressBar.setLayoutParams(paramProgressBar);
        }

        private void setProgressHorizontal (boolean indeterminate)
        {
            progressBar = new ProgressBar(G.getActivity() , null , android.R.attr.progressBarStyleHorizontal);
            progressBar.setIndeterminate(indeterminate);
            layoutProgressBar.addView(progressBar , 0);
            layoutMessageAndProgressBar.setOrientation(LinearLayout.VERTICAL);
            if (!indeterminate)
            {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutProgressAndProgressInt.setLayoutParams(params);

                layoutProgressBar.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams paramsTxtShowProgress = new LinearLayout.LayoutParams(0 , LinearLayout.LayoutParams.WRAP_CONTENT);
                paramsTxtShowProgress.weight = 0.1f;


                setTxtShowProgress(getProgress());


                progressBar.setProgress(getProgress());
                progressBar.setMax(getMax());
                layoutProgressAndProgressInt.addView(layoutProgressBar , 0);

                if (isSetJustProgress())
                {
                    txtShowProgress.setLayoutParams(paramsTxtShowProgress);
                    txtShowProgress.setTypeface(Typeface.createFromAsset(G.getAssetsManager() , "iranian_sans.ttf"));
                    txtShowProgress.setGravity(Gravity.CENTER);
                    txtShowProgress.setTextColor(GetValues.getColor("COLOR_BLACK"));
                    layoutProgressBar.addView(txtShowProgress , 1);
                }

                LinearLayout.LayoutParams paramsProgressBar = new LinearLayout.LayoutParams(0 , LinearLayout.LayoutParams.WRAP_CONTENT);
                paramsProgressBar.weight = 0.9f;
                progressBar.setLayoutParams(paramsProgressBar);
            }
            else progressBar.setLayoutParams(params);
        }

        private void setButton ()
        {
            if (isSetJustProgress()) return;
            btnCancel.setText(GetValues.getString("cancel"));
            btnCancel.setTypeface(Typeface.createFromAsset(G.getAssetsManager() , "iranian_sans.ttf"));

            int margin = Math.convertDPPX(2);
            ShapeDrawable colorWhite = GetShapeDrawable.get(GetValues.getColor("COLOR_WHITE") , new float[] {margin , margin , margin , margin , margin , margin , margin , margin});
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                btnCancel.setBackground(colorWhite);
            else
                btnCancel.setBackgroundDrawable(colorWhite);

            layoutButton.addView(btnCancel);

            btnCancel.setOnClickListener(v ->
            {
                if (afterClickCancel != null)
                {
                    try
                    {
                        afterClickCancel.call();
                    }
                    catch (Exception ignored)
                    {
                    }
                }
                if (closeAfterClickCancel) close();
            });
        }

        public void setTxtShowProgress (int progress)
        {
            String txtValue = String.valueOf(progress) + "%";
            txtShowProgress.setText(txtValue);
        }

        @Override
        public void Title ()
        {
            bardiademon.Memoir.bardiademon.Class.View.Title titleAndMessage = new bardiademon.Memoir.bardiademon.Class.View.Title();
            titleAndMessage.setCurvedMargin(true);

            if (title != null)
            {
                titleAndMessage.setMainLayout(layoutTitle);
                titleAndMessage.setText(title);
                titleAndMessage.apply();
            }
            // ==========================================

            titleAndMessage.setText(message);
            titleAndMessage.setGravity(Gravity.END);
            titleAndMessage.setMainLayout(layoutMessage);
            titleAndMessage.setCurvedMargin(false);
            titleAndMessage.setMargin(Math.convertDPPX(5));
            titleAndMessage.setBackgroundColor(GetValues.getColor("COLOR_WHITE"));
            titleAndMessage.setTextColor(GetValues.getColor("COLOR_BLACK"));
            titleAndMessage.apply();
        }

        private void addView ()
        {
            if (title != null && !isSetJustProgress()) mainLayout.addView(layoutTitle);
            Integer stateOfProgress = getStateOfProgress();
            if (!isSetJustProgress())
            {
                if (stateOfProgress.equals(PROGRESS_NORMAL) || stateOfProgress.equals(PROGRESS_HORIZONTAL_INDETERMINATE))
                {
                    if (stateOfProgress.equals(PROGRESS_HORIZONTAL_INDETERMINATE))
                    {
                        layoutMessageAndProgressBar.addView(layoutMessage , 0);
                        layoutMessageAndProgressBar.addView(layoutProgressBar , 1);
                    }
                    else
                    {
                        layoutMessageAndProgressBar.addView(layoutProgressBar , 0);
                        layoutMessageAndProgressBar.addView(layoutMessage , 1);
                    }
                }
                else
                {
                    layoutMessageAndProgressBar.addView(layoutMessage , 0);
                    layoutMessageAndProgressBar.addView(layoutProgressAndProgressInt , 1);
                }
            }
            else
                layoutMessageAndProgressBar.addView(layoutProgressBar);

            mainLayout.addView(layoutMessageAndProgressBar);
            if (isBtnCancel() && !isSetJustProgress()) mainLayout.addView(layoutButton);
        }

        LinearLayout getMainLayout ()
        {
            return mainLayout;
        }

        void clear ()
        {
            mainLayout = null;
            layoutTitle = null;
            layoutMessage = null;
            layoutProgressBar = null;
            layoutMessageAndProgressBar = null;
            layoutButton = null;
            layoutProgressAndProgressInt = null;
            progressBar = null;
            btnCancel = null;
            params = null;
            txtShowProgress = null;
        }

    }

    public static void afterTimeGoToActivity (int sec , Class<?> where)
    {
        new Handler().postDelayed(() -> new ActiveSwitching(where) , sec);
    }

    public interface CallBack
    {
        interface AfterClose
        {
            void CallBack ();
        }
    }

}
