package bardiademon.Memoir.Server.Found;

import org.json.JSONException;
import org.json.JSONObject;

import bardiademon.Memoir.Server.AnswerServer;
import bardiademon.Memoir.bardiademon.Interface.bardiademon;

// InfoUserFFC => Info User For Found Comment
@bardiademon
public class InfoUserFFC
{
    private final JSONObject InfoUser;

    @bardiademon
    public InfoUserFFC (JSONObject InfoUser)
    {
        this.InfoUser = InfoUser;
    }

    @bardiademon
    public String getPictureLink (int idUser)
    {
        try
        {
            return (getJsonInfo (idUser)).getString (AnswerServer.KJS.KJSGetComment.PIC);
        }
        catch (JSONException e)
        {
            return null;
        }
    }

    @bardiademon
    public String getUsername (int idUser)
    {
        try
        {
            return (getJsonInfo (idUser)).getString (AnswerServer.KJS.KJSGetComment.USERNAME);
        }
        catch (JSONException e)
        {
            return null;
        }
    }

    @bardiademon
    private JSONObject getJsonInfo (int idUser) throws JSONException
    {
        return InfoUser.getJSONObject (String.valueOf (idUser));
    }
}
