package bardiademon.Memoir.bardiademon.Class.Internet;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import bardiademon.Memoir.bardiademon.Class.BeforeRunActivity.G;
import bardiademon.Memoir.bardiademon.Class.Other.FatalError;
import bardiademon.Memoir.bardiademon.Interface.bardiademon;

@bardiademon
public abstract class CheckConnectInternet
{
    /**
     * @return boolean
     */
    @bardiademon
    public static boolean isConnect ()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) G.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null)
        {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            return (networkInfo != null && networkInfo.isConnectedOrConnecting());
        }
        else
            return false;
    }

    @bardiademon
    public static boolean checkInternet ()
    {
        if (isConnect()) return true;
        else
            new FatalError(FatalError.DEFAULT_MESSAGE__NO_INTERNET);
        return false;
    }

}
