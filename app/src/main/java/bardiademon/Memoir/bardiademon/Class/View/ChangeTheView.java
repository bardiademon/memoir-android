package bardiademon.Memoir.bardiademon.Class.View;

import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import bardiademon.Memoir.bardiademon.Class.BeforeRunActivity.G;
import bardiademon.Memoir.bardiademon.Class.Other.ActiveSwitching;
import bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues;
import bardiademon.Memoir.bardiademon.Class.Other.Math.Math;
import bardiademon.Memoir.bardiademon.Interface.Title;

public class ChangeTheView
{

    public static final int BUTTON_VIEW_MODE_VERTICAL = 0, BUTTON_VIEW_MODE_HORIZONTAL = 1;
    public static final int BUTTON_IN_ROW = 2;
    public static final int BACKGROUND_COLOR_BUTTON = GetValues.getColor("colorAccent");
    public static final int TEXT_COLOR_BUTTON = GetValues.getColor("COLOR_WHITE");

    private int howMuchButtonInRow;
    private int buttonViewMode;

    private String title, message;
    private List<Button> buttons;
    private Map<String, Callable<Void>> buttonsMap;
    private LinearLayout layout;
    private int textColorButton;
    private ShapeDrawable shapeDrawable = null;

    public ChangeTheView (String title , String message)
    {
        this(title , message , (List<Button>) null);
    }

    public ChangeTheView (String title , String message , List<Button> buttons)
    {
        setTitle(title);
        setMessage(message);
        setButton(buttons);
        setLayout(null);
        setButtonViewMode(BUTTON_VIEW_MODE_VERTICAL);
        howMuchButtonInRow(BUTTON_IN_ROW);
    }

    public ChangeTheView (String title , String message , Map<String, Callable<Void>> buttons)
    {
        setTitle(title);
        setMessage(message);
        setButton(buttons);
        setLayout(null);
        setButtonViewMode(BUTTON_VIEW_MODE_VERTICAL);
        howMuchButtonInRow(BUTTON_IN_ROW);
        setBackgroundColorButton(BACKGROUND_COLOR_BUTTON);
        setTextColorButton(TEXT_COLOR_BUTTON);
    }

    public void setLayout (LinearLayout layout)
    {
        if (layout == null) this.layout = GetValues.getTools("ll_content_view");
        else
            this.layout = layout;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public void setBackgroundColorButton (int color)
    {
        if (color != 0)
        {
            int margin = Math.convertDPPX(2);
            shapeDrawable = GetShapeDrawable.get(color , new float[] {margin , margin , margin , margin , margin , margin , margin , margin});
        }
        else shapeDrawable = null;
    }

    public void setTextColorButton (int color)
    {
        textColorButton = color;
    }

    public void howMuchButtonInRow (int howMuchButtonInRow)
    {
        if (howMuchButtonInRow > getLen())
            setButtonViewMode(BUTTON_VIEW_MODE_VERTICAL);
        else
            this.howMuchButtonInRow = howMuchButtonInRow;
    }

    private boolean isSetButton ()
    {
        return (buttons != null || buttonsMap != null);
    }

    private int getLen ()
    {
        if (buttonsMap == null && buttons != null) return buttons.size();
        else if (buttons == null && buttonsMap != null) return buttonsMap.size();

        return 0;
    }

    public void setButton (Map<String, Callable<Void>> buttons)
    {
        this.buttonsMap = buttons;
        this.buttons = null;
    }

    public void setButton (List<Button> buttons)
    {
        this.buttons = buttons;
        buttonsMap = null;
    }

    public void setButtonViewMode (int buttonViewMode)
    {
        if (buttonViewMode == BUTTON_VIEW_MODE_VERTICAL || buttonViewMode == BUTTON_VIEW_MODE_HORIZONTAL)
            this.buttonViewMode = buttonViewMode;
        else
            this.buttonViewMode = BUTTON_VIEW_MODE_VERTICAL;
    }

    public void change ()
    {
        SetTools setTools = new SetTools();

        layout.setGravity(Gravity.CENTER);
        layout.removeAllViews();

        layout.addView(setTools.getMainLayout());
    }

    public class SetTools implements Title
    {
        private LinearLayout layoutTitleAndMessage;
        private LinearLayout mainLayout, newLayout, layoutInScroll, layoutButton;
        private ScrollView scrollView;
        private CardView cardView;
        private bardiademon.Memoir.bardiademon.Class.View.Title titleShowTitleAndMessage;
        private LinearLayout.LayoutParams paramsBtn;
        private LinearLayout layoutHorizontal;
        private int margin = Math.convertDPPX(5);

        SetTools ()
        {
            SetTools();
            setParam();
            Title();
            if (buttons != null || buttonsMap != null)
                setLayoutButton();
            addView();
        }

        private void SetTools ()
        {
            mainLayout = new LinearLayout(G.getActivity());
            mainLayout.setGravity(Gravity.CENTER);

            newLayout = new LinearLayout(G.getActivity());
            newLayout.setGravity(Gravity.CENTER);

            scrollView = new ScrollView(G.getActivity());
            cardView = new CardView(G.getActivity());

            layoutTitleAndMessage = new LinearLayout(G.getActivity());
            layoutTitleAndMessage.setOrientation(LinearLayout.VERTICAL);

            layoutInScroll = new LinearLayout(G.getActivity());
            layoutInScroll.setOrientation(LinearLayout.VERTICAL);
            layoutInScroll.setGravity(Gravity.CENTER);

            if (isSetButton())
            {
                layoutButton = new LinearLayout(G.getActivity());
                layoutButton.setOrientation(LinearLayout.VERTICAL);
                layoutButton.setGravity(Gravity.CENTER);
            }

            titleShowTitleAndMessage = new bardiademon.Memoir.bardiademon.Class.View.Title();
        }

        private void setParam ()
        {
            LinearLayout.LayoutParams paramCardView = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);
            int margin = Math.convertDPPX(10);
            paramCardView.setMargins(margin , margin , margin , margin);
            cardView.setLayoutParams(paramCardView);


            LinearLayout.LayoutParams paramNewMainLayoutAndScroll = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);
            newLayout.setLayoutParams(paramNewMainLayoutAndScroll);
            layoutInScroll.setLayoutParams(paramNewMainLayoutAndScroll);
            mainLayout.setLayoutParams(paramNewMainLayoutAndScroll);
            scrollView.setLayoutParams(paramNewMainLayoutAndScroll);

            LinearLayout.LayoutParams paramLayoutMsgAndTitleAndBtn = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutTitleAndMessage.setLayoutParams(paramLayoutMsgAndTitleAndBtn);

            if (isSetButton())
                layoutButton.setLayoutParams(paramLayoutMsgAndTitleAndBtn);
        }

        @Override
        public void Title ()
        {
            titleShowTitleAndMessage.setMainLayout(layoutTitleAndMessage);

            titleShowTitleAndMessage.setText(title);
            titleShowTitleAndMessage.setIndex(bardiademon.Memoir.bardiademon.Class.View.Title.indexTop);
            titleShowTitleAndMessage.apply();

            titleShowTitleAndMessage.setBackgroundColor(layout.getDrawingCacheBackgroundColor());
            titleShowTitleAndMessage.setTextColor(GetValues.getColor("COLOR_BLACK"));
            titleShowTitleAndMessage.setText(message);
            titleShowTitleAndMessage.setIndex(bardiademon.Memoir.bardiademon.Class.View.Title.indexAfterAbout);
            titleShowTitleAndMessage.apply();
        }

        private void setLayoutButton ()
        {
            if (buttonViewMode == BUTTON_VIEW_MODE_VERTICAL || howMuchButtonInRow < 2 || getLen() < 2)
            {
                setParamButtonVertical();
                if (buttons != null && buttonsMap == null)
                {
                    for (Button button : buttons)
                        setButtonVertical(button);
                }
                else if (buttonsMap != null && buttons == null)
                {
                    for (Map.Entry<String, Callable<Void>> button : buttonsMap.entrySet())
                        setButtonVertical(setButtons(button.getKey() , button.getValue()));

                }
            }
            else if (buttonViewMode == BUTTON_VIEW_MODE_HORIZONTAL)
            {
                setParamButtonHorizontal();
                layoutHorizontal = new LinearLayout(G.getActivity());
                if (buttons != null && buttonsMap == null)
                {
                    for (int i = 0, len = buttons.size(); i < len; i++)
                        setButtonHorizontal(buttons.get(i) , i , len);
                }
                else if (buttonsMap != null && buttons == null)
                {
                    int i = 0, len = buttonsMap.size();
                    for (Map.Entry<String, Callable<Void>> button : buttonsMap.entrySet())
                        setButtonHorizontal(setButtons(button.getKey() , button.getValue()) , i++ , len);
                }
            }
        }

        private void setParamButtonHorizontal ()
        {
            paramsBtn = new LinearLayout.LayoutParams(0 , LinearLayout.LayoutParams.WRAP_CONTENT);
            paramsBtn.setMargins(margin , margin , margin , margin);
            paramsBtn.weight = (float) howMuchButtonInRow / 0.10f;
        }

        private void setParamButtonVertical ()
        {
            paramsBtn = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);
            paramsBtn.setMargins(margin , margin , margin , margin);
        }

        private void setButtonVertical (Button button)
        {
            LinearLayout layoutBtn = getLayoutBtn();
            setBackgroundColorAndTextColorButton(button);
            setFontButton(button);
            layoutBtn.addView(button);
            layoutButton.addView(layoutBtn);
        }

        private int counter = 0;

        private LinearLayout getLayoutBtn ()
        {
            LinearLayout layoutBtn = new LinearLayout(G.getActivity());
            layoutBtn.setOrientation(LinearLayout.HORIZONTAL);
            layoutBtn.setGravity(Gravity.CENTER);
            layoutBtn.setLayoutParams(paramsBtn);
            return layoutBtn;
        }

        private void setButtonHorizontal (Button button , int i , int len)
        {
            try
            {
                LinearLayout layoutBtn = getLayoutBtn();

                setBackgroundColorAndTextColorButton(button);
                setFontButton(button);
                layoutBtn.addView(button);
                layoutHorizontal.addView(layoutBtn);
                if (++counter == howMuchButtonInRow || i + counter >= len)
                {
                    layoutButton.addView(layoutHorizontal);
                    layoutHorizontal = new LinearLayout(G.getActivity());
                    counter = 0;
                }
            }
            catch (Exception ignored)
            {
            }
        }

        private Button setButtons (String text , Callable<Void> callable)
        {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT);
            Button button = new Button(G.getActivity());
            button.setLayoutParams(params);
            button.setText(text);
            if (callable != null)
            {
                button.setOnClickListener(v ->
                {
                    try
                    {
                        callable.call();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                });
            }
            return button;
        }

        private void setBackgroundColorAndTextColorButton (Button button)
        {
            if (shapeDrawable != null)
            {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                    button.setBackground(shapeDrawable);
                else
                    button.setBackgroundDrawable(shapeDrawable);
            }

            button.setTextColor(textColorButton);
        }

        private void setFontButton (Button button)
        {
            button.setTypeface(Typeface.createFromAsset(G.getAssetsManager() , "iranian_sans.ttf"));
        }

        private void addView ()
        {
            layoutInScroll.addView(layoutTitleAndMessage);
            if (isSetButton())
                layoutInScroll.addView(layoutButton);

            scrollView.addView(layoutInScroll);
            newLayout.addView(scrollView);
            cardView.addView(newLayout);
            mainLayout.addView(cardView);
        }

        LinearLayout getMainLayout ()
        {
            return mainLayout;
        }
    }

    public abstract static class CTVButton
    {
        private static Map<Integer, Map<String, Callable<Void>>> buttons;

        public static void putButton (Integer index , String title , Callable<Void> afterClick)
        {
            if (buttons == null) buttons = new LinkedHashMap<>();
            Map<String, Callable<Void>> buttons = new LinkedHashMap<>();
            buttons.put(title , afterClick);
            CTVButton.buttons.put(index , buttons);
            buttons.clear();
        }

        public static Map<String, Callable<Void>> getButtons (int index)
        {
            return buttons.get(index);
        }

        public static void clear (int indexButton)
        {
            if (buttons != null && indexButton != 0)
                buttons.remove(indexButton);
            System.gc();
        }

        public static int getIndex ()
        {
            boolean found = false;
            if (buttons == null) buttons = new LinkedHashMap<>();
            while (true)
            {
                int index = Math.getRandom();
                Integer key;
                for (Map.Entry<Integer, Map<String, Callable<Void>>> entry : buttons.entrySet())
                {
                    key = entry.getKey();
                    if (key.equals(index))
                    {
                        found = true;
                        break;
                    }
                }
                if (found) found = false;
                else return index;
            }
        }
    }

    public abstract static class ChangeTheViewReady
    {
        public static void NOT_FOUND (boolean buttonTryAgain)
        {
            int INDEX_BUTTON = CTVButton.getIndex();
            if (buttonTryAgain)
            {
                CTVButton.putButton(INDEX_BUTTON , GetValues.getString("string__try_again") , () ->
                {
                    ActiveSwitching.restartActivity();
                    return null;
                });
            }

            change(GetValues.getString("not_found") , GetValues.getString("string__sorry_not_found") , CTVButton.getButtons(INDEX_BUTTON));
            CTVButton.clear(INDEX_BUTTON);
        }

        public static void NOT_FOUND ()
        {
            NOT_FOUND(true);
        }

        private static void change (String title , String message , Map<String, Callable<Void>> buttons)
        {
            ChangeTheView changeTheView = new ChangeTheView(title , message , buttons);
            changeTheView.change();
        }
    }

}
