package bardiademon.Memoir.Server.Check;

import bardiademon.Memoir.Other.MakeHeader;
import bardiademon.Memoir.Server.AnswerServer;
import bardiademon.Memoir.bardiademon.Class.Other.CloseTheApp;
import bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues;
import bardiademon.Memoir.bardiademon.Class.Other.Service.GetSerialPhone;
import bardiademon.Memoir.bardiademon.Class.Server.MakeRequest.Request;
import bardiademon.Memoir.bardiademon.Class.Server.SendInfoToServer;
import bardiademon.Memoir.bardiademon.Class.Server.Url;
import bardiademon.Memoir.bardiademon.Interface.ExchangeInformationWithTheServer;
import bardiademon.Memoir.bardiademon.Interface.bardiademon;

import static bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues.getString;

@bardiademon
public class CheckInfoLogin implements ExchangeInformationWithTheServer
{

    private ExchangeInformationWithTheServer.AfterExchange afterExchange;
    private String username, password;

    private SendInfoToServer sendInfoToServer;
    private Request request;

    private boolean valid;

    private String msgServer;
    private boolean mServer; // m => message

    @bardiademon
    public CheckInfoLogin (ExchangeInformationWithTheServer.AfterExchange AfterExchange , String Username , String Password)
    {
        this.afterExchange = AfterExchange;
        this.username = Username;
        this.password = Password;
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

        put.Put (getString ("nr__username") , username);
        put.Put (getString ("nr__password") , password);
        put.Put (getString ("nr__serial") , new GetSerialPhone ().getResult ());

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
            sendInfoToServer = new SendInfoToServer (this::AfterExchange , Url.GetUrl ("ap__CheckInfoLogin") , request.getParam ());
            sendInfoToServer.setHeader (MakeHeader.MakeHeaderRequest (getString ("name_request__check_info_login")));
            sendInfoToServer.apply ();
        }
        else CloseTheApp.close (true);
    }

    @bardiademon
    @Override
    public void AfterExchange ()
    {
        assert sendInfoToServer != null;
        valid = (sendInfoToServer.sendAndGetAnswer () && sendInfoToServer.isCode200 ());
        if (valid)
        {
            try
            {
                int answerServer = Integer.parseInt (sendInfoToServer.getAnswerServer ());
                switch (answerServer)
                {
                    case AnswerServer.CheckInfoLogin.SC200.VALID:
                        break;
                    case AnswerServer.CheckInfoLogin.SC200.DEACTIVE_ACCOUNT:
                        setMServer ();
                        break;

                }
            }
            catch (Exception e)
            {
                valid = false;
            }
        }
        afterExchange.Callback ();
    }

    @bardiademon
    private void setMServer ()
    {
        msgServer = GetValues.getString ("str_err__deactive_account");
        mServer = true;
        valid = false;
    }

    @bardiademon
    public String getMsgServer ()
    {
        return msgServer;
    }

    @bardiademon
    public boolean isMServer ()
    {
        return mServer;
    }

    @bardiademon
    @Override
    public boolean AnswerServerOrResult ()
    {
        return valid;
    }
}
