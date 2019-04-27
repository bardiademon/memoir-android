package bardiademon.Memoir.Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import bardiademon.Memoir.Activity.Show.ActivityShowMemoir;
import bardiademon.Memoir.R;
import bardiademon.Memoir.Server.Delete.DeleteMemoirUser;
import bardiademon.Memoir.Server.Found.FoundMemoirUser;
import bardiademon.Memoir.bardiademon.Class.BeforeRunActivity.G;
import bardiademon.Memoir.bardiademon.Class.Other.ActiveSwitching;
import bardiademon.Memoir.bardiademon.Class.Other.Question;
import bardiademon.Memoir.bardiademon.Class.View.Icon;
import bardiademon.Memoir.bardiademon.Class.View.Toast;
import bardiademon.Memoir.bardiademon.Class.View.Wait;
import bardiademon.Memoir.bardiademon.Interface.bardiademon;

@bardiademon
public class AdapterLstShowMemoirUser extends ArrayAdapter <FoundMemoirUser>
{
    private static final int RES = R.layout.layout__lst_show_memoir_user;

    private TextView txtName, txtLink;
    private ImageView imgOpen, imgConfirmation;
    private Button btnShow, btnChange, btnDelete;

    private DeleteMemoirUser deleteMemoirUser;

    private ArrayList <FoundMemoirUser> foundMemoirUsers;

    @bardiademon
    public AdapterLstShowMemoirUser (ArrayList <FoundMemoirUser> foundMemoirUsers)
    {
        super (G.getActivity () , RES , foundMemoirUsers);
        this.foundMemoirUsers = foundMemoirUsers;
    }

    @bardiademon
    @SuppressLint ("ViewHolder")
    @NonNull
    @Override
    public View getView (int position , @Nullable View view , @NonNull ViewGroup parent)
    {
        view = G.getActivity ().getLayoutInflater ().inflate (RES , null);
        setTools (view);
        FoundMemoirUser foundMemoirUser = foundMemoirUsers.get (position);
        setOnClick (foundMemoirUser.Id , position , foundMemoirUser.Link);
        setValue (foundMemoirUser);
        return view;
    }

    @bardiademon
    private void setTools (View view)
    {
        txtName = view.findViewById (R.id.layout__lst_show_memoir_user__txt_show_name);
//        txtSubject = view.findViewById (R.id.layout__lst_show_memoir_user__txt_show_subject);
        txtLink = view.findViewById (R.id.layout__lst_show_memoir_user__txt_show_link);
        imgOpen = view.findViewById (R.id.layout__lst_show_memoir_user__img_open);
        imgConfirmation = view.findViewById (R.id.layout__lst_show_memoir_user__img_confirmation);
        btnShow = view.findViewById (R.id.layout__lst_show_memoir_user__btn_show);
        btnChange = view.findViewById (R.id.layout__lst_show_memoir_user__btn_change);
        btnDelete = view.findViewById (R.id.layout__lst_show_memoir_user__btn_delete);
    }

    @bardiademon
    private void setOnClick (final int id , final int index , final String link)
    {
        btnShow.setOnClickListener (v ->
        {
            Intent intent = new Intent ();
            intent.putExtra (ActivityShowMemoir.NAME_INTENT__LINK , link);
            new ActiveSwitching (ActivityShowMemoir.class , intent);
        });
        btnChange.setOnClickListener (v ->
        {

        });
        btnDelete.setOnClickListener (v -> onClickBtnDelete (id , index));
    }

    private void onClickBtnDelete (final int id , final int index)
    {
        Question.QuestionReady.RECS.REMOVE (() ->
        {
            Wait.Set ();
            deleteMemoirUser = new DeleteMemoirUser (() -> afterDelete (index) , id);
        });
    }

    private void afterDelete (int index)
    {
        Wait.CloseWait ();
        if (deleteMemoirUser.AnswerServerOrResult ())
        {
            foundMemoirUsers.remove (index);
            notifyDataSetChanged ();
            Toast.ToastReady.DELETED ();
        }
        else Toast.ToastReady.ERROR ();
        System.gc ();
    }

    @bardiademon
    private void setValue (FoundMemoirUser foundMemoirUser)
    {
        txtName.setText (foundMemoirUser.Name);
//        txtSubject.setText (foundMemoirUser.Subject);
        txtLink.setText (foundMemoirUser.Link);

        imgOpen.setImageResource (getPic (foundMemoirUser.Open));
        imgConfirmation.setImageResource (getPic (foundMemoirUser.Confirmation));
    }

    @bardiademon
    private int getPic (boolean b)
    {
        if (b) return Icon.ACCEPTED;
        else return Icon.NOT_ACCEPTED;
    }

}
