package bardiademon.Memoir.Server.Set;

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
public class SetINeedItCmnt implements ExchangeInformationWithTheServer
{

    private int idComment;
    private ExchangeInformationWithTheServer.AfterExchange afterExchange;

    private SendInfoToServer sendInfoToServer;
    private Request request;

    private boolean requestOk, set;

    private String msgServer;

    @bardiademon
    public SetINeedItCmnt (ExchangeInformationWithTheServer.AfterExchange AfterExchange , int IdComment)
    {
        this.afterExchange = AfterExchange;
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
        request = Request.Ready.SetRequestId (idComment);
    }

    @bardiademon
    @Override
    public void Exchange ()
    {
        assert request != null;
        if (request.isBuilt ())
        {
            sendInfoToServer = new SendInfoToServer (this::AfterExchange , Url.GetUrl ("ap__set_i_need_cmnt") , request.getParam ());
            sendInfoToServer.setHeader (MakeHeader.MakeHeaderLogin (GetValues.getString ("nrh__sinic")));
            sendInfoToServer.apply ();
        }
        else CloseTheApp.close ();
    }

    @bardiademon
    @Override
    public void AfterExchange ()
    {
        assert sendInfoToServer != null;
        requestOk = (sendInfoToServer.sendAndGetAnswer () && sendInfoToServer.isCode200 ());
        if (requestOk)
        {
            try
            {
                int answer = Integer.parseInt (sendInfoToServer.getAnswerServer ());
                switch (answer)
                {
                    case AnswerServer.PublicAnswer.SET:
                        set = true;
                        setMessage ("set_ineedcmnt");
                        break;
                    case AnswerServer.PublicAnswer.UNSET:
                        set = false;
                        setMessage ("unset_ineedcmnt");
                        break;
                    default:
                        throw new Exception ();
                }
            }
            catch (Exception e)
            {
                requestOk = false;
                set = false;
            }
        }
        afterExchange.Callback ();
    }

    @bardiademon
    private void setMessage (String message)
    {
        msgServer = GetValues.getString (message);
    }

    @bardiademon
    @Override
    public boolean AnswerServerOrResult ()
    {
        return requestOk;
    }

    @bardiademon
    public boolean isSet ()
    {
        return set;
    }

    @bardiademon
    public String getMsgServer ()
    {
        return msgServer;
    }
}
