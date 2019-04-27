package bardiademon.Memoir.bardiademon.Class.BeforeRunActivity;

// Created by bardiademon on 06/03/2018_22:32.

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Environment;
import android.support.annotation.ColorInt;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import bardiademon.Memoir.R;
import bardiademon.Memoir.bardiademon.Class.Other.ActiveSwitching;
import bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues;
import bardiademon.Memoir.bardiademon.Class.Other.Service.GetSerialPhone;
import bardiademon.Memoir.bardiademon.Class.View.Title;


@SuppressLint("StaticFieldLeak")
public class G extends Application
{

    private static Context context;
    private static Activity activity, activityHome;
    public static final String NAME_KEY_SP_GO_TO_ACTIVITY = "go_to_activity";
    public static final String NAME_KEY_PUT_GO_TO_ACTIVITY = "name_class";
    private static String
            ADDRESS_DIR_SD,
            ADDRESS_DIR_PACKAGE,
            ADDRESS_DIR_DATABASE;

    private static Class<?>
            activityClass,
            activityClassForBack, activityHomeClass,
            activityClassRestart;

    private static Intent intent;

    private static AssetManager assetManager;

    private static FragmentManager fragmentManager;
    private static View view;

    @Override
    public void onCreate ()
    {
        super.onCreate();
        context = getApplicationContext();
        address();
        setAssetsManager();
    }

    public static void clearTitle ()
    {
        Title.clear();
        System.gc();
    }

    @SuppressLint("SdCardPath")
    private void address ()
    {
        ADDRESS_DIR_SD = Environment.getExternalStorageDirectory().getAbsolutePath();
        ADDRESS_DIR_PACKAGE = "/data/data/" + G.getContext().getPackageName();
        ADDRESS_DIR_DATABASE = ADDRESS_DIR_PACKAGE + "/database";
    }

    public static void setActivityClassForBack (Class activityClassForBack)
    {
        G.activityClassForBack = activityClassForBack;
    }

    public static View getView ()
    {
        return view;
    }

    public static void setView (View view)
    {
        G.view = view;
    }

    public static void setViewForActivity ()
    {
        view = getActivity().getWindow().getDecorView();
    }

    public static Class<?> getActivityClassForBack ()
    {
        return activityClassForBack;
    }

    public static void setActivityClass (Class<?> activityClass)
    {
        G.activityClass = activityClass;
    }

    public static Class<?> getActivityClass ()
    {
        return activityClass;
    }

    private void setAssetsManager ()
    {
        assetManager = G.getContext().getAssets();
    }

    public static AssetManager getAssetsManager ()
    {
        return assetManager;
    }

    public static Context getContext ()
    {
        return context;
    }

    public static void setActivity (Activity activity)
    {
        G.activity = activity;
        G.setIntent(null);
        G.setActivityClassRestart(null);
    }

    public static Activity getActivity ()
    {
        return activity;
    }

    public static void setActivityHome (Activity activityHome)
    {
        G.activityHome = activityHome;
    }

    public static void setActivityHomeClass (Class<?> activityHomeClass)
    {
        G.activityHomeClass = activityHomeClass;
    }

    public static Intent getIntent ()
    {
        return intent;
    }

    public static void setIntent (Intent intent)
    {
        G.intent = intent;
    }

    public static Class<?> getActivityHomeClass ()
    {
        return activityHomeClass;
    }

    public static Activity getActivityHome ()
    {
        return activityHome;
    }

    public static String getAddressDirSd ()
    {
        return ADDRESS_DIR_SD;
    }

    public static String getAddressDirPackage ()
    {
        return ADDRESS_DIR_PACKAGE;
    }

    public static String getAddressDirDatabase ()
    {
        return ADDRESS_DIR_DATABASE;
    }

    public static FragmentManager getSupportFragmentManager ()
    {
        return fragmentManager;
    }

    public static Class<?> getActivityClassRestart ()
    {
        return activityClassRestart;
    }

    public static void setActivityClassRestart (Class<?> activityClassRestart)
    {
        G.activityClassRestart = activityClassRestart;
        if (activityClassRestart != null)
            setIntent(G.getActivity().getIntent());
    }

    public static void setSupportFragmentManager (FragmentManager fragmentManager)
    {
        G.fragmentManager = fragmentManager;
    }

    public static class Permissions
    {
        public static String[] permissions = {Manifest.permission.READ_PHONE_STATE};

        public static boolean checkPermission (String permissions)
        {
            return (ActivityCompat.checkSelfPermission(G.getActivity(), permissions) == PackageManager.PERMISSION_GRANTED);
        }
    }

    public static String getSerialNumber ()
    {
        return new GetSerialPhone().getResult();
    }

    public static void GoToActivity ()
    {
        GoToActivity(G.getActivityClass());
    }

    public static void GoToActivity (Class<?> activityClass)
    {
        SharedPreferences.Editor editor = G.getActivity().getSharedPreferences(NAME_KEY_SP_GO_TO_ACTIVITY, MODE_PRIVATE).edit();
        editor.clear();
        editor.putString(NAME_KEY_PUT_GO_TO_ACTIVITY, activityClass.getName());
        editor.apply();
    }

    public static void CheckGoToActivity ()
    {
        SharedPreferences editor = G.getActivity().getSharedPreferences(NAME_KEY_SP_GO_TO_ACTIVITY, MODE_PRIVATE);
        String nameActivity = editor.getString(NAME_KEY_PUT_GO_TO_ACTIVITY, null);
        if (nameActivity != null)
        {
            try
            {
                Class<?> activityClass = Class.forName(nameActivity);
                ClearSPGoToActivity();
                new ActiveSwitching(activityClass);
            }
            catch (ClassNotFoundException ignored)
            {
                ignored.printStackTrace();
            }
            ClearSPGoToActivity();
        }
    }

    private static void ClearSPGoToActivity ()
    {
        SharedPreferences.Editor editor = G.getActivity().getSharedPreferences(NAME_KEY_SP_GO_TO_ACTIVITY, MODE_PRIVATE).edit();
        editor.clear();
        editor.apply();
    }
}