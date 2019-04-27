package bardiademon.Memoir.Server;

import bardiademon.Memoir.bardiademon.Interface.bardiademon;

@bardiademon
public interface AnswerServer
{
    String KEY_JSON_RESULT = "Result";

    @bardiademon
    abstract class PublicAnswer
    {
        public static final int PUBLIC_ERROR_REQUEST = -11;
        public static final int PUBLIC_ERROR = -12;
        public static final int NOT_LOGGED_IN = -13;
        public static final int NOT_FOUND = 0;
        public static final int IS_OK = 7;
        public static final int NOT_OK = -7;
        public static final int FOUND = 1;
        public static final int NULL = -123;
    }

    @bardiademon
    abstract class RecordNewMemoir extends PublicAnswer
    {
        // SC = Status Code
        public static abstract class SC400
        {
            public static final int DUPLICATE_LINK = 12;
        }

        public static abstract class SC200
        {
            public static final int NOT_RECORDED = 10, RECORDED = 11;
            public static final int ERROR_LINK = 8, ERROR_DATE = 9;
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
        public final static int VALID = 6, INVALID = 5;
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
        @bardiademon
        abstract class KJSPublic
        {
            public static final String NAME = "name", ID = "id";
        }

        @bardiademon
        abstract class KJSGetMemoirUser extends KJSPublic
        {
            public static final String SUBJECT = "sub";
            public static final String CONFIRMATION = "con";
            public static final String LINK = "lnk";
            public static final String OPEN = "opn";

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
    }
}
