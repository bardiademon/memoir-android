package bardiademon.Memoir.bardiademon.Class.Other;

// Created by bardiademon on 07/03/2018_14:46.

import android.content.Intent;

import bardiademon.Memoir.bardiademon.Class.BeforeRunActivity.G;
import bardiademon.Memoir.bardiademon.Interface.bardiademon;

@bardiademon
public class ActiveSwitching
{

    private Class where;
    private Intent intent;

    @bardiademon
    public ActiveSwitching (Class where)
    {
        this(where, null);
    }

    @bardiademon
    public ActiveSwitching (Class where, Intent intent)
    {
        this.where = where;
        this.intent = intent;
        Switch();
    }

    @bardiademon
    private void Switch ()
    {
        if (intent == null)
            intent = new Intent();

        intent.setClass(G.getActivity(), where);

        G.getActivity().startActivity(intent);

        G.getActivity().finish();
    }

    @bardiademon
    public static void restartActivity ()
    {
        if (G.getActivityClassRestart() != null)
            new ActiveSwitching(G.getActivityClassRestart(), G.getIntent());
    }
}
