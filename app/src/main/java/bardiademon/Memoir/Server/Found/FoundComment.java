package bardiademon.Memoir.Server.Found;


import bardiademon.Memoir.bardiademon.Interface.bardiademon;

@bardiademon
public class FoundComment
{

    public final String TextComment, Time;
    public final int IdUser, IdComment;

    @bardiademon
    public FoundComment (int IdComment , int IdUser , String TextComment , String Time)
    {
        this.IdComment = IdComment;
        this.IdUser = IdUser;
        this.TextComment = TextComment;
        this.Time = Time;
    }

}