package bardiademon.Memoir.Activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import bardiademon.Memoir.R;
import bardiademon.Memoir.bardiademon.Class.About.About;
import bardiademon.Memoir.bardiademon.Class.BeforeRunActivity.G;
import bardiademon.Memoir.bardiademon.Class.Other.ActiveSwitching;
import bardiademon.Memoir.bardiademon.Class.Other.FatalError;
import bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues;
import bardiademon.Memoir.bardiademon.Interface.Activity;

public class ActivitySplash extends AppCompatActivity implements Activity
{

    private TextView txtShowTimeStopped;
    private int oneOrTwo = 3;
    private String time;

    private Thread goToMain;

    private int h = 0, m = 0, s = 0;
    private String strH = "00", strM = "00", strS = "00";

    private static final int MAX_H = 13, MAX_M = 9, MAX_S = 56;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        RunClass();
    }


    private void goToMain ()
    {
        new ActiveSwitching(ActivityLogin.class);
    }

    @Override
    public void RunClass ()
    {
        View();
        SetTools();
        getPer();
    }

    private void getPer ()
    {
        Integer isPermission = ContextCompat.checkSelfPermission(this , Manifest.permission.READ_PHONE_STATE);
        if (isPermission.equals(PackageManager.PERMISSION_DENIED))
            ActivityCompat.requestPermissions(this , new String[] {Manifest.permission.READ_PHONE_STATE} , 1);
        else goToLogin(true);
    }

    @Override
    public void onRequestPermissionsResult (int requestCode , @NonNull String[] permissions , @NonNull int[] grantResults)
    {
        goToLogin((requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED));
    }

    private void goToLogin (boolean per)
    {
        if (!per)
        {
            new FatalError(FatalError.DEFAULT_MESSAGE__NO_PERMISSION);
            return;
        }
        new Thread(() ->
        {
            while (true)
            {
                runOnUiThread(() ->
                {
                    if (oneOrTwo < 3)
                    {
                        if (oneOrTwo == 1) time = GetValues.getString("time_stopped_1");
                        else time = GetValues.getString("time_stopped");
                    }
                    else
                    {
                        time = String.format("%s:%s:%s" , strH , strM , strS);
                        if (h >= 13 && m >= 9 && s >= 56) oneOrTwo = 1;
                    }
                    txtShowTimeStopped.setText(time);
                });
                try
                {
                    if (oneOrTwo == 3)
                    {
                        if (++m >= 59)
                        {
                            if (h < MAX_H) h++;
                            m = 0;
                            s = 0;
                        }
                        if (++s >= 59)
                        {
                            if (h < MAX_H) h++;
                            m++;
                            s = 0;
                        }

                        strH = (h < 10) ? "0" + h : "" + h;
                        strM = (m < 10) ? "0" + m : "" + m;
                        strS = (s < 10) ? "0" + s : "" + s;
                        Thread.sleep(3);
                    }
                    else
                    {
                        if (oneOrTwo == 1) oneOrTwo = 2;
                        else oneOrTwo = 1;
                        Thread.sleep(80);
                        if (goToMain == null)
                        {
                            goToMain = new Thread(() ->
                            {
                                try
                                {
                                    Thread.sleep(2000);
                                }
                                catch (InterruptedException ignored)
                                {
                                }
                                finally
                                {
                                    goToMain();
                                }
                            });
                            goToMain.start();
                        }
                    }
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void View ()
    {
        GSet();
        SetAbout();
        Title();
    }

    @Override
    public void GSet ()
    {
        G.setActivity(ActivitySplash.this);
        G.setViewForActivity();
    }

    @Override
    public void SetAbout ()
    {
        new About();
    }

    @Override
    public void Title ()
    {

    }

    @Override
    public void SetTools ()
    {
        txtShowTimeStopped = findViewById(R.id.txt_show_time_stopped);
    }

    @Override
    public void SetOnClick ()
    {

    }


}
