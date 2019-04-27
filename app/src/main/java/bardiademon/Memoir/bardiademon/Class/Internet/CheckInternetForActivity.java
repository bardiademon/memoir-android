package bardiademon.Memoir.bardiademon.Class.Internet;


import bardiademon.Memoir.bardiademon.Class.BeforeRunActivity.G;

public abstract class CheckInternetForActivity
{
    public static boolean check ()
    {
        return check(true);
    }

    public static boolean check (boolean setActivityForBackToHome)
    {
        if (!CheckConnectInternet.checkInternet())
        {
            if ((setActivityForBackToHome))
                G.setActivityClassForBack(G.getActivityHomeClass());
            else
                G.setActivityClassForBack(G.getActivityClassForBack());

            return false;
        }
        return true;
    }
}
