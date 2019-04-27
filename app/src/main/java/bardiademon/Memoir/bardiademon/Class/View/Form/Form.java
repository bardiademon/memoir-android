package bardiademon.Memoir.bardiademon.Class.View.Form;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.os.Build;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;


import bardiademon.Memoir.bardiademon.Class.BeforeRunActivity.G;
import bardiademon.Memoir.bardiademon.Class.Other.Default;
import bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues;
import bardiademon.Memoir.bardiademon.Class.Other.Math.Math;
import bardiademon.Memoir.bardiademon.Class.View.Description;
import bardiademon.Memoir.bardiademon.Class.View.GetShapeDrawable;
import bardiademon.Memoir.bardiademon.Interface.bardiademon;
import bardiademon.Memoir.bardiademon.bardiademonException;


@bardiademon
public class Form
{
    private final static String ID_MAIN_LAYOUT = "layout_form";
    public static final int BUTTON_DISPLAY_MODE__ONE_BY_ONE = 1, BUTTON_DISPLAY_MODE__TWO_BY_TWO = 2, BUTTON_DISPLAY_MODE__THREE_BY_THREE = 3;

    private static final int TOOLS__TEXT_BOX = 0, TOOLS__CHECK_BOX = 1, TOOLS__CHECK_BOX_GROUPS = 2, TOOLS__RADIO_BUTTON = 3, TOOLS__BUTTON_BOTTOM = 4;

    public static final int SET_BUTTON_BOTTOM = 0, SET_CHECK_BOX = 1, SET_CHECK_BOX_GROUP = 11, SET_EDIT_TEXT = 2, SET_EDIT_TEXT_GROUP = 22, SET_RADIO_BTN = 3, SET_RADIO_BTN_GROUP = 33;

    public static final int DEFAULT_MARGIN_BUTTON = 5;
    private int marginButton;
    private static int buttonDisplayMode;
    private boolean fixBtnBottom = true;

    private ViewGroup mainLayout = null;
    private String nameIdLayout;
    private boolean cardView;

    private static Map<Integer, Value.Values.IndexValue> tools;
    private static Map<Integer, String> idText, idButton, idCheckBox, idRadioBtn;
    private static Map<Integer, Map<Integer, String>> idCheckBoxGroup, idEditTextGroup, idRadioBtnGroups;
    private static Map<Integer, Value.Values.ValueForm> valueForm;
    private static Map<Integer, Value.Values.ButtonForm> buttonForm;
    private static Map<Integer, Value.Values.CheckBoxForm> checkBoxForm;
    private static Map<Integer, Value.Values.RadioBtnForm> radioBtnForm;
    private Set set;

    @bardiademon
    public Form ()
    {
        setIdMainLayout (ID_MAIN_LAYOUT);
        setButtonDisplayMode (BUTTON_DISPLAY_MODE__TWO_BY_TWO);
        setMainLayout (GetValues.getTools (nameIdLayout));
        setMarginButton (DEFAULT_MARGIN_BUTTON);
        tools = new LinkedHashMap<> ();
        SetPublicStyle.LayoutValue.setDefault ();
    }

    @bardiademon
    public void setValueForm (Integer... set)
    {
        for (Integer s : set)
            setValueForm (s);
    }

    @bardiademon
    public void setValueForm (Integer set)
    {
        switch (set)
        {
            case SET_EDIT_TEXT:
                valueForm = new LinkedHashMap<> ();
                idText = new LinkedHashMap<> ();
                SetPublicStyle.SetPublicStyleEditText.setDefault ();
                break;
            case SET_EDIT_TEXT_GROUP:
                idEditTextGroup = new LinkedHashMap<> ();
                setValueForm (SET_EDIT_TEXT);
                break;
            case SET_CHECK_BOX:
                checkBoxForm = new LinkedHashMap<> ();
                idCheckBox = new LinkedHashMap<> ();
                SetPublicStyle.SetPublicStyleCheckBox.setDefault ();
                break;
            case SET_CHECK_BOX_GROUP:
                idCheckBoxGroup = new LinkedHashMap<> ();
                setValueForm (SET_CHECK_BOX);
                break;
            case SET_BUTTON_BOTTOM:
                buttonForm = new LinkedHashMap<> ();
                idButton = new LinkedHashMap<> ();
                SetPublicStyle.SetPublicStyleBottom.setDefault ();
                break;
            case SET_RADIO_BTN:
                radioBtnForm = new LinkedHashMap<> ();
                idRadioBtn = new LinkedHashMap<> ();
                SetPublicStyle.SetPublicStyleRadioBtn.setDefault ();
                break;
            case SET_RADIO_BTN_GROUP:
                setValueForm (SET_RADIO_BTN);
                idRadioBtnGroups = new LinkedHashMap<> ();
                break;
        }
    }

    @bardiademon
    public void setCardView (boolean cardView)
    {
        this.cardView = cardView;
    }

    @bardiademon
    public boolean isCardView ()
    {
        return cardView;
    }

    @bardiademon
    private static Value.Values.ValueForm getValueForm (int index)
    {
        return valueForm.get (index);
    }

    @bardiademon
    private static Value.Values.ButtonForm getButtonForm (int index)
    {
        return buttonForm.get (index);
    }

    @bardiademon
    public void setMainLayout (ViewGroup layout)
    {
        mainLayout = layout;
    }

    @bardiademon
    public void setIdMainLayout (String nameIdLayout)
    {
        this.nameIdLayout = nameIdLayout;
    }

    public void setFixBtnBottom (boolean fixBtnBottom)
    {
        this.fixBtnBottom = fixBtnBottom;
    }

    @bardiademon
    public void setButtonDisplayMode (int buttonDisplayMode)
    {
        Form.buttonDisplayMode = buttonDisplayMode;
    }

    @bardiademon
    public void setMarginButton (int marginButton)
    {
        this.marginButton = Math.convertDPPX (marginButton);
    }

    @bardiademon
    public int getMarginButton ()
    {
        return marginButton;
    }

    @bardiademon
    public static int getButtonDisplayMode ()
    {
        return buttonDisplayMode;
    }

    @bardiademon
    private static void setValueForm (int counter , Value.Values.ValueForm valueForm)
    {
        Form.valueForm.put (counter , valueForm);
    }

    @bardiademon
    private static void setButtonForm (int counter , Value.Values.ButtonForm buttonForm)
    {
        Form.buttonForm.put (counter , buttonForm);
    }

    @bardiademon
    private static int getLenValueForm ()
    {
        return valueForm.size ();
    }

    @bardiademon
    private static int getLenButtonForm ()
    {
        return buttonForm.size ();
    }

    @bardiademon
    public void apply ()
    {
        set = new Set (isCardView () , fixBtnBottom);

        ViewGroup layout = set.getMainLayout ();
        mainLayout.addView (layout);
        clear ();
    }

    @bardiademon
    private void clear ()
    {
        mainLayout = null;
        nameIdLayout = null;
        set = null;
        System.gc ();
    }

    @bardiademon
    public void clearForm ()
    {
        clear ();
        valueForm = null;
        buttonForm = null;
        idText = null;
        idButton = null;
        SetPublicStyle.SetPublicStyleCheckBox.clear ();
        SetPublicStyle.SetPublicStyleBottom.clear ();
        System.gc ();
    }

    @bardiademon
    public abstract static class Value
    {
        private static final String ERROR_ID_NOT_FOUND = "Id not found => ", ERROR_CLEANED_CLASS = "Cleaned class";

        @bardiademon
        private static boolean checkClass ()
        {
            if (Form.valueForm != null && Form.idText != null) return true;
            else bardiademonException.ERROR (ERROR_CLEANED_CLASS);
            return false;
        }

        @bardiademon
        public static abstract class ManagementValues
        {
            private static boolean foundIdInGroup;

            @bardiademon
            public static abstract class EdtText
            {
                private static EditText editText;

                @bardiademon
                public static boolean SetText (String id , String value)
                {
                    editText = GetEditText (id);
                    if (editText != null)
                    {
                        editText.setText (value);
                        return true;
                    }
                    else return false;
                }

                @bardiademon
                public static String GetStringText (String id)
                {
                    Editable text = GetText (id);
                    if (text != null)
                        return text.toString ();
                    else return null;
                }

                @bardiademon
                public static String GetStringTextGroup (String id)
                {
                    Editable text = GetTextGroup (id);
                    if (text != null)
                        return text.toString ();
                    else return null;
                }

                @bardiademon
                public static Map<String, String> GetAllStringText ()
                {
                    Map<String, String> resultString = new LinkedHashMap<> ();
                    String text;
                    for (Map.Entry<Integer, String> idGroup : idText.entrySet ())
                    {
                        text = GetStringText (idGroup.getValue ());
                        if (!foundIdInGroup)
                        {
                            if (text == null) text = "";
                            resultString.put (idGroup.getValue () , text);
                        }
                    }
                    return resultString;
                }

                @bardiademon
                public static Map<String, String> GetAllGroupsStringText ()
                {
                    String text;
                    Map<String, String> resultString = new LinkedHashMap<> ();
                    for (Map.Entry<Integer, Map<Integer, String>> entry : idEditTextGroup.entrySet ())
                    {
                        for (Map.Entry<Integer, String> idGroup : entry.getValue ().entrySet ())
                        {
                            text = GetStringText (idGroup.getValue ());
                            if (foundIdInGroup)
                            {
                                if (text == null) text = "";
                                resultString.put (idGroup.getValue () , text);
                            }
                        }
                    }
                    return resultString;
                }

                @bardiademon
                public static Editable GetText (String id)
                {
                    editText = GetEditText (id);
                    if (editText != null)
                        return editText.getText ();
                    else return null;
                }

                @bardiademon
                public static Editable GetTextGroup (String id)
                {
                    editText = GetEditTextGroup (id);
                    if (editText != null)
                        return editText.getText ();
                    else return null;
                }

                @bardiademon
                public static boolean CheckIdGroup (String id)
                {
                    return SearchMap.SearchMap2 (idEditTextGroup , id);
                }

                @bardiademon
                public static boolean CheckId (String id)
                {
                    if (SearchMap.SearchMap (idText , id))
                    {
                        foundIdInGroup = false;
                        return true;
                    }
                    else
                    {
                        if (CheckIdGroup (id))
                        {
                            foundIdInGroup = true;
                            return true;
                        }
                    }
                    bardiademonException.ERROR (ERROR_ID_NOT_FOUND);
                    return false;
                }

                @bardiademon
                public static EditText GetEditText (String id)
                {
                    if (CheckId (id))
                    {
                        if (foundIdInGroup) return GetEditTextGroup ();
                        else
                        {
                            Values.ValueForm valueForm = Form.valueForm.get (SearchMap.getIndex ());
                            return valueForm.editText;
                        }
                    }
                    else return null;
                }

                @bardiademon
                public static EditText GetEditTextGroup (String id)
                {
                    if (CheckId (id) && foundIdInGroup) return GetEditTextGroup ();
                    else return null;
                }

                @bardiademon
                private static EditText GetEditTextGroup ()
                {
                    Values.ValueForm valueForms = Form.valueForm.get (SearchMap.getIndexInstanceofMap ());
                    Values.ValueForm valueForm = valueForms.valueForms.get (SearchMap.getIndex ());
                    return valueForm.editText;
                }

                public static Integer SearchValueInEditText (String value , String... ids)
                {
                    int counterEmpty = 0;
                    String text;
                    for (String id : ids)
                    {
                        text = GetStringText (id);
                        counterEmpty = (text != null && text.equals (value)) ? ++counterEmpty : counterEmpty;
                    }
                    return counterEmpty;
                }

                public static Integer SearchValueInAllEditText (String value)
                {
                    int counterEmpty = 0;
                    String text;
                    Map<String, String> allStringText = EdtText.GetAllStringText ();
                    if (allStringText != null)
                    {
                        for (Map.Entry<String, String> entry : allStringText.entrySet ())
                        {
                            text = entry.getValue ();
                            counterEmpty = (text != null && text.equals (value)) ? ++counterEmpty : counterEmpty;

                        }
                    }
                    return counterEmpty;
                }

                @bardiademon
                public static Integer SearchValueInAllEditTextGroup (String value)
                {
                    int counterEmpty = 0;
                    String text;
                    Map<String, String> allStringText = EdtText.GetAllGroupsStringText ();
                    if (allStringText != null)
                    {
                        for (Map.Entry<String, String> entry : allStringText.entrySet ())
                        {
                            text = entry.getValue ();
                            counterEmpty = (text != null && text.equals (value)) ? ++counterEmpty : counterEmpty;

                        }
                    }
                    return counterEmpty;
                }

                @bardiademon
                public static Integer SearchValueInAllEditTextBoth (String value)
                {
                    return (SearchValueInAllEditTextGroup (value) + SearchValueInAllEditTextGroup (value));
                }

                @bardiademon
                public static boolean IsChangedText (String id)
                {
                    String text = GetStringText (id);
                    String defaultText = valueForm.get (SearchMap.getIndex ()).defaultText;
                    return (text != null && defaultText != null && !(text.equals (defaultText)));
                }

                @bardiademon
                public static boolean IsChangedTextGroup (String id)
                {
                    String text = GetStringTextGroup (id);
                    String defaultText = valueForm.get (SearchMap.getIndexInstanceofMap ()).valueForms.get (SearchMap.getIndex ()).defaultText;
                    return (text != null && defaultText != null && !(text.equals (defaultText)));
                }
            }

            @bardiademon
            public static abstract class ChkBox
            {
                private static CheckBox checkBox;

                @bardiademon
                public static CheckBox getCheckBox (String id)
                {
                    if (checkId (id))
                    {
                        if (foundIdInGroup)
                            return getCheckBox ();
                        else
                        {
                            Values.CheckBoxForm checkBoxForm = Form.checkBoxForm.get (SearchMap.getIndex ());
                            return checkBoxForm.checkBox;
                        }
                    }
                    else return null;
                }

                @bardiademon
                public static CheckBox getCheckBoxGroup (String id)
                {
                    if (checkId (id) && foundIdInGroup)
                        return getCheckBox ();
                    else return null;
                }

                @bardiademon
                private static CheckBox getCheckBox ()
                {
                    Values.CheckBoxForm checkBoxForms = Form.checkBoxForm.get (SearchMap.getIndexInstanceofMap ());
                    Values.CheckBoxForm checkBoxForm = checkBoxForms.checkBoxForms.get (SearchMap.getIndex ());
                    return checkBoxForm.checkBox;
                }

                @bardiademon
                public static boolean checkId (String id)
                {
                    if (SearchMap.SearchMap (idCheckBox , id))
                    {
                        foundIdInGroup = false;
                        return true;
                    }
                    else if (SearchMap.SearchMap2 (idCheckBoxGroup , id))
                    {
                        foundIdInGroup = true;
                        return true;
                    }
                    else return false;
                }

                @bardiademon
                public static CharSequence getText (String id)
                {
                    checkBox = getCheckBox (id);
                    if (checkBox != null)
                        return checkBox.getText ();
                    else return null;
                }

                @bardiademon
                public static String getStringText (String id)
                {
                    CharSequence text = getText (id);
                    if (text != null)
                        return text.toString ();
                    else return null;
                }

                @bardiademon
                public static CharSequence getTextGroup (String id)
                {
                    checkBox = getCheckBoxGroup (id);
                    if (checkBox != null)
                        return checkBox.getText ();
                    else return null;
                }

                @bardiademon
                public static String getStringTextGroup (String id)
                {
                    CharSequence textGroup = getTextGroup (id);
                    if (textGroup != null)
                        return textGroup.toString ();
                    else return null;
                }

                @bardiademon
                public static void setChecked (String id , boolean checked)
                {
                    checkBox = getCheckBox (id);
                    if (checkBox != null)
                        checkBox.setChecked (checked);
                }

                @bardiademon
                public static void setCheckedGroup (String id , boolean checked)
                {
                    checkBox = getCheckBoxGroup (id);
                    if (checkBox != null)
                        checkBox.setChecked (checked);
                }

                @bardiademon
                public static boolean isChecked (String id)
                {
                    checkBox = getCheckBox (id);
                    return checkBox != null && checkBox.isChecked ();
                }

                @bardiademon
                public static boolean isCheckedGroups (String id)
                {
                    checkBox = getCheckBoxGroup (id);
                    return checkBox != null && checkBox.isChecked ();
                }
            }

            @bardiademon
            public static abstract class RadioBtn
            {
                private static RadioButton radioButton;

                @bardiademon
                public static boolean checkId (String id)
                {
                    if (SearchMap.SearchMap (idRadioBtn , id))
                    {
                        foundIdInGroup = false;
                        return true;
                    }
                    else return checkIdGroup (id);
                }

                @bardiademon
                public static boolean checkIdGroup (String id)
                {
                    if (SearchMap.SearchMap2 (idRadioBtnGroups , id))
                    {
                        foundIdInGroup = true;
                        return true;
                    }
                    else
                    {
                        bardiademonException.ERROR (ERROR_ID_NOT_FOUND + id);
                        return false;
                    }
                }

                public static String getDefaultValue (String id)
                {
                    if (checkId (id))
                    {
                        if (foundIdInGroup)
                        {
                            Values.RadioBtnForm radioBtnFormGroup = getRadioBtnFormGroup ();
                            if (radioBtnFormGroup != null) return radioBtnFormGroup.defaultValue;
                        }
                        else return getRadioBtnForm ().defaultValue;
                    }
                    return null;
                }

                @bardiademon
                public static RadioButton getRadioButton (String id)
                {
                    if (checkId (id))
                    {
                        if (foundIdInGroup) return getRadioButton ();
                        else
                            return getRadioBtnForm ().radioButton;
                    }
                    else return null;
                }

                @bardiademon
                public static RadioButton getRadioButtonGroup (String id)
                {
                    if (checkIdGroup (id) && foundIdInGroup) return getRadioButton ();
                    else return null;
                }

                @bardiademon
                private static RadioButton getRadioButton ()
                {
                    Values.RadioBtnForm radioBtnForm = getRadioBtnFormGroup ();
                    if (radioBtnForm != null) return radioBtnForm.radioButton;
                    else return null;
                }

                private static Values.RadioBtnForm getRadioBtnFormGroup ()
                {
                    Values.RadioBtnForm radioBtnForms = Form.radioBtnForm.get (SearchMap.getIndexInstanceofMap ());
                    if (radioBtnForms != null)
                        return radioBtnForms.radioBtnForm.get (SearchMap.getIndex ());
                    else return null;
                }

                private static Values.RadioBtnForm getRadioBtnForm ()
                {
                    return Form.radioBtnForm.get (SearchMap.getIndexInstanceofMap ());
                }

                public static boolean isChecked (String id)
                {
                    radioButton = getRadioButton (id);
                    return (radioButton != null && radioButton.isChecked ());
                }

                public static String isOneCheckedGroups (int indexRadioBtn)
                {
                    Map<Integer, String> integerStringMap = idRadioBtnGroups.get (indexRadioBtn);
                    if (integerStringMap != null)
                    {
                        String IS_CHECKED = "is_checked";
                        for (Map.Entry<Integer, String> entry : integerStringMap.entrySet ())
                            if (isChecked (entry.getValue ()))
                            {
                                Values.RadioBtnForm radioBtnFormGroup = getRadioBtnFormGroup ();
                                if (radioBtnFormGroup != null)
                                    return radioBtnFormGroup.defaultValue;
                                else return IS_CHECKED;
                            }
                    }
                    return null;
                }

                public static boolean isCheckedGroups (String id)
                {
                    radioButton = getRadioButtonGroup (id);
                    return (radioButton != null && radioButton.isChecked ());
                }

                public static void setChecked (String id , boolean checked)
                {
                    radioButton = getRadioButton (id);
                    if (radioButton != null)
                        radioButton.setChecked (checked);
                }

                public static void setCheckedGroups (String id , boolean checked)
                {
                    radioButton = getRadioButtonGroup (id);
                    if (radioButton != null)
                        radioButton.setChecked (checked);
                }
            }

            public static abstract class Btn
            {
                private static Button btn;

                public static Button getButton (String id)
                {
                    if (checkId (id))
                        return buttonForm.get (SearchMap.getIndex ()).button;
                    else return null;
                }

                public static boolean checkId (String id)
                {
                    if (SearchMap.SearchMap (idButton , id))
                        return true;
                    else
                    {
                        bardiademonException.ERROR (ERROR_ID_NOT_FOUND + id);
                        return false;
                    }
                }

                public static void setText (String id , String text)
                {
                    if (getCheckBtn (id))
                        btn.setText (text);
                }

                public static CharSequence getText (String id)
                {
                    if (getCheckBtn (id))
                        return btn.getText ();
                    else return null;
                }

                public static String getStringText (String id)
                {
                    CharSequence text = getText (id);
                    if (text != null) return text.toString ();
                    else return null;
                }

                public static void setEnable (String id , boolean enable)
                {
                    if (getCheckBtn (id)) btn.setEnabled (enable);
                }

                public static boolean isEnable (String id)
                {
                    return (getCheckBtn (id) && btn.isEnabled ());
                }

                public static boolean checkEnable (String id , boolean enable)
                {
                    return (getCheckBtn (id) && btn.isEnabled () == enable);
                }

                public static void setVisibility (String id , int visibility)
                {
                    if (getCheckBtn (id)) btn.setVisibility (visibility);
                }

                public static int getVisibility (String id)
                {
                    if (getCheckBtn (id)) return btn.getVisibility ();
                    else return 0;
                }

                public static boolean checkVisibility (String id , int visibility)
                {
                    return (getCheckBtn (id) && btn.getVisibility () == visibility);
                }

                public static void setColor (String id , int backgroundColor , int textColor)
                {
                    if (getCheckBtn (id))
                    {
                        setBackgroundColor (backgroundColor);
                        btn.setText (textColor);
                    }
                }

                public static void setBackgroundColor (String id , int backgroundColor)
                {
                    if (getCheckBtn (id)) setBackgroundColor (backgroundColor);
                }

                private static void setBackgroundColor (int backgroundColor)
                {
                    int radius = getButtonForm ().radius;
                    ShapeDrawable shapeDrawable = GetShapeDrawable.get (backgroundColor , new float[] {radius , radius , radius , radius , radius , radius , radius , radius});
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                        btn.setBackground (shapeDrawable);
                    else btn.setBackgroundDrawable (shapeDrawable);
                }

                private static Values.ButtonForm getButtonForm ()
                {
                    return buttonForm.get (SearchMap.getIndex ());
                }

                public static void setTextColor (String id , int textColor)
                {
                    if (getCheckBtn (id)) btn.setTextColor (textColor);
                }

                public static void setTextStyle (String id , int textStyle)
                {
                    if (getCheckBtn (id))
                        btn.setTypeface (Typeface.createFromAsset (G.getAssetsManager () , "iranian_sans.ttf") , textStyle);
                }

                public static void setTextSize (String id , int textSize)
                {
                    if (getCheckBtn (id)) btn.setTextSize (textSize);
                }

                public static void setGravityText (String id , int gravity)
                {
                    if (getCheckBtn (id)) btn.setGravity (gravity);
                }

                private static boolean getCheckBtn (String id)
                {
                    btn = null;
                    return ((btn = getButton (id)) != null);
                }
            }

        }

        @bardiademon
        public abstract static class Put
        {
            private static final String ERROR_DUPLICATE_ID = "Id is duplicate > Please check it => ", AN_ERROR_OCCURRED = "An error occurred";
            private static Values.ValueForm valueForm, valueForms;
            private static Values.ButtonForm buttonForm;
            private static Values.CheckBoxForm checkBoxForm;
            private static Values.RadioBtnForm radioBtnForm;
            public static final String NULL = "";
            private static boolean error = false;
            private static int counter = 0;
            private static List<Integer> tools = new ArrayList<> ();
            private static Values.IndexValue indexValue = new Values.IndexValue ();

            @bardiademon
            public abstract static class PutButton
            {
                @bardiademon
                public static void put (String text)
                {
                    checkButtonForm ();
                    buttonForm.text = text;
                }

                @bardiademon
                public static void put (String text , Callable<Void> afterClick)
                {
                    checkButtonForm ();
                    buttonForm.text = text;
                    buttonForm.afterClick = afterClick;
                }

                @bardiademon
                public static void setVisibility (int visibility)
                {
                    checkButtonForm ();
                    buttonForm.visibility = visibility;
                }

                @bardiademon
                public static void setEnable (boolean enable)
                {
                    checkButtonForm ();
                    buttonForm.enable = enable;
                }

                @bardiademon
                public static void setStyle (int backgroundColor , int textColor , int textStyle , int textSize , int radius , int gravityText)
                {
                    checkButtonForm ();
                    buttonForm.backgroundColor = backgroundColor;
                    buttonForm.textColor = textColor;
                    buttonForm.textStyle = textStyle;
                    buttonForm.textSize = Math.convertDPPX (textSize);
                    buttonForm.radius = Math.convertDPPX (radius);
                    buttonForm.gravityText = gravityText;
                    buttonForm.setBackground = true;
                    buttonForm.setTextColor = true;
                    buttonForm.setTextSize = true;
                }

                @bardiademon
                public static void setColor (int backgroundColor , int textColor)
                {
                    checkButtonForm ();
                    buttonForm.backgroundColor = backgroundColor;
                    buttonForm.textColor = textColor;
                    buttonForm.setBackground = true;
                    buttonForm.setTextColor = true;
                }

                @bardiademon
                public static void setTextColor (int textColor)
                {
                    checkButtonForm ();
                    buttonForm.textColor = textColor;
                    buttonForm.setTextColor = true;
                }

                @bardiademon
                public static void setBackgroundColor (int backgroundColor)
                {
                    checkButtonForm ();
                    buttonForm.backgroundColor = backgroundColor;
                    buttonForm.setBackground = true;
                }

                @bardiademon
                public static void setTextStyle (int textStyle)
                {
                    checkButtonForm ();
                    buttonForm.textStyle = textStyle;
                }

                @bardiademon
                public static void setTextSize (int textSize)
                {
                    checkButtonForm ();
                    buttonForm.textSize = Math.convertDPPX (textSize);
                    buttonForm.setTextSize = true;
                }

                @bardiademon
                public static void setRadius (int radius)
                {
                    checkButtonForm ();
                    buttonForm.radius = Math.convertDPPX (radius);
                }

                @bardiademon
                public static void setGravityText (int gravityText)
                {
                    checkButtonForm ();
                    buttonForm.gravityText = gravityText;
                }

                @bardiademon
                private static void checkButtonForm ()
                {
                    if (buttonForm == null) buttonForm = new Values.ButtonForm ();
                }

                @bardiademon
                public static void apply (String id)
                {
                    apply ();
                    setId (id);
                }

                @bardiademon
                public static void apply ()
                {
                    indexValue.indexButton++;
                }

                @bardiademon
                private static void setId (String id)
                {
                    checkButtonForm ();
                    if (!SearchMap.SearchMap (idButton , id))
                        idButton.put (indexValue.indexButton , id);
                    else bardiademonException.ERROR (ERROR_DUPLICATE_ID + id);
                }

            }

            @bardiademon
            public abstract static class PutEditText
            {
                private static boolean isGroup;
                private static Map<Integer, String> idGroups;
                private static int counterIdGroups;

                @bardiademon
                public static void setGroup ()
                {
                    isGroup = true;
                    valueForms = new Values.ValueForm ();
                    valueForms.valueForms = new LinkedHashMap<> ();
                    valueForms.isGroup = true;
                    idGroups = new LinkedHashMap<> ();
                    counterIdGroups = 0;
                }

                @bardiademon
                public static void putEditText (String defaultValue , String hint , String titleDescription , String valueDescription , Callable<Void> afterClickText , TextWatcher afterChangeText)
                {
                    setValueForm ();
                    putEditText (defaultValue , hint);
                    putDescriptionEditText (titleDescription , valueDescription);
                    putAfterClickOrOnChangeEditText (afterClickText , afterChangeText);
                }

                @bardiademon
                public static void putEditText (String defaultValue , String hint , String titleDescription , String valueDescription)
                {
                    setValueForm ();
                    putEditText (defaultValue , hint);
                    putDescriptionEditText (titleDescription , valueDescription);
                }

                @bardiademon
                public static void putEditText (String hint , String titleDescription , String valueDescription)
                {
                    setValueForm ();
                    putEditText (valueForm.defaultText , hint);
                    putDescriptionEditText (titleDescription , valueDescription);
                }

                @bardiademon
                public static void putStyle (int style)
                {
                    valueForm.style = style;
                }

                @bardiademon
                public static void putEditText (String hint)
                {
                    setValueForm ();
                    putEditText (valueForm.defaultText , hint);
                }

                @bardiademon
                public static void putEditText (String defaultValue , String hint)
                {
                    setValueForm ();
                    valueForm.defaultText = defaultValue;
                    valueForm.hintText = hint;
                }

                @bardiademon
                public static void putInputTypeEditText (int inputType)
                {
                    setValueForm ();
                    valueForm.inputType = (InputType.TYPE_CLASS_TEXT | inputType);
                }

                @bardiademon
                public static void putAfterClickOrOnChangeEditText (Callable<Void> afterClickText , TextWatcher afterChangeText)
                {
                    setValueForm ();
                    valueForm.afterClick = afterClickText;
                    valueForm.afterOnChange = afterChangeText;
                }

                @bardiademon
                public static void putAfterClickEditTextText (Callable<Void> afterClickText)
                {
                    setValueForm ();
                    putAfterClickOrOnChangeEditText (afterClickText , valueForm.afterOnChange);
                }

                @bardiademon
                public static void putOnChangeEditText (TextWatcher afterChangeText)
                {
                    setValueForm ();
                    putAfterClickOrOnChangeEditText (valueForm.afterClick , afterChangeText);
                }

                @bardiademon
                public static void putDescriptionEditText (String titleDescription , String valueDescription)
                {
                    setValueForm ();
                    valueForm.description = true;
                    valueForm.titleDescription = titleDescription;
                    valueForm.textDescription = valueDescription;
                }

                @bardiademon
                private static void putIdEditText (String id)
                {
                    if (SearchMap.SearchMap (Form.idText , id))
                        bardiademonException.ERROR (ERROR_DUPLICATE_ID + id);
                    else
                        Form.idText.put (indexValue.indexEditText , id);
                }

                @bardiademon
                public static void setIdGroup (String id)
                {
                    if (SearchMap.SearchMap2 (Form.idEditTextGroup , id))
                        bardiademonException.ERROR (ERROR_DUPLICATE_ID + id);
                    else idGroups.put (counterIdGroups , id);
                }

                @bardiademon
                private static void setValueForm ()
                {
                    if (tools == null) tools = new ArrayList<> ();
                    if (valueForm == null) valueForm = new Values.ValueForm ();
                }

                @bardiademon
                public static void apply ()
                {
                    apply (true);
                }

                @bardiademon
                public static void apply (String id)
                {
                    apply (id , true);
                }

                @bardiademon
                public static void apply (String id , boolean applyFinal)
                {
                    apply (applyFinal);
                    if (applyFinal) putIdEditText (id);
                }

                @bardiademon
                public static void apply (boolean applyFinal)
                {
                    if (tools != null && valueForm != null)
                    {
                        if (applyFinal) indexValue.indexEditText++;
                        if (isGroup)
                        {
                            valueForms.valueForms.put (counterIdGroups , valueForm);
                            valueForm = new Values.ValueForm ();
                            if (applyFinal)
                            {
                                valueForm = valueForms;
                                Form.idEditTextGroup.put (indexValue.indexEditText , idGroups);
                                idGroups = null;
                                counterIdGroups = 0;
                            }
                            else counterIdGroups++;
                        }
                        if (applyFinal)
                        {
                            isGroup = false;
                            tools.add (TOOLS__TEXT_BOX);
                        }
                    }
                }
            }

            @bardiademon
            public abstract static class PutCheckBox
            {
                private static boolean isGroups;
                private static Values.CheckBoxForm checkBoxForms;
                private static Map<Integer, String> idGroups;
                private static int counterIdCoup;

                @bardiademon
                public static void put ()
                {
                    put (false);
                }

                @bardiademon
                public static void put (boolean isGroups)
                {
                    put (isGroups , LinearLayout.VERTICAL);
                }

                @bardiademon
                public static void put (boolean isGroups , int orientation)
                {
                    if (checkBoxForm == null)
                    {
                        if (isGroups)
                        {
                            checkBoxForms = new Values.CheckBoxForm ();
                            checkBoxForms.orientationForGroup = orientation;
                            checkBoxForms.isGroup = true;
                            checkBoxForms.checkBoxForms = new ArrayList<> ();
                            PutCheckBox.isGroups = true;
                            idGroups = new LinkedHashMap<> ();
                            counterIdCoup = 0;
                        }
                        checkBoxForm = new Values.CheckBoxForm ();
                    }
                }

                @bardiademon
                public static void setOrientation (int orientation)
                {
                    if (isGroups) checkBoxForms.orientationForGroup = orientation;
                }

                @bardiademon
                public static void put (String text , int textColor , int textStyle , int gravity , boolean isChecked , CompoundButton.OnCheckedChangeListener onCheckedChange , Callable<Void> afterOnClick)
                {
                    put ();
                    checkBoxForm.text = text;
                    checkBoxForm.textColor = textColor;
                    checkBoxForm.textStyle = textStyle;
                    checkBoxForm.gravity = gravity;
                    checkBoxForm.isChecked = isChecked;
                    checkBoxForm.onCheckedChange = onCheckedChange;
                    checkBoxForm.afterOnClick = afterOnClick;
                }

                @bardiademon
                public static void put (String text)
                {
                    put ();
                    checkBoxForm.text = text;
                }

                @bardiademon
                public static void put (String text , int textColor , int textStyle , int gravity , boolean isChecked , CompoundButton.OnCheckedChangeListener onCheckedChange)
                {
                    put ();
                    checkBoxForm.text = text;
                    checkBoxForm.textColor = textColor;
                    checkBoxForm.textStyle = textStyle;
                    checkBoxForm.gravity = gravity;
                    checkBoxForm.isChecked = isChecked;
                    checkBoxForm.onCheckedChange = onCheckedChange;
                }

                @bardiademon
                public static void put (String text , CompoundButton.OnCheckedChangeListener onCheckedChange)
                {
                    put ();
                    checkBoxForm.text = text;
                    checkBoxForm.onCheckedChange = onCheckedChange;
                }

                @bardiademon
                public static void put (String text , Callable<Void> afterOnClick)
                {
                    put ();
                    checkBoxForm.text = text;
                    checkBoxForm.afterOnClick = afterOnClick;
                }

                @bardiademon
                public static void put (String text , CompoundButton.OnCheckedChangeListener onCheckedChange , Callable<Void> afterOnClick)
                {
                    put ();
                    checkBoxForm.text = text;
                    checkBoxForm.onCheckedChange = onCheckedChange;
                    checkBoxForm.afterOnClick = afterOnClick;
                }

                @bardiademon
                public static void put (String text , int textColor , int textStyle , int gravity , boolean isChecked)
                {
                    put ();
                    checkBoxForm.text = text;
                    checkBoxForm.textColor = textColor;
                    checkBoxForm.textStyle = textStyle;
                    checkBoxForm.gravity = gravity;
                    checkBoxForm.isChecked = isChecked;
                }

                public static void put (String text , int textColor , int textStyle , int gravity)
                {
                    put ();
                    checkBoxForm.text = text;
                    checkBoxForm.textColor = textColor;
                    checkBoxForm.textStyle = textStyle;
                    checkBoxForm.gravity = gravity;
                }

                @bardiademon
                public static void put (String text , int textColor , int textStyle)
                {
                    put ();
                    checkBoxForm.text = text;
                    checkBoxForm.textColor = textColor;
                    checkBoxForm.textStyle = textStyle;
                }

                @bardiademon
                public static void setStyle (int textColor , int textStyle , int gravity)
                {
                    put ();
                    checkBoxForm.textColor = textColor;
                    checkBoxForm.textStyle = textStyle;
                    checkBoxForm.gravity = gravity;
                }

                @bardiademon
                public static void setStyle (int textColor , int textStyle)
                {
                    put ();
                    checkBoxForm.textColor = textColor;
                    checkBoxForm.textStyle = textStyle;
                }

                @bardiademon
                public static void setTextColor (int textColor)
                {
                    put ();
                    checkBoxForm.textColor = textColor;
                }

                @bardiademon
                public static void setTextStyle (int textStyle)
                {
                    put ();
                    checkBoxForm.textStyle = textStyle;
                }

                @bardiademon
                public static void setText (String text)
                {
                    put ();
                    checkBoxForm.text = text;
                }

                @bardiademon
                public static void setChecked (boolean isChecked)
                {
                    put ();
                    checkBoxForm.isChecked = isChecked;
                }

                @bardiademon
                public static void setGravity (int gravity)
                {
                    put ();
                    checkBoxForm.gravity = gravity;
                }

                @bardiademon
                private static void setId (String id)
                {
                    if (SearchMap.SearchMap (idCheckBox , id))
                        bardiademonException.ERROR (ERROR_DUPLICATE_ID + id);
                    else
                        idCheckBox.put (indexValue.indexCheckBox , id);
                }

                @bardiademon
                public static void setIdGroup (String id)
                {
                    if (SearchMap.SearchMap2 (idCheckBoxGroup , id) || SearchMap.SearchMap2 (PutCheckBox.idGroups , id))
                        bardiademonException.ERROR (ERROR_DUPLICATE_ID + id);
                    else PutCheckBox.idGroups.put (counterIdCoup++ , id);
                }

                @bardiademon
                public static void apply ()
                {
                    apply (true , true);
                }

                @bardiademon
                public static void apply (boolean applyFinal)
                {
                    apply (applyFinal , true);
                }

                @bardiademon
                public static void apply (String idCheckBox)
                {
                    apply (true , true);
                    setId (idCheckBox);
                }

                @bardiademon
                public static void apply (boolean applyFinal , boolean newCheck)
                {
                    if (applyFinal) indexValue.indexCheckBox++;
                    if (isGroups)
                    {
                        checkBoxForms.checkBoxForms.add (checkBoxForm);
                        if (newCheck && !applyFinal) checkBoxForm = new Values.CheckBoxForm ();
                        if (applyFinal)
                        {
                            checkBoxForm = checkBoxForms;
                            idCheckBoxGroup.put (indexValue.indexCheckBox , idGroups);
                        }
                    }
                    if (applyFinal)
                    {
                        int tools = (isGroups) ? TOOLS__CHECK_BOX_GROUPS : TOOLS__CHECK_BOX;
                        isGroups = false;
                        Put.tools.add (tools);
                    }
                }
            }

            @bardiademon
            public abstract static class PutRadioBtn
            {
                public static boolean isGroups;
                private static Values.RadioBtnForm radioBtnForms;
                private static Map<Integer, String> idGroups;
                private static int counterRadioBtn;

                @bardiademon
                public static void putGroups ()
                {
                    isGroups = true;
                    radioBtnForms = new Values.RadioBtnForm ();
                    radioBtnForms.radioBtnForm = new ArrayList<> ();
                    radioBtnForms.isGroup = true;
                    idGroups = new LinkedHashMap<> ();
                    counterRadioBtn = 0;
                }

                @bardiademon
                public static void put (String text)
                {
                    checkRadioBtnForm ();
                    radioBtnForm.text = text;
                }

                @bardiademon
                public static void put (String text , CompoundButton.OnCheckedChangeListener listener , Callable<Void> afterOnClick)
                {
                    checkRadioBtnForm ();
                    radioBtnForm.text = text;
                    radioBtnForm.onCheckedChangeListener = listener;
                    radioBtnForm.afterOnClick = afterOnClick;
                }

                @bardiademon
                public static void put (String text , Callable<Void> afterOnClick)
                {
                    checkRadioBtnForm ();
                    radioBtnForm.text = text;
                    radioBtnForm.afterOnClick = afterOnClick;
                }

                @bardiademon
                public static void put (String text , CompoundButton.OnCheckedChangeListener listener)
                {
                    checkRadioBtnForm ();
                    radioBtnForm.text = text;
                    radioBtnForm.onCheckedChangeListener = listener;
                }

                @bardiademon
                public static void setOnCheckedChangeListener (CompoundButton.OnCheckedChangeListener listener)
                {
                    checkRadioBtnForm ();
                    radioBtnForm.onCheckedChangeListener = listener;
                }

                @bardiademon
                public static void setAfterOnClick (Callable<Void> afterOnClick)
                {
                    checkRadioBtnForm ();
                    radioBtnForm.afterOnClick = afterOnClick;
                }

                public static void setDefaultValue (String defaultValue)
                {
                    checkRadioBtnForm ();
                    radioBtnForm.defaultValue = defaultValue;
                }

                @bardiademon
                public static void setGravity (int gravity)
                {
                    checkRadioBtnForm ();
                    radioBtnForm.gravity = gravity;
                }

                @bardiademon
                public static void setGravityGroup (int gravity)
                {
                    if (isGroups) radioBtnForms.gravity = gravity;
                }

                @bardiademon
                public static void setTextStyle (int textStyle)
                {
                    checkRadioBtnForm ();
                    radioBtnForm.textStyle = textStyle;
                }

                @bardiademon
                public static void setTextColor (int textColor)
                {
                    checkRadioBtnForm ();
                    radioBtnForm.textColor = textColor;
                }

                public static void setStyle (int gravity , int textStyle , int textColor)
                {
                    checkRadioBtnForm ();
                    radioBtnForm.gravity = gravity;
                    radioBtnForm.textStyle = textStyle;
                    radioBtnForm.textColor = textColor;
                }

                @bardiademon
                private static void checkRadioBtnForm ()
                {
                    if (radioBtnForm == null) radioBtnForm = new Values.RadioBtnForm ();
                }

                @bardiademon
                public static void setIdGroups (String id)
                {
                    if (SearchMap.SearchMap (idGroups , id) || SearchMap.SearchMap2 (idRadioBtnGroups , id))
                        bardiademonException.ERROR (ERROR_DUPLICATE_ID + id);
                    else idGroups.put (counterRadioBtn++ , id);
                }

                public static void setChecked (boolean isChecked)
                {
                    checkRadioBtnForm ();
                    radioBtnForm.isChecked = isChecked;
                }

                @bardiademon
                private static void setId (String id)
                {
                    if (SearchMap.SearchMap (idRadioBtn , id))
                        bardiademonException.ERROR (ERROR_DUPLICATE_ID + id);
                    else idRadioBtn.put (indexValue.indexRadioBtn , id);
                }

                @bardiademon
                public static void apply ()
                {
                    apply (LinearLayout.VERTICAL);
                }

                @bardiademon
                public static void apply (int orientation)
                {
                    if (isGroups) radioBtnForms.orientationForGroup = orientation;
                    apply (true);
                }

                @bardiademon
                public static void apply (String id)
                {
                    apply (id , LinearLayout.VERTICAL);
                }

                @bardiademon
                public static void apply (String id , int orientation)
                {
                    if (isGroups) radioBtnForms.orientationForGroup = orientation;
                    apply (true);
                    setId (id);
                }

                public static void setDescription (String title , String text)
                {
                    checkRadioBtnForm ();
                    boolean description = (text != null);
                    if (description)
                    {
                        if (isGroups)
                        {
                            radioBtnForms.description = true;
                            radioBtnForms.titleDescription = title;
                            radioBtnForms.textDescription = text;
                        }
                        else
                        {
                            radioBtnForm.description = true;
                            radioBtnForm.titleDescription = title;
                            radioBtnForm.textDescription = text;
                        }
                    }
                }

                @bardiademon
                public static void apply (String id , boolean applyFinal)
                {
                    apply (id , applyFinal , LinearLayout.VERTICAL);
                }

                @bardiademon
                public static void apply (String id , boolean applyFinal , int orientation)
                {
                    if (isGroups) if (applyFinal) radioBtnForms.orientationForGroup = orientation;
                    apply (applyFinal);
                    setId (id);
                }

                public static int getIndexRadioBtn ()
                {
                    return indexValue.indexRadioBtn;
                }

                @bardiademon
                public static void apply (boolean applyFinal)
                {
                    if (applyFinal) indexValue.indexRadioBtn++;
                    if (isGroups)
                    {
                        radioBtnForms.radioBtnForm.add (radioBtnForm);

                        if (applyFinal)
                        {
                            radioBtnForm = radioBtnForms;
                            radioBtnForms = null;
                            idRadioBtnGroups.put (indexValue.indexRadioBtn , idGroups);
                        }
                        else radioBtnForm = new Values.RadioBtnForm ();
                    }
                    if (applyFinal)
                    {
                        tools.add (TOOLS__RADIO_BUTTON);
                        isGroups = false;
                    }
                }
            }

            @bardiademon
            public static void apply ()
            {
                if (!error)
                {
                    if (valueForm != null)
                        Form.setValueForm (indexValue.indexEditText , valueForm);
                    else indexValue.indexEditText = -1;
                    if (buttonForm != null)
                        Form.setButtonForm (indexValue.indexButton , buttonForm);
                    else indexValue.indexButton = -1;
                    if (checkBoxForm != null)
                        Form.checkBoxForm.put (indexValue.indexCheckBox , checkBoxForm);
                    else indexValue.indexCheckBox = -1;
                    if (radioBtnForm != null)
                        Form.radioBtnForm.put (indexValue.indexRadioBtn , radioBtnForm);
                    else indexValue.indexRadioBtn = -1;
                    counter++;

                    indexValue.tools = tools;
                    Form.tools.put (counter , indexValue);
                }
                else
                    bardiademonException.ERROR (AN_ERROR_OCCURRED);

                indexValue = Values.IndexValue.increase (indexValue);
                clear ();
                tools = new ArrayList<> ();
            }

            @bardiademon
            private static void clear ()
            {
                valueForm = null;
                buttonForm = null;
                checkBoxForm = null;
                radioBtnForm = null;
                error = false;
                tools = null;
                System.gc ();
            }
        }

        @bardiademon
        private abstract static class Values
        {
            @bardiademon
            private static class ValueForm
            {
                final int defaultStyle = 0;
                int style;
                boolean isGroup = false;
                String defaultText, hintText;
                Callable<Void> afterClick;
                TextWatcher afterOnChange;
                String titleDescription, textDescription;
                boolean description;
                TextInputEditText editText;
                int inputType = InputType.TYPE_CLASS_TEXT;
                Map<Integer, ValueForm> valueForms;

                @bardiademon
                void setDefault ()
                {
                    defaultText = null;
                    hintText = null;
                    afterClick = null;
                    afterOnChange = null;
                    titleDescription = null;
                    textDescription = null;
                    description = false;
                    editText = null;
                    style = defaultStyle;
                    inputType = InputType.TYPE_CLASS_TEXT;
                }

                boolean isDefaultStyle ()
                {
                    return (style == defaultStyle);
                }
            }

            @bardiademon
            private static class ButtonForm
            {
                String text;
                Callable<Void> afterClick;
                Button button;

                boolean enable = SetPublicStyle.SetPublicStyleBottom.DEFAULT_ENABLE;
                int visibility = SetPublicStyle.SetPublicStyleBottom.DEFAULT_VISIBILITY;

                int backgroundColor = SetPublicStyle.SetPublicStyleBottom.backgroundColor,
                        textColor = SetPublicStyle.SetPublicStyleBottom.textColor,
                        textStyle = SetPublicStyle.SetPublicStyleBottom.textStyle,
                        textSize = SetPublicStyle.SetPublicStyleBottom.textSize,
                        gravityText = SetPublicStyle.SetPublicStyleBottom.gravityText,
                        radius = SetPublicStyle.SetPublicStyleBottom.radius,
                        width = SetPublicStyle.SetPublicStyleBottom.width,
                        height = SetPublicStyle.SetPublicStyleBottom.height;

                boolean setBackground = false;
                boolean setTextColor = false;
                boolean setTextSize = false;

            }

            @bardiademon
            private static class CheckBoxForm
            {
                int orientationForGroup;
                boolean isGroup;
                Callable<Void> afterOnClick;
                CompoundButton.OnCheckedChangeListener onCheckedChange;
                String text = SetPublicStyle.SetPublicStyleCheckBox.text;
                boolean isChecked = SetPublicStyle.SetPublicStyleCheckBox.isChecked;
                int textStyle = SetPublicStyle.SetPublicStyleCheckBox.textStyle, textColor = SetPublicStyle.SetPublicStyleCheckBox.textColor;
                int gravity = SetPublicStyle.SetPublicStyleCheckBox.gravity;
                String defaultValue = null;
                CheckBox checkBox;
                List<CheckBoxForm> checkBoxForms;
            }

            @bardiademon
            private static class IndexValue
            {
                public List<Integer> tools;
                public int indexButton = -1, indexEditText = -1, indexCheckBox = -1, indexRadioBtn = -1;

                @bardiademon
                public static IndexValue increase (IndexValue indexValue)
                {
                    IndexValue indexValueNew = new IndexValue ();
                    indexValueNew.indexEditText = indexValue.indexEditText;
                    indexValueNew.indexButton = indexValue.indexButton;
                    indexValueNew.indexCheckBox = indexValue.indexCheckBox;
                    indexValueNew.indexRadioBtn = indexValue.indexRadioBtn;
                    return indexValueNew;
                }
            }

            @bardiademon
            private static class RadioBtnForm
            {
                int orientationForGroup = LinearLayout.VERTICAL;
                boolean isGroup;

                String titleDescription, textDescription;
                boolean description;

                boolean isChecked = false;
                String text = Put.NULL;
                int textStyle = SetPublicStyle.SetPublicStyleRadioBtn.textStyle, textColor = SetPublicStyle.SetPublicStyleRadioBtn.textColor, gravity = SetPublicStyle.SetPublicStyleRadioBtn.gravity;
                Callable<Void> afterOnClick;
                CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
                RadioButton radioButton;
                String defaultValue = null;
                List<RadioBtnForm> radioBtnForm;
            }
        }
    }

    @bardiademon
    private static class Set
    {
        private LinearLayout mainLayout, mainLayoutFinal, finalLayoutValue, layoutScrollIfFixBtnBottom;
        private CardView cardView;
        private boolean isCardView;
        private LinearLayout.LayoutParams params;
        private ScrollView scrollView;
        private boolean fixBtnBottom;
        private SetValue.SetButtonBottom setButtonBottom;

        @bardiademon
        Set (boolean isCardView , boolean fixBtnBottom)
        {
            this.isCardView = isCardView;
            this.fixBtnBottom = fixBtnBottom;
            RunClass ();
        }

        @bardiademon
        private void RunClass ()
        {
            SetTools ();
            set ();
        }

        @bardiademon
        private void SetTools ()
        {
            params = new LinearLayout.LayoutParams (LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);
            mainLayout = new LinearLayout (G.getActivity ());
            mainLayout.setLayoutParams (params);
            mainLayout.setOrientation (LinearLayout.VERTICAL);

            if (buttonForm != null && fixBtnBottom)
            {
                LinearLayout.LayoutParams paramsLayoutScrollIfFixBtnBottom = new LinearLayout.LayoutParams (LinearLayout.LayoutParams.MATCH_PARENT , 0);
                paramsLayoutScrollIfFixBtnBottom.weight = 0.8f;
                layoutScrollIfFixBtnBottom = new LinearLayout (G.getActivity ());
                layoutScrollIfFixBtnBottom.setOrientation (LinearLayout.VERTICAL);
                layoutScrollIfFixBtnBottom.setLayoutParams (paramsLayoutScrollIfFixBtnBottom);
            }

            scrollView = new ScrollView (G.getActivity ());
            scrollView.setLayoutParams (params);
            mainLayoutFinal = new LinearLayout (G.getActivity ());
            mainLayoutFinal.setLayoutParams (params);
            mainLayoutFinal.setOrientation (LinearLayout.VERTICAL);
        }

        @bardiademon
        private void set ()
        {
            SetValue setValue;
            boolean isExists = false;
            Value.Values.IndexValue indexValue;
            for (Map.Entry<Integer, Value.Values.IndexValue> entry : tools.entrySet ())
            {
                indexValue = entry.getValue ();

                setValue = new SetValue (entry.getKey () , indexValue);
                if (setValue.valueForm != null)
                {
                    Form.valueForm.remove (indexValue.indexEditText);
                    Form.valueForm.put (indexValue.indexEditText , setValue.valueForm);
                    isExists = true;
                }
                if (setValue.checkBoxForm != null)
                {
                    Form.checkBoxForm.remove (indexValue.indexCheckBox);
                    Form.checkBoxForm.put (indexValue.indexCheckBox , setValue.checkBoxForm);
                    isExists = true;
                }
                if (setValue.radioBtnForm != null)
                {
                    Form.radioBtnForm.remove (indexValue.indexRadioBtn);
                    Form.radioBtnForm.put (indexValue.indexRadioBtn , setValue.radioBtnForm);
                    isExists = true;
                }
                if (isExists) addView (setValue.getLayoutValue ());
                isExists = false;
                setValue.clear ();
            }
            if (buttonForm != null)
            {
                setButtonBottom = new SetValue.SetButtonBottom ();
                if (!fixBtnBottom) mainLayout.addView (setButtonBottom.getMainLayoutBtn ());
            }
        }

        @bardiademon
        private void addView (ViewGroup layout)
        {
            SetLayout ();
            if (isCardView)
            {
                cardView.addView (layout);
                finalLayoutValue.addView (cardView);
            }
            else finalLayoutValue.addView (layout);

            mainLayout.addView (finalLayoutValue);
        }

        @bardiademon
        private void SetLayout ()
        {
            LinearLayout.LayoutParams paramsFinalLayoutParams = new LinearLayout.LayoutParams (LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);
            paramsFinalLayoutParams.setMargins (SetPublicStyle.LayoutValue.marginLeft , SetPublicStyle.LayoutValue.marginTop , SetPublicStyle.LayoutValue.marginRight , SetPublicStyle.LayoutValue.marginBottom);
            finalLayoutValue = new LinearLayout (G.getActivity ());
            if (isCardView)
                finalLayoutValue.setPadding (SetPublicStyle.LayoutValue.paddingLeft , SetPublicStyle.LayoutValue.paddingTop , SetPublicStyle.LayoutValue.paddingRight , SetPublicStyle.LayoutValue.paddingBottom);
            finalLayoutValue.setOrientation (LinearLayout.VERTICAL);
            finalLayoutValue.setLayoutParams (paramsFinalLayoutParams);

            if (isCardView)
            {
                LinearLayout.LayoutParams paramsCardView = new LinearLayout.LayoutParams (LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);
                int margin = Math.convertDPPX (2);
                paramsCardView.setMargins (margin , margin , margin , margin);
                cardView = new CardView (G.getActivity ());
                cardView.setLayoutParams (paramsCardView);
            }
        }

        @bardiademon
        private static class SetValue
        {
            Value.Values.ValueForm valueForm;
            Value.Values.CheckBoxForm checkBoxForm;
            Value.Values.RadioBtnForm radioBtnForm;
            private Value.Values.IndexValue indexValue;
            private SetEditText setEditText;
            private SetCheckBox setCheckBox;
            private LinearLayout layoutValue;
            private SetRadioButton setRadioButton;

            @bardiademon
            SetValue (int counter , Value.Values.IndexValue indexValue)
            {
                this.indexValue = indexValue;
                if (this.indexValue.indexEditText != -1)
                    valueForm = Form.getValueForm (this.indexValue.indexEditText);
                if (this.indexValue.indexCheckBox != -1)
                    checkBoxForm = Form.checkBoxForm.get (this.indexValue.indexCheckBox);
                if (this.indexValue.indexRadioBtn != -1)
                    radioBtnForm = Form.radioBtnForm.get (this.indexValue.indexRadioBtn);
                RunClass ();
            }

            @bardiademon
            private void RunClass ()
            {
                SetTools ();
                for (Integer t : indexValue.tools)
                {
                    switch (t)
                    {
                        case TOOLS__TEXT_BOX:
                            if (indexValue.indexEditText != -1)
                            {
                                setEditText = new SetEditText ();
                                addView (setEditText.getMainLayoutEditText ());
                                if (!valueForm.isGroup)
                                    valueForm.editText = setEditText.valueForm.editText;
                                setEditText.clearClass ();
                            }
                            break;
                        case TOOLS__CHECK_BOX:
                        case TOOLS__CHECK_BOX_GROUPS:
                            setCheckBox = new SetCheckBox (t);
                            addView (setCheckBox.getMainLayoutCheckBox ());
                            checkBoxForm.checkBox = setCheckBox.checkBox;
                            setCheckBox.clear ();
                            break;
                        case TOOLS__RADIO_BUTTON:
                            setRadioButton = new SetRadioButton ();
                            addView (setRadioButton.getMainLayout ());
                            break;
                    }
                }
            }

            @bardiademon
            private void SetTools ()
            {
                layoutValue = new LinearLayout (G.getActivity ());
                layoutValue.setOrientation (LinearLayout.VERTICAL);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams (LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutValue.setPadding (SetPublicStyle.LayoutValue.paddingLeft , SetPublicStyle.LayoutValue.paddingTop , SetPublicStyle.LayoutValue.paddingRight , SetPublicStyle.LayoutValue.paddingBottom);
                layoutValue.setLayoutParams (params);
            }

            @bardiademon
            private class SetEditText
            {
                private LinearLayout mainLayoutEditText, layoutEditText, layoutDescription;
                private TextInputLayout textInputLayout;
                private Value.Values.ValueForm valueForm;

                @bardiademon
                SetEditText ()
                {
                    setMainLayout ();
                    if (SetValue.this.valueForm.isGroup)
                    {
                        Map<Integer, Value.Values.ValueForm> valueForms = SetValue.this.valueForm.valueForms;
                        for (Map.Entry<Integer, Value.Values.ValueForm> entry : valueForms.entrySet ())
                        {
                            this.valueForm = entry.getValue ();
                            RunClass ();
                            SetValue.this.valueForm.valueForms.put (entry.getKey () , valueForm);
                            clear ();
                        }
                    }
                    else
                    {
                        valueForm = SetValue.this.valueForm;
                        RunClass ();
                        SetValue.this.valueForm = valueForm;
                    }
                }

                @bardiademon
                private void RunClass ()
                {
                    SetTools ();
                    setEditText ();
                    Description ();
                    SetOnClick ();
                    addView ();
                }

                @bardiademon
                private void setMainLayout ()
                {
                    mainLayoutEditText = new LinearLayout (G.getActivity ());
                    mainLayoutEditText.setOrientation (LinearLayout.VERTICAL);
                    LinearLayout.LayoutParams paramsMainLayout = new LinearLayout.LayoutParams (LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);
                    paramsMainLayout.setMargins (SetPublicStyle.SetPublicStyleEditText.marginLeft , SetPublicStyle.SetPublicStyleEditText.marginTop , SetPublicStyle.SetPublicStyleEditText.marginRight , SetPublicStyle.SetPublicStyleEditText.marginBottom);
                    mainLayoutEditText.setLayoutParams (paramsMainLayout);
                    mainLayoutEditText.setOrientation (LinearLayout.VERTICAL);
                }

                @bardiademon
                private void SetTools ()
                {
                    layoutEditText = new LinearLayout (G.getActivity ());
                    layoutDescription = new LinearLayout (G.getActivity ());
                    valueForm.editText = new TextInputEditText (G.getActivity ());

                    if (valueForm.isDefaultStyle ())
                        textInputLayout = new TextInputLayout (G.getActivity ());
                    else
                    {
                        System.out.println ("textInputLayout = new TextInputLayout (G.getActivity () , null , valueForm.style);");
                        textInputLayout = new TextInputLayout (G.getActivity () , null , valueForm.style);
                    }

                    layoutEditText.setOrientation (LinearLayout.VERTICAL);
                    layoutDescription.setOrientation (LinearLayout.VERTICAL);

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams (LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutEditText.setLayoutParams (params);
                    layoutDescription.setLayoutParams (params);
                    valueForm.editText.setLayoutParams (params);
                    textInputLayout.setLayoutParams (params);
                }

                private void setEditText ()
                {
                    valueForm.editText.setText (valueForm.defaultText);
                    valueForm.editText.setHint (valueForm.hintText);
                    valueForm.editText.setTypeface (Typeface.createFromAsset (G.getAssetsManager () , Default.DEFAULT_FONT));
                    valueForm.editText.setInputType (valueForm.inputType);
                }

                @bardiademon
                private void SetOnClick ()
                {
                    if (valueForm.afterClick != null)
                    {
                        valueForm.editText.setOnClickListener (v ->
                        {
                            try
                            {
                                valueForm.afterClick.call ();
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace ();
                            }
                        });
                    }
                    if (valueForm.afterOnChange != null)
                        valueForm.editText.addTextChangedListener (valueForm.afterOnChange);
                }

                @bardiademon
                private void Description ()
                {
                    if (valueForm.description)
                    {
                        Description description = new Description (valueForm.titleDescription , valueForm.textDescription , layoutDescription);
                        description.setBackgroundColor (SetPublicStyle.SetPublicStyleDescription.Background);
                        description.setTextColorTitle (SetPublicStyle.SetPublicStyleDescription.TextColorTitle);
                        description.setTextColorMessage (SetPublicStyle.SetPublicStyleDescription.TextColorMessage);
                        description.set ();
                    }
                }

                @bardiademon
                private void addView ()
                {
                    textInputLayout.addView (valueForm.editText);
                    layoutEditText.addView (textInputLayout);
                    mainLayoutEditText.addView (layoutDescription , 0);
                    mainLayoutEditText.addView (layoutEditText , 1);
                }

                LinearLayout getMainLayoutEditText ()
                {
                    return mainLayoutEditText;
                }

                @bardiademon
                private void clear ()
                {
                    layoutEditText = null;
                    layoutDescription = null;
                    textInputLayout = null;
                }

                @bardiademon
                void clearClass ()
                {
                    mainLayoutEditText = null;
                    layoutEditText = null;
                    layoutDescription = null;
                    textInputLayout = null;
                }
            }

            @bardiademon
            private class SetCheckBox
            {
                private LinearLayout mainLayoutCheckBox;
                private SetCheckBoxGroup setCheckBoxGroup;
                private SetOneCheckBox setOneCheckBox;
                private CheckBox checkBox;

                @bardiademon
                SetCheckBox (int tools)
                {
                    SetTools ();
                    switch (tools)
                    {
                        case TOOLS__CHECK_BOX:
                            setOneCheckBox = new SetOneCheckBox (checkBoxForm);
                            break;
                        case TOOLS__CHECK_BOX_GROUPS:
                            if (checkBoxForm.isGroup) setCheckBoxGroup = new SetCheckBoxGroup ();
                            break;
                    }
                    setFinal ();
                }

                @bardiademon
                private void SetTools ()
                {
                    mainLayoutCheckBox = new LinearLayout (G.getActivity ());
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams (LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.setMargins (SetPublicStyle.SetPublicStyleCheckBox.marginMainLayoutLeft , SetPublicStyle.SetPublicStyleCheckBox.marginTop , SetPublicStyle.SetPublicStyleCheckBox.marginRight , SetPublicStyle.SetPublicStyleCheckBox.marginBottom);
                    mainLayoutCheckBox.setLayoutParams (params);
                    mainLayoutCheckBox.setGravity (checkBoxForm.gravity);
                    mainLayoutCheckBox.setOrientation (LinearLayout.VERTICAL);
                }

                @bardiademon
                private class SetCheckBoxGroup
                {
                    private LinearLayout layoutScroll, mainLayoutGroupCheckBox;
                    private HorizontalScrollView horizontalScrollView;
                    private boolean isHorizontal;

                    {
                        isHorizontal = (checkBoxForm.orientationForGroup == LinearLayout.HORIZONTAL);
                        SetTools ();
                        set ();
                    }

                    @bardiademon
                    private void SetTools ()
                    {
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams (LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);
                        if (isHorizontal)
                        {
                            horizontalScrollView = new HorizontalScrollView (G.getActivity ());
                            layoutScroll = new LinearLayout (G.getActivity ());
                            layoutScroll.setOrientation (LinearLayout.HORIZONTAL);
                            horizontalScrollView.setLayoutParams (params);
                            layoutScroll.setGravity (SetPublicStyle.SetPublicStyleCheckBox.gravityGroup);
                            layoutScroll.setLayoutParams (params);
                        }
                        mainLayoutGroupCheckBox = new LinearLayout (G.getActivity ());
                        mainLayoutGroupCheckBox.setOrientation (checkBoxForm.orientationForGroup);
                        mainLayoutGroupCheckBox.setGravity (SetPublicStyle.SetPublicStyleCheckBox.gravityGroup);
                        mainLayoutGroupCheckBox.setLayoutParams (params);
                    }

                    @bardiademon
                    private void set ()
                    {
                        for (int i = 0, len = checkBoxForm.checkBoxForms.size (); i < len; i++)
                        {
                            new SetOneCheckBox (checkBoxForm.checkBoxForms.get (i));
                            addView ();
                            checkBoxForm.checkBoxForms.get (i).checkBox = checkBox;
                        }
                        addViewFinal ();
                    }

                    @bardiademon
                    private void addView ()
                    {
                        if (isHorizontal)
                            layoutScroll.addView (checkBox);
                        else mainLayoutGroupCheckBox.addView (checkBox);
                    }

                    @bardiademon
                    private void addViewFinal ()
                    {
                        if (isHorizontal)
                        {
                            horizontalScrollView.addView (layoutScroll);
                            mainLayoutGroupCheckBox.addView (horizontalScrollView);
                        }
                    }

                    LinearLayout getMainLayoutGroupCheckBox ()
                    {
                        return mainLayoutGroupCheckBox;
                    }

                    @bardiademon
                    void clear ()
                    {
                        layoutScroll = null;
                        mainLayoutGroupCheckBox = null;
                        horizontalScrollView = null;
                        isHorizontal = false;
                    }
                }

                @bardiademon
                private class SetOneCheckBox
                {
                    private Value.Values.CheckBoxForm checkBoxForm;

                    @bardiademon
                    SetOneCheckBox (Value.Values.CheckBoxForm checkBoxForm)
                    {
                        this.checkBoxForm = checkBoxForm;
                        this.checkBoxForm = setCheckBoxForm ();
                        SetOnClick ();
                    }

                    @bardiademon
                    private Value.Values.CheckBoxForm setCheckBoxForm ()
                    {
                        checkBox = new CheckBox (G.getActivity ());
                        LinearLayout.LayoutParams paramsCheckBox = new LinearLayout.LayoutParams (LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT);
                        paramsCheckBox.setMargins (SetPublicStyle.SetPublicStyleCheckBox.marginLeft , SetPublicStyle.SetPublicStyleCheckBox.marginTop , SetPublicStyle.SetPublicStyleCheckBox.marginRight , SetPublicStyle.SetPublicStyleCheckBox.marginBottom);
                        checkBox.setLayoutParams (paramsCheckBox);
                        checkBox.setText (checkBoxForm.text);
                        checkBox.setTextColor (checkBoxForm.textColor);
                        checkBox.setChecked (checkBoxForm.isChecked);
                        checkBox.setTypeface (Typeface.createFromAsset (G.getAssetsManager () , "iranian_sans.ttf") , checkBoxForm.textStyle);
                        checkBoxForm.checkBox = checkBox;
                        return checkBoxForm;
                    }

                    @bardiademon
                    public void SetOnClick ()
                    {
                        if (checkBoxForm.afterOnClick != null)
                        {
                            checkBox.setOnClickListener (v ->
                            {
                                try
                                {
                                    checkBoxForm.afterOnClick.call ();
                                }
                                catch (Exception ignored)
                                {
                                }
                            });
                        }
                        checkBox.setOnCheckedChangeListener (checkBoxForm.onCheckedChange);
                    }

                }

                @bardiademon
                private void setFinal ()
                {
                    if (setCheckBoxGroup != null)
                    {
                        mainLayoutCheckBox.addView (setCheckBoxGroup.getMainLayoutGroupCheckBox ());
                        mainLayoutCheckBox.setGravity (SetPublicStyle.SetPublicStyleCheckBox.gravityGroup);
                        setCheckBoxGroup.clear ();
                        setCheckBoxGroup = null;
                    }
                    else
                    {
                        mainLayoutCheckBox.addView (checkBox);
                        Form.checkBoxForm.put (indexValue.indexCheckBox , checkBoxForm);
                    }

                }

                LinearLayout getMainLayoutCheckBox ()
                {
                    return mainLayoutCheckBox;
                }

                @bardiademon
                void clear ()
                {
                    mainLayoutCheckBox = null;
                    setCheckBoxGroup = null;
                    checkBox = null;
                }
            }

            @bardiademon
            private class SetRadioButton
            {
                private SetRadioButtonGroup setRadioButtonGroup;
                private LinearLayout layoutRadioBtn;
                private LinearLayout layoutDRadioBtn, layoutDescription, layoutRadioBtnAndDescription;
                private LinearLayout layoutScroll;
                private HorizontalScrollView horizontalScrollView;
                private boolean isHorizontal;

                SetRadioButton ()
                {
                    isHorizontal = (radioBtnForm.orientationForGroup == LinearLayout.HORIZONTAL);
                    SetTools ();
                    if (radioBtnForm.isGroup)
                    {
                        setRadioButtonGroup = new SetRadioButtonGroup ();
                        layoutRadioBtn.addView (setRadioButtonGroup.getLayoutRadioBtn ());
                        if (isHorizontal)
                        {
                            horizontalScrollView.addView (layoutRadioBtn);
                            layoutScroll.addView (horizontalScrollView);
                        }
                        setRadioButtonGroup.clear ();
                    }
                    else
                    {
                        new SetOneRadioButton (radioBtnForm);
                        if (setLayoutDescription (false , null))
                            layoutRadioBtn.addView (layoutRadioBtnAndDescription);
                        else layoutRadioBtn.addView (radioBtnForm.radioButton);
                    }
                }

                private boolean setLayoutDescription (boolean isGroup , RadioGroup radioGroup)
                {
                    if (radioBtnForm.description)
                    {
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams (LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);
                        layoutRadioBtnAndDescription = new LinearLayout (G.getActivity ());
                        layoutRadioBtnAndDescription.setOrientation (LinearLayout.VERTICAL);
                        layoutRadioBtnAndDescription.setLayoutParams (params);

                        layoutDRadioBtn = new LinearLayout (G.getActivity ());
                        layoutDRadioBtn.setOrientation (LinearLayout.VERTICAL);
                        layoutDRadioBtn.setLayoutParams (params);

                        layoutDescription = new LinearLayout (G.getActivity ());
                        layoutDescription.setOrientation (LinearLayout.VERTICAL);
                        layoutDescription.setLayoutParams (params);

                        Description ();
                        if (!isGroup) layoutDRadioBtn.addView (radioBtnForm.radioButton);
                        else layoutDRadioBtn.addView (radioGroup);

                        layoutRadioBtnAndDescription.addView (layoutDescription , 0);
                        layoutRadioBtnAndDescription.addView (layoutDRadioBtn , 1);
                    }
                    return radioBtnForm.description;
                }

                private void Description ()
                {
                    Description description = new Description (radioBtnForm.titleDescription , radioBtnForm.textDescription , layoutDescription);
                    description.setBackgroundColor (SetPublicStyle.SetPublicStyleDescription.Background);
                    description.setTextColorTitle (SetPublicStyle.SetPublicStyleDescription.TextColorTitle);
                    description.setTextColorMessage (SetPublicStyle.SetPublicStyleDescription.TextColorMessage);
                    description.set ();
                }

                private void SetTools ()
                {
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams (LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutRadioBtn = new LinearLayout (G.getActivity ());
                    layoutRadioBtn.setGravity (radioBtnForm.gravity);
                    if (radioBtnForm.isGroup)
                        layoutRadioBtn.setOrientation (radioBtnForm.orientationForGroup);

                    layoutRadioBtn.setLayoutParams (params);

                    if (radioBtnForm.isGroup && isHorizontal)
                    {
                        LinearLayout.LayoutParams paramsScroll = new LinearLayout.LayoutParams (LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);
                        paramsScroll.setMargins (SetPublicStyle.SetPublicStyleRadioBtn.marginLeft , SetPublicStyle.SetPublicStyleRadioBtn.marginTop , SetPublicStyle.SetPublicStyleRadioBtn.marginRight , SetPublicStyle.SetPublicStyleRadioBtn.marginBottom);
                        layoutScroll = new LinearLayout (G.getActivity ());
                        layoutScroll.setLayoutParams (paramsScroll);
                        layoutScroll.setGravity (radioBtnForm.gravity);
                        horizontalScrollView = new HorizontalScrollView (G.getActivity ());
                        LinearLayout.LayoutParams paramsHorizontalScrollView = new LinearLayout.LayoutParams (LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);
                        horizontalScrollView.setLayoutParams (paramsHorizontalScrollView);
                    }
                }


                private class SetRadioButtonGroup
                {
                    private LinearLayout layoutRadioBtn;
                    private RadioGroup radioGroup;

                    {
                        SetTools ();
                        set ();
                    }

                    private void SetTools ()
                    {
                        layoutRadioBtn = new LinearLayout (G.getActivity ());
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams (LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);
                        layoutRadioBtn.setLayoutParams (params);
                        layoutRadioBtn.setOrientation (radioBtnForm.orientationForGroup);

                        layoutRadioBtn.setGravity (radioBtnForm.gravity);

                        radioGroup = new RadioGroup (G.getActivity ());
                        LinearLayout.LayoutParams paramsRadioGroup = new LinearLayout.LayoutParams (LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT);
                        radioGroup.setLayoutParams (paramsRadioGroup);
                        radioGroup.setOrientation (radioBtnForm.orientationForGroup);
                    }

                    private void set ()
                    {
                        List<Value.Values.RadioBtnForm> radioBtnForm1 = radioBtnForm.radioBtnForm;
                        for (int i = 0; i < radioBtnForm1.size (); i++)
                        {
                            Value.Values.RadioBtnForm radioBtnForm = radioBtnForm1.get (i);
                            new SetOneRadioButton (radioBtnForm);
                            addView (radioBtnForm.radioButton);
                            SetValue.this.radioBtnForm.radioBtnForm.set (i , radioBtnForm);

                        }
                        if (setLayoutDescription (true , radioGroup))
                            layoutRadioBtn.addView (layoutRadioBtnAndDescription);
                        else
                            layoutRadioBtn.addView (radioGroup);
                    }

                    private void addView (RadioButton radioButton)
                    {
                        radioGroup.addView (radioButton);
                    }

                    LinearLayout getLayoutRadioBtn ()
                    {
                        return layoutRadioBtn;
                    }

                    void clear ()
                    {
                        layoutRadioBtn = null;
                    }
                }

                private class SetOneRadioButton
                {
                    private Value.Values.RadioBtnForm radioBtnForm;

                    SetOneRadioButton (Value.Values.RadioBtnForm radioBtnForm)
                    {
                        this.radioBtnForm = radioBtnForm;
                        SetTools ();
                        setValue ();
                        setOnClickAndOnChangeChecked ();
                    }

                    private void SetTools ()
                    {
                        radioBtnForm.radioButton = new RadioButton (G.getActivity ());
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams (LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT);
                        params.setMargins (SetPublicStyle.SetPublicStyleRadioBtn.marginLeft , SetPublicStyle.SetPublicStyleRadioBtn.marginTop , SetPublicStyle.SetPublicStyleRadioBtn.marginRight , SetPublicStyle.SetPublicStyleRadioBtn.marginBottom);
                        radioBtnForm.radioButton.setLayoutParams (params);
                    }

                    private void setValue ()
                    {
                        radioBtnForm.radioButton.setText (radioBtnForm.text);
                        radioBtnForm.radioButton.setChecked (radioBtnForm.isChecked);
                        radioBtnForm.radioButton.setTextColor (radioBtnForm.textColor);
                        radioBtnForm.radioButton.setTypeface (Typeface.createFromAsset (G.getAssetsManager () , "iranian_sans.ttf") , radioBtnForm.textStyle);
                    }

                    private void setOnClickAndOnChangeChecked ()
                    {
                        if (radioBtnForm.afterOnClick != null)
                        {
                            radioBtnForm.radioButton.setOnClickListener (v ->
                            {
                                try
                                {
                                    radioBtnForm.afterOnClick.call ();
                                }
                                catch (Exception ignored)
                                {
                                }
                            });
                        }
                        radioBtnForm.radioButton.setOnCheckedChangeListener (radioBtnForm.onCheckedChangeListener);
                    }
                }

                LinearLayout getMainLayout ()
                {
                    if (radioBtnForm.isGroup)
                    {
                        if (isHorizontal) return layoutScroll;
                        else return layoutRadioBtn;
                    }
                    else return layoutRadioBtn;
                }

                void clear ()
                {
                    setRadioButtonGroup = null;
                    layoutRadioBtn = null;
                    layoutScroll = null;
                    horizontalScrollView = null;
                    isHorizontal = false;
                }
            }

            private static class SetButtonBottom
            {
                private float weight = 0;
                private LinearLayout mainLayoutBtn;
                private Value.Values.ButtonForm buttonForm;

                SetButtonBottom ()
                {
                    SetTools ();
                    set ();
                }

                private void SetTools ()
                {
                    mainLayoutBtn = new LinearLayout (G.getActivity ());
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams (LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);
                    mainLayoutBtn.setLayoutParams (params);
                    mainLayoutBtn.setGravity (Gravity.CENTER);
                    mainLayoutBtn.setOrientation (LinearLayout.VERTICAL);
                }

                private void set ()
                {
                    if (buttonDisplayMode == Form.BUTTON_DISPLAY_MODE__ONE_BY_ONE || Form.buttonForm.size () == 1)
                    {
                        for (Map.Entry<Integer, Value.Values.ButtonForm> entry : Form.buttonForm.entrySet ())
                        {
                            buttonForm = entry.getValue ();
                            new SetOneButton ();
                            addViewOneToOne ();
                            Form.buttonForm.put (entry.getKey () , buttonForm);
                        }
                    }
                    else
                    {
                        SetButton setButton = new SetButton ();
                        mainLayoutBtn.addView (setButton.getLayoutAllButton ());
                        setButton.clear ();
                    }
                }

                private void addViewOneToOne ()
                {
                    mainLayoutBtn.addView (buttonForm.button);
                }

                private class SetOneButton
                {

                    SetOneButton ()
                    {
                        SetTools ();
                        setOnClick (buttonForm.button , buttonForm.afterClick);
                    }

                    private void SetTools ()
                    {
                        buttonForm.button = new Button (G.getActivity () , null , android.R.attr.buttonBarButtonStyle);
                        buttonForm.button.setText (buttonForm.text);
                        if (buttonForm.setTextColor)
                            buttonForm.button.setTextColor (buttonForm.textColor);

                        if (buttonForm.setBackground)
                        {
                            ShapeDrawable shapeDrawable = GetShapeDrawable.get (buttonForm.backgroundColor , new float[] {buttonForm.radius , buttonForm.radius , buttonForm.radius , buttonForm.radius , buttonForm.radius , buttonForm.radius , buttonForm.radius , buttonForm.radius});

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                                buttonForm.button.setBackground (shapeDrawable);
                            else
                                buttonForm.button.setBackgroundDrawable (shapeDrawable);
                        }

                        buttonForm.button.setGravity (buttonForm.gravityText | Gravity.CENTER);
                        buttonForm.button.setEnabled (buttonForm.enable);
                        buttonForm.button.setVisibility (buttonForm.visibility);
                        if (buttonForm.setTextSize)
                            buttonForm.button.setTextSize (buttonForm.textSize);
                        buttonForm.button.setTypeface (Typeface.createFromAsset (G.getAssetsManager () , "iranian_sans.ttf") , buttonForm.textStyle);

                        LinearLayout.LayoutParams params;
                        if (buttonDisplayMode != BUTTON_DISPLAY_MODE__ONE_BY_ONE)
                        {
                            if (buttonDisplayMode == BUTTON_DISPLAY_MODE__TWO_BY_TWO || Form.buttonForm.size () == 2)
                                weight = 0.5f;
                            else
                                weight = 0.3f;

                            params = new LinearLayout.LayoutParams (0 , buttonForm.height);
                            params.weight = weight;
                        }
                        else
                            params = new LinearLayout.LayoutParams (buttonForm.width , buttonForm.height);

                        params.setMargins (SetPublicStyle.SetPublicStyleBottom.marginLeft , SetPublicStyle.SetPublicStyleBottom.marginTop , SetPublicStyle.SetPublicStyleBottom.marginRight , SetPublicStyle.SetPublicStyleBottom.marginBottom);

                        buttonForm.button.setLayoutParams (params);
                    }

                    private void setOnClick (Button button , Callable<Void> callable)
                    {
                        if (callable != null)
                        {
                            button.setOnClickListener ((View v) ->
                            {
                                try
                                {
                                    callable.call ();
                                }
                                catch (Exception ignored)
                                {
                                }
                            });
                        }
                    }
                }

                private class SetButton
                {
                    private LinearLayout layoutHorizontalButton, layoutAllButton;

                    SetButton ()
                    {
                        SetTools ();
                        set ();
                    }

                    private void SetTools ()
                    {
                        layoutAllButton = new LinearLayout (G.getActivity ());
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams (LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);
                        layoutAllButton.setLayoutParams (params);
                        layoutAllButton.setOrientation (LinearLayout.VERTICAL);
                    }

                    private void set ()
                    {
                        int counterBtn = 0;
                        setLayoutHorizontal ();
                        for (Map.Entry<Integer, Value.Values.ButtonForm> entry : Form.buttonForm.entrySet ())
                        {
                            if (counterBtn == buttonDisplayMode)
                            {
                                counterBtn = 0;
                                addView ();
                                setLayoutHorizontal ();
                            }
                            buttonForm = entry.getValue ();
                            new SetOneButton ();
                            Form.buttonForm.put (entry.getKey () , buttonForm);
                            addViewHorizontalButton ();
                            counterBtn++;
                        }
                        addView ();
                    }

                    private void setLayoutHorizontal ()
                    {
                        layoutHorizontalButton = new LinearLayout (G.getActivity ());
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams (LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);
                        layoutHorizontalButton.setLayoutParams (params);
                        layoutHorizontalButton.setOrientation (LinearLayout.HORIZONTAL);
                    }

                    private void addViewHorizontalButton ()
                    {
                        layoutHorizontalButton.addView (buttonForm.button);
                    }

                    private void addView ()
                    {
                        layoutAllButton.addView (layoutHorizontalButton);
                    }

                    LinearLayout getLayoutAllButton ()
                    {
                        return layoutAllButton;
                    }

                    void clear ()
                    {
                        layoutHorizontalButton = null;
                        layoutAllButton = null;
                    }
                }

                LinearLayout getMainLayoutBtn ()
                {
                    return mainLayoutBtn;
                }
            }

            @bardiademon
            private void addView (ViewGroup layout)
            {
                layoutValue.addView (layout);
            }

            @bardiademon
            LinearLayout getLayoutValue ()
            {
                return layoutValue;
            }

            @bardiademon
            void clear ()
            {
                valueForm = null;
                checkBoxForm = null;
                indexValue = null;
                setEditText = null;
                setCheckBox = null;
                layoutValue = null;
            }
        }

        @bardiademon
        LinearLayout getMainLayout ()
        {
            scrollView.addView (mainLayout);
            if (buttonForm != null && fixBtnBottom)
            {
                layoutScrollIfFixBtnBottom.addView (scrollView);
                mainLayoutFinal.addView (layoutScrollIfFixBtnBottom);
                mainLayoutFinal.addView (setButtonBottom.getMainLayoutBtn ());
            }
            else mainLayoutFinal.addView (scrollView);
            return mainLayoutFinal;
        }

    }

    public static abstract class SetPublicStyle
    {
        public static final int SET__MP_ALL = 0, SET__MP_LEFT = 1, SET__MP_TOP = 2, SET__MP_RIGHT = 3, SET__MP_BOTTOM = 4;

        @bardiademon
        public static abstract class SetPublicStyleDescription
        {
            public static final int DEFAULT_BACKGROUND = Color.WHITE;
            public static final int DEFAULT_TEXT_COLOR = Color.BLACK;

            public static int Background = DEFAULT_BACKGROUND;
            public static int TextColorTitle = DEFAULT_TEXT_COLOR;
            public static int TextColorMessage = DEFAULT_TEXT_COLOR;

            public static void SetDefault ()
            {
                Background = DEFAULT_BACKGROUND;
                TextColorTitle = DEFAULT_TEXT_COLOR;
                TextColorMessage = DEFAULT_TEXT_COLOR;
            }
        }

        @bardiademon
        public static abstract class SetPublicStyleCheckBox
        {
            public static final int DEFAULT_GRAVITY_GROUP = Gravity.CENTER_HORIZONTAL;
            public static final int DEFAULT_TEXT_STYLE = Typeface.NORMAL, DEFAULT_TEXT_COLOR = GetValues.getColor ("COLOR_BLACK");
            public static final boolean DEFAULT_IS_CHECKED = false;
            public static final String DEFAULT_TEXT = Value.Put.NULL;
            public static final int DEFAULT_GRAVITY = Gravity.END;
            public static final int DEFAULT_MARGIN_MAIN_LAYOUT = 0, DEFAULT_MARGIN = 0;
            public static int marginLeft, marginRight, marginTop, marginBottom;
            public static int marginMainLayoutLeft, marginMainLayoutRight, marginMainLayoutTop, marginMainLayoutBottom;
            public static int gravityGroup;


            @bardiademon
            public static void margin (int which , int margin)
            {
                margin = Math.convertDPPX (margin);
                switch (which)
                {
                    case SET__MP_TOP:
                        marginTop = margin;
                        break;
                    case SET__MP_RIGHT:
                        marginRight = margin;
                        break;
                    case SET__MP_BOTTOM:
                        marginBottom = margin;
                        break;
                    case SET__MP_LEFT:
                        marginLeft = margin;
                        break;
                    case SET__MP_ALL:
                        margin (SET__MP_LEFT , margin);
                        margin (SET__MP_TOP , margin);
                        margin (SET__MP_RIGHT , margin);
                        margin (SET__MP_BOTTOM , margin);
                        break;
                }
            }

            @bardiademon
            public static void marginMainLayout (int which , int margin)
            {
                margin = Math.convertDPPX (margin);
                switch (which)
                {
                    case SET__MP_TOP:
                        marginMainLayoutTop = margin;
                        break;
                    case SET__MP_RIGHT:
                        marginMainLayoutRight = margin;
                        break;
                    case SET__MP_BOTTOM:
                        marginMainLayoutBottom = margin;
                        break;
                    case SET__MP_LEFT:
                        marginMainLayoutLeft = margin;
                        break;
                    case SET__MP_ALL:
                        margin (SET__MP_LEFT , margin);
                        margin (SET__MP_TOP , margin);
                        margin (SET__MP_RIGHT , margin);
                        margin (SET__MP_BOTTOM , margin);
                        break;
                }
            }

            public static int textStyle, textColor, gravity;
            public static boolean isChecked;
            public static String text;

            @bardiademon
            public static void setDefault ()
            {
                text = DEFAULT_TEXT;
                textColor = DEFAULT_TEXT_COLOR;
                textStyle = DEFAULT_TEXT_STYLE;
                isChecked = DEFAULT_IS_CHECKED;
                gravity = DEFAULT_GRAVITY;
                gravityGroup = DEFAULT_GRAVITY_GROUP;
                marginMainLayout (SET__MP_ALL , DEFAULT_MARGIN_MAIN_LAYOUT);
                margin (SET__MP_ALL , DEFAULT_MARGIN);
            }

            @bardiademon
            public static void clear ()
            {
                text = null;
                textColor = 0;
                textStyle = 0;
                isChecked = false;
                System.gc ();
            }
        }

        @bardiademon
        public static abstract class SetPublicStyleEditText
        {
            public static final int DEFAULT_MARGIN = 0, DEFAULT_PADDING = 0;

            public static int marginLeft, marginRight, marginTop, marginBottom;
            public static int paddingLeft, paddingRight, paddingTop, paddingBottom;

            @bardiademon
            public static void margin (int which , int margin)
            {
                margin = Math.convertDPPX (margin);
                switch (which)
                {
                    case SET__MP_TOP:
                        marginTop = margin;
                        break;
                    case SET__MP_RIGHT:
                        marginRight = margin;
                        break;
                    case SET__MP_BOTTOM:
                        marginBottom = margin;
                        break;
                    case SET__MP_LEFT:
                        marginLeft = margin;
                        break;
                    case SET__MP_ALL:
                        margin (SET__MP_LEFT , margin);
                        margin (SET__MP_TOP , margin);
                        margin (SET__MP_RIGHT , margin);
                        margin (SET__MP_BOTTOM , margin);
                        break;
                }
            }

            @bardiademon
            public static void padding (int which , int padding)
            {
                padding = Math.convertDPPX (padding);
                switch (which)
                {
                    case SET__MP_TOP:
                        paddingTop = padding;
                        break;
                    case SET__MP_RIGHT:
                        paddingRight = padding;
                        break;
                    case SET__MP_BOTTOM:
                        paddingBottom = padding;
                        break;
                    case SET__MP_LEFT:
                        paddingLeft = padding;
                        break;
                    case SET__MP_ALL:
                        padding (SET__MP_LEFT , padding);
                        padding (SET__MP_TOP , padding);
                        padding (SET__MP_RIGHT , padding);
                        padding (SET__MP_BOTTOM , padding);
                        break;
                }
            }

            @bardiademon
            public static void setDefault ()
            {
                margin (SET__MP_ALL , DEFAULT_MARGIN);
                padding (SET__MP_ALL , DEFAULT_PADDING);
            }
        }

        @bardiademon
        public static abstract class SetPublicStyleBottom
        {
            public static final int
                    DEFAULT_BACKGROUND = GetValues.getColor ("COLOR_WHITE"),
                    DEFAULT_TEXT_COLOR = GetValues.getColor ("COLOR_BLACK"),
                    DEFAULT_TEXT_STYLE = Typeface.NORMAL,
                    DEFAULT_TEXT_SIZE = -1,
                    DEFAULT_GRAVITY_TEXT = Gravity.CENTER,
                    DEFAULT_RADIUS = Math.convertDPPX (2),
                    DEFAULT_WIDTH = LinearLayout.LayoutParams.WRAP_CONTENT, DEFAULT_HEIGHT = LinearLayout.LayoutParams.WRAP_CONTENT,
                    DEFAULT_VISIBILITY = View.VISIBLE;

            public static final int DEFAULT_MARGIN = 0;

            public static final boolean DEFAULT_ENABLE = true;

            public static int marginLeft, marginRight, marginTop, marginBottom, width, height;

            public static int visibility;
            public static boolean enable;

            @bardiademon
            public static void margin (int which , int margin)
            {
                margin = Math.convertDPPX (margin);
                switch (which)
                {
                    case SET__MP_TOP:
                        marginTop = margin;
                        break;
                    case SET__MP_RIGHT:
                        marginRight = margin;
                        break;
                    case SET__MP_BOTTOM:
                        marginBottom = margin;
                        break;
                    case SET__MP_LEFT:
                        marginLeft = margin;
                        break;
                    case SET__MP_ALL:
                        margin (SET__MP_LEFT , margin);
                        margin (SET__MP_TOP , margin);
                        margin (SET__MP_RIGHT , margin);
                        margin (SET__MP_BOTTOM , margin);
                        break;
                }
            }

            public static int backgroundColor, textColor, textStyle, textSize, gravityText, radius;

            @bardiademon
            public static void setDefault ()
            {
                backgroundColor = DEFAULT_BACKGROUND;
                textColor = DEFAULT_TEXT_COLOR;
                textStyle = DEFAULT_TEXT_STYLE;
                textSize = DEFAULT_TEXT_SIZE;
                gravityText = DEFAULT_GRAVITY_TEXT;
                radius = DEFAULT_RADIUS;
                width = DEFAULT_WIDTH;
                height = DEFAULT_HEIGHT;
                visibility = DEFAULT_VISIBILITY;
                enable = DEFAULT_ENABLE;
                margin (SetPublicStyle.SET__MP_ALL , DEFAULT_MARGIN);
            }

            @bardiademon
            public static void clear ()
            {
                backgroundColor = 0;
                textColor = 0;
                textStyle = 0;
                textSize = 0;
                gravityText = 0;
                radius = 0;
                System.gc ();
            }
        }

        @bardiademon
        public static abstract class LayoutValue
        {
            public static final int DEFAULT_MARGIN = 0, DEFAULT_PADDING = 0;

            public static int marginLeft, marginRight, marginTop, marginBottom;
            public static int paddingLeft, paddingRight, paddingTop, paddingBottom;

            @bardiademon
            public static void margin (int which , int margin)
            {
                margin = Math.convertDPPX (margin);
                switch (which)
                {
                    case SET__MP_TOP:
                        marginTop = margin;
                        break;
                    case SET__MP_RIGHT:
                        marginRight = margin;
                        break;
                    case SET__MP_BOTTOM:
                        marginBottom = margin;
                        break;
                    case SET__MP_LEFT:
                        marginLeft = margin;
                        break;
                    case SET__MP_ALL:
                        margin (SET__MP_LEFT , margin);
                        margin (SET__MP_TOP , margin);
                        margin (SET__MP_RIGHT , margin);
                        margin (SET__MP_BOTTOM , margin);
                        break;
                }
            }

            @bardiademon
            public static void margin (int margin , int... which)
            {
                for (int w : which) margin (w , margin);
            }

            @bardiademon
            public static void padding (int which , int padding)
            {
                padding = Math.convertDPPX (padding);
                switch (which)
                {
                    case SET__MP_TOP:
                        paddingTop = padding;
                        break;
                    case SET__MP_RIGHT:
                        paddingRight = padding;
                        break;
                    case SET__MP_BOTTOM:
                        paddingBottom = padding;
                        break;
                    case SET__MP_LEFT:
                        paddingLeft = padding;
                        break;
                    case SET__MP_ALL:
                        padding (SET__MP_LEFT , padding);
                        padding (SET__MP_TOP , padding);
                        padding (SET__MP_RIGHT , padding);
                        padding (SET__MP_BOTTOM , padding);
                        break;
                }
            }

            @bardiademon
            public static void padding (int padding , int... which)
            {
                for (int w : which) padding (w , padding);
            }

            @bardiademon
            public static void setDefault ()
            {
                margin (SET__MP_ALL , DEFAULT_MARGIN);
                padding (SET__MP_ALL , DEFAULT_PADDING);
            }
        }

        public static abstract class SetPublicStyleRadioBtn
        {
            public static final int DEFAULT_GRAVITY = Gravity.CENTER, DEFAULT_MARGIN = Math.convertDPPX (1);
            public static final int DEFAULT_TEXT_STYLE = Typeface.NORMAL, DEFAULT_TEXT_COLOR = GetValues.getColor ("COLOR_BLACK");

            public static int marginLeft, marginRight, marginTop, marginBottom;
            public static int gravity;
            public static int textStyle, textColor;

            @bardiademon
            public static void margin (int which , int margin)
            {
                margin = Math.convertDPPX (margin);
                switch (which)
                {
                    case SET__MP_TOP:
                        marginTop = margin;
                        break;
                    case SET__MP_RIGHT:
                        marginRight = margin;
                        break;
                    case SET__MP_BOTTOM:
                        marginBottom = margin;
                        break;
                    case SET__MP_LEFT:
                        marginLeft = margin;
                        break;
                    case SET__MP_ALL:
                        margin (SET__MP_LEFT , margin);
                        margin (SET__MP_TOP , margin);
                        margin (SET__MP_RIGHT , margin);
                        margin (SET__MP_BOTTOM , margin);
                        break;
                }
            }

            @bardiademon
            public static void setDefault ()
            {
                margin (SET__MP_ALL , DEFAULT_MARGIN);
                gravity = DEFAULT_GRAVITY;
                textStyle = DEFAULT_TEXT_STYLE;
                textColor = DEFAULT_TEXT_COLOR;
            }
        }
    }
}
