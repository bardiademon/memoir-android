package bardiademon.Memoir.bardiademon.Class.Other;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import bardiademon.Memoir.bardiademon.Interface.bardiademon;

@bardiademon
public class JSONGetStringUtf8 extends JSONObject
{
    public JSONGetStringUtf8 (String json) throws JSONException
    {
        super (json);
    }

    @bardiademon
    public String getString (String name , boolean utf8) throws JSONException
    {
        if (utf8) return this.getString (name);
        else return super.getString (name);
    }

    @bardiademon
    @Override
    public String getString (String name) throws JSONException
    {
        try
        {
            return URLDecoder.decode (super.getString (name) , "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            return null;
        }
    }
}
