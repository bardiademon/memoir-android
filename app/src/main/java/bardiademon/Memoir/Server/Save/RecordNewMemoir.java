package bardiademon.Memoir.Server.Save;

import bardiademon.Memoir.Login;
import bardiademon.Memoir.Other.MakeHeader;
import bardiademon.Memoir.Server.AnswerServer;
import bardiademon.Memoir.bardiademon.Class.Other.CloseTheApp;
import bardiademon.Memoir.bardiademon.Class.Other.Encode;
import bardiademon.Memoir.bardiademon.Class.Server.MakeRequest.Request;
import bardiademon.Memoir.bardiademon.Class.Server.SendInfoToServer;
import bardiademon.Memoir.bardiademon.Class.Server.Url;
import bardiademon.Memoir.bardiademon.Class.View.ShowMessage;
import bardiademon.Memoir.bardiademon.Interface.ExchangeInformationWithTheServer;
import bardiademon.Memoir.bardiademon.Interface.bardiademon;

import static bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues.getString;

public class RecordNewMemoir implements ExchangeInformationWithTheServer
{

    private String name, subject, link, date, text;
    private boolean open;
    private Request request;

    private SendInfoToServer sendInfoToServer;

    private boolean errorFromServer;
    private String messageServer;

    private ExchangeInformationWithTheServer.AfterExchange afterExchange;

    private boolean done;

    @bardiademon
    public RecordNewMemoir (ExchangeInformationWithTheServer.AfterExchange AfterExchange, String Name, String Subject, String Link, String Date, String Text, boolean Open)
    {
        this.afterExchange = AfterExchange;
        this.name = Name;
        this.subject = Subject;
        this.link = Link;
        this.date = Date;
        this.text = Text;
        this.open = Open;
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

        put.Put (getString ("nr__name"), Encode.encode (name));
        put.Put (getString ("nr__subject"), Encode.encode (subject));
        put.Put (getString ("nr__link"), link);
        put.Put (getString ("nr__date"), date);
        put.Put (getString ("nr__text"), Encode.encode (text));
        put.Put (getString ("nr__open"), open);

        put.Apply ();
        request.Apply ();
    }

    @bardiademon
    @Override
    public void Exchange ()
    {
        System.out.println (request.getParam ().toString ());
        assert request != null;
        if (request.isBuilt ())
        {
            sendInfoToServer = new SendInfoToServer (this::AfterExchange, Url.GetUrl ("ap__record_new_memoir"), request.getParam ());
            sendInfoToServer.setHeader (MakeHeader.MakeHeaderLogin (getString ("name_request__record_new_memoir")));
            sendInfoToServer.apply ();
        }
        else CloseTheApp.close (true);
    }

    @bardiademon
    @Override
    public void AfterExchange ()
    {
        assert sendInfoToServer != null;
        int answerServer;
        if ((done = (sendInfoToServer.sendAndGetAnswer () && sendInfoToServer.isCode200 ())))
        {
            answerServer = Integer.parseInt (sendInfoToServer.getAnswerServer ());
            switch (answerServer)
            {
                case AnswerServer.RecordNewMemoir.SC200.RECORDED:
                    setErrorFromServer ("str_suc__add_new_memoir__suc", false);
                    break;
                case AnswerServer.RecordNewMemoir.SC200.ERROR_DATE:
                    setErrorFromServer ("str_err__add_new_memoir__err_date", true);
                    break;
                case AnswerServer.RecordNewMemoir.SC200.ERROR_LINK:
                    setErrorFromServer ("str_err__add_new_memoir__err_link", true);
                    break;
                case AnswerServer.RecordNewMemoir.SC200.NOT_RECORDED:
                default:
                    setErrorFromServer ("str_err__add_new_memoir__err_public", true);
                    break;
            }
        }
        else
        {
            if (sendInfoToServer.sendAndGetAnswer ())
            {
                answerServer = Integer.parseInt (sendInfoToServer.getAnswerServer ());
                switch (answerServer)
                {
                    case AnswerServer.RecordNewMemoir.SC400.DUPLICATE_LINK:
                        setErrorFromServer ("str_err__add_new_memoir__err_duplicate_link", true);
                        break;
                    case AnswerServer.RecordNewMemoir.NOT_LOGGED_IN:
                        Login.LogOut ();
                        break;
                    default:
                        setErrorFromServer ("str_err__add_new_memoir__err_public", true);
                        break;
                }
            }
            else setErrorFromServer ("str_err__add_new_memoir__err_public", true);
        }
        afterExchange.Callback ();
    }

    @bardiademon
    @Override
    public boolean AnswerServerOrResult ()
    {
        return done;
    }

    private void setErrorFromServer (String keyMessage, boolean isError)
    {
        this.messageServer = getString (keyMessage);
        errorFromServer = isError;
    }

    @bardiademon
    public String messageServer ()
    {
        return messageServer;
    }

    @bardiademon
    public boolean isMessageErrFromServer ()
    {
        return errorFromServer;
    }
}
