package bardiademon.Memoir.Server;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import bardiademon.Memoir.Other.MakeHeader;
import bardiademon.Memoir.Server.Found.FoundMemoirUser;
import bardiademon.Memoir.bardiademon.Class.Other.JSONGetStringUtf8;
import bardiademon.Memoir.bardiademon.Interface.bardiademon;
import bardiademon.Memoir.bardiademon.Class.Other.CloseTheApp;
import bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues;
import bardiademon.Memoir.bardiademon.Class.Server.MakeRequest.Request;
import bardiademon.Memoir.bardiademon.Class.Server.SendInfoToServer;
import bardiademon.Memoir.bardiademon.Class.Server.Url;
import bardiademon.Memoir.bardiademon.Interface.ExchangeInformationWithTheServer;

@bardiademon
public class GetMemoirUser implements ExchangeInformationWithTheServer
{

    private ArrayList <FoundMemoirUser> foundMemoirUsers;

    private boolean wasTaken;

    private ExchangeInformationWithTheServer.AfterExchange afterExchange;

    private Request request;
    private SendInfoToServer sendInfoToServer;

    public static final int GET_ALL = 0;

    private int idMemoir;

    @bardiademon
    public GetMemoirUser (ExchangeInformationWithTheServer.AfterExchange AfterExchange , int IdMemoir)
    {
        this.idMemoir = IdMemoir;
        this.afterExchange = AfterExchange;
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
        Request.MKRequest ();
        Request._Put.Put (GetValues.getString ("nr__id") , idMemoir);
        request = Request.GetRequest ();
    }

    @bardiademon
    @Override
    public void Exchange ()
    {
        assert request != null;
        if (request.isBuilt ())
        {
            sendInfoToServer = new SendInfoToServer (this::AfterExchange , Url.GetUrl ("ap__get_memoir_user") , request.getParam ());
            sendInfoToServer.setHeader (MakeHeader.MakeHeaderLogin (GetValues.getString ("nrh__get_memoir_user")));
            sendInfoToServer.apply ();
        }
        else CloseTheApp.close ();
    }

    @bardiademon
    @Override
    public void AfterExchange ()
    {
        assert sendInfoToServer != null;
        wasTaken = (sendInfoToServer.sendAndGetAnswer () && sendInfoToServer.isCode200 ());
        if (wasTaken)
        {
            if (sendInfoToServer.getAnswerServer ().matches ("[0-9]*"))
                wasTaken = false;
            else
            {
                try
                {
                    toSplit (sendInfoToServer.getAnswerServer ());
                }
                catch (JSONException e)
                {
                    wasTaken = false;
                }
            }

        }
        afterExchange.Callback ();
    }

    @bardiademon
    private void toSplit (String answerServer) throws JSONException
    {
        JSONArray jsonArray = new JSONArray (answerServer);

        if (jsonArray.length () > 0)
        {
            int id;
            String name, subject, date, link;
            boolean open, confirmation;
            JSONGetStringUtf8 jsonObject;
            foundMemoirUsers = new ArrayList <> ();
            for (int i = 0, len = jsonArray.length (); i < len; i++)
            {
                jsonObject = new JSONGetStringUtf8 (jsonArray.getString (i));
                id = jsonObject.getInt (AnswerServer.KJS.KJSGetMemoirUser.ID);
                name = jsonObject.getString (AnswerServer.KJS.KJSGetMemoirUser.NAME);
                subject = jsonObject.getString (AnswerServer.KJS.KJSGetMemoirUser.SUBJECT);
                date = jsonObject.getString (AnswerServer.KJS.KJSGetMemoirUser.DATE , false);
                link = jsonObject.getString (AnswerServer.KJS.KJSGetMemoirUser.LINK , false);
                open = jsonObject.getBoolean (AnswerServer.KJS.KJSGetMemoirUser.OPEN);
                confirmation = jsonObject.getBoolean (AnswerServer.KJS.KJSGetMemoirUser.CONFIRMATION);
                foundMemoirUsers.add (new FoundMemoirUser (id , name , subject , date , link , confirmation , open));
            }
        }

    }

    @bardiademon
    @Override
    public boolean AnswerServerOrResult ()
    {
        return wasTaken;
    }

    @bardiademon
    public ArrayList <FoundMemoirUser> getFoundMemoirUsers ()
    {
        return foundMemoirUsers;
    }
}
