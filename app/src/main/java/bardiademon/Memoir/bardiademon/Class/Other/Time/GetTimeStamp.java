package bardiademon.Memoir.bardiademon.Class.Other.Time;

// Created by bardiademon on 12/03/2018_17:22.

public abstract class GetTimeStamp
{
    public static String get ()
    {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }
}
