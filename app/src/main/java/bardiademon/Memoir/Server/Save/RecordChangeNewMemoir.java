package bardiademon.Memoir.Server.Save;

import bardiademon.Memoir.Login;
import bardiademon.Memoir.Other.MakeHeader;
import bardiademon.Memoir.Server.AnswerServer;
import bardiademon.Memoir.bardiademon.Class.Other.CloseTheApp;
import bardiademon.Memoir.bardiademon.Class.Other.Encode;
import bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues;
import bardiademon.Memoir.bardiademon.Class.Server.MakeRequest.Request;
import bardiademon.Memoir.bardiademon.Class.Server.SendInfoToServer;
import bardiademon.Memoir.bardiademon.Class.Server.Url;
import bardiademon.Memoir.bardiademon.Interface.ExchangeInformationWithTheServer;
import bardiademon.Memoir.bardiademon.Interface.bardiademon;

import static bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues.getString;

public class RecordChangeNewMemoir implements ExchangeInformationWithTheServer
{

    private String name, subject, link, date, text;
    private int id;
    private boolean open, change;
    private Request request;

    private SendInfoToServer sendInfoToServer;

    private boolean errorFromServer;
    private String messageServer;

    private ExchangeInformationWithTheServer.AfterExchange afterExchange;

    private boolean done;

    @bardiademon
    public RecordChangeNewMemoir (ExchangeInformationWithTheServer.AfterExchange AfterExchange , String Name , String Subject , String Link , String Date , String Text , boolean Open , boolean Change , int Id)
    {
        this.afterExchange = AfterExchange;
        this.name = Name;
        this.subject = Subject;
        this.link = Link;
        this.date = Date;
        this.text = Text;
        this.open = Open;
        this.change = Change;
        this.id = Id;
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

        put.Put (getString ("nr__name") , Encode.encode (name));
        put.Put (getString ("nr__subject") , Encode.encode (subject));
        put.Put (getString ("nr__link") , link);
        put.Put (getString ("nr__date") , date);
        put.Put (getString ("nr__text") , Encode.encode (text));
        put.Put (getString ("nr__open") , open);
        if (change)
        {
            put.Put (GetValues.getString ("nr__change") , true);
            put.Put (GetValues.getString ("nr__id") , id);
        }
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
            sendInfoToServer = new SendInfoToServer (this::AfterExchange , Url.GetUrl ("ap__record_change_memoir") , request.getParam ());
            sendInfoToServer.setHeader (MakeHeader.MakeHeaderLogin (getString ("nt__record_change_memoir")));
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
                case AnswerServer.RecordChangeMemoir.SC200.RECORDED:
                case AnswerServer.RecordChangeMemoir.SC200.CHANGED:
                {
                    setErrorFromServer (((change) ? "str_suc__change_memoir__suc" : "str_suc__add_new_memoir__suc") , false);
                    break;
                }
                case AnswerServer.RecordChangeMemoir.SC200.ERROR_DATE:
                    setErrorFromServer ("str_err__add_new_memoir__err_date" , true);
                    break;
                case AnswerServer.RecordChangeMemoir.SC200.ERROR_LINK:
                    setErrorFromServer ("str_err__add_new_memoir__err_link" , true);
                    break;
                case AnswerServer.RecordChangeMemoir.SC200.NOT_RECORDED:
                default:
                    setErrorFromServer ("str_err__add_new_memoir__err_public" , true);
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
                    case AnswerServer.RecordChangeMemoir.SC400.DUPLICATE_LINK:
                        setErrorFromServer ("str_err__add_new_memoir__err_duplicate_link" , true);
                        break;
                    case AnswerServer.RecordChangeMemoir.NOT_LOGGED_IN:
                        Login.LogOut ();
                        break;
                    default:
                        setErrorFromServer ("str_err__add_new_memoir__err_public" , true);
                        break;
                }
            }
            else setErrorFromServer ("str_err__add_new_memoir__err_public" , true);
        }
        afterExchange.Callback ();
    }

    @bardiademon
    @Override
    public boolean AnswerServerOrResult ()
    {
        return done;
    }

    private void setErrorFromServer (String keyMessage , boolean isError)
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
