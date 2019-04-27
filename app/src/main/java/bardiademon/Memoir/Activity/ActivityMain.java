package bardiademon.Memoir.Activity;

import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;

import java.util.Locale;

import bardiademon.Memoir.Activity.Add.ActivityAddNewMemoir;
import bardiademon.Memoir.Activity.Get.ActivityGetMemoirUser;
import bardiademon.Memoir.R;
import bardiademon.Memoir.bardiademon.Class.About.About;
import bardiademon.Memoir.bardiademon.Class.BeforeRunActivity.G;
import bardiademon.Memoir.bardiademon.Class.Other.ActiveSwitching;
import bardiademon.Memoir.bardiademon.Class.View.Icon;
import bardiademon.Memoir.bardiademon.Class.View.Title;
import bardiademon.Memoir.bardiademon.Interface.Activity;

public class ActivityMain extends AppCompatActivity implements Activity
{

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        RunClass ();
    }

    @Override
    public void RunClass ()
    {
        changeToRTL ();
        View ();
        SetTools ();
        SetOnClick ();
    }

    private void changeToRTL ()
    {
        Locale locale = new Locale ("en");
        Locale.setDefault (locale);
        Configuration config = new Configuration ();
        config.locale = locale;
        getApplicationContext ().getResources ().updateConfiguration (config, getApplicationContext ().getResources ().getDisplayMetrics ());
    }

    @Override
    public void SetTools ()
    {
        drawerLayout = findViewById (R.id.id__activity_home__drawer_layout);
        navigationView = findViewById (R.id.id__activity_home__navigation_view);
    }

    @Override
    public void SetOnClick ()
    {
        navigationView.setNavigationItemSelectedListener (menuItem ->
        {
            int idItem = menuItem.getItemId ();
            switch (idItem)
            {
                case R.id.id__activity_home_nav__add_new_memoir:
                    onClickMIAddNewMemoir ();
                    break;
                case R.id.id__activity_home_nav__list_memoir:
                    onClickMIListYourMemoir ();
                    break;
                default:
                    return false;
            }
            return true;
        });
    }

    // MI => Menu Item
    private void onClickMIAddNewMemoir ()
    {
        new ActiveSwitching (ActivityAddNewMemoir.class);
    }

    private void onClickMIListYourMemoir ()
    {
        new ActiveSwitching (ActivityGetMemoirUser.class);
    }

    @Override
    public void View ()
    {
        GSet ();
        SetAbout ();
        Title ();
    }

    @Override
    public void GSet ()
    {
        G.setActivity (this);
        G.setActivityClass (ActivityMain.class);
        G.setActivityHome (G.getActivity ());
        G.setActivityHomeClass (G.getActivityClass ());
        G.setViewForActivity ();
        G.setActivityClassForBack (G.getActivityClass ());
    }

    @Override
    public void SetAbout ()
    {
        new About ();
    }

    @Override
    public void Title ()
    {
        Title title = new Title ();
        title.setText (Title.FOUND_AUTO_TITLE_ACTIVITY);
        title.setGravity (Gravity.END);
        title.setViewSmallPhoto (true, () -> drawerLayout.openDrawer (Gravity.START), Icon.MENU_NAV_THREE_LINE, 1);
        title.apply ();
    }
}
