package bardiademon.Memoir.bardiademon.Class.Server;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;
import java.util.concurrent.Callable;

import bardiademon.Memoir.bardiademon.Class.BeforeRunActivity.G;
import bardiademon.Memoir.bardiademon.Class.Internet.CheckConnectInternet;
import bardiademon.Memoir.bardiademon.Class.Other.FatalError;
import bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues;
import bardiademon.Memoir.bardiademon.Class.Other.SetMapForVolley;

@Deprecated
public class SendInfoWithVolley
{
    private String resultGet;

    private int resultSend;

    private boolean methodRun;

    final public static int
            SEND = 0,
            SEND_AND_GET_RESULT = 1,
            CANNOT_SEND = 2,
            ERROR = 3;

    public String msgError;

    private Map<String, String> param;
    private String url;
    private Callable<Void> callable;

    @Deprecated
    public SendInfoWithVolley (String[][] infoSend, Callable<Void> checkResult)
    {
        this.url = GetValues.getString("address_page_request");

        this.param = SetMapForVolley.set(infoSend);
        this.callable = checkResult;
        send();
    }

    @Deprecated
    public SendInfoWithVolley (Map<String, String> infoSend, Callable<Void> checkResult)
    {
        if (checkIsConnected())
        {
            this.url = GetValues.getString("address_page_request");
            this.param = infoSend;
            this.callable = checkResult;
            send();
        }
    }

    private void send ()
    {
        if (!CheckConnectInternet.isConnect())
        {
            resultSend = CANNOT_SEND;
            runMethod();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse (String response)
            {
                if (response.equals(""))
                    resultSend = SEND;
                else
                {
                    resultGet = response;
                    resultSend = SEND_AND_GET_RESULT;
                }
                runMethod();
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse (VolleyError error)
            {
                resultSend = ERROR;
                msgError = error.getMessage();
                runMethod();
            }
        })
        {
            @Override
            public Map<String, String> getParams ()
            {
                return param;
            }
        };
        (Volley.newRequestQueue(G.getContext())).add(stringRequest);
    }

    @Deprecated
    public int getResultSend ()
    {
        return resultSend;
    }

    @Deprecated
    public String getResultGet ()
    {
        return resultGet;
    }

    private void runMethod ()
    {
        try
        {
            callable.call();
            methodRun = true;
        }
        catch (Exception e)
        {
            methodRun = false;
        }
    }

    private boolean checkIsConnected ()
    {
        if (!CheckConnectInternet.isConnect())
        {
            resultSend = ERROR;
            new FatalError(FatalError.DEFAULT_MESSAGE__NO_INTERNET);
        }
        return true;
    }


    @Deprecated
    public boolean isMethodRun ()
    {
        return methodRun;
    }

    @Deprecated
    public boolean sendAndGetResult ()
    {
        return (resultSend == SEND_AND_GET_RESULT);
    }
}
