package bardiademon.Memoir.bardiademon.Class.Other;

import android.content.Intent;

import bardiademon.Memoir.bardiademon.Class.Activity.ActivityFatalError;
import bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues;


// Created by bardiademon on 07/03/2018_14:39.

public class FatalError
{

    private Intent intent;

    public static final int DEFAULT_MESSAGE__FATAL_ERROR = -1;
    public static final int DEFAULT_MESSAGE__NO_PERMISSION = 0;
    public static final int DEFAULT_MESSAGE__NO_INTERNET = 1;

    private String[] defaultMessage = new String[2];
    private int message;

    public FatalError (String titleError, String explanationError)
    {
        intent = new Intent();

        intent.putExtra("title_error", titleError);
        intent.putExtra("explanation_error", explanationError);
        start();
    }

    public FatalError (int message)
    {
        this.message = message;
        setArrayDefaultMessage();

        intent = new Intent();
        intent.putExtra("title_error", defaultMessage[0]);
        intent.putExtra("explanation_error", defaultMessage[1]);
        start();
    }

    private void start ()
    {
        new ActiveSwitching(ActivityFatalError.class, intent);
    }

    private void setArrayDefaultMessage ()
    {
        switch (message)
        {
            case DEFAULT_MESSAGE__NO_PERMISSION:
            {
                defaultMessage[0] = GetValues.getString("default__activity_fatal_error__title_error__no_permission");
                defaultMessage[1] = GetValues.getString("default__activity_fatal_error__message_error__no_permission");
                break;
            }
            case DEFAULT_MESSAGE__NO_INTERNET:
            {
                defaultMessage[0] = GetValues.getString("default__activity_fatal_error__error_no_internet__title");
                defaultMessage[1] = GetValues.getString("default__activity_fatal_error__error_no_internet__message");
                break;
            }
            case DEFAULT_MESSAGE__FATAL_ERROR:
            default:
            {
                defaultMessage[0] = GetValues.getString("error");
                defaultMessage[1] = GetValues.getString("default__activity_fatal_error__message_error");
                break;
            }
        }
    }
}
