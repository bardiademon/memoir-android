package bardiademon.Memoir.bardiademon.Class.Other.String;

public abstract class CheckString
{
    public static final int MAX_LEN_STRING_USERNAME__INT = 5;
    public static final String MAX_LEN_STRING_USERNAME__STR = "پنج";

    public static boolean AreOnlyEnglishWords (String text)
    {
        return (text.matches ("[a-z]") || text.matches ("[A-Z]"));
    }

    public static boolean checkUsername (String username)
    {
        char[] chars = username.toCharArray ();
        if (testCharEnglish (chars[0]) && chars.length >= MAX_LEN_STRING_USERNAME__INT)
        {
            for (int i = 1, len = chars.length; i < len; i++)
                if (!testCharEnglish (chars[i]) && !testCharNumber (chars[i]) && chars[i] != '_')
                    return false;
        }
        else return false;

        return true;
    }

    public static boolean CheckJustNumber (String Value)
    {
        return (Value.matches ("[0-9]*"));
    }

    private static boolean testCharEnglish (char c)
    {
        String str = String.valueOf (c);
        return str.matches ("[a-z]") || str.matches ("[A-Z]");
    }

    private static boolean testCharNumber (char c)
    {
        String str = String.valueOf (c);
        return str.matches ("[0-9]");
    }

    public abstract static class CheckPhone
    {

        private static final int LEN_PHONE_WITH_98 = 13; // +989170221393
        private static final int LEN_PHONE_NO_98_AND_0 = 10; // 9170221393
        private static final int LEN_PHONE_WITH_98_AND_0 = 14; // +9809170221393
        private static final int LEN_PHONE_0_OR_PLUS = 11; // 0 OR + 9170221393
        private static int Len;
        private static String Correction;

        public static boolean Check (String Phone)
        {
            return Check (Phone , true);
        }

        public static boolean Check (String Phone , boolean IsCorrection)
        {
            Correction = null;
            Len = Phone.length ();
            switch (Len)
            {
                case LEN_PHONE_WITH_98:
                case LEN_PHONE_NO_98_AND_0:
                case LEN_PHONE_WITH_98_AND_0:
                case LEN_PHONE_0_OR_PLUS:
                {
                    if (IsCorrection) Correction = Correction (Phone);
                    return (Correction != null);
                }
                default:
                    return false;
            }
        }

        private static String Correction (String Phone)
        {
            Correction = null;
            char[] PhoneChar = Phone.toCharArray ();
            StringBuilder TempPhone = new StringBuilder ();
            for (int i = 1; i < PhoneChar.length; i++) TempPhone.append (PhoneChar[i]);
            if (!TempPhone.toString ().matches ("[0-9]*")) return null;
            switch (Len)
            {
                case LEN_PHONE_WITH_98:
                {
                    if (CheckChar (PhoneChar , '+' , '9' , '8' , '9'))
                        return Phone;
                    else if (CheckChar (PhoneChar , '9' , '8' , '0' , '9'))
                        return CorrectionLen980 (PhoneChar , 2 , true);
                    else break;
                }
                case LEN_PHONE_NO_98_AND_0:
                {
                    if (CheckChar (PhoneChar , '9'))
                    {
                        TempPhone = new StringBuilder ();
                        TempPhone.append ("+98");
                        for (int i = 0, len = PhoneChar.length; i < len; i++)
                            TempPhone.append (PhoneChar[i]);
                        return TempPhone.toString ();
                    }
                    break;
                }
                case LEN_PHONE_WITH_98_AND_0:
                    if (CheckChar (PhoneChar , '+' , '9' , '8' , '0' , '9'))
                        return CorrectionLen980 (PhoneChar , 3 , false);
                case LEN_PHONE_0_OR_PLUS:
                {
                    if (CheckChar (PhoneChar , '0' , '9') || CheckChar (PhoneChar , '+' , '9'))
                    {
                        TempPhone = new StringBuilder ();
                        TempPhone.append ("+98");
                        for (int i = 1, len = PhoneChar.length; i < len; i++)
                            TempPhone.append (PhoneChar[i]);
                        return TempPhone.toString ();
                    }
                    break;
                }
            }
            return null;
        }

        private static String CorrectionLen980 (char[] PhoneChar , int IndexDelete , boolean AppendPlus)
        {
            StringBuilder TempPhone = new StringBuilder ();
            if (AppendPlus) TempPhone.append ("+");
            for (int i = 0, len = PhoneChar.length; i < len; i++)
                if (i != IndexDelete) TempPhone.append (PhoneChar[i]);
            return TempPhone.toString ();
        }

        private static boolean CheckChar (char[] PhoneChar , char... Check)
        {
            for (int i = 0, len = Check.length; i < len; i++)
                if (PhoneChar[i] != Check[i]) return false;
            return true;
        }

        public static String GetCorrection ()
        {
            return Correction;
        }
    }


}
