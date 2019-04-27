package bardiademon.Memoir.bardiademon.Class.Other.Check;

import bardiademon.Memoir.bardiademon.Class.Other.FatalError;
import bardiademon.Memoir.bardiademon.Class.Other.Service.GetSerialPhone;

public class CheckSerialPhone
{

    private boolean isValid;
    private String serial;

    public CheckSerialPhone (String serial)
    {
        this.serial = serial;
        check();
    }

    private void check ()
    {
        GetSerialPhone serialPhone = new GetSerialPhone();

        if (serialPhone.getResult() == null || serialPhone.getResult().equals(""))
            new FatalError(FatalError.DEFAULT_MESSAGE__NO_PERMISSION);
        else
            isValid = (serialPhone.getResult().equals(serial));
    }

    public boolean isValid ()
    {
        return isValid;
    }
}
