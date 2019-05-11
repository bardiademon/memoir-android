package bardiademon.Memoir.Server.Check;

import bardiademon.Memoir.Other.MakeHeader;
import bardiademon.Memoir.Server.AnswerServer;
import bardiademon.Memoir.bardiademon.Class.Other.CloseTheApp;
import bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues;
import bardiademon.Memoir.bardiademon.Class.Server.MakeRequest.Request;
import bardiademon.Memoir.bardiademon.Class.Server.SendInfoToServer;
import bardiademon.Memoir.bardiademon.Class.Server.Url;
import bardiademon.Memoir.bardiademon.Interface.ExchangeInformationWithTheServer;

public class CheckINeedItComment implements ExchangeInformationWithTheServer
{

    private ExchangeInformationWithTheServer.AfterExchange afterExchange;
    private int idComment;

    private SendInfoToServer sendInfoToServer;
    private Request request;

    private boolean is;

    public CheckINeedItComment (ExchangeInformationWithTheServer.AfterExchange AfterExchange , int IdComment)
    {
        this.afterExchange = AfterExchange;
        this.idComment = IdComment;
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
        request = Request.Ready.SetRequestId (idComment);
    }

    @Override
    public void Exchange ()
    {
        assert request != null;
        if (request.isBuilt ())
        {
            sendInfoToServer = new SendInfoToServer (this::AfterExchange , Url.GetUrl ("ap__set_i_need_cmnt") , request.getParam ());
            sendInfoToServer.setHeader (MakeHeader.MakeHeaderLogin (GetValues.getString ("nrh__ciiinc")));
            sendInfoToServer.apply ();
        }
        else CloseTheApp.close ();
    }

    @Override
    public void AfterExchange ()
    {
        assert sendInfoToServer != null;
        is = (sendInfoToServer.sendAndGetAnswer () && sendInfoToServer.isCode200 ());
        if (is)
        {
            try
            {
                Integer answer = Integer.parseInt (sendInfoToServer.getAnswerServer ());
                is = (answer.equals (AnswerServer.PublicAnswer.FOUND));
            }
            catch (Exception e)
            {
                is = false;
            }
        }
        afterExchange.Callback ();
    }

    @Override
    public boolean AnswerServerOrResult ()
    {
        return is;
    }
}
