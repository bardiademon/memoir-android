package bardiademon.Memoir.bardiademon.Class.Other.Time;

import bardiademon.Memoir.bardiademon.Interface.bardiademon;

@bardiademon
public class TimeEasy
{
    private String timeStamp;
    private String timeEasy;

    @bardiademon
    public TimeEasy (String TimeStamp)
    {
        this.timeStamp = TimeStamp;
    }

    @bardiademon
    public String getTimeEasy ()
    {
        apply ();
        return timeEasy;
    }

    @bardiademon
    private void apply ()
    {
        ConvertTime convertTimeNow = new ConvertTime (GetTimeStamp.get ());
        int yearNow = Integer.parseInt (convertTimeNow.year ());
        int monIntNow = Integer.parseInt (convertTimeNow.monInt ());
        int dayOnMonthNow = Integer.parseInt (convertTimeNow.dayOnMonth ());
        int weekOnMonthNow = Integer.parseInt (convertTimeNow.weekOnMonth ());
        int hour24Now = Integer.parseInt (convertTimeNow.hour24 ());
        int minutesNow = Integer.parseInt (convertTimeNow.minutes ());
        int secondNow = Integer.parseInt (convertTimeNow.second ());

        ConvertTime convertTime = new ConvertTime (timeStamp);
        int year = Integer.parseInt (convertTime.year ());
        int monInt = Integer.parseInt (convertTime.monInt ());
        int dayOnMonth = Integer.parseInt (convertTime.dayOnMonth ());
        int weekOnMonth = Integer.parseInt (convertTime.weekOnMonth ());
        int hour24 = Integer.parseInt (convertTime.hour24 ());
        int minutes = Integer.parseInt (convertTime.minutes ());
        int second = Integer.parseInt (convertTime.second ());

        if (yearNow > year && monIntNow >= 12)
            timeEasy = Math.abs (yearNow - year) + " سال پیش";

        else if (monIntNow > monInt && weekOnMonthNow >= 4)
            timeEasy = Math.abs (monIntNow - monInt) + " ماه پیش";

        else if (weekOnMonthNow > weekOnMonth && dayOnMonthNow >= 7)
            timeEasy = Math.abs (weekOnMonthNow - weekOnMonth) + " هفته پیش";

        else if (dayOnMonthNow > dayOnMonth && hour24Now >= 24)
            timeEasy = Math.abs (dayOnMonthNow - dayOnMonth) + " روز پیش";

        else if (hour24Now > hour24 && minutesNow >= 59)
            timeEasy = Math.abs (hour24Now - hour24) + " ساعت پیش";

        else if (minutesNow > minutes && secondNow >= 59)
            timeEasy = Math.abs (minutesNow - minutes) + " دقیقه پیش";

        else if (secondNow > second)
            timeEasy = Math.abs (secondNow - second) + " ثانیه پیش";

        else timeEasy = "";
    }
}
