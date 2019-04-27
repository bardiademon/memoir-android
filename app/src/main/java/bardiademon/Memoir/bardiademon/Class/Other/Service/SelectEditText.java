package bardiademon.Memoir.bardiademon.Class.Other.Service;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import bardiademon.Memoir.bardiademon.Class.BeforeRunActivity.G;

public abstract class SelectEditText
{
    public static void select (EditText edt)
    {
        edt.requestFocus();
        edt.postDelayed(() ->
        {
            InputMethodManager inputMethodManager = (InputMethodManager) G.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            assert inputMethodManager != null;
            inputMethodManager.showSoftInput(edt, 0);
        }, 1);
    }
}
