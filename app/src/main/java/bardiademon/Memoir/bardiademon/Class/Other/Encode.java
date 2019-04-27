package bardiademon.Memoir.bardiademon.Class.Other;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public abstract class Encode
{
    public static String encode (String Value)
    {
        try
        {
            return URLEncoder.encode(Value , "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            return null;
        }
    }
}
