package bardiademon.Memoir.Activity.Add;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.List;

import bardiademon.Memoir.R;
import bardiademon.Memoir.Server.Get.GetSubject;
import bardiademon.Memoir.Server.Save.RecordNewMemoir;
import bardiademon.Memoir.bardiademon.Class.BeforeRunActivity.G;
import bardiademon.Memoir.bardiademon.Class.Other.ActiveSwitching;
import bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues;
import bardiademon.Memoir.bardiademon.Class.Other.Question;
import bardiademon.Memoir.bardiademon.Class.Other.String.CheckString;
import bardiademon.Memoir.bardiademon.Class.View.Icon;
import bardiademon.Memoir.bardiademon.Class.View.ShowMessage;
import bardiademon.Memoir.bardiademon.Class.View.Title;
import bardiademon.Memoir.bardiademon.Class.View.Wait;
import bardiademon.Memoir.bardiademon.Interface.Activity;
import bardiademon.Memoir.bardiademon.Interface.bardiademon;

@bardiademon
public class ActivityAddNewMemoir extends AppCompatActivity implements Activity
{

    private EditText txtName, txtLink, txtDate, txtText;
    private Button btnRecord, btnCancel;
    private CheckBox checkOpen;

    private MaterialSpinner spinnerSubject;

    private String name, link, date, text;
    private boolean open;

    private List <String> subject;

    private RecordNewMemoir recordNewMemoir;

    private GetSubject getSubject;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_add_new_memoir);
        RunClass ();
    }

    @bardiademon
    @Override
    public void RunClass ()
    {
        View ();
        SetTools ();
        getSubject ();
    }

    @bardiademon
    @Override
    public void View ()
    {
        GSet ();
        SetAbout ();
        Title ();
    }

    @bardiademon
    @Override
    public void GSet ()
    {
        G.setActivity (this);
        G.setActivityClass (ActivityAddNewMemoir.class);
        G.setViewForActivity ();
    }

    @bardiademon
    @Override
    public void SetAbout ()
    {
    }

    @bardiademon
    @Override
    public void Title ()
    {
        Title.TitleReady.ACTION_BAR ();
    }

    @bardiademon
    @Override
    public void SetOnClick ()
    {
        btnRecord.setOnClickListener (v -> onClickRecord ());
        btnCancel.setOnClickListener (v -> onBackPressed ());
    }

    @bardiademon
    private void onClickRecord ()
    {
        if (checkValue ()) Question.QuestionReady.RECS.ADD (this::recordInfo);
    }

    @bardiademon
    private boolean checkValue ()
    {
        if ((name = txtName.getText ().toString ()).equals ("") || (text = txtText.getText ().toString ()).equals (""))
            showErrorMessage ("toast__please_enter_info");
        else
        {
            link = txtLink.getText ().toString ();
            if (checkLink ())
            {
                date = txtDate.getText ().toString ();
                if (checkDate ())
                {
                    open = checkOpen.isChecked ();
                    return true;
                }
                else showErrorMessage ("toast_error__activity_add_new_memoir__err_date");
            }
            else showErrorMessage ("toast_error__activity_add_new_memoir__err_link");
        }
        return false;
    }

    @bardiademon
    private void showErrorMessage (String message)
    {
        ShowMessage.show (GetValues.getString ("error") , GetValues.getString (message) , Icon.ICON_ERROR);
    }

    @bardiademon
    private boolean checkLink ()
    {
        return (CheckString.checkUsername (link));
    }

    @bardiademon
    private boolean checkDate ()
    {
        return (CheckString.CheckJustNumber (date));
    }

    @bardiademon
    private void recordInfo ()
    {
        recordNewMemoir = new RecordNewMemoir (this::afterRecord , name , subject.get (spinnerSubject.getSelectedIndex ()) , link , date , text , open);
    }

    @bardiademon
    private void afterRecord ()
    {
        if (recordNewMemoir.AnswerServerOrResult ())
        {
            int iconMessage;
            ShowMessage.AfterClick afterClick = null;
            if (recordNewMemoir.isMessageErrFromServer ()) iconMessage = Icon.ICON_ERROR;
            else
            {
                iconMessage = Icon.ICON_SUCCESSFUL;
                afterClick = this::onBackPressed;
            }
            ShowMessage.show (GetValues.getString ("str_msg__msg_from_server") , recordNewMemoir.messageServer () , iconMessage , afterClick);
        }
        else
        {
            if (recordNewMemoir.isMessageErrFromServer ())
                ShowMessage.show (GetValues.getString ("str_msg__msg_from_server") , recordNewMemoir.messageServer () , Icon.ICON_ERROR);
            else
                ShowMessage.show (GetValues.getString ("error") , GetValues.getString ("str_err_msg") , Icon.ICON_ERROR);
        }
    }

    @bardiademon
    @Override
    public void SetTools ()
    {
        txtName = findViewById (R.id.id__activity_add_new_memoir__txt_name);
        txtLink = findViewById (R.id.id__activity_add_new_memoir__txt_link);
        txtDate = findViewById (R.id.id__activity_add_new_memoir__txt_date);
        txtText = findViewById (R.id.id__activity_add_new_memoir__txt_text);

        spinnerSubject = findViewById (R.id.id__activity_add_new_memoir__spinner_sub);


        btnRecord = findViewById (R.id.id__activity_add_new_memoir__btn_record);
        btnCancel = findViewById (R.id.id__activity_add_new_memoir__btn_cancel);

        checkOpen = findViewById (R.id.id__activity_add_new__memoir__chk_open);
    }

    private void getSubject ()
    {
        Wait.Set ();
        getSubject = new GetSubject (() ->
        {
            Wait.CloseWait ();
            if (getSubject.AnswerServerOrResult ())
            {
                subject = getSubject.getSubject ();
                spinnerSubject.setItems (subject);
                SetOnClick ();
            }
            else
                ShowMessage.show (GetValues.getString ("error") , GetValues.getString ("error") , Icon.ICON_ERROR , () -> new ActiveSwitching (G.getActivityClassForBack ()));
        });
    }

    @bardiademon
    @Override
    public void onBackPressed ()
    {
        Question.QuestionReady.Exit.EXIT_ACTIVITY (G.getActivityClassForBack ());
    }
}
