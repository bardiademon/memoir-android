package bardiademon.Memoir.bardiademon.Interface;

import android.database.sqlite.SQLiteDatabase;

public interface Model
{
    void RunClass ();

    SQLiteDatabase ConnectDatabase (String nameDatabase);

    void CommunicationWithTheDatabase ();

    String MakeQuery ();

    boolean Answer ();
}
