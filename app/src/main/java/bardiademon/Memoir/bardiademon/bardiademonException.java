package bardiademon.Memoir.bardiademon;

import bardiademon.Memoir.bardiademon.Interface.bardiademon;

@bardiademon
public class bardiademonException extends Exception
{
    @bardiademon
    public bardiademonException ()
    {
    }

    @bardiademon
    public bardiademonException (String message)
    {
        super(message);
    }

    public static void ERROR (String message)
    {
        try
        {
            throw new bardiademonException(message);
        }
        catch (bardiademonException e)
        {
            e.printStackTrace();
        }
    }
}
