package bardiademon.Memoir.Server.Delete;

import bardiademon.Memoir.Other.MakeHeader;
import bardiademon.Memoir.Server.AnswerServer;
import bardiademon.Memoir.bardiademon.Class.Other.CloseTheApp;
import bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues;
import bardiademon.Memoir.bardiademon.Class.Server.MakeRequest.Request;
import bardiademon.Memoir.bardiademon.Class.Server.SendInfoToServer;
import bardiademon.Memoir.bardiademon.Class.Server.Url;
import bardiademon.Memoir.bardiademon.Interface.ExchangeInformationWithTheServer;
import bardiademon.Memoir.bardiademon.Interface.bardiademon;

@bardiademon
public class DeleteComment implements ExchangeInformationWithTheServer
{

    private ExchangeInformationWithTheServer.AfterExchange afterExchange;
    private String linkMemoir;
    private int idComment;

    private SendInfoToServer sendInfoToServer;
    private Request request;

    private boolean deleted;

    @bardiademon
    public DeleteComment (ExchangeInformationWithTheServer.AfterExchange AfterExchange , String LinkMemoir , int IdComment)
    {
        this.afterExchange = AfterExchange;
        this.linkMemoir = LinkMemoir;
        this.idComment = IdComment;
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
        put.Put (GetValues.getString ("nr__lnkm") , linkMemoir);
        put.Put (GetValues.getString ("nr__idc") , idComment);
        put.Apply ();
        request.Apply ();
    }

    @bardiademon
    @Override
    public void Exchange ()
    {
        assert request != null;
        if (request.isBuilt ())
        {
            sendInfoToServer = new SendInfoToServer (this::AfterExchange , Url.GetUrl ("ap__delcmnt") , request.getParam ());
            sendInfoToServer.setHeader (MakeHeader.MakeHeaderLogin (GetValues.getString ("nrh__delcmnt")));
            sendInfoToServer.apply ();
        }
        else CloseTheApp.close ();
    }

    @bardiademon
    @Override
    public void AfterExchange ()
    {
        assert sendInfoToServer != null;
        deleted = (sendInfoToServer.sendAndGetAnswer () && sendInfoToServer.isCode200 () && Integer.parseInt (sendInfoToServer.getAnswerServer ()) == AnswerServer.PublicAnswer.IS_OK);
        afterExchange.Callback ();
    }

    @bardiademon
    @Override
    public boolean AnswerServerOrResult ()
    {
        return deleted;
    }
}
