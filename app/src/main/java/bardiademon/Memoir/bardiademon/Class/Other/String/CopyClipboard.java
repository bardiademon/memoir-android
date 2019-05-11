package bardiademon.Memoir.bardiademon.Class.Other.String;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import bardiademon.Memoir.bardiademon.Class.BeforeRunActivity.G;
import bardiademon.Memoir.bardiademon.Class.View.Toast;

public class CopyClipboard
{
    public static void Copy (String str)
    {
        ClipboardManager clipboard = (ClipboardManager) G.getActivity ().getSystemService (Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText ("new_clipboard" , str);
        clipboard.setPrimaryClip (clip);
        Toast.ToastReady.DONE ();
    }
}
