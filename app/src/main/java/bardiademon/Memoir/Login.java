package bardiademon.Memoir;


import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import bardiademon.Memoir.Activity.ActivityLogin;
import bardiademon.Memoir.Server.Check.CheckInfoLogin;
import bardiademon.Memoir.bardiademon.Class.BeforeRunActivity.G;
import bardiademon.Memoir.bardiademon.Class.Other.ActiveSwitching;
import bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues;
import bardiademon.Memoir.bardiademon.Class.Other.Service.GetSerialPhone;

public abstract class Login
{
    private static String Username, Password;
    private static boolean IsLogin;
    private static CheckInfoLogin checkInfoLogin;

    private static final String SHP__LOGIN = "login";

    public static void SetLogin (CallBack.SetLogin SetLogin , String Username , String Password)
    {
        checkInfoLogin = new CheckInfoLogin (() ->
        {
            if (checkInfoLogin.AnswerServerOrResult ())
            {
                Login.Username = Username;
                Login.Password = Password;
                IsLogin = true;
            }
            SetLogin.AfterCheckInfo (checkInfoLogin.AnswerServerOrResult ());
        } , Username , Password);
    }

    public interface CallBack
    {
        interface SetLogin
        {
            void AfterCheckInfo (boolean ValidInfo);
        }
    }

    public static JSONObject GetInfoLogin ()
    {
        if (IsLogin ())
        {
            try
            {
                JSONObject infoLogin = new JSONObject ();
                infoLogin.put (GetValues.getString ("nr__username") , GetUsername ());
                infoLogin.put (GetValues.getString ("nr__password") , GetPassword ());
                infoLogin.put (GetValues.getString ("nr__serial") , new GetSerialPhone ().getResult ());
                return infoLogin;
            }
            catch (JSONException ignored)
            {
            }
        }
        return null;
    }

    public static String GetUsername ()
    {
        return Username;
    }

    public static String GetPassword ()
    {
        return Password;
    }

    public static void LogOut ()
    {
        IsLogin = false;
        Username = null;
        Password = null;
        G.getActivity ().getSharedPreferences (SHP__LOGIN , Context.MODE_PRIVATE).edit ().clear ().apply ();
        new ActiveSwitching (ActivityLogin.class);
        System.gc ();
    }

    public static boolean IsLogin ()
    {
        return IsLogin;
    }
}
