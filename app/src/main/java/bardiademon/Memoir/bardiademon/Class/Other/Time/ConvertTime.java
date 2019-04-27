package bardiademon.Memoir.bardiademon.Class.Other.Time;

// Created by bardiademon on 01/03/2018_12:01.

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import java.sql.Date;
import java.text.SimpleDateFormat;

@SuppressLint("SimpleDateFormat")
public class ConvertTime
{

    private String[] dateIr;
    private Date date;

    private String weekFa;
    private String weekInt;
    private boolean setTimeEn;
    private boolean setTime;

    public ConvertTime ()
    {
        setTime = false;
    }

    public ConvertTime (String time)
    {
        this();
        setTime(time);
    }


    public ConvertTime (boolean setTimeEn)
    {
        this.setTimeEn = setTimeEn;
    }

    public ConvertTime (String time , boolean setTimeEn)
    {
        this.setTimeEn = setTimeEn;
        setTime(time);
    }

    public void setTime (String time)
    {
        try
        {
            date = new Date(Long.parseLong(time) * 1000L);

            int y = Integer.parseInt(new SimpleDateFormat("y").format(date));
            int m = Integer.parseInt(new SimpleDateFormat("M").format(date));
            int d = Integer.parseInt(new SimpleDateFormat("d").format(date));

            if (setTimeEn)
                dateIr = new String[]{String.valueOf(y) , String.valueOf(m) , String.valueOf(d)};
            else
                dateIr = gregorian_to_jalali(y , m , d);

            setTime = true;

            weekFa(new SimpleDateFormat("E").format(date));

        }
        catch (Exception e)
        {
            setTime = false;
        }
    }

    public String todayTime ()
    {
        if (setTime)
            return String.format("%s %s %s %s - %s:%s:%s" , weekFa , dayOnMonth() , monthName() , year() , hour24() , minutes() , second());

        return "";
    }

    public String pm_am ()
    {
        if (setTime)
            return new SimpleDateFormat("a").format(date);

        return "";
    }

    public String hour12 ()
    {
        if (setTime)
        {
            int h = Integer.parseInt(new SimpleDateFormat("h").format(date));
            return String.valueOf((h < 10) ? "0" + h : h);
        }

        return "";
    }

    public String hour24 ()
    {
        if (setTime)
        {
            int H = Integer.parseInt(new SimpleDateFormat("H").format(date));
            return String.valueOf((H < 10) ? "0" + H : H);
        }

        return "";
    }


    public String minutes ()
    {
        if (setTime)
        {
            int m = Integer.parseInt(new SimpleDateFormat("m").format(date));
            return String.valueOf((m < 10) ? "0" + m : m);
        }

        return "";
    }

    public String second ()
    {
        if (setTime)
        {
            int s = Integer.parseInt(new SimpleDateFormat("s").format(date));
            return String.valueOf((s < 10) ? "0" + s : s);
        }

        return "";
    }

    public String dayInt ()
    {
        if (setTime) return weekInt;

        return "";
    }

    public String nameDay ()
    {
        if (setTime) return weekFa;

        return "";
    }

    public String year ()
    {
        if (setTime) return dateIr[0];

        return "";
    }

    public String monInt ()
    {
        if (setTime) return dateIr[1];

        return "";
    }

    public String monthName ()
    {
        if (setTime)
            return monFa(Integer.parseInt(monInt()));

        return "";
    }

    public String dayOnMonth ()
    {
        if (setTime)
            return dateIr[2];

        return "";
    }

    public String weekOnMonth ()
    {
        if (setTime)
            return new SimpleDateFormat("W").format(date);

        return "";
    }

    public String weekOnYear ()
    {
        if (setTime)
            return new SimpleDateFormat("w").format(date);

        return "";
    }

    private void weekFa (String week)
    {
        if (setTime)
        {
            switch (week)
            {
                case "Sat":
                {
                    weekInt = "0";
                    if (setTimeEn) weekFa = "Sat";
                    else weekFa = "شنبه";
                    break;
                }
                case "Sun":
                {
                    weekInt = "1";
                    if (setTimeEn) weekFa = "Sun";
                    else weekFa = "یک شنبه";
                    break;
                }
                case "Mon":
                {
                    weekInt = "2";
                    if (setTimeEn) weekFa = "Mon";
                    else weekFa = "دو شنبه";
                    break;
                }
                case "Tue":
                {
                    weekInt = "3";
                    if (setTimeEn) weekFa = "Tue";
                    else weekFa = "سه شنبه";
                    break;
                }
                case "Wed":
                {
                    weekInt = "4";
                    if (setTimeEn) weekFa = "Wed";
                    else weekFa = "چهار شنبه";
                    break;
                }
                case "Thu":
                {
                    weekInt = "5";
                    if (setTimeEn) weekFa = "Thu";
                    else weekFa = "پنج شنبه";
                    break;
                }
                case "Fri":
                {
                    weekInt = "6";
                    if (setTimeEn) weekFa = "Fri";
                    else weekFa = "جمعه";
                    break;
                }
                default:
                {
                    weekInt = "-1";
                    weekFa = "";
                    break;
                }
            }
        }
        else
        {
            weekInt = "-1";
            weekFa = "";
        }
    }

    private String monFa (int mon)
    {
        if (setTime)
        {
            switch (mon)
            {
                case 1:
                {
                    if (setTimeEn) return "January";
                    else return "فروردین";
                }
                case 2:
                {
                    if (setTimeEn) return "February";
                    else return "اردیبهشت";
                }
                case 3:
                {
                    if (setTimeEn) return "March";
                    else return "خرداد";
                }
                case 4:
                {
                    if (setTimeEn) return "April";
                    else return "تیر";
                }
                case 5:
                {
                    if (setTimeEn) return "May";
                    else return "مرداد";
                }
                case 6:
                {
                    if (setTimeEn) return "June";
                    else return "شهریور";
                }
                case 7:
                {
                    if (setTimeEn) return "July";
                    else return "مهر";
                }
                case 8:
                {
                    if (setTimeEn) return "August";
                    else return "آبان";
                }
                case 9:
                {
                    if (setTimeEn) return "September";
                    else return "آذر";
                }
                case 10:
                {
                    if (setTimeEn) return "October";
                    else return "دی";
                }
                case 11:
                {
                    if (setTimeEn) return "November";
                    else return "بهمن";
                }
                case 12:
                {
                    if (setTimeEn) return "December";
                    else return "اسفند";
                }
                default:
                    return "";
            }
        }
        return "";
    }

    @NonNull
    private String[] gregorian_to_jalali (int gy , int gm , int gd)
    {
        int[] g_d_m = {0 , 31 , 59 , 90 , 120 , 151 , 181 , 212 , 243 , 273 , 304 , 334};
        int jy;
        if (gy > 1600)
        {
            jy = 979;
            gy -= 1600;
        }
        else
        {
            jy = 0;
            gy -= 621;
        }
        int gy2 = (gm > 2) ? (gy + 1) : gy;
        int days = (365 * gy) + ((int) ((gy2 + 3) / 4)) - ((int) ((gy2 + 99) / 100)) + ((int) ((gy2 + 399) / 400)) - 80 + gd + g_d_m[gm - 1];
        jy += 33 * ((int) (days / 12053));
        days %= 12053;
        jy += 4 * ((int) (days / 1461));
        days %= 1461;
        if (days > 365)
        {
            jy += (int) ((days - 1) / 365);
            days = (days - 1) % 365;
        }
        int jm = (days < 186) ? 1 + (int) (days / 31) : 7 + (int) ((days - 186) / 30);
        int jd = 1 + ((days < 186) ? (days % 31) : ((days - 186) % 30));

        return new String[]{String.valueOf(jy) , String.valueOf(jm) , String.valueOf(jd)};
    }
}
