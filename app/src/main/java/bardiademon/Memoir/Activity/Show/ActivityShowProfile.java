package bardiademon.Memoir.Activity.Show;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import bardiademon.Memoir.R;

public class ActivityShowProfile extends AppCompatActivity
{

    public static final String KI_IDUSER = "id_user";

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_show_profile);
    }
}
