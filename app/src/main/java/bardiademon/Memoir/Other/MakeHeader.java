package bardiademon.Memoir.Other;

import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

import bardiademon.Memoir.Login;
import bardiademon.Memoir.bardiademon.Interface.bardiademon;

import static bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues.getString;

@bardiademon
public abstract class MakeHeader
{
    @bardiademon
    public static Map<String, String> MakeHeaderLogin (String request)
    {
        if (Login.IsLogin ())
        {
            Map<String, String> header = new LinkedHashMap<> ();
            header.put (getString ("name_request") , request);
            JSONObject jsonLogin = Login.GetInfoLogin ();
            assert jsonLogin != null;
            header.put (getString ("nrh__info_login") , jsonLogin.toString ());
            return header;
        }
        else return null;
    }

    @bardiademon
    public static Map<String, String> MakeHeaderRequest (String request)
    {
        Map<String, String> header = new LinkedHashMap<> ();
        header.put (getString ("name_request") , request);
        return header;
    }

}
