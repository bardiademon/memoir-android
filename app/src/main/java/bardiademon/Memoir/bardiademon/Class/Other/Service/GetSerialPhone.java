package bardiademon.Memoir.bardiademon.Class.Other.Service;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import bardiademon.Memoir.bardiademon.Class.BeforeRunActivity.G;

public class GetSerialPhone extends Activity
{

    private String result;

    public GetSerialPhone ()
    {
        try
        {
            checkPermission();
        }
        catch (Exception e)
        {
            result = "Error";
        }
    }

    private void checkPermission ()
    {
        if (Build.VERSION.SDK_INT >= 23)
        {
            int per = ActivityCompat.checkSelfPermission(G.getContext() , Manifest.permission.READ_PHONE_STATE);
            if (per == PackageManager.PERMISSION_DENIED)
            {
                ActivityCompat.requestPermissions(G.getActivity() , new String[]{Manifest.permission.READ_PHONE_STATE} , 1);
            }
            else get(true);
        }
        else get(true);
    }

    @SuppressLint({"HardwareIds" , "MissingPermission"})
    private void get (boolean get)
    {
        if (get)
        {
            if (Build.VERSION.SDK_INT >= 26)
                result = Build.getSerial();
            else
                result = Build.SERIAL;
        }

    }

    @Override
    public void onRequestPermissionsResult (int requestCode , @NonNull String[] permissions , @NonNull int[] grantResults)
    {
        if (requestCode == 1 && permissions.length > 0 && grantResults.length > 0)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                get(true);
            else
                get(false);
        }
    }

    public String getResult ()
    {
//        return result;
        return "2XJDU17729000087";
    }
}
