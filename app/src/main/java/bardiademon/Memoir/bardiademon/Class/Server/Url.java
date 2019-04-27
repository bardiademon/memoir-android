package bardiademon.Memoir.bardiademon.Class.Server;

import bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues;
import bardiademon.Memoir.bardiademon.Interface.bardiademon;

@bardiademon
public interface Url
{
    String Protocol = GetValues.getString("protocol");
    String Domain = GetValues.getString("domain");
    int Port = Integer.parseInt(GetValues.getString("port"));
    String Router = GetValues.getString("router");
    String Url = String.format("%s://%s/%s/" , Protocol , Domain , Router);
    String UrlWithPort = String.format("%s://%s:%s/%s/" , Protocol , Domain , Port , Router);
    int StaticPort = 80;

    static String GetUrl (String AddressPage)
    {
        return GetUrl(AddressPage , false);
    }

    static String GetUrl (String AddressPage , boolean NotKey)
    {
        String url = (Port == StaticPort) ? Url : UrlWithPort;
        return (url + ((!NotKey) ? GetValues.getString(AddressPage) : AddressPage));
    }
}
