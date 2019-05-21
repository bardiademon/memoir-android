package bardiademon.Memoir.Server.Found;

import bardiademon.Memoir.bardiademon.Interface.bardiademon;

@bardiademon
public class FoundMemoirUser
{
    public final int Id;
    public final String Name, Subject, Date, Link;
    public final boolean Open, Confirmation;

    @bardiademon
    public FoundMemoirUser (int Id , String Name , String Subject , String Date , String Link , boolean Confirmation , boolean Open)
    {
        this.Id = Id;
        this.Name = Name;
        this.Subject = Subject;
        this.Date = Date;
        this.Link = Link;
        this.Confirmation = Confirmation;
        this.Open = Open;
    }
}
