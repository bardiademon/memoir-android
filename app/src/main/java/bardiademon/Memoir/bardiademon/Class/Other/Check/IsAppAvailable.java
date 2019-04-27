package bardiademon.Memoir.bardiademon.Class.Other.Check;

//bardiademon@gmail.com

import android.content.pm.PackageManager;

import bardiademon.Memoir.bardiademon.Class.BeforeRunActivity.G;

public abstract class IsAppAvailable
{
    public static boolean check (String packageName)
    {
        PackageManager packageManager = G.getContext().getPackageManager();
        try
        {
            packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        }
        catch (PackageManager.NameNotFoundException e)
        {
            return false;
        }
    }
}
