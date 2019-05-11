package bardiademon.Memoir.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import org.jetbrains.annotations.NotNull;

import bardiademon.Memoir.Activity.Show.ActivityShowMemoir;
import bardiademon.Memoir.Dialog.DialogOptionComment;
import bardiademon.Memoir.R;
import bardiademon.Memoir.Server.Found.FoundComment;
import bardiademon.Memoir.Server.Save.SendComment;
import bardiademon.Memoir.bardiademon.Class.BeforeRunActivity.G;
import bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues;
import bardiademon.Memoir.bardiademon.Class.View.Icon;
import bardiademon.Memoir.bardiademon.Class.View.Toast;
import bardiademon.Memoir.bardiademon.Interface.bardiademon;

@bardiademon
public class FragmentComment extends BottomSheetDialogFragment
{

    private ListView lstShowComment;
    private ImageView sendComment;
    private EditText txtGetComment;

    private SendComment _SendComment;

    @bardiademon
    public FragmentComment ()
    {

    }

    @bardiademon
    @NotNull
    @SuppressLint ("InflateParams")
    @Override
    public Dialog onCreateDialog (Bundle savedInstanceState)
    {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog (savedInstanceState);
        View view = G.getActivity ().getLayoutInflater ().inflate (R.layout.layout_fragment_comment , null);
        dialog.setContentView (view);

        setTools (view);
        setLst ();
        setOnClick ();

        return dialog;
    }

    @bardiademon
    private void setTools (View view)
    {
        lstShowComment = view.findViewById (R.id.id__layout_fragment__lst_show_comment);
        sendComment = view.findViewById (R.id.id__layout_fragment_comment__send_comment);
        txtGetComment = view.findViewById (R.id.id__layout_fragment__txt_snd_cmnt);
    }

    @bardiademon
    private void setLst ()
    {
        lstShowComment.setAdapter (ActivityShowMemoir.GetAdapter ());
    }

    private void setOnClick ()
    {
        txtGetComment.addTextChangedListener (new TextWatcher ()
        {
            @Override
            public void beforeTextChanged (CharSequence s , int start , int count , int after)
            {

            }

            @Override
            public void onTextChanged (CharSequence s , int start , int before , int count)
            {

            }

            @Override
            public void afterTextChanged (Editable s)
            {
                sendComment.setEnabled ((!txtGetComment.getText ().toString ().isEmpty ()));
            }
        });
        sendComment.setOnClickListener (v -> setonClickBtnSend ());
        lstShowComment.setOnItemClickListener ((parent , view , position , id) ->
        {
            FoundComment foundComment = ActivityShowMemoir.GetFoundComments ().get (position);
            new DialogOptionComment
                    (position , ActivityShowMemoir.GetLinkMemoir () , foundComment.IdComment , foundComment.IdUser , foundComment.TextComment);
        });
    }

    private void setonClickBtnSend ()
    {
        String txtComment = txtGetComment.getText ().toString ();
        if (!txtComment.isEmpty ())
        {
            Toast.show (Icon.ICON_SUCCESSFUL , GetValues.getString ("str__sending"));
            _SendComment = new SendComment (() ->
            {
                if (_SendComment.AnswerServerOrResult ())
                    Toast.show (Icon.ICON_SUCCESSFUL , GetValues.getString ("str__recorded_comment"));
                else Toast.ToastReady.ERROR ();
            } , ActivityShowMemoir.GetLinkMemoir () , txtComment);
        }
    }

}
