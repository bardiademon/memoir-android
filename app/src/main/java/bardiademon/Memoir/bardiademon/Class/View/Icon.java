package bardiademon.Memoir.bardiademon.Class.View;

import bardiademon.Memoir.R;

public interface Icon
{
    int
            NO_ICON = -1, ICON_INFORMATION = R.drawable.information,
            ICON_SUCCESSFUL = R.drawable.successful,
            ICON_WARNING = R.drawable.warning,
            ICON_ERROR = R.drawable.error;

    int MENU_NAV_THREE_LINE = android.R.drawable.ic_menu_sort_by_size;

    int ERROR_LOAD_PIC = R.drawable.error_load_image;

    int
            ACCEPTED = R.drawable.pic_accepted,
            NOT_ACCEPTED = R.drawable.pic_not_accepted;

    interface IconBackActivity
    {
        int
                ICON_BACK_ACTIVITY_1 = R.drawable.back_activity_1,
                ICON_BACK_ACTIVITY_2 = R.drawable.back_activity_2,
                ICON_BACK_ACTIVITY_2_WHITE = R.drawable.back_activity_2_white;

    }
}
