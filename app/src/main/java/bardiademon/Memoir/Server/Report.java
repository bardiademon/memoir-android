package bardiademon.Memoir.Server;

import org.jetbrains.annotations.NotNull;

import bardiademon.Memoir.Other.MakeHeader;
import bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues;
import bardiademon.Memoir.bardiademon.Class.Server.MakeRequest.Request;
import bardiademon.Memoir.bardiademon.Class.Server.SendInfoToServer;
import bardiademon.Memoir.bardiademon.Class.Server.Url;
import bardiademon.Memoir.bardiademon.Interface.ExchangeInformationWithTheServer;

public class Report implements ExchangeInformationWithTheServer
{

    private String text, reportRelated, dec;


    private Request request;

    public Report (String Text , String ReportRelated , @NotNull String Description)
    {
        this.text = Text;
        this.reportRelated = ReportRelated;
        this.dec = Description;
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
        //   r => report
        put.Put (GetValues.getString ("nr__text_r") , text);
        put.Put (GetValues.getString ("nr__report_related") , reportRelated);
        put.Put (GetValues.getString ("nr__des_r") , dec);
        put.Apply ();
        request.Apply ();
    }

    @Override
    public void Exchange ()
    {
        assert request != null;
        SendInfoToServer sendInfoToServer = new SendInfoToServer (this::AfterExchange , Url.GetUrl ("ap__report") , request.getParam ());
        sendInfoToServer.setHeader (MakeHeader.MakeHeaderLogin (GetValues.getString ("nrh__report")));
        sendInfoToServer.apply ();
    }

    @Override
    public void AfterExchange ()
    {
    }

    @Override
    public boolean AnswerServerOrResult ()
    {
        return false;
    }
}
