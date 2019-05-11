package bardiademon.Memoir.Server.Save;

import bardiademon.Memoir.Other.MakeHeader;
import bardiademon.Memoir.Server.AnswerServer;
import bardiademon.Memoir.bardiademon.Class.Other.CloseTheApp;
import bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues;
import bardiademon.Memoir.bardiademon.Class.Server.MakeRequest.Request;
import bardiademon.Memoir.bardiademon.Class.Server.SendInfoToServer;
import bardiademon.Memoir.bardiademon.Class.Server.Url;
import bardiademon.Memoir.bardiademon.Interface.ExchangeInformationWithTheServer;

import static bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues.getString;

public class SendComment implements ExchangeInformationWithTheServer
{

    private ExchangeInformationWithTheServer.AfterExchange afterExchange;
    private String linkMemoir, txtComment;


    private boolean ok;

    private Request request;
    private SendInfoToServer sendInfoToServer;

    public SendComment (ExchangeInformationWithTheServer.AfterExchange AfterExchange , String LinkMemoir , String TxtComment)
    {
        this.afterExchange = AfterExchange;
        this.linkMemoir = LinkMemoir;
        this.txtComment = TxtComment;
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
        Request.Put put = new Request.Put ();
        put.Put (GetValues.getString ("nr__lnkm") , linkMemoir);
        put.Put (GetValues.getString ("nr__text") , txtComment);
        put.Apply ();
        request.Apply ();
    }

    @Override
    public void Exchange ()
    {
        assert request != null;
        if (request.isBuilt ())
        {
            System.out.println (request.getParam ());
            System.out.println (Url.GetUrl ("ap__new_comment"));
            sendInfoToServer = new SendInfoToServer (this::AfterExchange , Url.GetUrl ("ap__new_comment") , request.getParam ());
            System.out.println (MakeHeader.MakeHeaderLogin (getString ("nrh__new_comment")));
            sendInfoToServer.setHeader (MakeHeader.MakeHeaderLogin (getString ("nrh__new_comment")));
            sendInfoToServer.apply ();
        }
        else CloseTheApp.close (true);
    }

    @Override
    public void AfterExchange ()
    {
        assert sendInfoToServer != null;
        ok = (sendInfoToServer.sendAndGetAnswer () && sendInfoToServer.isCode200 () && sendInfoToServer.getAnswerServer ().equals (String.valueOf (AnswerServer.PublicAnswer.IS_OK)));
        afterExchange.Callback ();
    }

    @Override
    public boolean AnswerServerOrResult ()
    {
        return ok;
    }
}
