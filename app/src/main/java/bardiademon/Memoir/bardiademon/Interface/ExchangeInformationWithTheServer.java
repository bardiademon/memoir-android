package bardiademon.Memoir.bardiademon.Interface;

@bardiademon
public interface ExchangeInformationWithTheServer
{
    @bardiademon
    void RunClass ();

    @bardiademon
    void MakeRequest ();

    @bardiademon
    void Exchange ();

    @bardiademon
    void AfterExchange ();

    @bardiademon
    boolean AnswerServerOrResult ();

    @bardiademon
    interface AfterExchange
    {
        void Callback ();
    }

}
