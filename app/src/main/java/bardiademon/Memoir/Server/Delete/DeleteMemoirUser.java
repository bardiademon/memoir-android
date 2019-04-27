package bardiademon.Memoir.Server.Delete;

import bardiademon.Memoir.Other.MakeHeader;
import bardiademon.Memoir.Server.AnswerServer;
import bardiademon.Memoir.bardiademon.Class.Other.CloseTheApp;
import bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues;
import bardiademon.Memoir.bardiademon.Class.Server.MakeRequest.Request;
import bardiademon.Memoir.bardiademon.Class.Server.SendInfoToServer;
import bardiademon.Memoir.bardiademon.Class.Server.Url;
import bardiademon.Memoir.bardiademon.Interface.ExchangeInformationWithTheServer;

public class DeleteMemoirUser implements ExchangeInformationWithTheServer
{
    private ExchangeInformationWithTheServer.AfterExchange afterExchange;
    private int idMemoir;

    private Request request;
    private SendInfoToServer sendInfoToServer;

    private boolean deleted;

    public DeleteMemoirUser (ExchangeInformationWithTheServer.AfterExchange AfterExchange , int IdMemoir)
    {
        this.afterExchange = AfterExchange;
        this.idMemoir = IdMemoir;
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
        Request.MKRequest ();
        Request._Put.Put (GetValues.getString ("nr__id") , idMemoir);
        request = Request.GetRequest ();
    }

    @Override
    public void Exchange ()
    {
        assert request != null;
        if (request.isBuilt ())
        {
            sendInfoToServer = new SendInfoToServer (this::AfterExchange , Url.GetUrl ("ap__delete_memoir_user") , request.getParam ());
            sendInfoToServer.setHeader (MakeHeader.MakeHeaderLogin (GetValues.getString ("nrh__delete_memoir_user")));
            sendInfoToServer.apply ();
        }
        else CloseTheApp.close ();
    }

    @Override
    public void AfterExchange ()
    {
        assert sendInfoToServer != null;
        deleted = (sendInfoToServer.sendAndGetAnswer () && sendInfoToServer.isCode200 ());
        deleted = (deleted && Integer.parseInt (sendInfoToServer.getAnswerServer ()) == AnswerServer.DelMemoirMemoir.SC200.DELETED);
        afterExchange.Callback ();
    }

    @Override
    public boolean AnswerServerOrResult ()
    {
        return deleted;
    }
}
