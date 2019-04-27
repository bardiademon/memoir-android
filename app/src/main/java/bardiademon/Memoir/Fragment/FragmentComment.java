package bardiademon.Memoir.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;

import org.jetbrains.annotations.NotNull;

import bardiademon.Memoir.R;
import bardiademon.Memoir.bardiademon.Class.BeforeRunActivity.G;

public class FragmentComment extends BottomSheetDialogFragment
{

    public FragmentComment ()
    {

    }

    @NotNull
    @SuppressLint ("InflateParams")
    @Override
    public Dialog onCreateDialog (Bundle savedInstanceState)
    {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog (savedInstanceState);
        View view = G.getActivity ().getLayoutInflater ().inflate (R.layout.layout_fragment_comment , null);
        dialog.setContentView (view);
        return dialog;
    }
}
