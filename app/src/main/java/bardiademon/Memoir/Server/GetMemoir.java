package bardiademon.Memoir.Server;

import org.json.JSONException;

import bardiademon.Memoir.Server.Found.FoundMemoir;
import bardiademon.Memoir.Other.MakeHeader;
import bardiademon.Memoir.Server.AnswerServer.KJS.KJSGetMemoir;
import bardiademon.Memoir.bardiademon.Class.Other.CloseTheApp;
import bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues;
import bardiademon.Memoir.bardiademon.Class.Other.JSONGetStringUtf8;
import bardiademon.Memoir.bardiademon.Class.Server.MakeRequest.Request;
import bardiademon.Memoir.bardiademon.Class.Server.SendInfoToServer;
import bardiademon.Memoir.bardiademon.Class.Server.Url;
import bardiademon.Memoir.bardiademon.Interface.ExchangeInformationWithTheServer;
import bardiademon.Memoir.bardiademon.Interface.bardiademon;

@bardiademon
public class GetMemoir implements ExchangeInformationWithTheServer
{

    private String linkMemoir;

    private Request request;
    private SendInfoToServer sendInfoToServer;

    private boolean found;
    private String messageServer = "";


    private FoundMemoir foundMemoir;
    private JSONGetStringUtf8 info;

    private ExchangeInformationWithTheServer.AfterExchange afterExchange;

    @bardiademon
    public GetMemoir (ExchangeInformationWithTheServer.AfterExchange AfterExchange , String LinkMemoir)
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

        System.out.println (request.getParam ().toString ());
    }

    @bardiademon
    @Override
    public void Exchange ()
    {
        assert request != null;
        if (request.isBuilt ())
        {
            sendInfoToServer = new SendInfoToServer (this::AfterExchange , Url.GetUrl ("ap__get_memoir") , request.getParam ());
            sendInfoToServer.setHeader (MakeHeader.MakeHeaderLogin (GetValues.getString ("nrh__get_memoir")));
            sendInfoToServer.apply ();
        }
        else CloseTheApp.close ();
    }

    @bardiademon
    @Override
    public void AfterExchange ()
    {
        found = (sendInfoToServer.sendAndGetAnswer () && sendInfoToServer.isCode200 ());
        if (AnswerServerOrResult ())
        {
            String answerServer = sendInfoToServer.getAnswerServer ();
            try
            {
                info = new JSONGetStringUtf8 (answerServer);
                toSplit ();
            }
            catch (JSONException e)
            {
                found = false;
                int answerInt = Integer.parseInt (answerServer);
                switch (answerInt)
                {
                    case AnswerServer.GetMemoir.SC200.NOT_OPEN:
                        messageServer = GetValues.getString ("str_err__not_open_memoir");
                        break;
                    case AnswerServer.GetMemoir.SC200.NOT_CONFIRMATION:
                        messageServer = GetValues.getString ("str_err__not_confirmation_memoir");
                        break;
                    default:
                        messageServer = GetValues.getString ("str_err__add_new_memoir__err_public");
                        break;
                }
            }
        }
        else messageServer = GetValues.getString ("str_err__add_new_memoir__err_public");
        afterExchange.Callback ();
    }

    private void toSplit () throws JSONException
    {
        String name = info.getString (KJSGetMemoir.NAME , true);
        String subject = info.getString (KJSGetMemoir.SUBJECT , true);
        String text = info.getString (KJSGetMemoir.TEXT , true);
        String date = info.getString (KJSGetMemoir.DATE);
        int visit = info.getInt (KJSGetMemoir.VISIT);
        int visitUser = info.getInt (KJSGetMemoir.VISIT_USER);
        String timeRecord = info.getString (KJSGetMemoir.TIME_RECORD);
        String timeLastEdit = info.getString (KJSGetMemoir.TIME_LAST_EDIT);
        int like = info.getInt (KJSGetMemoir.LIKE);
        int comment = info.getInt (KJSGetMemoir.COMMENT);
        boolean liked = info.getBoolean (KJSGetMemoir.LIKED);
        boolean isMemoirForMe = (info.has (KJSGetMemoir.IS_MEMOIR_FOR_YOU));
        foundMemoir = new FoundMemoir (name , subject , text , date , timeRecord , timeLastEdit , visit , visitUser , like , comment , liked , isMemoirForMe);
    }

    @bardiademon
    @Override
    public boolean AnswerServerOrResult ()
    {
        return found;
    }

    public String getMessageServer ()
    {
        return messageServer;
    }

    public FoundMemoir getFoundMemoir ()
    {
        return foundMemoir;
    }
}
