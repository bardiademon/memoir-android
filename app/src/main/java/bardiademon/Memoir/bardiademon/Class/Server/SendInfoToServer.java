package bardiademon.Memoir.bardiademon.Class.Server;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import bardiademon.Memoir.Server.AnswerServer;
import bardiademon.Memoir.bardiademon.Class.BeforeRunActivity.G;
import bardiademon.Memoir.bardiademon.Interface.ExchangeInformationWithTheServer;
import bardiademon.Memoir.bardiademon.Interface.bardiademon;

@bardiademon
public class SendInfoToServer
{
    private String url;
    private Map <String, String> param;
    private String paramRaw;
    private Map <String, String> header;
    private int method, methodSendParam;
    private int code;

    private int resultSend;
    private String answerServer;

    private final int CODE_DONE = 200;
    private final int METHOD_SEND_PARAM__GET = 0, METHOD_SEND_PARAM__NORMAL = 1, METHOD_SEND_PARAM__RAW = 2;
    private final int DEFAULT_METHOD = Request.Method.POST;
    private final int RESULT_SEND__SEND = 0, RESULT_SEND__SEND_AND_GET_ANSWER = 1, RESULT_SEND__ERROR_SEND = 2, RESULT_SEND__ERROR_SEND__GET_RESULT = 4, RESULT_SEND__ERROR_GET = 3;
    private StringRequest stringRequest;

    private ExchangeInformationWithTheServer.AfterExchange afterExchange;

    @bardiademon
    public SendInfoToServer (ExchangeInformationWithTheServer.AfterExchange afterExchange , String url , Map <String, String> param)
    {
        setAfterDone (afterExchange);
        this.url = url;
        this.param = param;
        setMethod (DEFAULT_METHOD);
        methodSendParam (METHOD_SEND_PARAM__NORMAL);
    }

    @bardiademon
    public SendInfoToServer (ExchangeInformationWithTheServer.AfterExchange afterExchange , String url , String paramRaw)
    {
        setAfterDone (afterExchange);
        this.url = url;
        this.paramRaw = paramRaw;
        setMethod (DEFAULT_METHOD);
        methodSendParam (METHOD_SEND_PARAM__RAW);
    }

    @bardiademon
    public void setAfterDone (ExchangeInformationWithTheServer.AfterExchange afterExchange)
    {
        this.afterExchange = afterExchange;
    }

    @bardiademon
    public void methodSendParam (int methodSendParam)
    {
        this.methodSendParam = methodSendParam;
    }

    @bardiademon
    public void setHeader (Map <String, String> header)
    {
        this.header = header;
    }

    @bardiademon
    public void setMethod (int method)
    {
        this.method = method;
    }

    @bardiademon
    private void setRequest ()
    {
        stringRequest = new StringRequest (method , url , response () , error ())
        {
            @Override
            public Map <String, String> getHeaders () throws AuthFailureError
            {
                if (header != null) return header;
                return super.getHeaders ();
            }

            @Override
            protected Map <String, String> getParams () throws AuthFailureError
            {
                if (param != null) return param;
                else return super.getParams ();
            }
        };
    }

    public void setParamInUrl (Map <String, String> param)
    {
        int len;
        if (param != null && (len = param.size ()) > 0)
        {
            url += "?";
            StringBuilder finalParam = new StringBuilder ();
            int counter = 0;
            for (Map.Entry <String, String> entry : param.entrySet ())
            {
                finalParam.append (entry.getKey ()).append ("=").append (entry.getValue ());
                if (counter++ + 1 < len) finalParam.append ("&");
            }
            url += finalParam.toString ();
        }
    }

    @bardiademon
    private void setRequestRaw ()
    {
        stringRequest = new StringRequest (method , url , response () , error ())
        {
            @Override
            public Map <String, String> getHeaders () throws AuthFailureError
            {
                if (header != null) return header;
                return super.getHeaders ();
            }

            @Override
            public byte[] getBody ()
            {
                return paramRaw.getBytes ();
            }
        };
    }


    @bardiademon
    private Response.Listener <String> response ()
    {
        return response ->
        {
            code = CODE_DONE;
            answerServer = response;
            if (answerServer.equals ("")) resultSend = RESULT_SEND__SEND;
            else resultSend = RESULT_SEND__SEND_AND_GET_ANSWER;
            if (afterExchange != null) afterExchange.Callback ();
        };
    }

    @bardiademon
    private Response.ErrorListener error ()
    {
        return error ->
        {
            if (error.networkResponse == null) return;
            code = error.networkResponse.statusCode;
            answerServer = new String (error.networkResponse.data , StandardCharsets.UTF_8);
            if (answerServer.equals ("")) resultSend = RESULT_SEND__ERROR_SEND;
            else resultSend = RESULT_SEND__ERROR_SEND__GET_RESULT;
            if (afterExchange != null) afterExchange.Callback ();
        };
    }

    @bardiademon
    public void apply ()
    {
        boolean ok = false;
        switch (methodSendParam)
        {
            case METHOD_SEND_PARAM__GET:
            case METHOD_SEND_PARAM__NORMAL:
                setRequest ();
                ok = true;
                break;
            case METHOD_SEND_PARAM__RAW:
                setRequestRaw ();
                ok = true;
                break;
        }
        if (ok) Volley.newRequestQueue (G.getActivity ()).add (stringRequest);
    }

    @bardiademon
    public int getCode ()
    {
        return code;
    }

    @bardiademon
    public int getCodeDone ()
    {
        return CODE_DONE;
    }

    @bardiademon
    public int getResultSend ()
    {
        return resultSend;
    }

    @bardiademon
    public String getAnswerServer ()
    {
        return getAnswerServerFromJson ();
    }

    @bardiademon
    public boolean isCode200 ()
    {
        return (getCode () == getCodeDone ());
    }

    @bardiademon
    public boolean resultIsError ()
    {
        return (getResultSend () == RESULT_SEND__ERROR_SEND);
    }

    @bardiademon
    public boolean sendAndGetAnswer ()
    {
        return (getResultSend () == RESULT_SEND__SEND_AND_GET_ANSWER || getResultSend () == RESULT_SEND__ERROR_SEND__GET_RESULT);
    }

    private String getAnswerServerFromJson ()
    {
        if (sendAndGetAnswer ())
        {
            try
            {
                JSONObject jsonObject = new JSONObject (answerServer);
                return jsonObject.get (AnswerServer.KEY_JSON_RESULT).toString ();
            }
            catch (JSONException ignored)
            {
            }
        }
        return null;
    }
}
