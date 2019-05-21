package bardiademon.Memoir.Server.Get;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import bardiademon.Memoir.Other.MakeHeader;
import bardiademon.Memoir.bardiademon.Class.Other.CloseTheApp;
import bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues;
import bardiademon.Memoir.bardiademon.Class.Server.MakeRequest.Request;
import bardiademon.Memoir.bardiademon.Class.Server.SendInfoToServer;
import bardiademon.Memoir.bardiademon.Class.Server.Url;
import bardiademon.Memoir.bardiademon.Interface.ExchangeInformationWithTheServer;

public class GetTextMemoir implements ExchangeInformationWithTheServer
{

    private AfterGetText afterGetText;
    private int idMemoir;

    private SendInfoToServer sendInfoToServer;
    private Request request;

    private String textMemoir;

    public GetTextMemoir (AfterGetText _AfterGetText , int IdMemoir)
    {
        this.afterGetText = _AfterGetText;
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
        request = Request.Ready.SetRequestId (idMemoir);
    }

    @Override
    public void Exchange ()
    {
        assert request != null;
        if (request.isBuilt ())
        {
            sendInfoToServer = new SendInfoToServer (this::AfterExchange , Url.GetUrl ("ap__get_text_memoir") , request.getParam ());
            sendInfoToServer.setHeader (MakeHeader.MakeHeaderLogin (GetValues.getString ("nrh__get_text_memoir")));
            sendInfoToServer.apply ();
        }
        else CloseTheApp.close ();
    }

    @Override
    public void AfterExchange ()
    {
        assert sendInfoToServer != null;
        boolean wasTaken = (sendInfoToServer.sendAndGetAnswer () && sendInfoToServer.isCode200 ());
        if (wasTaken)
        {
            try
            {
                textMemoir = URLDecoder.decode (sendInfoToServer.getAnswerServer () , "UTF-8");
            }
            catch (UnsupportedEncodingException e)
            {
                wasTaken = false;
            }
        }
        afterGetText.AfterGet (wasTaken , textMemoir);
    }

    @Override
    public boolean AnswerServerOrResult ()
    {
        return false;
    }

    public interface AfterGetText
    {
        void AfterGet (boolean WasTaken , String TextMemoir);
    }
}
