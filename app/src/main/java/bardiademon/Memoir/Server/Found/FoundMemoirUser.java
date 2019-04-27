package bardiademon.Memoir.Server.Found;

import bardiademon.Memoir.bardiademon.Interface.bardiademon;

@bardiademon
public class FoundMemoirUser
{
    public final int Id;
    public final String Name, Subject, Link;
    public final boolean Open, Confirmation;

    @bardiademon
    public FoundMemoirUser (int Id , String Name , String Subject , String Link , boolean Confirmation , boolean Open)
    {
        this.Id = Id;
        this.Name = Name;
        this.Subject = Subject;
        this.Link = Link;
        this.Confirmation = Confirmation;
        this.Open = Open;
    }
}
