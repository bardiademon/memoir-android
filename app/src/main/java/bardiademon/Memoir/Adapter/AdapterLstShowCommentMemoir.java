package bardiademon.Memoir.Adapter;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import bardiademon.Memoir.R;
import bardiademon.Memoir.Server.Found.FoundComment;
import bardiademon.Memoir.Server.Found.InfoUserFFC;
import bardiademon.Memoir.bardiademon.Class.BeforeRunActivity.G;
import bardiademon.Memoir.bardiademon.Class.Other.Time.TimeEasy;
import bardiademon.Memoir.bardiademon.Interface.bardiademon;
import de.hdodenhof.circleimageview.CircleImageView;

@bardiademon
public class AdapterLstShowCommentMemoir extends ArrayAdapter <FoundComment>
{

    private static final int RES = R.layout.layout_show_comment;

    private List <FoundComment> foundComments;
    private InfoUserFFC infoUserFFC;

    private TextView txtComment, txtTime, txtUsername;
    private CircleImageView picProfile;

    @bardiademon
    public AdapterLstShowCommentMemoir (List <FoundComment> FoundComments , InfoUserFFC InfoUserFFC)
    {
        super (G.getActivity () , RES , FoundComments);
        this.foundComments = FoundComments;
        this.infoUserFFC = InfoUserFFC;
    }

    @bardiademon
    @SuppressLint ("ViewHolder")
    @NonNull
    @Override
    public View getView (int position , @Nullable View convertView , @NonNull ViewGroup parent)
    {
        View view = G.getActivity ().getLayoutInflater ().inflate (RES , null);
        txtComment = view.findViewById (R.id.id__layout_show_comment__txt_comment);
        txtTime = view.findViewById (R.id.id__layout_show_comment__txt_time);
        txtUsername = view.findViewById (R.id.id__layout_show_comment__txt_name);
        picProfile = view.findViewById (R.id.id__layout_show_comment__img_profile);
        setValue (foundComments.get (position));
        return view;
    }

    @bardiademon
    private void setValue (FoundComment foundComment)
    {
        txtComment.setText (foundComment.TextComment);
        txtTime.setText (new TimeEasy (foundComment.Time).getTimeEasy ());
        txtUsername.setText (infoUserFFC.getUsername (foundComment.IdUser));
        Glide.with (G.getActivity ()).load (infoUserFFC.getUsername (foundComment.IdUser))
                .error (Glide.with (G.getActivity ()).load (R.drawable.pic_profile))
                .into (picProfile);
    }
}

