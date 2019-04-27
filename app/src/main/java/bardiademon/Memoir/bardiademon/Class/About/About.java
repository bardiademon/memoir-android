package bardiademon.Memoir.bardiademon.Class.About;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import bardiademon.Memoir.R;
import bardiademon.Memoir.bardiademon.Class.BeforeRunActivity.G;
import bardiademon.Memoir.bardiademon.Class.Other.Check.IsAppAvailable;
import bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues;
import bardiademon.Memoir.bardiademon.Class.Other.Math.Math;
import bardiademon.Memoir.bardiademon.Class.View.Icon;
import bardiademon.Memoir.bardiademon.Class.View.Toast;
import bardiademon.Memoir.bardiademon.Interface.Dialog;
import bardiademon.Memoir.bardiademon.Interface.bardiademon;

import static bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues.getString;

@bardiademon
public class About implements Dialog
{

    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    private LinearLayout linearLayout;
    private Map<String, ImageView> btnOpenLink;
    private View view;
    private boolean show;

    public About ()
    {
        this(false);
    }

    @bardiademon
    public About (boolean show)
    {
        this.show = show;
        RunClass();
    }

    @bardiademon
    @Override
    public void RunClass ()
    {
        SetLayout();
        SetTools();
        SetOnClick();
        if (show)Show();
        else
            setLayoutBardiademonToApplicantLayout();
    }

    @bardiademon
    @Override
    @SuppressLint("InflateParams")
    public void SetLayout ()
    {
        builder = new AlertDialog.Builder(G.getActivity());
        view = GetValues.getView("layout_show_info_bardiademon");
        builder.setView(view);
        alertDialog = builder.create();
    }

    @bardiademon
    @Override
    public void SetTools ()
    {
        if (!show) linearLayout = G.getView().findViewById(R.id.main_layout);
        btnOpenLink = new LinkedHashMap<>();

        btnOpenLink.put("btn_email" , (view.findViewById(R.id.id__layout_show_info_bardiademon__open_link__email)));
        btnOpenLink.put("btn_telegram" , (view.findViewById(R.id.id__layout_show_info_bardiademon__open_link__telegram)));
        btnOpenLink.put("btn_instagram" , (view.findViewById(R.id.id__layout_show_info_bardiademon__open_link__instagram)));

    }

    @bardiademon
    @Override
    public void SetOnClick ()
    {
        (Objects.requireNonNull(btnOpenLink.get("btn_email"))).setOnClickListener(v -> sendMail());
        (Objects.requireNonNull(btnOpenLink.get("btn_telegram"))).

                setOnClickListener(v -> toInstagramOrTelegram("telegram"));
        (Objects.requireNonNull(btnOpenLink.get("btn_instagram"))).
                setOnClickListener(v -> toInstagramOrTelegram("instagram"));
    }


    @bardiademon
    private void toInstagramOrTelegram (String name)
    {
        String packageName, address;
        switch (name)
        {
            case "telegram":
                packageName = getString("package_name__telegram");
                address = "address_telegram";
                break;
            case "instagram":
                packageName = getString("package_name__instagram");
                address = "address_instagram";
                break;
            default:
                return;
        }
        if (isAppAvailable(packageName))
        {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.parse(getString(address , "bardiademon"));
            intent.setData(uri);
            intent.setPackage(packageName);
            G.getActivity().startActivity(intent);
        }
    }

    @bardiademon
    private void sendMail ()
    {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL , new String[]{getString("bardiademon__email")});
        intent.putExtra(Intent.EXTRA_SUBJECT , G.getContext().getPackageName());

        try
        {
            G.getActivity().startActivity(Intent.createChooser(intent , "GetUrl mail..."));
        }
        catch (android.content.ActivityNotFoundException ex)
        {
            Toast.ToastReady.ERROR();
        }
    }

    @bardiademon
    private boolean isAppAvailable (String packageName)
    {
        if (IsAppAvailable.check(packageName))
            return true;
        else
        {
            Toast.show(Icon.ICON_ERROR , getString("package_not_installed"));
            return false;
        }
    }

    @bardiademon
    private void setLayoutBardiademonToApplicantLayout ()
    {
        int padding = Math.convertDPPX(5);

        LinearLayout linearLayout = new LinearLayout(G.getActivity());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0 , 0 , 0 , Math.convertDPPX(10));
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setPadding(padding , padding , padding , padding);
        linearLayout.setBackgroundColor(GetValues.getColor("background_color__layout_bardiademon"));
        linearLayout.setGravity(Gravity.CENTER);

        TextView txtShowName = new TextView(G.getActivity());
        txtShowName.setTextColor(GetValues.getColor("color_txt__layout_bardiademon"));
        txtShowName.setTypeface(Typeface.createFromAsset(G.getAssetsManager() , "iranian_sans.ttf") , Typeface.BOLD);
        txtShowName.setTextSize(20);
        txtShowName.setText(getString("bardiademon"));

        linearLayout.addView(txtShowName);
        this.linearLayout.addView(linearLayout , 0);

        setOnClick(linearLayout);
    }

    @bardiademon
    private void setOnClick (LinearLayout linearLayout)
    {
        linearLayout.setOnClickListener(v -> Show());
    }

    @bardiademon
    @Override
    public void Show ()
    {
        if (CheckBuilder() && !alertDialog.isShowing())
            alertDialog.show();
    }

    @bardiademon
    @Override
    public boolean CheckBuilder ()
    {
        return (builder != null && alertDialog != null);
    }

    @bardiademon
    @Override
    public void Cancel ()
    {
    }

    @bardiademon
    @Override
    public void View ()
    {
    }

    @bardiademon
    @Override
    public void SetAbout ()
    {
    }

    @bardiademon
    @Override
    public void GSet ()
    {
    }

    @Override
    public void Title ()
    {
    }
}
