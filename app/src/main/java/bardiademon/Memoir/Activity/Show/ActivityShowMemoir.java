package bardiademon.Memoir.Activity.Show;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bardiademon.Memoir.Adapter.AdapterLstShowCommentMemoir;
import bardiademon.Memoir.Server.Found.FoundComment;
import bardiademon.Memoir.Server.Found.FoundMemoir;
import bardiademon.Memoir.Fragment.FragmentComment;
import bardiademon.Memoir.Other.GetResPicShowMemoir;
import bardiademon.Memoir.R;
import bardiademon.Memoir.Server.Get.GetComment;
import bardiademon.Memoir.Server.GetMemoir;
import bardiademon.Memoir.Server.Update.UpdateLVC;
import bardiademon.Memoir.bardiademon.Class.BeforeRunActivity.G;
import bardiademon.Memoir.bardiademon.Class.Other.ActiveSwitching;
import bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues;
import bardiademon.Memoir.bardiademon.Class.View.Icon;
import bardiademon.Memoir.bardiademon.Class.View.ShowMessage;
import bardiademon.Memoir.bardiademon.Class.View.Toast;
import bardiademon.Memoir.bardiademon.Class.View.Wait;
import bardiademon.Memoir.bardiademon.Interface.bardiademon;
import bardiademon.Memoir.bardiademon.Interface.Activity;

@bardiademon
public class ActivityShowMemoir extends AppCompatActivity implements Activity
{
    private TextView txtName, txtSub, txtVisit, txtVisitUser, txtLike;
    private static TextView txtComment;
    private ImageView comment;
    private ImageButton like;
    private ImageView image;

    private TextView txtShowText;

    private static String linkMemoir;

    private GetMemoir getMemoir;

    public static final String NAME_INTENT__LINK = "link_memoir";

    private boolean likeMemoir;

    private GetComment getComment;

    private static boolean IsMemoirForMe;

    private static AdapterLstShowCommentMemoir Adapter;

    private static List <FoundComment> FoundComments;

    @bardiademon
    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_show_memoir);
        get_intent ();
    }

    @bardiademon
    @Override
    public void RunClass ()
    {
        View ();
        SetTools ();
        SetOnClick ();
        getMemoir ();
    }

    @bardiademon
    private void get_intent ()
    {
        Bundle bundle = getIntent ().getExtras ();
        if (bundle != null && (linkMemoir = bundle.getString (NAME_INTENT__LINK)) != null)
            RunClass ();
        else
            ((TextView) (findViewById (R.id.id__activity_show_memoir__txt_text))).setText (GetValues.getString ("str_not_send_link_memoir"));
    }

    @bardiademon
    @Override
    public void SetTools ()
    {
        image = findViewById (R.id.id__activity_show_memoir__image);
        txtName = findViewById (R.id.id__activity_show_memoir__txt_name);
        txtSub = findViewById (R.id.id__activity_show_memoir__txt_sub);
        txtShowText = findViewById (R.id.id__activity_show_memoir__txt_text);

        txtVisit = findViewById (R.id.id__activity_show_memoir__txt_visit);
        txtVisitUser = findViewById (R.id.id__activity_show_memoir__txt_visit_user);
        txtLike = findViewById (R.id.id__activity_show_memoir__txt_like);
        txtComment = findViewById (R.id.id__activity_show_memoir__txt_comment);

        like = findViewById (R.id.id__activity_show_memoir__like);
        comment = findViewById (R.id.id__activity_show_memoir__comment);
    }

    @bardiademon
    @Override
    public void SetOnClick ()
    {
        like.setOnClickListener (v -> like (!likeMemoir , false));
        comment.setOnClickListener (v -> getComment ());
    }

    @bardiademon
    private void getComment ()
    {
        getComment = new GetComment (this::afterGetComment , linkMemoir);
    }

    @bardiademon
    private void afterGetComment ()
    {
        if (!getComment.AnswerServerOrResult ())
        {
            Toast.ToastReady.NOT_FOUND ();
            FoundComments = new ArrayList <> ();
        }
        else FoundComments = getComment.getFoundComments ();
        Adapter = new AdapterLstShowCommentMemoir (FoundComments , getComment.getInfoUserFFC ());
        FragmentComment fragmentComment = new FragmentComment ();
        fragmentComment.show (getSupportFragmentManager () , "");
    }

    @bardiademon
    @Override
    public void View ()
    {
        GSet ();
        Title ();
        SetAbout ();
    }

    @bardiademon
    @Override
    public void SetAbout ()
    {
//        new About ();
    }

    @bardiademon
    @Override
    public void GSet ()
    {
        G.setActivity (ActivityShowMemoir.this);
        G.setActivityClass (ActivityShowMemoir.class);
        G.setViewForActivity ();
    }

    @bardiademon
    @Override
    public void Title ()
    {
    }

    @bardiademon
    private void getMemoir ()
    {
        txtShowText.setText ("لطفا صبر کنید, در حال دریافت....");
        Wait.Set ();
        getMemoir = new GetMemoir (() ->
        {
            if (getMemoir.AnswerServerOrResult ()) setValue (getMemoir.getFoundMemoir ());
            else
            {
                ShowMessage.show (GetValues.getString ("str_msg__msg_from_server") , getMemoir.getMessageServer () , Icon.ICON_ERROR);
                txtShowText.setText (getMemoir.getMessageServer ());
                txtName.setText (GetValues.getString ("error"));
            }
            Wait.CloseWait ();
        } , linkMemoir);
    }

    @bardiademon
    private void setValue (FoundMemoir memoir)
    {
        image.setImageResource (new GetResPicShowMemoir ().get (memoir.Subject));
        txtVisit.setText (memoir.NumberOfVisit);
        txtVisitUser.setText (memoir.NumberOfVisitUser);
        txtLike.setText (memoir.NumberOfLike);
        txtComment.setText (memoir.NumberOfComment);
        txtName.setText (memoir.Name);
        txtSub.setText (memoir.Subject);
        txtShowText.setText (memoir.Text);
        like (memoir.Liked , true);
        if (!memoir.IsMemoirForMe) visit ();
        ActivityShowMemoir.IsMemoirForMe = memoir.IsMemoirForMe;
    }

    @bardiademon
    private void like (boolean like , boolean first)
    {
        likeMemoir = like;
        int res;
        if (like) res = R.drawable.view_like_red;
        else res = R.drawable.view_like;
        this.like.setImageResource (res);
        if (!first)
        {
            int valueTxtLike = Integer.parseInt (txtLike.getText ().toString ());
            if (like) valueTxtLike++;
            else valueTxtLike--;
            txtLike.setText (String.valueOf (valueTxtLike));
            updateLike ();
        }
    }

    @bardiademon
    private void visit ()
    {
        new UpdateLVC (linkMemoir , UpdateLVC.UV);
    }

    @bardiademon
    private void updateLike ()
    {
        new UpdateLVC (linkMemoir , UpdateLVC.UL);
    }

    @bardiademon
    @Override
    public void onBackPressed ()
    {
        new ActiveSwitching (G.getActivityClassForBack ());
    }

    @bardiademon
    public static boolean IsMemoirForMe ()
    {
        return IsMemoirForMe;
    }

    @bardiademon
    public static AdapterLstShowCommentMemoir GetAdapter ()
    {
        return Adapter;
    }

    @bardiademon
    public static void RemoveFromFoundComment (int IndexRemove)
    {
        if (IndexRemove < 0 || IndexRemove >= FoundComments.size ()) return;
        FoundComments.remove (IndexRemove);
        Adapter.notifyDataSetChanged ();
        int numberOfComment = Integer.parseInt (txtComment.getText ().toString ());
        if (numberOfComment > 0) txtComment.setText (String.valueOf (--numberOfComment));
    }

    @bardiademon
    public static String GetLinkMemoir ()
    {
        return linkMemoir;
    }

    public static List <FoundComment> GetFoundComments ()
    {
        return FoundComments;
    }

    @Override
    protected void onDestroy ()
    {
        super.onDestroy ();
        Null ();
    }

    private void Null ()
    {
        FoundComments = null;
        Adapter = null;
        txtComment = null;
    }
}
