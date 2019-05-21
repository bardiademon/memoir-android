package bardiademon.Memoir.Server;

import bardiademon.Memoir.bardiademon.Interface.bardiademon;

@bardiademon
public interface AnswerServer
{
    String KEY_JSON_RESULT = "Result";

    @bardiademon
    abstract class PublicAnswer
    {
        public static final int PUBLIC_ERROR_REQUEST = -11,
                PUBLIC_ERROR = -12,
                NOT_LOGGED_IN = -13,
                NOT_FOUND = 0,
                IS_OK = 7,
                NOT_OK = -7,
                FOUND = 1,
                NULL = -123,
                SET = 17, UNSET = 18;

    }

    @bardiademon
    abstract class RecordChangeMemoir extends PublicAnswer
    {
        // SC = Status Code
        public static abstract class SC400
        {
            public static final int DUPLICATE_LINK = 12;
        }

        public static abstract class SC200
        {
            public static final int NOT_RECORDED = 10, RECORDED = 11, ERROR_LINK = 8, ERROR_DATE = 9, CHANGED = 19;
        }
    }

    @bardiademon
    abstract class DelMemoirMemoir extends PublicAnswer
    {
        // SC = Status Code
        public static abstract class SC200
        {
            public static final int DELETED = 13;
        }
    }

    @bardiademon
    abstract class CheckInfoLogin extends PublicAnswer
    {
        public static abstract class SC200
        {
            public final static int VALID = 6, INVALID = 5, DEACTIVE_ACCOUNT = 3;
        }
    }

    @bardiademon
    abstract class GetMemoir extends PublicAnswer
    {
        // SC = Status Code
        public static abstract class SC200
        {
            public static final int NOT_OPEN = 15, NOT_CONFIRMATION = 16;
        }
    }

    @bardiademon
            // KJS => Key Json Server
    interface KJS
    {

        abstract class KJR_ID
        {
            public static final String ID = "id", ID_USER = "idusr";
        }

        @bardiademon
        abstract class KJSPublic extends KJR_ID
        {
            public static final String NAME = "name";
        }

        @bardiademon
        abstract class KJSGetMemoirUser extends KJSPublic
        {
            public static final String SUBJECT = "sub", CONFIRMATION = "con", LINK = "lnk", OPEN = "opn", DATE = "dt";

        }

        abstract class KJRSubject extends KJSPublic
        {
            public static final String NAME = "nm";
        }

        abstract class KJSGetMemoir extends KJSPublic
        {
            public static final String NAME = "name",
                    SUBJECT = "sbjct",
                    TEXT = "txt",
                    DATE = "date",
                    TIME_RECORD = "timrcrd",
                    TIME_LAST_EDIT = "timlstedt",
                    VISIT = "vst",
                    VISIT_USER = "vstusr",
                    LIKE = "lk",
                    COMMENT = "cmt",
                    LIKED = "lkd",
                    IS_MEMOIR_FOR_YOU = "imfy";

        }

        abstract class KJSGetComment extends KJSPublic
        {
            public static final String JSON_INFO_USER = "infusr",
                    TXT_COMMENT = "tcmnt",
                    TIME = "tm",
                    USERNAME = "uname",
                    PIC = "pic";

        }
    }
}
