package bardiademon.Memoir.Activity;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.SignInButton;

import bardiademon.Memoir.Login;
import bardiademon.Memoir.R;
import bardiademon.Memoir.bardiademon.Class.BeforeRunActivity.G;
import bardiademon.Memoir.bardiademon.Class.Other.ActiveSwitching;
import bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues;
import bardiademon.Memoir.bardiademon.Class.View.Icon;
import bardiademon.Memoir.bardiademon.Class.View.Title;
import bardiademon.Memoir.bardiademon.Class.View.Toast;
import bardiademon.Memoir.bardiademon.Class.View.Wait;
import bardiademon.Memoir.bardiademon.Interface.Activity;
import bardiademon.Memoir.bardiademon.Interface.bardiademon;

@bardiademon
public class ActivityLogin extends AppCompatActivity implements Activity
{

    private Button btnLogin;
    private TextView btnRegister;
    private TextInputEditText txtUsername, txtPassword;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);
        RunClass ();
    }

    @bardiademon
    @Override
    public void RunClass ()
    {
        View ();
        SetTools ();
        SetOnClick ();
    }

    @bardiademon
    @Override
    public void SetTools ()
    {
        txtUsername = findViewById (R.id.id__activity_login__txt_username);
        txtPassword = findViewById (R.id.id__activity_login__txt_password);

        btnLogin = findViewById (R.id.id__activity_login__btn_login);
        btnRegister = findViewById (R.id.id__activity_login__btn_register);
    }

    @bardiademon
    @Override
    public void SetOnClick ()
    {
        btnLogin.setOnClickListener (view -> onClickLogin ());
    }

    private void onClickLogin ()
    {
        String username = txtUsername.getText ().toString ();
        String password = txtPassword.getText ().toString ();
        if (username.isEmpty () || password.isEmpty ())
            Toast.show (Icon.ICON_ERROR , GetValues.getString ("toast__please_enter_info") , Toast.LENGTH_LONG);
        else
        {
            Wait.Set ();
            Login.SetLogin (this::afterSetLogin , username , password);
        }
    }

    private void afterSetLogin (boolean validInfo)
    {
        Wait.CloseWait ();
        if (validInfo) new ActiveSwitching (ActivityMain.class);
        else Toast.show (Icon.ICON_ERROR , GetValues.getString ("info_invalid"));
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
        G.setActivityClass (ActivityLogin.class);
        G.setViewForActivity ();
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
        title.setText ("text");
        title.setCircleSmallImageView (true);
        title.setViewSmallPhoto (true , () ->
        {

        } , R.id.image , 0);
    }
}
