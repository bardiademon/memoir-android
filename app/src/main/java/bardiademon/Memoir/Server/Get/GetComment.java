package bardiademon.Memoir.Server.Get;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import bardiademon.Memoir.Server.Found.FoundComment;
import bardiademon.Memoir.Server.Found.InfoUserFFC;
import bardiademon.Memoir.Other.MakeHeader;
import bardiademon.Memoir.Server.AnswerServer;
import bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues;
import bardiademon.Memoir.bardiademon.Class.Server.MakeRequest.Request;
import bardiademon.Memoir.bardiademon.Class.Server.SendInfoToServer;
import bardiademon.Memoir.bardiademon.Class.Server.Url;
import bardiademon.Memoir.bardiademon.Interface.ExchangeInformationWithTheServer;
import bardiademon.Memoir.bardiademon.Interface.bardiademon;

@bardiademon
public class GetComment implements ExchangeInformationWithTheServer
{

    private ExchangeInformationWithTheServer.AfterExchange afterExchange;
    private String linkMemoir;

    private boolean found;

    private Request request;
    private SendInfoToServer sendInfoToServer;

    private List <FoundComment> foundComments;
    private InfoUserFFC infoUserFFC;

    private JSONObject allComment;

    @bardiademon
    public GetComment (ExchangeInformationWithTheServer.AfterExchange AfterExchange , String LinkMemoir)
    {
        this.afterExchange = AfterExchange;
        this.linkMemoir = LinkMemoir;
        RunClass ();
    }

    @bardiademon
    @Override
    public void RunClass ()
    {
        MakeRequest ();
        Exchange ();
    }

    @bardiademon
    @Override
    public void MakeRequest ()
    {
        request = new Request ();
        Request.Put put = new Request.Put ();
        put.Put (GetValues.getString ("nr__link") , linkMemoir);
        put.Apply ();
        request.Apply ();
    }

    @bardiademon
    @Override
    public void Exchange ()
    {
        assert request != null;
        System.out.println (request.getParam ());
        sendInfoToServer = new SendInfoToServer (this::AfterExchange , Url.GetUrl ("ap__get_comment") , request.getParam ());
        sendInfoToServer.setHeader (MakeHeader.MakeHeaderLogin (GetValues.getString ("nrh__get_comment")));
        sendInfoToServer.apply ();
    }

    @bardiademon
    @Override
    public void AfterExchange ()
    {
        assert sendInfoToServer != null;
        found = (sendInfoToServer.sendAndGetAnswer () && sendInfoToServer.isCode200 ());
        if (found)
        {
            try
            {
                allComment = new JSONObject (sendInfoToServer.getAnswerServer ());
                toSplit ();
            }
            catch (Exception e)
            {
                System.out.println (e.getMessage ());
                found = false;
            }
        }
        afterExchange.Callback ();
    }

    @bardiademon
    private void toSplit () throws JSONException
    {
        JSONObject jsonComment;
        String txtComment, time;
        int id, idUser;
        foundComments = new ArrayList <> ();
        for (int i = 0, len = (allComment.length () - 1); i < len; i++)
        {
            jsonComment = allComment.getJSONObject (String.valueOf (i));
            txtComment = jsonComment.getString (AnswerServer.KJS.KJSGetComment.TXT_COMMENT);
            id = jsonComment.getInt (AnswerServer.KJS.KJSGetComment.ID);
            idUser = jsonComment.getInt (AnswerServer.KJS.KJSGetComment.ID_USER);
            time = jsonComment.getString (AnswerServer.KJS.KJSGetComment.TIME);
            foundComments.add (new FoundComment (id , idUser , txtComment , time));
        }
        infoUserFFC = new InfoUserFFC (allComment.getJSONObject (AnswerServer.KJS.KJSGetComment.JSON_INFO_USER));
    }

    @bardiademon
    @Override
    public boolean AnswerServerOrResult ()
    {
        return found;
    }

    public List <FoundComment> getFoundComments ()
    {
        return foundComments;
    }

    public InfoUserFFC getInfoUserFFC ()
    {
        return infoUserFFC;
    }
}
