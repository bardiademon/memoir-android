package bardiademon.Memoir.bardiademon.Class.Other;

// Created by bardiademon on 26/02/2018_00:08.

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import bardiademon.Memoir.R;
import bardiademon.Memoir.bardiademon.Class.BeforeRunActivity.G;
import bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues;
import bardiademon.Memoir.bardiademon.Class.View.Icon;
import bardiademon.Memoir.bardiademon.Class.View.Toast;

@Deprecated
public class Wait
{
    private Activity activity;

    private String title = "", message = "";
    private int counter;
    private boolean showMessageCloseWaitInCaseOfError;
    private String messageCloseWaitInCaseOfError = "";
    private int secCloseWaitInCaseOfError;
    private Timer timer;
    private AlertDialog alertDialog;
    private View view;
    private boolean closeWaitAuto;

    public Wait ()
    {
        this.activity = G.getActivity();
    }

    private Boolean isShowing ()
    {
        return alertDialog != null && alertDialog.isShowing();
    }

    public void setTitleAndMessage (String title, String message)
    {
        setTitle(title);
        setMessage(message);
    }

    public void closeWaitInCaseOfErrorAfterSpecificSeconds ()
    {
        int secDefaultCloseWaitInCaseOfError = 20;
        closeWaitInCaseOfErrorAfterSpecificSeconds(secDefaultCloseWaitInCaseOfError);
    }

    public void closeWaitInCaseOfErrorAfterSpecificSeconds (int sec)
    {
        closeWaitInCaseOfErrorAfterSpecificSeconds(sec, false);
    }

    public void closeWaitInCaseOfErrorAfterSpecificSeconds (int sec, boolean afterCloseShowMessage)
    {
        secCloseWaitInCaseOfError = sec;
        showMessageCloseWaitInCaseOfError = afterCloseShowMessage;

        if (messageCloseWaitInCaseOfError.equals(""))
            messageCloseWaitInCaseOfError = GetValues.getString("toast__error_show_class_wait");

        closeWaitAuto = true;
    }

    public void closeWaitInCaseOfErrorAfterSpecificSeconds (int sec, boolean afterCloseShowMessage, String message)
    {
        this.messageCloseWaitInCaseOfError = message;
        closeWaitInCaseOfErrorAfterSpecificSeconds(sec, afterCloseShowMessage);
    }

    private void checkSecForCloseAuto ()
    {

        if (alertDialog == null) return;

        counter = 0;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run ()
            {
                activity.runOnUiThread(new TimerTask()
                {
                    @Override
                    public void run ()
                    {
                        if (++counter == secCloseWaitInCaseOfError && isShowing())
                        {
                            timer.cancel();
                            try
                            {
                                Wait.this.Cancel();
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }

                            if (showMessageCloseWaitInCaseOfError)
                                Toast.show(Icon.ICON_ERROR, messageCloseWaitInCaseOfError);
                        }
                        if (!isShowing())
                            timer.cancel();
                    }
                });
            }
        }, 1000, 1000);
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public void set_cancelable (boolean cancelable)
    {
        this.cancelable = cancelable;
    }


    private boolean cancelable = false;

    private void set ()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        view = layoutInflater.inflate(R.layout.wait, null);

        builder.setView(view);
        builder.setCancelable(cancelable);

        SetTools();
        setValueTools();

        alertDialog = builder.create();
    }

    public void Show ()
    {
        set();
        alertDialog.show();

        if (closeWaitAuto)
            checkSecForCloseAuto();
    }

    public void Cancel ()
    {
        if (isShowing())
            alertDialog.dismiss();

        if (timer != null)
            timer.cancel();
    }

    private TextView txtViewTitle, txtViewMessage;

    private void SetTools ()
    {
        txtViewTitle = view.findViewById(R.id.txt_title);
        txtViewMessage = view.findViewById(R.id.txt_msg);
    }

    private void setValueTools ()
    {
        String title = ((this.title.equals("")) ? GetValues.getString("please_wait") : this.title);
        String message = ((this.message.equals("")) ? GetValues.getString("please_wait") : this.message);

        txtViewTitle.setText(title);
        txtViewMessage.setText(message);
    }
}
