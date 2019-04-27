package bardiademon.Memoir.Server.Get;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import bardiademon.Memoir.Other.MakeHeader;
import bardiademon.Memoir.Server.AnswerServer;
import bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues;
import bardiademon.Memoir.bardiademon.Class.Other.JSONGetStringUtf8;
import bardiademon.Memoir.bardiademon.Class.Server.MakeRequest.Request;
import bardiademon.Memoir.bardiademon.Class.Server.SendInfoToServer;
import bardiademon.Memoir.bardiademon.Class.Server.Url;
import bardiademon.Memoir.bardiademon.Class.View.ShowMessage;
import bardiademon.Memoir.bardiademon.Interface.ExchangeInformationWithTheServer;

public class GetSubject implements ExchangeInformationWithTheServer
{

    private Request request;

    private SendInfoToServer sendInfoToServer;

    private boolean found;
    private List <String> subject;

    private ExchangeInformationWithTheServer.AfterExchange afterExchange;

    public GetSubject (ExchangeInformationWithTheServer.AfterExchange AfterExchange)
    {
        this.afterExchange = AfterExchange;
        RunClass ();
    }

    @Override
    public void RunClass ()
    {
        MakeRequest ();
        Exchange ();
    }

    @Override
    public void MakeRequest ()
    {
        request = new Request ();
        request.setParamEmpty ();
    }

    @Override
    public void Exchange ()
    {
        assert request != null;
        sendInfoToServer = new SendInfoToServer (this::AfterExchange , Url.GetUrl ("ap__get_subject") , request.getParam ());
        sendInfoToServer.setHeader (MakeHeader.MakeHeaderLogin (GetValues.getString ("nrh__get_subject")));
        sendInfoToServer.apply ();
    }

    @Override
    public void AfterExchange ()
    {
        assert sendInfoToServer != null;
        found = (sendInfoToServer.sendAndGetAnswer () && sendInfoToServer.isCode200 ());
        if (found)
        {
            String answerServer = sendInfoToServer.getAnswerServer ();
            try
            {
                JSONGetStringUtf8 info = new JSONGetStringUtf8 (new JSONGetStringUtf8 (answerServer).getString (AnswerServer.KJS.KJRSubject.NAME));
                subject = new ArrayList <> ();
                for (int i = 0, len = info.length (); i < len; i++)
                    subject.add (info.getString (String.valueOf (i)));
            }
            catch (JSONException e)
            {
                found = false;
            }
        }
        afterExchange.Callback ();
    }

    @Override
    public boolean AnswerServerOrResult ()
    {
        return found;
    }

    public List <String> getSubject ()
    {
        return subject;
    }
}
