package bardiademon.Memoir.bardiademon.Class.View;

import android.graphics.Typeface;
import android.os.Build;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import bardiademon.Memoir.bardiademon.Class.BeforeRunActivity.G;
import bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues;
import bardiademon.Memoir.bardiademon.Class.Other.Math.Math;
import bardiademon.Memoir.bardiademon.Class.Other.String.GettingAPartOfAText;
import bardiademon.Memoir.bardiademon.Interface.Title;

public class Description
{
    private String title, description;
    private ViewGroup mainLayout;
    private int backgroundColor;
    private int textColorTitle;
    private int textColorMessage;

    private final static int BACKGROUND_COLOR = GetValues.getColor ("background_color__description");
    private final static int TEXT_COLOR_TITLE = GetValues.getColor ("text_color_title__description");
    private final static int TEXT_COLOR_MESSAGE = GetValues.getColor ("text_color_message__description");
    private boolean btnShowAll;

    public Description (String title , String description , ViewGroup layout)
    {
        setTitle (title);
        setDescription (description);
        setLayout (layout);
        setBackgroundColor (BACKGROUND_COLOR);
        setTextColorTitle (TEXT_COLOR_TITLE);
        setTextColorMessage (TEXT_COLOR_MESSAGE);
    }


    private String[][] titleDescription;
    private ViewGroup[] mainLayoutGroup;

    public Description (String[][] titleDescription , ViewGroup[] layout)
    {
        this.titleDescription = titleDescription;
        mainLayoutGroup = layout;
        setGroup ();
    }

    private void setGroup ()
    {
        String title, description;
        for (int i = 0, len = mainLayoutGroup.length; i < len; i++)
        {
            title = titleDescription[i][0];
            description = titleDescription[i][1];
            mainLayout = mainLayoutGroup[i];
            if ((title != null && description != null) && (!title.equals ("") && !description.equals ("")) && mainLayout != null)
                new Description (title , description , mainLayout).set ();
        }
    }

    public Description (String title , String description)
    {
        this (title , description , null);
    }

    public Description (String description)
    {
        this ("" , description , null);
    }

    public Description ()
    {
        this ("" , "" , null);
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public void setLayout (ViewGroup layout)
    {
        this.mainLayout = layout;
    }

    public void setBackgroundColor (int backgroundColor)
    {
        this.backgroundColor = backgroundColor;
    }

    public void setTextColorTitle (int textColorTitle)
    {
        this.textColorTitle = textColorTitle;
    }

    public void setTextColorMessage (int textColorMessage)
    {
        this.textColorMessage = textColorMessage;
    }

    public void set ()
    {
        if (!description.equals ("") || mainLayout != null)
            new SetDescription ();
    }

    public int getDefaultBackgroundColor ()
    {
        return this.BACKGROUND_COLOR;
    }

    private class SetDescription implements Title
    {
        private TextView txtShowDescription, btnShowAllDescription;
        private LinearLayout layout;

        private String title, description;
        private ViewGroup mainLayout;
        private boolean showAllDescription = false;
        private GettingAPartOfAText gettingAPartOfAText;

        SetDescription ()
        {
            if (Description.this.title != null) title = Description.this.title;
            description = Description.this.description;
            mainLayout = Description.this.mainLayout;
            RunClass ();
        }

        private void RunClass ()
        {
            SetTools ();
            SetLayout ();
        }

        private void SetTools ()
        {
            layout = new LinearLayout (G.getActivity ());
            txtShowDescription = new TextView (G.getActivity ());
            btnShowAllDescription = new TextView (G.getActivity ());
        }

        private void SetLayout ()
        {
            final int PADDING = Math.convertDPPX (10), MARGIN = Math.convertDPPX (5);

            LinearLayout.LayoutParams params;

            params = new LinearLayout.LayoutParams (LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins (MARGIN , MARGIN , MARGIN , MARGIN);
            layout.setLayoutParams (params);
            layout.setPadding (PADDING , PADDING , PADDING , PADDING);
            layout.setOrientation (LinearLayout.VERTICAL);
            int index = 0;
            if (title != null)
            {
                Title ();
                index++;
            }

            params = new LinearLayout.LayoutParams (LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins (MARGIN , MARGIN , MARGIN , MARGIN);
            txtShowDescription.setLayoutParams (params);
            txtShowDescription.setPadding (PADDING , PADDING , PADDING , PADDING);
            txtShowDescription.setTextColor (textColorMessage);
            txtShowDescription.setTypeface (Typeface.createFromAsset (G.getAssetsManager () , "iranian_sans.ttf"));

            txtShowDescription.setText (description);
            int len = 0;
            if (description.length () >= 50) len = 20;
            gettingAPartOfAText = new GettingAPartOfAText (description , Math.CalculateThePercentOfANumber (description.length () , len));
            txtShowDescription.setText ((gettingAPartOfAText.isDone ()) ? gettingAPartOfAText.getFinalText () : description);

            layout.addView (txtShowDescription , index);
            if (len > 0 && gettingAPartOfAText.isDone ())
            {
                params = new LinearLayout.LayoutParams (LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins (MARGIN , MARGIN , MARGIN , MARGIN);
                btnShowAllDescription.setText (GetValues.getString ("string_description__show_all_text"));
                btnShowAllDescription.setTextColor (GetValues.getColor ("color_show"));
                layout.addView (btnShowAllDescription , 2);
                SetOnClick ();
                btnShowAll = true;
            }

            float[] margin = new float[] {5 , 5 , 5 , 5 , 5 , 5 , 5 , 5};
            int color = backgroundColor;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                layout.setBackground (GetShapeDrawable.get (color , margin));
            else
                layout.setBackgroundDrawable (GetShapeDrawable.get (color , margin));

            mainLayout.addView (layout , 0);
        }

        @Override
        public void Title ()
        {
            bardiademon.Memoir.bardiademon.Class.View.Title title = new bardiademon.Memoir.bardiademon.Class.View.Title ();
            title.setText (this.title);
            title.setGravity (Gravity.END);
            title.setBackgroundColor (layout.getDrawingCacheBackgroundColor ());
            title.setTextColor (textColorTitle);
            title.setMainLayout (layout);
            title.apply ();
        }

        private void SetOnClick ()
        {
            btnShowAllDescription.setOnClickListener (v -> showAllOrPartOrDescription ());
        }


        private void showAllOrPartOrDescription ()
        {
            if (showAllDescription)
            {
                txtShowDescription.setText (description);
                if (btnShowAll)
                    btnShowAllDescription.setText (GetValues.getString ("string_description__show_less"));

                showAllDescription = false;
            }
            else
            {
                txtShowDescription.setText (gettingAPartOfAText.getFinalText ());
                if (btnShowAll)
                    btnShowAllDescription.setText (GetValues.getString ("string_description__show_all_text"));

                showAllDescription = true;
            }
        }
    }
}
