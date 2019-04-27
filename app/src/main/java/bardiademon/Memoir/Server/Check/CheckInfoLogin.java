package bardiademon.Memoir.Server.Check;

import bardiademon.Memoir.Other.MakeHeader;
import bardiademon.Memoir.Server.AnswerServer;
import bardiademon.Memoir.bardiademon.Class.Other.CloseTheApp;
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

    @bardiademon
    public CheckInfoLogin (ExchangeInformationWithTheServer.AfterExchange AfterExchange, String Username, String Password)
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

        put.Put (getString ("nr__username"), username);
        put.Put (getString ("nr__password"), password);
        put.Put (getString ("nr__serial"), new GetSerialPhone ().getResult ());

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
            sendInfoToServer = new SendInfoToServer (this::AfterExchange, Url.GetUrl ("ap__CheckInfoLogin"), request.getParam ());
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
            Integer answerServer = Integer.parseInt (sendInfoToServer.getAnswerServer ());
            valid = answerServer.equals (AnswerServer.CheckInfoLogin.VALID);
        }
        afterExchange.Callback ();
    }

    @bardiademon
    @Override
    public boolean AnswerServerOrResult ()
    {
        return valid;
    }
}
