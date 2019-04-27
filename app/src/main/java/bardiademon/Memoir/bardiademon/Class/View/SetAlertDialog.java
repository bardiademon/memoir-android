package bardiademon.Memoir.bardiademon.Class.View;

import android.support.v7.app.AlertDialog;
import android.view.Window;
import android.view.WindowManager;

public abstract class SetAlertDialog
{
    public static void setSizeAlertDialog (AlertDialog alertDialog)
    {
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = alertDialog.getWindow();
        assert window != null;
        lp.copyFrom(window.getAttributes());
//This makes the dialog take up the full width
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
    }
}
