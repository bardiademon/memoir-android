package bardiademon.Memoir.Server.Found;

public class FoundMemoir
{
    public final String Name, Subject, Text, Date, TimeRecord, TimeLastUpdate, NumberOfVisit, NumberOfVisitUser, NumberOfLike, NumberOfComment;
    public final boolean Liked, IsMemoirForMe;

    public FoundMemoir (String Name , String Subject , String Text , String Date , String TimeRecord , String TimeLastUpdate , int NumberOfVisit , int NumberOfVisitUser , int NumberOfLike , int NumberOfComment , boolean Liked , boolean IsMemoirForMe)
    {
        this.Name = Name;
        this.Subject = Subject;
        this.Text = Text;
        this.Date = Date;
        this.TimeRecord = TimeRecord;
        this.TimeLastUpdate = TimeLastUpdate;
        this.NumberOfVisit = String.valueOf (NumberOfVisit);
        this.NumberOfVisitUser = String.valueOf (NumberOfVisitUser);
        this.NumberOfLike = String.valueOf (NumberOfLike);
        this.NumberOfComment = String.valueOf (NumberOfComment);
        this.Liked = Liked;
        this.IsMemoirForMe = IsMemoirForMe;
    }
}
