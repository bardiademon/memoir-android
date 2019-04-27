package bardiademon.Memoir.bardiademon.Class.View;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import bardiademon.Memoir.bardiademon.Interface.CallBack;

import bardiademon.Memoir.R;
import bardiademon.Memoir.bardiademon.Class.BeforeRunActivity.G;
import bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues;
import bardiademon.Memoir.bardiademon.Class.Other.Math.Math;
import bardiademon.Memoir.bardiademon.Interface.bardiademon;
import de.hdodenhof.circleimageview.CircleImageView;

import static bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues.getColor;

@bardiademon
public class ShowInfo
{
    @bardiademon
    public static final int
            BACKGROUND_TITLE__MODE_SEPARATED = getColor("show_info__background_title__separated"),
            BACKGROUND_VALUE__MODE_SEPARATED = getColor("show_info__background_value__separated"),
            TEXT_COLOR_TITLE__MODE_SEPARATED = getColor("show_info__text_color_title__separated"),
            TEXT_COLOR_VALUE__MODE_SEPARATED = getColor("show_info__text_color_value__separated");

    @bardiademon
    public static final int SMALL_SIZE_IMAGE = 20, BIG_SIZE_IMAGE = 100;


    @bardiademon
    public static final int MARGIN_DEFAULT = 0;

    @bardiademon
    private static final int INDEX_SMALL_PIC = 0, INDEX_SMALL_PIC__BOOL = 0, INDEX_SMALL_PIC__CALLBACK = 1;

    @bardiademon
    private static final int INDEX_IMAGE_SMALL_PIC__MODE = 0, INDEX_IMAGE_SMALL_PIC__IMAGE = 1;

    @bardiademon
    public static final int SMALL_PIC__INDEX_IMAGE = R.drawable.show_more;

    private int picSmallPic;
    private String picSmallPic__link;
    private int modePicSmallPic;

    @bardiademon
    private static final int MODE_SMALL_PIC_INT = -1, MODE_SMALL_PIC_LINK = -2;

    @bardiademon
    public static final int DEFAULT_MARGIN_SEPARATED = 5;
    private int marginModeSeparated, marginLeftRightModeSeparated, marginTopBottomModeSeparated;

    private boolean setSizeImageDefault;
    private int sizeImageDefault;

    @bardiademon
    private static int INDEX_TITLE = 0, INDEX_VALUE = 1, INDEX_TITLE_VALUE = 0;

    @bardiademon
    public static final int
            BACKGROUND_TITLE__MODE_CONNECTED_TOGETHER = getColor("show_info__background_title_value__connected_together"),
            BACKGROUND_VALUE__MODE_CONNECTED_TOGETHER = getColor("show_info__background_title_value__connected_together"),
            TEXT_COLOR_TITLE__MODE_CONNECTED_TOGETHER = getColor("show_info__text_color_title_value__connected_together"),
            TEXT_COLOR_VALUE__MODE_CONNECTED_TOGETHER = getColor("show_info__text_color_title_value__connected_together");
    public static final int MODE_SEPARATED = 0, MODE_CONNECTED_TOGETHER = 1;
    public static final int TITLE_IMAGE = 4, VALUE_IMAGE = 5, BOTH = 6;
    public static final int TYPE_PIC_INT = 0, TYPE_PIC_LINK = 2, TYPE_STRING = 3;
    public static final String NAME_LAYOUT = "layout_show_info";

    private boolean isImage;
    private int margin;

    private int backgroundTitle, backgroundValue, textColorTitle, textColorValue;
    private int counter, typeTitle, typeValue, mode;


    @bardiademon
    private boolean scrollView = true;
    private ViewGroup layout;
    private List<Integer> sizeImage;
    private Map<Integer, String[][]> infoFinal;
    private Map<Integer, String[][]> imageSmallPic;
    private Map<Integer, Object[][]> onClick;
    private Map<Integer, Integer[][]> type;
    private Map<Integer, Object[][]> smallPic;
    private String value, title;
    private SetTools setTools;
    private Title libTitle = null;

    @bardiademon
    public ShowInfo ()
    {
        RunClass();
    }

    @bardiademon
    private void RunClass ()
    {
        counter = -1;
        setMap();
        setDefaultModeSeparated();
        setLayout(GetValues.getTools(NAME_LAYOUT));
        setSizeImageDefault(BIG_SIZE_IMAGE);
        setSizeImageDefault = true;
        setMarginModeSeparated(DEFAULT_MARGIN_SEPARATED);
        setPicSmallPic(SMALL_PIC__INDEX_IMAGE);
    }

    @bardiademon
    public boolean isImage ()
    {
        return isImage;
    }

    @bardiademon
    public void setImage (boolean image)
    {
        isImage = image;

        if (isImage()) sizeImage = new ArrayList<>();
    }

    @bardiademon
    public int getMarginLeftRightModeSeparated ()
    {
        return marginLeftRightModeSeparated;
    }

    @bardiademon
    public int getMarginTopBottomModeSeparated ()
    {
        return marginTopBottomModeSeparated;
    }

    @bardiademon
    public void setMarginLeftRightModeSeparated (int marginLeftRightModeSeparated)
    {
        this.marginLeftRightModeSeparated = marginLeftRightModeSeparated;
    }

    @bardiademon
    public void setMarginTopBottomModeSeparated (int marginTopBottomModeSeparated)
    {
        this.marginTopBottomModeSeparated = marginTopBottomModeSeparated;
    }

    @bardiademon
    private void restart ()
    {
        clear();
        RunClass();
    }

    @bardiademon
    public void setMargin (int margin)
    {
        this.margin = Math.convertDPPX(margin);
    }

    @bardiademon
    public int getMargin ()
    {
        return margin;
    }

    @bardiademon
    public void setSizeImage (int size)
    {
        setSizeImageDefault = false;
        sizeImage.set(counter , Math.convertDPPX(size));
    }

    @bardiademon
    public int getMarginModeSeparated ()
    {
        return marginModeSeparated;
    }

    @bardiademon
    public void setMarginModeSeparated (int marginModeSeparated)
    {
        this.marginModeSeparated = Math.convertDPPX(marginModeSeparated);
        setMarginLeftRightModeSeparated(this.marginModeSeparated);
        setMarginTopBottomModeSeparated(this.marginModeSeparated);
    }

    @bardiademon
    public void setLayout (ViewGroup layout)
    {
        this.layout = layout;
    }

    @bardiademon
    private void setMap ()
    {
        type = new LinkedHashMap<>();
        infoFinal = new LinkedHashMap<>();
        onClick = new LinkedHashMap<>();
        smallPic = new LinkedHashMap<>();
        imageSmallPic = new LinkedHashMap<>();
    }

    @bardiademon
    public boolean isScrollView ()
    {
        return scrollView;
    }

    @bardiademon
    public void setMode (Integer mode)
    {
        if (!mode.equals(MODE_SEPARATED) && !mode.equals(MODE_CONNECTED_TOGETHER))
            mode = MODE_SEPARATED;

        this.mode = mode;
    }

    @bardiademon
    public void setScrollView (boolean scrollView)
    {
        this.scrollView = scrollView;
    }

    @bardiademon
    public void setDefaultModeSeparated ()
    {
        setMode(MODE_SEPARATED);
        setBackgroundTitleAndValue(BACKGROUND_TITLE__MODE_SEPARATED , BACKGROUND_VALUE__MODE_SEPARATED);
        setTextColorTitleAndValue(TEXT_COLOR_TITLE__MODE_SEPARATED , TEXT_COLOR_VALUE__MODE_SEPARATED);
    }

    @bardiademon
    public void setDefaultModeConnectedTogether ()
    {
        setMode(MODE_CONNECTED_TOGETHER);
        setBackgroundTitleAndValue(BACKGROUND_TITLE__MODE_CONNECTED_TOGETHER , BACKGROUND_VALUE__MODE_CONNECTED_TOGETHER);
        setTextColorTitleAndValue(TEXT_COLOR_TITLE__MODE_CONNECTED_TOGETHER , TEXT_COLOR_VALUE__MODE_CONNECTED_TOGETHER);
    }

    @bardiademon
    public int getSizeImageDefault ()
    {
        return sizeImageDefault;
    }

    @bardiademon
    public void setSizeImageDefault (int sizeImageDefault)
    {
        this.sizeImageDefault = Math.convertDPPX(sizeImageDefault);
    }

    @bardiademon
    public Integer getMode ()
    {
        return mode;
    }

    @bardiademon
    public void setBackgroundTitle (int backgroundTitle)
    {
        this.backgroundTitle = backgroundTitle;
    }

    @bardiademon
    public void setBackgroundValue (int backgroundValue)
    {
        this.backgroundValue = backgroundValue;
    }

    @bardiademon
    public void setBackgroundTitleAndValue (int backgroundTitle , int backgroundValue)
    {
        setBackgroundTitle(backgroundTitle);
        setBackgroundValue(backgroundValue);
    }

    @bardiademon
    public void setTextColorTitle (int textColorTitle)
    {
        this.textColorTitle = textColorTitle;
    }

    @bardiademon
    public void setTextColorValue (int textColorValue)
    {
        this.textColorValue = textColorValue;
    }

    @bardiademon
    public void setTextColorTitleAndValue (int textColorTitle , int textColorValue)
    {
        setTextColorTitle(textColorTitle);
        setTextColorValue(textColorValue);
    }

    @bardiademon
    public void setOnClickTitleAndValue (Callable<Void> afterClickTitle , Callable<Void> afterClickValue)
    {
        Object[][] callable = new Object[1][2];
        onClick.put(counter , callable);

        callable[INDEX_TITLE_VALUE][INDEX_TITLE] = afterClickTitle;
        callable[INDEX_TITLE_VALUE][INDEX_VALUE] = afterClickValue;
    }

    @bardiademon
    public void put (String title , String value)
    {
        typeTitle = TYPE_STRING;
        typeValue = TYPE_STRING;
        put(title , value , typeTitle , typeValue);
    }

    @bardiademon
    public void put (String title , String value , int which)
    {
        if (!checkWhich(which)) return;
        switch (which)
        {
            case BOTH:
                typeTitle = TYPE_PIC_LINK;
                typeValue = TYPE_PIC_LINK;
                break;
            case TITLE_IMAGE:
                typeTitle = TYPE_PIC_LINK;
                typeValue = TYPE_STRING;
                break;
            case VALUE_IMAGE:
                typeTitle = TYPE_STRING;
                typeValue = TYPE_PIC_LINK;
                break;
        }
        put(title , value , typeTitle , typeValue);
    }

    @bardiademon
    public void waitTitle (Title title)
    {
        libTitle = title;
    }

    @bardiademon
    public void setPicSmallPic (int picSmallPic)
    {
        this.picSmallPic = picSmallPic;
        modePicSmallPic = MODE_SMALL_PIC_INT;
    }

    @bardiademon
    public void setPicSmallPic__link (String picSmallPic__link)
    {
        this.picSmallPic__link = picSmallPic__link;
        modePicSmallPic = MODE_SMALL_PIC_LINK;
    }

    @bardiademon
    public void put (String title , int value)
    {
        typeTitle = TYPE_STRING;
        typeValue = TYPE_PIC_INT;
        put(title , String.valueOf(value) , typeTitle , typeValue);
    }

    @bardiademon
    public void put (String title , int value , boolean notPic)
    {
        if (!notPic) put(title , value);
        else
        {
            typeTitle = TYPE_STRING;
            typeValue = TYPE_STRING;
            put(title , String.valueOf(value) , typeTitle , typeValue);
        }
    }

    @bardiademon
    public void put (int title , int value , boolean notPic)
    {
        if (!notPic) put(title , value);
        else
        {
            typeTitle = TYPE_STRING;
            typeValue = TYPE_STRING;
            put(String.valueOf(title) , String.valueOf(value) , typeTitle , typeValue);
        }
    }

    @bardiademon
    public void put (int title , int value)
    {
        typeTitle = TYPE_PIC_INT;
        typeValue = TYPE_PIC_INT;
        put(String.valueOf(title) , String.valueOf(value) , typeTitle , typeValue);
    }

    @bardiademon
    public String getTitle ()
    {
        return title;
    }

    @bardiademon
    public String getValue ()
    {
        return value;
    }

    @bardiademon
    public void put (String title , String value , int typeTitle , int typeValue)
    {
        if (checkType(typeTitle) && checkType(typeValue))
        {
            this.value = value;
            this.title = title;
            counter++;
            String[][] info = new String[1][2];
            Integer[][] typeInfo = new Integer[1][2];

            info[0][INDEX_TITLE] = title;
            info[0][INDEX_VALUE] = value;
            typeInfo[0][INDEX_TITLE] = typeTitle;
            typeInfo[0][INDEX_VALUE] = typeValue;

            type.put(counter , typeInfo);
            infoFinal.put(counter , info);
            if (!setSizeImageDefault && isImage()) sizeImage.add(getSizeImageDefault());
            setOnClickTitleAndValue(null , null);
        }
    }

    @bardiademon
    public void setSmallPic (CallBack afterClick , boolean set)
    {
        Object[][] smallPic = new Object[1][2];
        smallPic[INDEX_SMALL_PIC][INDEX_SMALL_PIC__BOOL] = set;
        smallPic[INDEX_SMALL_PIC][INDEX_SMALL_PIC__CALLBACK] = afterClick;
        this.smallPic.put(counter , smallPic);
    }

    @bardiademon
    public void setSmallPic (CallBack afterClick , String image , boolean set)
    {
        Object[][] smallPic = new Object[1][2];
        smallPic[INDEX_SMALL_PIC][INDEX_SMALL_PIC__BOOL] = set;
        smallPic[INDEX_SMALL_PIC][INDEX_SMALL_PIC__CALLBACK] = afterClick;
        this.smallPic.put(counter , smallPic);
        setImageCustomSmallPic(image , MODE_SMALL_PIC_LINK);
    }

    @bardiademon
    public void setSmallPic (CallBack afterClick , int image , boolean set)
    {
        Object[][] smallPic = new Object[1][2];
        smallPic[INDEX_SMALL_PIC][INDEX_SMALL_PIC__BOOL] = set;
        smallPic[INDEX_SMALL_PIC][INDEX_SMALL_PIC__CALLBACK] = afterClick;
        this.smallPic.put(counter , smallPic);
        setImageCustomSmallPic(String.valueOf(image) , MODE_SMALL_PIC_INT);
    }

    @bardiademon
    public void setSmallPic (CallBack afterClick)
    {
        setSmallPic(afterClick , true);
    }

    @bardiademon
    public void setSmallPic (CallBack afterClick , String image)
    {
        setSmallPic(afterClick , image , true);
    }

    @bardiademon
    public void setSmallPic (CallBack afterClick , int image)
    {
        setSmallPic(afterClick , image , true);
    }

    @bardiademon
    public void setImageCustomSmallPic (String image)
    {
        setImageCustomSmallPic(image , MODE_SMALL_PIC_LINK);
    }

    @bardiademon
    public void setImageCustomSmallPic (int image)
    {
        setImageCustomSmallPic(String.valueOf(image) , MODE_SMALL_PIC_INT);
    }

    @bardiademon
    private void setImageCustomSmallPic (String image , int mode)
    {
        if (imageSmallPic.get(counter) != null) imageSmallPic.remove(counter);
        String[][] modeImage = new String[1][2];
        modeImage[0][INDEX_IMAGE_SMALL_PIC__MODE] = String.valueOf(mode);
        modeImage[0][INDEX_IMAGE_SMALL_PIC__IMAGE] = image;
        this.imageSmallPic.put(counter , modeImage);
    }


    @bardiademon
    private String getCustomImageSmallPic (int index , Integer which)
    {
        if (!which.equals(INDEX_IMAGE_SMALL_PIC__MODE) && !which.equals(INDEX_IMAGE_SMALL_PIC__IMAGE))
            return null;
        try
        {
            String[][] modeImage = imageSmallPic.get(index);
            if (modeImage != null) return modeImage[0][which];

        }
        catch (Exception ignored)
        {
        }
        return null;
    }

    @bardiademon
    private boolean checkWhich (Integer which)
    {
        return (which.equals(TITLE_IMAGE) || which.equals(VALUE_IMAGE) || which.equals(BOTH));
    }

    @bardiademon
    private boolean checkType (Integer type)
    {
        return (type.equals(TYPE_STRING) || type.equals(TYPE_PIC_INT) || type.equals(TYPE_PIC_LINK));
    }

    @bardiademon
    public void set ()
    {
        set(true);
    }

    @bardiademon
    public void set (boolean clear)
    {
        int sizeInfo = infoFinal.size();
        int sizeType = type.size();
        if (sizeInfo > 0 && sizeType > 0 && sizeInfo == sizeType)
        {
            setTools = new SetTools();
            layout.addView(setTools.getMainLayout());
        }
        if (clear) clear();
        else restart();
    }

    @bardiademon
    public ViewGroup getLayout ()
    {
        return layout;
    }

    @bardiademon
    private void clear ()
    {
        backgroundTitle = 0;
        backgroundValue = 0;
        textColorTitle = 0;
        textColorValue = 0;
        sizeImage = null;
        infoFinal = null;
        value = null;
        title = null;
        type = null;
        smallPic = null;
        imageSmallPic = null;
        typeTitle = 0;
        typeValue = 0;
        onClick = null;
        counter = 0;
        setTools.clear();
        setTools = null;
        System.gc();
    }

    @bardiademon
    public void clearLayout ()
    {
        layout = null;
    }

    @bardiademon
    private class SetTools
    {
        private LinearLayout mainLayout, mainLayout2, layoutInfo, layoutAllInfo;
        private CardView cardView;
        private CircleImageView imageView;
        private ScrollView scrollView;
        private LinearLayout.LayoutParams params;
        private LinearLayout layoutTitle, layoutValue;
        private LinearLayout.LayoutParams paramsLayoutTitleAndValue;
        private final int DEFAULT_RADIUS = 5;
        private int index;

        @bardiademon
        SetTools ()
        {
            SetTools();
            Integer mode = getMode();
            if (mode.equals(MODE_SEPARATED)) new Separated();
            else if (mode.equals(MODE_CONNECTED_TOGETHER)) new ConnectedTogether();
        }

        @bardiademon
        private void SetTools ()
        {
            mainLayout2 = new LinearLayout(G.getActivity());
            mainLayout2.setOrientation(LinearLayout.VERTICAL);

            mainLayout = new LinearLayout(G.getActivity());
            mainLayout.setOrientation(LinearLayout.VERTICAL);

            layoutAllInfo = new LinearLayout(G.getActivity());
            layoutAllInfo.setOrientation(LinearLayout.VERTICAL);

            cardView = new CardView(G.getActivity());

            params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.MATCH_PARENT);

            int margin = getMargin();
            params.setMargins(margin , margin , margin , margin);

            mainLayout.setLayoutParams(params);
            if (isScrollView())
            {
                scrollView = new ScrollView(G.getActivity());
                scrollView.setLayoutParams(params);
            }

            LinearLayout.LayoutParams paramsCardView = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.MATCH_PARENT);
            cardView.setLayoutParams(paramsCardView);
            mainLayout2.setLayoutParams(paramsCardView);

            paramsLayoutTitleAndValue = new LinearLayout.LayoutParams(0 , LinearLayout.LayoutParams.MATCH_PARENT);
            paramsLayoutTitleAndValue.weight = 0.5f;
        }

        @bardiademon
        private class Separated
        {
            private final int TOP = 0, DOWN = 1;

            Separated ()
            {
                set();
            }

            @bardiademon
            private void set ()
            {
                String[][] info;
                Integer[][] typeInfo;
                Object[][] callable;
                for (int i = 0; i <= counter; i++)
                {
                    index = i;
                    info = infoFinal.get(i);
                    typeInfo = type.get(i);
                    callable = onClick.get(i);
                    setLayoutInfo();
                    setValue(info[INDEX_TITLE_VALUE][INDEX_VALUE] , typeInfo[INDEX_TITLE_VALUE][INDEX_VALUE] , (CallBack) callable[INDEX_TITLE_VALUE][INDEX_VALUE]);
                    setTitle(info[INDEX_TITLE_VALUE][INDEX_TITLE] , typeInfo[INDEX_TITLE_VALUE][INDEX_TITLE] , (CallBack) callable[INDEX_TITLE_VALUE][INDEX_TITLE]);
                    setLayoutAllInfo();
                }
                cardView.addView(layoutAllInfo);
                if (isScrollView())
                {
                    scrollView.addView(cardView);
                    mainLayout2.addView(scrollView);
                }
                else
                    mainLayout2.addView(cardView);
                mainLayout.addView(mainLayout2);
                if (libTitle != null) libTitle.closeWait();
            }

            @bardiademon
            private void setLayoutInfo ()
            {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.MATCH_PARENT);

                params.setMargins(getMarginLeftRightModeSeparated() , getMarginTopBottomModeSeparated() , getMarginLeftRightModeSeparated() , getMarginTopBottomModeSeparated());
                layoutInfo = new LinearLayout(G.getActivity());
                layoutInfo.setOrientation(LinearLayout.VERTICAL);
                layoutInfo.setGravity(Gravity.CENTER);
                layoutInfo.setLayoutParams(params);
            }

            @bardiademon
            private void setTitle (String title , Integer type , CallBack callable)
            {
                switch (type)
                {
                    case TYPE_PIC_INT:
                    case TYPE_PIC_LINK:
                    {
                        if (isImage()) setTitleValuePic(title , type , TOP , backgroundTitle);
                        break;
                    }
                    case TYPE_STRING:
                    {
                        setTitleValueString(title , callable , TOP , backgroundTitle , textColorTitle);
                        break;
                    }
                }
            }

            @bardiademon
            private void setValue (String value , Integer type , CallBack callable)
            {
                switch (type)
                {
                    case TYPE_PIC_INT:
                    case TYPE_PIC_LINK:
                    {
                        if (isImage()) setTitleValuePic(value , type , DOWN , backgroundValue);
                        break;
                    }
                    case TYPE_STRING:
                    {
                        setTitleValueString(value , callable , DOWN , backgroundValue , textColorValue);
                        break;
                    }
                }
            }

            @bardiademon
            private void setTitleValuePic (String pic , Integer type , Integer topDown , int background)
            {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);

                LinearLayout layoutImg = new LinearLayout(G.getActivity());
                layoutImg.setGravity(Gravity.CENTER);
                layoutImg.setLayoutParams(params);

                int index;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                {
                    index = 0;
                    layoutImg.setBackground(GetShapeDrawable.get(background , getRadius(topDown)));
                }
                else
                {
                    index = 1;
                    layoutImg.setBackgroundDrawable(GetShapeDrawable.get(background , getRadius(topDown)));
                }

                layoutImg.setGravity(Gravity.CENTER);

                int whImage;
                if (setSizeImageDefault)
                    whImage = getSizeImageDefault();
                else
                    whImage = sizeImage.get(index);

                LinearLayout.LayoutParams paramsImage = new LinearLayout.LayoutParams(whImage , whImage);
                imageView = new CircleImageView(G.getActivity());
                imageView.setLayoutParams(paramsImage);

                RequestBuilder<Drawable> load;
                if (type.equals(TYPE_PIC_INT))
                    load = Glide.with(G.getActivity()).load(Integer.parseInt(pic));
                else
                    load = Glide.with(G.getActivity()).load(pic);

                load.error(Glide.with(G.getActivity()).load(R.drawable.error)).into(imageView);

                layoutImg.addView(imageView);
                int padding = Math.convertDPPX(5);
                layoutImg.setPadding(padding , padding , padding , padding);

                layoutInfo.addView(layoutImg , index);
            }

            @bardiademon
            private void setTitleValueString (String value , CallBack afterClick , Integer topDown , int background , int textColor)
            {
                Title title = new Title();
                title.setMainLayout(layoutInfo);
                title.setBackgroundColor(background);
                title.setTextColor(textColor);
                title.setCurvedMargin(false);
                title.setBorderIndexTop(true);
                title.setTextStyle(Typeface.BOLD);
                title.setText(value);
                title.setBendingTheMargin(getRadius(topDown));
                if (afterClick != null) title.setOnClickTitle(true , afterClick);
                if (topDown.equals(TOP)) setSmallPic(title);
                title.apply();
            }

            @bardiademon
            private void setSmallPic (Title title)
            {
                Object[][] smallP = smallPic.get(index);
                if (smallP == null) return;

                boolean setSmallPic = (boolean) smallP[INDEX_SMALL_PIC][INDEX_SMALL_PIC__BOOL];
                if (setSmallPic)
                {
                    int picInt = 0;
                    String picLink = null;
                    Integer mode;
                    String customImageSmallPic = getCustomImageSmallPic(index , INDEX_IMAGE_SMALL_PIC__MODE);
                    if (customImageSmallPic != null)
                    {
                        Integer modePic = Integer.parseInt(customImageSmallPic);
                        if (modePic.equals(MODE_SMALL_PIC_INT))
                            picInt = Integer.parseInt(getCustomImageSmallPic(index , INDEX_IMAGE_SMALL_PIC__IMAGE));
                        else if (modePic.equals(MODE_SMALL_PIC_LINK))
                            picLink = getCustomImageSmallPic(index , INDEX_IMAGE_SMALL_PIC__IMAGE);
                        mode = modePic;
                    }
                    else
                    {
                        if (modePicSmallPic == MODE_SMALL_PIC_INT) picInt = picSmallPic;
                        else if (modePicSmallPic == MODE_SMALL_PIC_LINK)
                            picLink = picSmallPic__link;
                        mode = modePicSmallPic;
                    }

                    CallBack callable = (CallBack) smallP[INDEX_SMALL_PIC][INDEX_SMALL_PIC__CALLBACK];
                    if (mode == MODE_SMALL_PIC_INT)
                    {
                        title.setViewSmallPhoto(true , () ->
                        {
                            if (callable != null) callable.Call();
                        } , picInt , index);
                    }
                    else
                    {
                        title.setViewSmallPhoto(true , () ->
                        {
                            if (callable != null) callable.Call();
                        } , picLink , index);
                    }
                }
            }

            @bardiademon
            private float[] getRadius (Integer topDown)
            {
                int topLeft = 0, topRight = 0, downLeft = 0, downRight = 0;
                if (topDown.equals(TOP))
                {
                    topLeft = DEFAULT_RADIUS;
                    topRight = DEFAULT_RADIUS;
                }
                else if (topDown.equals(DOWN))
                {
                    downLeft = DEFAULT_RADIUS;
                    downRight = DEFAULT_RADIUS;
                }
                return new float[] {topLeft , topLeft , topRight , topRight , downRight , downRight , downLeft , downLeft};
            }

            @bardiademon
            private void setLayoutAllInfo ()
            {
                layoutAllInfo.addView(layoutInfo);
            }
        }

        @bardiademon
        private class ConnectedTogether
        {
            private int index;
            private final int TOP = 0, DOWN = 1;

            @bardiademon
            ConnectedTogether ()
            {
                set();
            }

            @bardiademon
            private void set ()
            {
                String[][] info;
                Integer[][] typeInfo;
                Object[][] callable;
                for (int i = 0; i <= counter; i++)
                {
                    index = i;
                    info = infoFinal.get(i);
                    typeInfo = type.get(i);
                    callable = onClick.get(i);
                    setLayoutInfo(i);
                    setValue(info[INDEX_TITLE_VALUE][INDEX_VALUE] , typeInfo[INDEX_TITLE_VALUE][INDEX_VALUE] , (CallBack) callable[INDEX_TITLE_VALUE][INDEX_VALUE]);
                    setTitle(info[INDEX_TITLE_VALUE][INDEX_TITLE] , typeInfo[INDEX_TITLE_VALUE][INDEX_TITLE] , (CallBack) callable[INDEX_TITLE_VALUE][INDEX_TITLE]);
                    setLayoutAllInfo();
                }
                cardView.addView(layoutAllInfo);
                if (isScrollView())
                {
                    scrollView.addView(cardView);
                    mainLayout2.addView(scrollView);
                }
                else
                    mainLayout2.addView(cardView);
                mainLayout.addView(mainLayout2);
            }

            @bardiademon
            private void setLayoutInfo (int index)
            {
                layoutInfo = new LinearLayout(G.getActivity());
                layoutInfo.setOrientation(LinearLayout.HORIZONTAL);
                layoutInfo.setGravity(Gravity.CENTER);
                int padding;
                if (getMode().equals(MODE_SEPARATED)) layoutInfo.setLayoutParams(params);
                else
                {
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutInfo.setLayoutParams(params);

                    padding = Math.convertDPPX(20);
                    if (index == 0)
                        layoutInfo.setPadding(padding , padding , padding , 0);
                    else if (index == counter)
                        layoutInfo.setPadding(padding , 0 , padding , padding);
                    else
                        layoutInfo.setPadding(padding , 0 , padding , 0);
                }

                layoutTitle = new LinearLayout(G.getActivity());
                layoutValue = new LinearLayout(G.getActivity());
                if (index % 2 == 0 && infoFinal.size() > 1)
                {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                    {
                        layoutTitle.setBackground(GetShapeDrawable.get(backgroundTitle , getRadius(TOP)));
                        layoutValue.setBackground(GetShapeDrawable.get(backgroundValue , getRadius(DOWN)));
                    }
                    else
                    {
                        layoutTitle.setBackgroundDrawable(GetShapeDrawable.get(backgroundTitle , getRadius(TOP)));
                        layoutValue.setBackgroundDrawable(GetShapeDrawable.get(backgroundValue , getRadius(DOWN)));
                    }
                }
                layoutTitle.setGravity(Gravity.CENTER);
                layoutValue.setGravity(Gravity.CENTER);
                layoutTitle.setLayoutParams(paramsLayoutTitleAndValue);
                layoutValue.setLayoutParams(paramsLayoutTitleAndValue);
            }

            @bardiademon
            private float[] getRadius (Integer topDown)
            {
                int topLeft = 0, topRight = 0, downLeft = 0, downRight = 0;
                if (topDown.equals(TOP))
                {
                    topRight = DEFAULT_RADIUS;
                    downRight = DEFAULT_RADIUS;
                }
                else if (topDown.equals(DOWN))
                {
                    topLeft = DEFAULT_RADIUS;
                    downLeft = DEFAULT_RADIUS;
                }
                return new float[] {topLeft , topLeft , topRight , topRight , downRight , downRight , downLeft , downLeft};
            }

            @bardiademon
            private void setTitle (String title , Integer type , CallBack callable)
            {
                switch (type)
                {
                    case TYPE_PIC_INT:
                    case TYPE_PIC_LINK:
                    {
                        if (isImage()) setTitleValuePic(title , type , TOP);
                        break;
                    }
                    case TYPE_STRING:
                    {
                        setTitleValueString(title , callable , TOP , backgroundTitle , textColorTitle);
                        break;
                    }
                }
            }

            @bardiademon
            private void setValue (String value , Integer type , CallBack callable)
            {
                switch (type)
                {
                    case TYPE_PIC_INT:
                    case TYPE_PIC_LINK:
                    {
                        if (isImage) setTitleValuePic(value , type , DOWN);
                        break;
                    }
                    case TYPE_STRING:
                    {
                        setTitleValueString(value , callable , DOWN , backgroundValue , textColorValue);
                        break;
                    }
                }
            }

            @bardiademon
            private void setTitleValuePic (String pic , Integer type , Integer topDown)
            {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.MATCH_PARENT);

                imageView = new CircleImageView(G.getActivity());
                LinearLayout layoutImage = new LinearLayout(G.getActivity());
                layoutImage.setGravity(Gravity.CENTER);
                int whImage;
                if (setSizeImageDefault)
                    whImage = getSizeImageDefault();
                else
                    whImage = sizeImage.get(index);

                layoutImage.setLayoutParams(params);

                LinearLayout.LayoutParams paramsImage = new LinearLayout.LayoutParams(whImage , whImage);
                imageView.setLayoutParams(paramsImage);

                RequestBuilder<Drawable> load;
                if (type.equals(TYPE_PIC_INT))
                    load = Glide.with(G.getActivity()).load(Integer.parseInt(pic));
                else
                    load = Glide.with(G.getActivity()).load(pic);

                load.error(Glide.with(G.getActivity()).load(R.drawable.error)).into(imageView);

                layoutImage.addView(imageView);

                if (topDown.equals(TOP))
                    layoutTitle.addView(layoutImage);
                else
                    layoutValue.addView(layoutImage);

                int padding = Math.convertDPPX(5);
                layoutTitle.setPadding(padding , padding , padding , padding);
                layoutValue.setPadding(padding , padding , padding , padding);
            }

            @bardiademon
            private void setTitleValueString (String value , CallBack afterClick , Integer topDown , int background , int textColor)
            {
                Title title = new Title();
                if (topDown.equals(TOP)) title.setMainLayout(layoutTitle);
                else if (topDown.equals(DOWN)) title.setMainLayout(layoutValue);
                title.setText(value);
                if (index % 2 == 0 && infoFinal.size() > 1)
                    title.setBackgroundColor(background);
                else
                    title.setBackgroundColor(GetValues.getColor("COLOR_WHITE"));

                title.setBorderIndexTop(true);
                title.setBendingTheMargin(getRadius(topDown));
                title.setTextColor(textColor);
                if (afterClick != null) title.setOnClickTitle(true , afterClick);
                title.apply();
            }

            @bardiademon
            private void setLayoutAllInfo ()
            {
                layoutInfo.addView(layoutValue);
                layoutInfo.addView(layoutTitle);
                layoutAllInfo.addView(layoutInfo);
            }

        }

        @bardiademon
        LinearLayout getMainLayout ()
        {
            return mainLayout;
        }

        @bardiademon
        void clear ()
        {
            mainLayout = null;
            mainLayout2 = null;
            layoutInfo = null;
            layoutAllInfo = null;
            cardView = null;
            imageView = null;
            scrollView = null;
            params = null;
            layoutTitle = null;
            layoutValue = null;
            paramsLayoutTitleAndValue = null;
            System.gc();
        }
    }

    @bardiademon
    public abstract static class MoreFacilities
    {
        public static final String NAME_LAYOUT = "ll_content_view";

        @bardiademon
        public abstract static class ShowInfoGrouping
        {
            private static List<ShowInfoGroupingInfo> showInfo;
            private static ViewGroup mainLayout, layout;
            private static boolean setScroll;

            @bardiademon
            public static void SetLayout (ViewGroup mainLayout)
            {
                setScroll = false;
                ShowInfoGrouping.mainLayout = mainLayout;
            }

            @bardiademon
            public static void GroupSetting (ShowInfo showInfo , String nameGroup)
            {
                SetLayout(GetValues.getTools(NAME_LAYOUT));
                GroupSetting(showInfo , nameGroup , Typeface.NORMAL);
            }

            @bardiademon
            public static void SetScroll (boolean setScroll)
            {
                ShowInfoGrouping.setScroll = setScroll;
            }

            @bardiademon
            public static void GroupSetting (ShowInfo showInfo , String nameGroup , int textStyle)
            {
                if (ShowInfoGrouping.showInfo == null)
                    ShowInfoGrouping.showInfo = new ArrayList<>();
                ShowInfoGrouping.showInfo.add(new ShowInfoGroupingInfo(showInfo , nameGroup , textStyle));
            }

            @bardiademon
            public static void Set ()
            {
                if (showInfo != null && mainLayout != null)
                {
                    SetTools setTools = new SetTools();
                    layout = setTools.getLayoutScroll();
                    mainLayout.addView(setTools.getLayoutGroups());
                    setTools.clear();
                    clear();
                }
            }

            @bardiademon
            public static ViewGroup getLayout ()
            {
                return layout;
            }

            @bardiademon
            public static void clearLayout ()
            {
                layout = null;
            }

            @bardiademon
            private static void clear ()
            {
                showInfo = null;
                mainLayout = null;
                System.gc();
            }

            @bardiademon
            private static class SetTools
            {
                private LinearLayout layoutGroups, layoutGroup, layoutNameGroup, layoutInfo, layoutScroll;
                private View lineLeft, lineRight;
                private TextView txtShowNameGroup;
                private ScrollView scrollView;

                @bardiademon
                SetTools ()
                {
                    SetTools();
                    set();
                }

                @bardiademon
                private void SetTools ()
                {
                    layoutGroups = new LinearLayout(G.getActivity());
                    layoutGroups.setOrientation(LinearLayout.VERTICAL);

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.MATCH_PARENT);
                    layoutGroups.setLayoutParams(params);


                    if (setScroll)
                    {
                        scrollView = new ScrollView(G.getActivity());
                        layoutScroll = new LinearLayout(G.getActivity());
                        layoutScroll.setOrientation(LinearLayout.VERTICAL);

                        scrollView.setLayoutParams(params);
                        layoutScroll.setLayoutParams(params);
                    }
                }

                @bardiademon
                private void set ()
                {
                    for (ShowInfoGroupingInfo showInfoGroupingInfo : showInfo)
                    {
                        setToolsShowInfo();
                        setTextStyle(showInfoGroupingInfo.textStyle);
                        txtShowNameGroup.setText(showInfoGroupingInfo.nameGroup);
                        showInfoGroupingInfo.getShowInfo().setLayout(layoutInfo);
                        showInfoGroupingInfo.getShowInfo().set();
                        addView();
                    }
                    if (setScroll)
                    {
                        scrollView.addView(layoutScroll);
                        layoutGroups.addView(scrollView);
                    }
                }

                @bardiademon
                private void setToolsShowInfo ()
                {
                    layoutGroup = new LinearLayout(G.getActivity());
                    layoutGroup.setOrientation(LinearLayout.VERTICAL);

                    layoutNameGroup = new LinearLayout(G.getActivity());
                    layoutNameGroup.setOrientation(LinearLayout.HORIZONTAL);
                    layoutNameGroup.setGravity(Gravity.CENTER);

                    layoutInfo = new LinearLayout(G.getActivity());
                    layoutInfo.setOrientation(LinearLayout.VERTICAL);

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);

                    layoutGroup.setLayoutParams(params);
                    layoutInfo.setLayoutParams(params);

                    LinearLayout.LayoutParams paramsLayoutNameGroup = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);
                    int marginLayout = Math.convertDPPX(10);
                    paramsLayoutNameGroup.setMargins(marginLayout , Math.convertDPPX(marginLayout * 2) , marginLayout , marginLayout);
                    layoutNameGroup.setLayoutParams(paramsLayoutNameGroup);

                    txtShowNameGroup = new TextView(G.getActivity());
                    LinearLayout.LayoutParams paramsTextView = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT);
                    paramsTextView.setMargins(marginLayout , 0 , marginLayout , 0);
                    txtShowNameGroup.setGravity(Gravity.CENTER);
                    txtShowNameGroup.setTextColor(GetValues.getColor("COLOR_BLACK"));
                    txtShowNameGroup.setLayoutParams(paramsTextView);

                    LinearLayout.LayoutParams paramsLine = new LinearLayout.LayoutParams(0 , 1);
                    paramsLine.weight = 0.4f;

                    int margin = Math.convertDPPX(10);
                    params.setMargins(margin , 0 , margin , 0);

                    lineLeft = new View(G.getActivity());
                    lineLeft.setBackgroundColor(GetValues.getColor("COLOR_BLACK"));
                    lineLeft.setLayoutParams(paramsLine);

                    lineRight = new View(G.getActivity());
                    lineRight.setBackgroundColor(GetValues.getColor("COLOR_BLACK"));
                    lineRight.setLayoutParams(paramsLine);
                }

                @bardiademon
                private void setTextStyle (int textStyle)
                {
                    txtShowNameGroup.setTypeface(Typeface.createFromAsset(G.getAssetsManager() , "iranian_sans.ttf") , textStyle);
                }

                @bardiademon
                private void addView ()
                {
                    layoutNameGroup.addView(lineLeft , 0);
                    layoutNameGroup.addView(txtShowNameGroup , 1);
                    layoutNameGroup.addView(lineRight , 2);
                    layoutGroup.addView(layoutNameGroup , 0);
                    layoutGroup.addView(layoutInfo , 1);
                    if (setScroll)
                        layoutScroll.addView(layoutGroup);
                    else layoutGroups.addView(layoutGroup);
                }

                @bardiademon
                LinearLayout getLayoutGroups ()
                {
                    return layoutGroups;
                }

                @bardiademon
                LinearLayout getLayoutScroll ()
                {
                    return layoutScroll;
                }

                @bardiademon
                private void clear ()
                {
                    layoutGroup = null;
                    layoutNameGroup = null;
                    layoutInfo = null;
                    lineLeft = null;
                    layoutGroups = null;
                    scrollView = null;
                    layoutScroll = null;
                    lineRight = null;
                    txtShowNameGroup = null;
                    System.gc();
                }
            }

            @bardiademon
            private static class ShowInfoGroupingInfo
            {
                private ShowInfo showInfo;
                private String nameGroup;
                private int textStyle;

                @bardiademon
                ShowInfoGroupingInfo (ShowInfo showInfo , String nameGroup , int textStyle)
                {
                    this.showInfo = showInfo;
                    this.nameGroup = nameGroup;
                    this.textStyle = textStyle;
                }

                @bardiademon
                public ShowInfo getShowInfo ()
                {
                    return showInfo;
                }

                @bardiademon
                public String getNameGroup ()
                {
                    return nameGroup;
                }

                @bardiademon
                public int getTextStyle ()
                {
                    return textStyle;
                }
            }
        }

        @bardiademon
        public abstract static class ShowInfoPictureTop
        {
            private static int pic;
            private static String linkPic;
            private static int width, height;
            private static Callable<Void> callable;
            private static final int NOT_SET_ERROR = -1, PIC_ERROR_LINK = 0, PIC_ERROR_NO_LINK = 1;
            private static int typePicError = NOT_SET_ERROR;
            private static String linkPicError;
            private static int picError, picWaitForLoad;
            public static final int SIZE = 150;
            private static boolean CircleImage = true;

            @bardiademon
            public static void SetPic (int pic)
            {
                AfterClick(null);
                SetSize(SIZE , SIZE);
                linkPic = null;
                ShowInfoPictureTop.pic = pic;
            }

            @bardiademon
            public static void SetLinkPic (String linkPic)
            {
                AfterClick(null);
                SetSize(SIZE , SIZE);
                pic = 0;
                ShowInfoPictureTop.linkPic = linkPic;
            }

            public static void WaitForLoadPic (int picWaitForLoad)
            {
                ShowInfoPictureTop.picWaitForLoad = picWaitForLoad;
            }

            @bardiademon
            public static void SetSize (int width , int height)
            {
                ShowInfoPictureTop.width = Math.convertDPPX(width);
                ShowInfoPictureTop.height = Math.convertDPPX(height);
            }

            public static void ErrorOpenPic (String linkPic)
            {
                ShowInfoPictureTop.linkPicError = linkPic;
                typePicError = PIC_ERROR_LINK;
            }

            public static void ErrorOpenPic (int pic)
            {
                ShowInfoPictureTop.picError = pic;
                typePicError = PIC_ERROR_NO_LINK;
            }

            @bardiademon
            public static void SetSizeWidth (int width)
            {
                ShowInfoPictureTop.width = Math.convertDPPX(width);
            }

            @bardiademon
            public static void SetSizeHeight (int height)
            {
                ShowInfoPictureTop.height = Math.convertDPPX(height);
            }

            @bardiademon
            public static void AfterClick (Callable<Void> afterClick)
            {
                callable = afterClick;
            }

            @bardiademon
            public static void Apply ()
            {
                Apply(null);
            }

            @bardiademon
            public static void Apply (ViewGroup layout)
            {
                if (linkPic != null || pic != 0)
                {
                    SetTools setTools = new SetTools();

                    if (layout == null) layout = GetValues.getTools(NAME_LAYOUT);

                    if (layout != null) layout.addView(setTools.getMainLayout() , 0);

                    setTools.clear();
                    clear();
                }
            }

            @bardiademon
            public static boolean IsCircleImage ()
            {
                return CircleImage;
            }

            @bardiademon
            public static void SetCircleImage (boolean CircleImage)
            {
                ShowInfoPictureTop.CircleImage = CircleImage;
            }

            @bardiademon
            private static void clear ()
            {
                pic = 0;
                linkPic = null;
                width = 0;
                height = 0;
                System.gc();
            }

            @bardiademon
            private static class SetTools
            {
                private LinearLayout layoutPic, mainLayout;
                private CircleImageView circleImgShowPic;
                private ImageView imgShowPic;

                @bardiademon
                SetTools ()
                {
                    SetTools();
                    setImage();
                    addView();
                    SetOnClick();
                }

                @bardiademon
                private void SetTools ()
                {
                    layoutPic = new LinearLayout(G.getActivity());

                    mainLayout = new LinearLayout(G.getActivity());
                    mainLayout.setGravity(Gravity.CENTER);

                    LinearLayout.LayoutParams paramsMainLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);
                    mainLayout.setLayoutParams(paramsMainLayout);

                    LinearLayout.LayoutParams paramsLayout = new LinearLayout.LayoutParams(width , height);
                    int margin = Math.convertDPPX(10);
                    paramsLayout.setMargins(margin , margin , margin , margin);
                    layoutPic.setLayoutParams(paramsLayout);

                    LinearLayout.LayoutParams paramsImageView = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.MATCH_PARENT);
                    if (IsCircleImage())
                    {
                        circleImgShowPic = new CircleImageView(G.getActivity());
                        circleImgShowPic.setLayoutParams(paramsImageView);
                    }
                    else
                    {
                        imgShowPic = new ImageView(G.getActivity());
                        imgShowPic.setLayoutParams(paramsImageView);
                    }
                }

                @bardiademon
                private void setImage ()
                {
                    RequestManager with = Glide.with(G.getActivity());
                    RequestBuilder<Drawable> load;
                    if (linkPic == null && pic != 0)
                        load = with.load(pic);
                    else if (linkPic != null && pic == 0)
                        load = with.load(linkPic);
                    else return;
                    RequestBuilder<Drawable> loadError;
                    switch (typePicError)
                    {
                        case PIC_ERROR_LINK:
                            loadError = Glide.with(G.getActivity()).load(linkPicError);
                            break;
                        case PIC_ERROR_NO_LINK:
                            loadError = Glide.with(G.getActivity()).load(picError);
                            break;
                        case NOT_SET_ERROR:
                        default:
                            loadError = Glide.with(G.getActivity()).load(R.drawable.error);
                            break;
                    }
                    RequestBuilder<Drawable> error = load.apply(new RequestOptions().placeholder(picWaitForLoad)).error(loadError);
                    if (IsCircleImage())
                        error.into(circleImgShowPic);
                    else
                        error.into(imgShowPic);
                }

                @bardiademon
                private void SetOnClick ()
                {
                    if (callable != null)
                    {
                        imgShowPic.setOnClickListener(v ->
                        {
                            try
                            {
                                callable.call();
                            }
                            catch (Exception ignored)
                            {
                            }
                        });
                    }
                }

                @bardiademon
                private void addView ()
                {
                    layoutPic.addView(imgShowPic);
                    mainLayout.addView(layoutPic);
                }

                @bardiademon
                LinearLayout getMainLayout ()
                {
                    return mainLayout;
                }

                @bardiademon
                private void clear ()
                {
                    mainLayout = null;
                    layoutPic = null;
                    imgShowPic = null;
                    circleImgShowPic = null;
                    System.gc();
                }
            }
        }
    }
}
