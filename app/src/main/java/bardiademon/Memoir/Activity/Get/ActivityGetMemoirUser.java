package bardiademon.Memoir.Activity.Get;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ListView;

import bardiademon.Memoir.Activity.Add.ActivityAddChangeMemoir;
import bardiademon.Memoir.Adapter.AdapterLstShowMemoirUser;
import bardiademon.Memoir.R;
import bardiademon.Memoir.Server.GetMemoirUser;
import bardiademon.Memoir.bardiademon.Class.BeforeRunActivity.G;
import bardiademon.Memoir.bardiademon.Class.Other.ActiveSwitching;
import bardiademon.Memoir.bardiademon.Class.View.Title;
import bardiademon.Memoir.bardiademon.Class.View.Toast;
import bardiademon.Memoir.bardiademon.Class.View.Wait;
import bardiademon.Memoir.bardiademon.Interface.Activity;
import bardiademon.Memoir.bardiademon.Interface.bardiademon;

@bardiademon
public class ActivityGetMemoirUser extends AppCompatActivity implements Activity
{

    public static final int INDEX_NUM_TITLE = 0;

    private GetMemoirUser getMemoirUser;
    private ListView listMemoir;
    private Button btnNew, btnBack;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_get_memoir_user);
        RunClass ();
    }

    @bardiademon
    @Override
    public void RunClass ()
    {
        View ();
        SetTools ();
        SetOnClick ();
        getMemoirUser ();
    }

    @bardiademon
    @Override
    public void SetTools ()
    {
        listMemoir = findViewById (R.id.id__activity_get_memoir_user__list_memoir);
        btnNew = findViewById (R.id.id__activity_get_memoir_user__add_new);
        btnBack = findViewById (R.id.id__activity_get_memoir_user__close_activity);
    }

    @bardiademon
    @Override
    public void SetOnClick ()
    {
        btnNew.setOnClickListener (v -> setOnClickBtnNew ());
        btnBack.setOnClickListener (v -> onBackPressed ());
    }

    @bardiademon
    private void setOnClickBtnNew ()
    {
        new ActiveSwitching (ActivityAddChangeMemoir.class);
    }

    @bardiademon
    private void getMemoirUser ()
    {
        Wait.Set ();
        getMemoirUser = new GetMemoirUser (this::afterGet , GetMemoirUser.GET_ALL);
    }

    @bardiademon
    private void afterGet ()
    {
        Wait.CloseWait ();
        if (getMemoirUser.AnswerServerOrResult ())
        {
            Title.ShowTheAmountOf.txtShowTheAmountOfLowOffOrIncrease
                    (getMemoirUser.getFoundMemoirUsers ().size () , Title.ShowTheAmountOf.NULL , INDEX_NUM_TITLE);
            setListMemoir ();
        }
        else Toast.ToastReady.NOT_FOUND ();
    }

    @bardiademon
    private void setListMemoir ()
    {
        listMemoir.setAdapter (new AdapterLstShowMemoirUser (getMemoirUser.getFoundMemoirUsers ()));
    }

    @bardiademon
    @Override
    public void View ()
    {
        GSet ();
        SetAbout ();
        Title ();
    }

    @bardiademon
    @Override
    public void GSet ()
    {
        G.setActivity (this);
        G.setActivityClass (ActivityGetMemoirUser.class);
        G.setViewForActivity ();
        G.setActivityClassForBack (G.getActivityClass ());
    }

    @bardiademon
    @Override
    public void SetAbout ()
    {
    }

    @bardiademon
    @Override
    public void Title ()
    {
        Title title = new Title ();
        title.setText (Title.FOUND_AUTO_TITLE_ACTIVITY);
        title.setGravity (Gravity.END);
        title.setShowTheAmountOf (true , 0 , INDEX_NUM_TITLE);
        title.apply ();
    }

    @Override
    public void onBackPressed ()
    {
        new ActiveSwitching (G.getActivityHomeClass ());
    }
}
