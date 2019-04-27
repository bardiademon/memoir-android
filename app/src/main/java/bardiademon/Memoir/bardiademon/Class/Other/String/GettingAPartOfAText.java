package bardiademon.Memoir.bardiademon.Class.Other.String;

import static bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues.getString;

public class GettingAPartOfAText
{
    private boolean done;
    private String text;
    private int howMuch;

    private StringBuilder finalText;

    public GettingAPartOfAText (String text, int howMuch)
    {
        if (howMuch > text.length() || howMuch == 0)
        {
            done = false;
            return;
        }
        this.text = text;
        this.howMuch = howMuch;
        toSplit();
    }

    private void toSplit ()
    {
        try
        {
            finalText = new StringBuilder();
            char[] charText = text.toCharArray();

            for (int i = 0, len = howMuch; i < len; i++)
                finalText.append(String.valueOf(charText[i]));

            finalText.append(getString("three_points"));
            done = true;
        }
        catch (Exception e)
        {
            done = false;
        }
    }

    public String getFinalText ()
    {
        if (done)
            return (finalText.toString());
        else
            return getString("error");
    }

    public boolean isDone ()
    {
        return done;
    }
}
