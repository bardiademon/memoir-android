package bardiademon.Memoir.Server.Update;

import org.jetbrains.annotations.NotNull;

import bardiademon.Memoir.Other.MakeHeader;
import bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues;
import bardiademon.Memoir.bardiademon.Class.Server.MakeRequest.Request;
import bardiademon.Memoir.bardiademon.Class.Server.SendInfoToServer;
import bardiademon.Memoir.bardiademon.Class.Server.Url;
import bardiademon.Memoir.bardiademon.Interface.ExchangeInformationWithTheServer;
import bardiademon.Memoir.bardiademon.Interface.bardiademon;

// LVC => Like Visit Comment
public class UpdateLVC implements ExchangeInformationWithTheServer
{

    public static final String UV = "uv";// UPDATE VISIT
    public static final String UL = "ul";// UPDATE LIKE
    public static final String UC = "uc";// UPDATE COMMENT

    private String linkMemoir;

    private Request request;

    private String updateLVC; // LVC => Like Visit Comment

    private String nameRequest, namePage;

    public UpdateLVC (String LinkMemoir , @bardiademon @NotNull String UpdateLVC)
    {
        if (UpdateLVC.equals (UV) || UpdateLVC.equals (UL) || UpdateLVC.equals (UC))
        {
            this.linkMemoir = LinkMemoir;
            this.updateLVC = UpdateLVC;
            RunClass ();
        }
    }

    @Override
    public void RunClass ()
    {
        requestPage ();
        MakeRequest ();
        Exchange ();
    }


    private void requestPage ()
    {
        switch (updateLVC)
        {
            case UL:
                get ("nrh__update_like" , "ap__update_like");
                break;
            case UC:
                get ("nrh__update_comment" , "ap__update_comment");
                break;
            case UV:
                get ("nrh__update_visit" , "ap__update_visit");
                break;
        }
    }

    private void get (String kerRequest , String keyPage)
    {
        nameRequest = GetValues.getString (kerRequest);
        namePage = keyPage;
    }

    @Override
    public void MakeRequest ()
    {
        request = new Request ();
        Request.Put put = new Request.Put ();
        put.Put (GetValues.getString ("nr__link_memoir") , linkMemoir);
        put.Apply ();
        request.Apply ();
    }

    @Override
    public void Exchange ()
    {
        assert request != null;
        SendInfoToServer sendInfoToServer = new SendInfoToServer (this::AfterExchange , Url.GetUrl (namePage) , request.getParam ());
        sendInfoToServer.setHeader (MakeHeader.MakeHeaderLogin (nameRequest));
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
