package bardiademon.Memoir.bardiademon.Class.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import bardiademon.Memoir.R;
import bardiademon.Memoir.bardiademon.Class.About.About;
import bardiademon.Memoir.bardiademon.Class.BeforeRunActivity.G;
import bardiademon.Memoir.bardiademon.Class.Other.ActiveSwitching;
import bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues;
import bardiademon.Memoir.bardiademon.Interface.Activity;
import bardiademon.Memoir.bardiademon.Interface.bardiademon;

public class ActivityFatalError extends AppCompatActivity implements Activity
{

    private TextView txtExplanationError, txtTitleError;

    private String explanationError, titleError;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fatal_error);
        RunClass();
    }

    @Override
    public void RunClass ()
    {
        GSet();
        SetAbout();
        get_intent();
        SetTools();
        setValueTools();
    }

    @bardiademon
    @Override
    public void GSet ()
    {
        G.setActivity(ActivityFatalError.this);
        G.setViewForActivity();
    }

    @bardiademon
    @Override
    public void SetAbout ()
    {
        new About();
    }

    @bardiademon
    private void get_intent ()
    {
        Bundle bundle = getIntent().getExtras();

        if (bundle != null)
        {
            titleError = bundle.getString("title_error");
            explanationError = bundle.getString("explanation_error");
        }
        else
            titleError = GetValues.getString("default__activity_fatal_error__message_error");
    }

    @bardiademon
    private void setValueTools ()
    {
        txtTitleError.setText(titleError);
        txtExplanationError.setText(explanationError);
    }

    @bardiademon
    @Override
    public void SetTools ()
    {
        txtTitleError = findViewById(R.id.txt_title_error);
        txtExplanationError = findViewById(R.id.txt_explanation_error);
    }

    @Override
    public void SetOnClick ()
    {

    }

    @bardiademon
    @Override
    public void onBackPressed ()
    {
        if (G.getActivityClassForBack() != null)
            new ActiveSwitching(G.getActivityClassForBack());
        else
            new ActiveSwitching(G.getActivityClass());
    }

    @Override
    public void View ()
    {

    }

    @Override
    public void Title ()
    {

    }
}
