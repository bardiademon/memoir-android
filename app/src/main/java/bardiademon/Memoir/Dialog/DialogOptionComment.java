package bardiademon.Memoir.Dialog;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import bardiademon.Memoir.Activity.Show.ActivityShowMemoir;
import bardiademon.Memoir.Activity.Show.ActivityShowProfile;
import bardiademon.Memoir.R;
import bardiademon.Memoir.Server.Check.CheckINeedItComment;
import bardiademon.Memoir.Server.Delete.DeleteComment;
import bardiademon.Memoir.Server.Report;
import bardiademon.Memoir.Server.Set.SetINeedItCmnt;
import bardiademon.Memoir.bardiademon.Class.BeforeRunActivity.G;
import bardiademon.Memoir.bardiademon.Class.Other.ActiveSwitching;
import bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues;
import bardiademon.Memoir.bardiademon.Class.Other.Question;
import bardiademon.Memoir.bardiademon.Class.Other.String.CopyClipboard;
import bardiademon.Memoir.bardiademon.Class.View.Icon;
import bardiademon.Memoir.bardiademon.Class.View.Toast;
import bardiademon.Memoir.bardiademon.Interface.Dialog;
import bardiademon.Memoir.bardiademon.Interface.bardiademon;

@bardiademon
public class DialogOptionComment implements Dialog
{

    //  ldsocc => Layout Dialog Show Option Click Comment

    private AlertDialog alertDialog;
    private AlertDialog.Builder builder;

    private View view;

    private Button btnShowProfile, btnINeedIt, btnCopyTxtComment, btnReport, btnDelete;

    private int idComment, idUser;
    private String linkMemoir, txtComment;

    private SetINeedItCmnt setINeedItCmnt;

    private CheckINeedItComment checkINeedItComment;
    private DeleteComment deleteComment;

    private int index;

    @bardiademon
    public DialogOptionComment (int Index , String LinkMemoir , int IdComment , int IdUser , String TxtComment)
    {
        this.index = Index;
        this.linkMemoir = LinkMemoir;
        this.idComment = IdComment;
        this.idUser = IdUser;
        this.txtComment = TxtComment;
        RunClass ();
    }

    @bardiademon
    @Override
    public void RunClass ()
    {
        SetLayout ();
        SetTools ();
        SetOnClick ();
        Show ();
    }

    @bardiademon
    @SuppressLint ("InflateParams")
    @Override
    public void SetLayout ()
    {
        view = G.getActivity ().getLayoutInflater ().inflate (R.layout.layout_dialog_show_option_click_comment , null);
        builder = new AlertDialog.Builder (G.getActivity ());
        builder.setView (view);
        builder.setCancelable (true);
        alertDialog = builder.create ();
    }

    @bardiademon
    @Override
    public void SetTools ()
    {
        btnShowProfile = view.findViewById (R.id.id__ldsocc__btn_show_profile);
        btnINeedIt = view.findViewById (R.id.id__ldsocc__btn_i_need_it);
        btnCopyTxtComment = view.findViewById (R.id.id__ldsocc__btn_cpy_txt_comment);
        btnReport = view.findViewById (R.id.id__ldsocc__btn_report);
        btnDelete = view.findViewById (R.id.id__ldsocc__btn_delete);
        if (!ActivityShowMemoir.IsMemoirForMe ()) btnDelete.setVisibility (View.INVISIBLE);
    }

    @bardiademon
    @Override
    public void SetOnClick ()
    {
        btnShowProfile.setOnClickListener (v ->
        {
            Intent intent = new Intent ();
            intent.putExtra (ActivityShowProfile.KI_IDUSER , idUser);
            new ActiveSwitching (ActivityShowProfile.class , intent);
        });

        btnCopyTxtComment.setOnClickListener (v -> CopyClipboard.Copy (txtComment));

        btnReport.setOnClickListener (v ->
        {
            try
            {
                JSONObject jsonDescription = new JSONObject ();
                jsonDescription.put ("link_memoir" , linkMemoir);
                jsonDescription.put ("id_comment" , idComment);
                new Report (txtComment , "comment" , jsonDescription.toString ());
            }
            catch (JSONException ignored)
            {
            }
        });

        checkINeedItComment = new CheckINeedItComment (() ->
        {
            setStringBtnINeedIt (checkINeedItComment.AnswerServerOrResult ());
            btnINeedIt.setOnClickListener (v -> onClickBtnINeedIt ());
        } , idComment);

        if (ActivityShowMemoir.IsMemoirForMe ())
            btnDelete.setOnClickListener (v -> Question.QuestionReady.RECS.REMOVE (this::onClickBtnDelete));
    }

    @bardiademon
    private void setStringBtnINeedIt (boolean checkINeedItComment)
    {
        String txtBtnINeedIt;
        if (checkINeedItComment) txtBtnINeedIt = GetValues.getString ("i_not_need_it");
        else txtBtnINeedIt = GetValues.getString ("i_need_it");
        btnINeedIt.setText (txtBtnINeedIt);
    }

    @bardiademon
    private void onClickBtnINeedIt ()
    {
        setINeedItCmnt = new SetINeedItCmnt (() ->
        {
            if (setINeedItCmnt.AnswerServerOrResult ())
            {
                Toast.show (Icon.ICON_SUCCESSFUL , setINeedItCmnt.getMsgServer ());
                setStringBtnINeedIt (setINeedItCmnt.isSet ());
            }
            else Toast.ToastReady.ERROR ();
        } , idComment);
    }

    @bardiademon
    private void onClickBtnDelete ()
    {
        deleteComment = new DeleteComment (() ->
        {
            if (deleteComment.AnswerServerOrResult ())
            {
                Toast.ToastReady.DELETED ();
                ActivityShowMemoir.RemoveFromFoundComment (index);
                Cancel ();
            }
            else Toast.ToastReady.ERROR ();
        } , linkMemoir , idComment);
    }

    @bardiademon
    @Override
    public void Show ()
    {
        if (CheckBuilder () && !alertDialog.isShowing ()) alertDialog.show ();
    }

    @bardiademon
    @Override
    public void Cancel ()
    {
        if (CheckBuilder () && alertDialog.isShowing ()) alertDialog.cancel ();
    }

    @bardiademon
    @Override
    public boolean CheckBuilder ()
    {
        return (alertDialog != null && builder != null);
    }

    @bardiademon
    @Override
    public void View ()
    {

    }

    @Override
    public void GSet ()
    {
    }

    @Override
    public void SetAbout ()
    {
    }

    @Override
    public void Title ()
    {
    }
}
