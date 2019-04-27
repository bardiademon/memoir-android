package bardiademon.Memoir.bardiademon.Class.View;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;

import java.util.LinkedHashMap;
import java.util.Map;

import bardiademon.Memoir.bardiademon.Class.BeforeRunActivity.G;
import bardiademon.Memoir.bardiademon.Class.Other.Math.Math;
import bardiademon.Memoir.bardiademon.Class.Other.String.ActivityClass;
import bardiademon.Memoir.R;
import bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues;
import bardiademon.Memoir.bardiademon.Interface.CallBack;
import de.hdodenhof.circleimageview.CircleImageView;

import static bardiademon.Memoir.bardiademon.Class.View.Wait.Set;


public class Title
{

    public static final int
            PADDING = 10, MARGIN = 10,
            BACKGROUND_COLOR = GetValues.getColor("BACKGROUND_COLOR__TITLE"), TEXT_COLOR = GetValues.getColor("TEXT_COLOR__TITLE"),
            TEXT_SIZE = 15, indexTop = 0, indexAfterAbout = 1, GRAVITY = Gravity.CENTER;

    public static final String FOUND_AUTO_TITLE_ACTIVITY = "12bardiademon12";

    public static final float[] BENDING_THE_MARGIN = new float[] {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0};

    public static int SMALL_PHOTO__WIDTH = Math.convertDPPX(30), SMALL_PHOTO__HEIGHT = Math.convertDPPX(30);

    private boolean circleSmallImageView;

    private float[] bendingTheMargin;

    private int smallPhotoWidth, smallPhotoHeight;
    private ViewGroup layout = null;

    private int errorShowPic = -1;
    private String text = "";
    private int backgroundColor, textColor, padding, margin, textSize, gravity, index;

    private ViewGroup mainLayout;
    private LinearLayout linearLayout, layoutWithWait, layoutTools, layoutWait;
    private Toolbar toolbar;
    private TextView txtShowTitle;
    private boolean showTheAmountOf;
    private int theAmountOf;
    private static int lastValueTheAmountOf = 100;
    @SuppressLint("StaticFieldLeak")
    private int indexTxtShowTheAmountOf, indexSmallImage;
    private static Map<Integer, TextView> txtShowTheAmountOf;

    private boolean curvedMargin = false;

    private boolean setOnClickTitle = false;
    private CallBack onClickTitle = null;

    private boolean border = false;
    private int textStyle;
    private boolean viewSmallPhoto = false;
    private int img = 0;
    private String linkImage = "";
    private CallBack setOnClickSmallPhoto = null;

    private int stateOfProgress;

    private boolean wait;

    public Title ()
    {
        setText(GetValues.getString("bardiademon"));
        setBackgroundColor(BACKGROUND_COLOR);
        setTextColor(TEXT_COLOR);
        setPadding(PADDING);
        setMargin(MARGIN);
        setTextSize(TEXT_SIZE);
        setGravity(GRAVITY);
        setBendingTheMargin(BENDING_THE_MARGIN);
    }

    public void setStateOfProgress (int stateOfProgress)
    {
        if (stateOfProgress != Wait.PROGRESS_NORMAL && stateOfProgress != Wait.PROGRESS_HORIZONTAL_INDETERMINATE)
            stateOfProgress = Wait.PROGRESS_NORMAL;

        this.stateOfProgress = stateOfProgress;
    }

    public int getStateOfProgress ()
    {
        return stateOfProgress;
    }

    public Title (String text)
    {
        this(text , Title.BACKGROUND_COLOR , Title.TEXT_COLOR , Title.TEXT_SIZE , Title.PADDING , Title.MARGIN , Title.GRAVITY , Title.indexTop , Title.BENDING_THE_MARGIN);
    }

    public Title (String text , int backgroundColor , int textColor , int textSize , int padding , int margin , int gravity , int index , float[] bendingTheMargin)
    {
        setText(text);
        setBackgroundColor(backgroundColor);
        setTextColor(textColor);
        setTextSize(textSize);
        setPadding(padding);
        setMargin(margin);
        setIndex(index);
        setGravity(gravity);
        setBendingTheMargin(bendingTheMargin);
        apply();
    }

    public void setTextStyle (int style)
    {
        textStyle = style;
    }

    public void apply ()
    {
        RunClass();
    }

    private void RunClass ()
    {
        SetTools();
        makeView();
    }

    public void setErrorShowPic (int errorShowPic)
    {
        this.errorShowPic = errorShowPic;
    }

    private void SetTools ()
    {
        if (layout == null)
            mainLayout = GetValues.getTools("main_layout");
        else
            mainLayout = layout;

        toolbar = new Toolbar(G.getContext());
        linearLayout = new LinearLayout(G.getContext());
        txtShowTitle = new TextView(G.getContext());
        if (txtShowTheAmountOf == null || txtShowTheAmountOf.isEmpty())
            txtShowTheAmountOf = new LinkedHashMap<>();

        if (isWait()) setToolsWait();
    }

    private void setToolsWait ()
    {
        layoutWithWait = new LinearLayout(G.getActivity());

        boolean stateOfProgressHorizontal = (stateOfProgress == Wait.PROGRESS_NORMAL);

        if (stateOfProgressHorizontal)
            layoutWithWait.setOrientation(LinearLayout.HORIZONTAL);
        else
            layoutWithWait.setOrientation(LinearLayout.VERTICAL);

        layoutWithWait.setGravity(Gravity.CENTER);

        layoutTools = new LinearLayout(G.getActivity());
        layoutTools.setOrientation(LinearLayout.HORIZONTAL);
        layoutTools.setGravity(Gravity.CENTER);

        layoutWait = new LinearLayout(G.getActivity());
        layoutWait.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);
        int margin = Math.convertDPPX(5);
        params.setMargins(margin , margin , margin , margin);
        layoutWithWait.setLayoutParams(params);

        layoutTools.setLayoutParams(getParamWait(stateOfProgressHorizontal , 0.9f));

        layoutWait.setLayoutParams(getParamWait(stateOfProgressHorizontal , 0.1f));
    }

    private LinearLayout.LayoutParams getParamWait (boolean horizontal , float weight)
    {
        LinearLayout.LayoutParams param;
        if (horizontal)
            param = new LinearLayout.LayoutParams(0 , LinearLayout.LayoutParams.MATCH_PARENT);
        else
            param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , 0);

        param.weight = weight;

        return param;
    }

    public void setOnClickTitle (boolean setOnClickTitle , CallBack onClickTitle)
    {
        this.setOnClickTitle = setOnClickTitle;
        this.onClickTitle = onClickTitle;
    }

    public void setBendingTheMargin (float topLeft , float topRight , float bottomLeft , float bottomRight)
    {
        this.bendingTheMargin = new float[] {topLeft , topLeft , topRight , topRight , bottomLeft , bottomLeft , bottomRight , bottomRight};
    }

    public void setBendingTheMargin (float topLeftRightAndBottomLeftRight)
    {
        setBendingTheMargin(topLeftRightAndBottomLeftRight , topLeftRightAndBottomLeftRight , topLeftRightAndBottomLeftRight , topLeftRightAndBottomLeftRight);
    }

    public void setBendingTheMargin (float[] topLeftRightAndBottomLeftRight)
    {
        this.bendingTheMargin = topLeftRightAndBottomLeftRight;
    }

    public void setMainLayout (ViewGroup layout)
    {
        this.layout = layout;
    }

    public void setBorderIndexTop (boolean border)
    {
        this.border = border;
    }

    public void setViewSmallPhoto (boolean viewSmallPhoto , CallBack onClick , int img , int index)
    {
        this.viewSmallPhoto = viewSmallPhoto;
        this.img = img;
        setOnClickSmallPhoto = onClick;
        indexSmallImage = index;
        this.smallPhotoWidth = SMALL_PHOTO__WIDTH;
        this.smallPhotoHeight = SMALL_PHOTO__HEIGHT;
    }

    public void setViewSmallPhoto (boolean viewSmallPhoto , CallBack onClick , String linkImage , int index)
    {
        this.viewSmallPhoto = viewSmallPhoto;
        this.linkImage = linkImage;
        setOnClickSmallPhoto = onClick;
        indexSmallImage = index;
        this.smallPhotoWidth = SMALL_PHOTO__WIDTH;
        this.smallPhotoHeight = SMALL_PHOTO__HEIGHT;
    }

    public void setCircleSmallImageView (boolean circleSmallImageView)
    {
        this.circleSmallImageView = circleSmallImageView;
    }

    public boolean isCircleSmallImageView ()
    {
        return circleSmallImageView;
    }

    public boolean isWait ()
    {
        return wait;
    }

    public void setWait (boolean wait)
    {
        this.wait = wait;
        if (isWait()) setStateOfProgress(Wait.PROGRESS_NORMAL);
    }

    public void closeWait ()
    {
        if (isWait())
        {
            setWait(false);
            toolbar.removeAllViews();
            apply();
        }
    }

    public void setSizeSmallPhoto (Integer width , Integer height)
    {
        this.smallPhotoWidth = Math.convertDPPX(width);
        this.smallPhotoHeight = Math.convertDPPX(height);
    }

    private TextView setTextTitle ()
    {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT);
        txtShowTitle.setTextColor(textColor);
        txtShowTitle.setTypeface(Typeface.createFromAsset(G.getAssetsManager() , "iranian_sans.ttf") , textStyle);
        txtShowTitle.setTextSize(textSize);
        txtShowTitle.setText(text);

        txtShowTitle.setLayoutParams(params);

        if (setOnClickTitle && onClickTitle != null)
        {
            txtShowTitle.setOnClickListener(v -> onClickTitle.Call());
        }

        return txtShowTitle;
    }

    public void setCurvedMargin (boolean curvedMargin)
    {
        this.curvedMargin = curvedMargin;
        if (this.curvedMargin) setBendingTheMargin(5);
    }

    public void setShowTheAmountOf (boolean showTheAmountOf , int theAmountOf , int index)
    {
        this.showTheAmountOf = showTheAmountOf;
        if (this.showTheAmountOf)
        {
            this.theAmountOf = theAmountOf;
            indexTxtShowTheAmountOf = index;
        }
    }

    private static TextView getTxtShowAmountOf (int index)
    {
        try
        {
            return txtShowTheAmountOf.get(index);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public static final int THE_LAST_VALUE_OF_THE_DISPLAY__ALL = 0;

    public void setTheLastValueOfTheDisplay (int lastValueTheAmountOf)
    {
        Title.lastValueTheAmountOf = lastValueTheAmountOf;
    }

    public void setText (String text)
    {
        if (text.equals(FOUND_AUTO_TITLE_ACTIVITY)) text = ActivityClass.getName();
        this.text = text;
    }

    public String getText ()
    {
        return text;
    }

    public void setBackgroundColor (int backgroundColor)
    {
        this.backgroundColor = backgroundColor;
    }

    public void setTextColor (int textColor)
    {
        this.textColor = textColor;
    }

    public void setTextSize (int textSize)
    {
        this.textSize = textSize;
    }

    public void setMargin (int margin)
    {
        this.margin = Math.convertDPPX(margin);
    }

    public void setPadding (int padding)
    {
        this.padding = Math.convertDPPX(padding);
    }

    public void setGravity (int gravity)
    {
        this.gravity = gravity;
    }

    public void setIndex (int index)
    {
        this.index = index;

        if (index == indexAfterAbout) setCurvedMargin(true);
    }


    private void makeView ()
    {
        try
        {
            if (mainLayout != null)
            {
                linearLayout.setPadding(padding , padding , padding , padding);
                linearLayout.setBackgroundColor(backgroundColor);
                linearLayout.setGravity(Gravity.CENTER);

                linearLayout.addView(setTextTitle());

                LinearLayout.LayoutParams toolBarParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);

                if (index == indexAfterAbout || curvedMargin)
                    toolBarParam.setMargins(this.margin , this.margin , this.margin , this.margin);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);
                toolbar.setBackgroundColor(backgroundColor);

                if (index == indexTop && !curvedMargin)
                {
                    if (border)
                        setBackgroundDrawable(GetShapeDrawable.get(backgroundColor , bendingTheMargin));
                    else
                    {
                        setBendingTheMargin(0);
                        setBackgroundDrawable(GetShapeDrawable.get(backgroundColor , bendingTheMargin));
                    }
                }
                else
                {
                    if (curvedMargin)
                        setBackgroundDrawable(GetShapeDrawable.get(backgroundColor , bendingTheMargin));
                }

                int margin = Math.convertDPPX(3);
                params.setMargins(margin , margin , margin , margin);
                toolbar.setLayoutParams(toolBarParam);

                Toolbar.LayoutParams llParam = new Toolbar.LayoutParams(Toolbar.LayoutParams.MATCH_PARENT , Toolbar.LayoutParams.WRAP_CONTENT);
                linearLayout.setLayoutParams(llParam);
                margin *= 2;
                llParam.setMargins(margin , margin , margin , margin);

                linearLayout.setGravity(gravity);

                if (isWait())
                {
                    layoutTools.addView(linearLayout);
                    new Set.Wait(layoutWait , getStateOfProgress());
                    layoutWithWait.addView(layoutTools , 0);
                    layoutWithWait.addView(layoutWait , 1);
                    toolbar.addView(layoutWithWait);
                }
                else toolbar.addView(linearLayout);

                if (showTheAmountOf)
                {
                    new Set().showTheAmountOf(theAmountOf , indexTxtShowTheAmountOf);
                    toolbar.addView(getTxtShowAmountOf(indexTxtShowTheAmountOf) , 0);
                }

                if (viewSmallPhoto)
                {
                    Set.SetViewSmallPhoto setViewSmallPhoto;
                    if (img == 0)
                        setViewSmallPhoto = new Set.SetViewSmallPhoto(linkImage , setOnClickSmallPhoto , smallPhotoWidth , smallPhotoHeight , indexSmallImage , circleSmallImageView , errorShowPic);
                    else
                        setViewSmallPhoto = new Set.SetViewSmallPhoto(img , setOnClickSmallPhoto , smallPhotoWidth , smallPhotoHeight , indexSmallImage , circleSmallImageView , errorShowPic);

                    if (circleSmallImageView)
                        toolbar.addView((CircleImageView) setViewSmallPhoto.getSmallPhoto(indexSmallImage) , 0);
                    else
                        toolbar.addView((ImageView) setViewSmallPhoto.getSmallPhoto(indexSmallImage) , 0);
                }

                mainLayout.addView(toolbar , index);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    private void setBackgroundDrawable (ShapeDrawable drawable)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            toolbar.setBackground(drawable);
        else toolbar.setBackgroundDrawable(drawable);
    }

    public static class ShowTheAmountOf
    {
        private static Map<Integer, Integer> ALL_THE_AMOUNT_OF;

        private static void setTextShowTheAmountOf (int theAmountOf , int index , boolean lastValueTheAmountOf)
        {
            TextView textView = getTxtShowAmountOf(index);
            if (textView == null) return;
            if (theAmountOf < 0) theAmountOf = 0;

            String valueTxtShowNumber;
            if (lastValueTheAmountOf)
                valueTxtShowNumber = (theAmountOf > Title.lastValueTheAmountOf && theAmountOf >= 10 && Title.lastValueTheAmountOf != THE_LAST_VALUE_OF_THE_DISPLAY__ALL) ? "+" + Title.lastValueTheAmountOf : String.valueOf(theAmountOf);
            else
                valueTxtShowNumber = String.valueOf(theAmountOf);

            textView.setText(valueTxtShowNumber);
        }

        public static final int LOW_OFF_ONE = -4, INCREASE_ONE = -3, LOW_OFF = -2, INCREASE = -1, SET_ALL_THE_AMOUNT_OF = -5, NULL = 0;

        public static void txtShowTheAmountOfLowOffOrIncrease (int theAmountOf , Integer per , int index)
        {
            if (ALL_THE_AMOUNT_OF == null) ALL_THE_AMOUNT_OF = new LinkedHashMap<>();

            TextView textView = getTxtShowAmountOf(index);
            if (textView == null || (per != SET_ALL_THE_AMOUNT_OF && per != LOW_OFF_ONE && per != INCREASE_ONE && per != LOW_OFF && per != INCREASE && per != NULL))
                return;

            boolean lastValueTheAmountOf = true;
            if (!per.equals(NULL))
            {
                int tempValueTxt = getAllTheAmountOf(index);
                switch (per)
                {
                    case SET_ALL_THE_AMOUNT_OF:
                        theAmountOf = getAllTheAmountOf(index);
                        lastValueTheAmountOf = false;
                        break;
                    case LOW_OFF_ONE:
                        theAmountOf = --tempValueTxt;
                        break;
                    case INCREASE_ONE:
                        theAmountOf = ++tempValueTxt;
                        break;
                    case INCREASE:
                        theAmountOf += tempValueTxt;
                        break;
                    case LOW_OFF:
                    {
                        if (tempValueTxt > theAmountOf) theAmountOf = (tempValueTxt - theAmountOf);
                        else theAmountOf -= tempValueTxt;
                        break;
                    }
                }

            }
            setTextShowTheAmountOf(theAmountOf , index , lastValueTheAmountOf);
            ALL_THE_AMOUNT_OF.put(index , theAmountOf);
        }

        public static int getAllTheAmountOf (int index)
        {
            return ALL_THE_AMOUNT_OF.get(index);
        }

        private static void clear ()
        {
            if (ALL_THE_AMOUNT_OF != null)
            {
                ALL_THE_AMOUNT_OF.clear();
                ALL_THE_AMOUNT_OF = null;
            }
        }
    }

    private static class Set
    {
        void showTheAmountOf (int theAmountOf , int index)
        {
            TextView textView;
            textView = new TextView(G.getActivity());

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER_VERTICAL;

            int padding = Math.convertDPPX(10);
            int bottomPadding = Math.convertDPPX(5);
            int margin = Math.convertDPPX(5);

            params.setMargins(margin , margin , margin , margin);
            textView.setLayoutParams(params);
            textView.setPadding(padding , padding , padding , padding - bottomPadding);
            textView.setTypeface(Typeface.createFromAsset(G.getAssetsManager() , "iranian_sans.ttf"));
            textView.setTextColor(GetValues.getColor("COLOR_WHITE"));
            textView.setGravity(Gravity.CENTER);

            txtShowTheAmountOf.put(index , textView);
            ShowTheAmountOf.txtShowTheAmountOfLowOffOrIncrease(theAmountOf , ShowTheAmountOf.NULL , index);
            setOnClickTheAmountOf(index);
        }

        private void setOnClickTheAmountOf (final int index)
        {
            (txtShowTheAmountOf.get(index)).setOnClickListener(v -> ShowTheAmountOf.txtShowTheAmountOfLowOffOrIncrease(0 , ShowTheAmountOf.SET_ALL_THE_AMOUNT_OF , index));
        }

        static class SetViewSmallPhoto
        {

            private static Map<Integer, ImageView> smallPhoto;
            private static Map<Integer, CircleImageView> smallPhotoCircle;
            private int index;
            private CallBack onClick;
            private int width, height;
            private static boolean circleSmallImageView;
            private static int errorShowPic;

            SetViewSmallPhoto (String linkPic , CallBack onClick , int width , int height , int index , boolean circleSmallImageView , int errorShowPic)
            {
                this.onClick = onClick;
                setSize(width , height);
                this.index = index;
                SetViewSmallPhoto.circleSmallImageView = circleSmallImageView;
                set();
                SetViewSmallPhoto.errorShowPic = errorShowPic;
                setImg(linkPic , index);
            }

            SetViewSmallPhoto (int img , CallBack onClick , int width , int height , int index , boolean circleSmallImageView , int errorShowPic)
            {
                this.onClick = onClick;
                setSize(width , height);
                this.index = index;
                SetViewSmallPhoto.circleSmallImageView = circleSmallImageView;
                set();
                SetViewSmallPhoto.errorShowPic = errorShowPic;
                setImg(img , index);
            }

            private void setSize (int width , int height)
            {
                this.width = width;
                this.height = height;
            }

            private void set ()
            {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width , height);
                int margin = Math.convertDPPX(10);
                params.setMargins(margin , margin , margin , margin);

                ImageView smallPhoto;
                CircleImageView circleImageView;
                setSmallPhoto();
                if (circleSmallImageView)
                {
                    circleImageView = new CircleImageView(G.getActivity());
                    circleImageView.setLayoutParams(params);
                    circleImageView.setOnClickListener(v -> afterClick());
                    SetViewSmallPhoto.smallPhotoCircle.put(index , circleImageView);
                }
                else
                {
                    smallPhoto = new ImageView(G.getActivity());
                    smallPhoto.setLayoutParams(params);
                    smallPhoto.setOnClickListener(v -> afterClick());
                    SetViewSmallPhoto.smallPhoto.put(index , smallPhoto);
                }
            }

            private void afterClick ()
            {
                if (onClick != null) onClick.Call();
            }

            private void setSmallPhoto ()
            {
                if (circleSmallImageView && (SetViewSmallPhoto.smallPhotoCircle == null || SetViewSmallPhoto.smallPhotoCircle.isEmpty()))
                    SetViewSmallPhoto.smallPhotoCircle = new LinkedHashMap<>();
                else if (!circleSmallImageView && (SetViewSmallPhoto.smallPhoto == null || SetViewSmallPhoto.smallPhoto.isEmpty()))
                    SetViewSmallPhoto.smallPhoto = new LinkedHashMap<>();
            }

            public static void setImg (String linkPic , int index)
            {
                setImage(Glide.with(G.getActivity()).load(linkPic) , index);
            }

            public static void setImg (int pic , int index)
            {
                setImage(Glide.with(G.getActivity()).load(pic) , index);
            }

            private static void setImage (RequestBuilder<Drawable> load , int index)
            {
                RequestBuilder<Drawable> error;
                if (errorShowPic != -1)
                    error = load.error(Glide.with(G.getActivity()).load(errorShowPic));
                else
                    error = load.error(Glide.with(G.getActivity()).load(R.drawable.error));

                if (circleSmallImageView) error.into(smallPhotoCircle.get(index));
                else error.into(smallPhoto.get(index));
            }

            Object getSmallPhoto (int index)
            {
                if (circleSmallImageView) return smallPhotoCircle.get(index);
                else return smallPhoto.get(index);

            }


            private static void clear ()
            {
                if (smallPhoto != null)
                {
                    smallPhoto.clear();
                    smallPhoto = null;
                }
            }
        }


        static class Wait
        {
            private LinearLayout linearLayout;
            private ViewGroup layout;
            private int stateOfProgress;

            Wait (ViewGroup layout , int stateOfProgress)
            {
                this.layout = layout;
                this.stateOfProgress = stateOfProgress;
                SetTools();
                setWait();
                addView();
                clear();
            }

            private void SetTools ()
            {
                linearLayout = new LinearLayout(G.getActivity());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);
                linearLayout.setLayoutParams(params);
            }

            private void setWait ()
            {
                Set(stateOfProgress , linearLayout);
            }

            private void addView ()
            {
                layout.addView(linearLayout);
            }

            private void clear ()
            {
                linearLayout = null;
            }
        }
    }

    public abstract static class SmallImage
    {
        public static void changeImg (int linkPic , int index)
        {
            Set.SetViewSmallPhoto.setImg(linkPic , index);
        }

        public static void changeImg (String linkPic , int index)
        {
            Set.SetViewSmallPhoto.setImg(linkPic , index);
        }

    }

    public static void clear ()
    {
        ShowTheAmountOf.clear();
        Set.SetViewSmallPhoto.clear();

        if (txtShowTheAmountOf != null)
        {
            txtShowTheAmountOf.clear();
            txtShowTheAmountOf = null;
        }
    }

    public static class TitleReady
    {
        public static void ACTION_BAR ()
        {
            Title title = new Title();
            title.setText(Title.FOUND_AUTO_TITLE_ACTIVITY);
            title.setGravity(Gravity.END);
            title.apply();
        }
    }
}

