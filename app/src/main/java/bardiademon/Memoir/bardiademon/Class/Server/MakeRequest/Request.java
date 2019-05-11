package bardiademon.Memoir.bardiademon.Class.Server.MakeRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import bardiademon.Memoir.bardiademon.Class.Other.Encode;
import bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues;

public class Request
{

    private static Request request;
    public static Put _Put;

    public static final int TYPE_NEW_PUT = -1, TYPE_INT = 0, TYPE_STRING = 1, TYPE_BOOL = 2, TYPE_LONG = 3, TYPE_FLOAT = 4, TYPE_DOUBLE = 5, TYPE_SHORT = 6;

    private static Map <Integer, Map <List <Integer>, Map <String, Object>>> Put;

    private Map <String, Object> PutTakenInternalValue;
    private List <Integer> TypeTakenInternalValue;
    private JSONObject resultBuild;
    private boolean built = false;
    private boolean paramEmpty;


    public static void MKRequest ()
    {
        request = new Request ();
        _Put = new Put ();
    }

    public static Request GetRequest ()
    {
        if (request != null && _Put != null)
        {
            _Put.Apply ();
            request.Apply ();
            _Put = null;
            System.gc ();
            return request;
        }
        else return null;
    }

    public static class Put
    {
        private int Type;
        private String Name;
        private Object Value;
        private Map <String, Object> put;
        private List <Integer> type;
        private int counter = 0;

        private boolean encode;

        public void Put (String Name , Object Value , int Type)
        {
            if (CheckType (Type))
            {
                this.Name = Name;

                if (encode && Type == TYPE_STRING)
                    this.Value = Encode.encode ((String) Value);
                else
                    this.Value = Value;
                this.Type = Type;
                OneApply ();
            }
        }

        private boolean CheckType (int Type)
        {
            return (Type == TYPE_NEW_PUT || Type == TYPE_INT || Type == TYPE_STRING || Type == TYPE_BOOL || Type == TYPE_LONG || Type == TYPE_FLOAT || Type == TYPE_DOUBLE || Type == TYPE_SHORT);
        }

        public void Put (String Name , int Value)
        {
            Put (Name , Value , TYPE_INT);
        }

        public void Put (String Name , String Value)
        {
            Put (Name , Value , TYPE_STRING);
        }

        public void Put (String Name , boolean Value)
        {
            Put (Name , Value , TYPE_BOOL);
        }

        public void Put (String Name , long Value)
        {
            Put (Name , Value , TYPE_LONG);
        }

        public void Put (String Name , float Value)
        {
            Put (Name , Value , TYPE_FLOAT);
        }

        public void Put (String Name , double Value)
        {
            Put (Name , Value , TYPE_DOUBLE);
        }

        public void Put (String Name , short Value)
        {
            Put (Name , Value , TYPE_SHORT);
        }

        public void Put (String Name)
        {
            Put (Name , getPut ());
        }

        public void Put (String Name , Map <List <Integer>, Map <String, Object>> getPut)
        {
            Put (Name , getPut , TYPE_NEW_PUT);
        }

        public Map <List <Integer>, Map <String, Object>> getPut ()
        {
            Map <List <Integer>, Map <String, Object>> Put = new LinkedHashMap <> ();
            Put.put (type , put);
            clear ();
            return Put;
        }

        public void clear ()
        {
            type = null;
            put = null;
            Name = null;
            System.gc ();
        }

        private void OneApply ()
        {
            if (type == null) type = new ArrayList <> ();
            if (put == null) put = new LinkedHashMap <> ();
            type.add (Type);
            put.put (Name , Value);
        }

        public void Apply ()
        {
            if (Request.Put == null) Request.Put = new LinkedHashMap <> ();
            Map <List <Integer>, Map <String, Object>> put = new LinkedHashMap <> ();
            put.put (type , this.put);
            Request.Put.put (counter++ , put);
            clear ();
        }

        public void encodeValue (boolean encode)
        {
            this.encode = encode;
        }
    }

    public void Apply ()
    {
        try
        {
            resultBuild = new JSONObject ();
            for (Map.Entry <Integer, Map <List <Integer>, Map <String, Object>>> entry : Put.entrySet ())
            {
                Map.Entry <List <Integer>, Map <String, Object>> next = entry.getValue ().entrySet ().iterator ().next ();
                Build (next.getKey () , next.getValue () , true);
            }
            built = true;
        }
        catch (JSONException e)
        {
            e.printStackTrace ();
        }
    }

    private JSONObject Build (List <Integer> Type , Map <String, Object> Put , boolean putJsonMain) throws JSONException
    {
        JSONObject jsonObject = null;
        if (!putJsonMain) jsonObject = new JSONObject ();
        String Name;
        Object Value;
        int counter = 0;
        int type;
        for (Map.Entry <String, Object> entry : Put.entrySet ())
        {
            type = Type.get (counter++);
            Name = entry.getKey ();
            Value = entry.getValue ();
            switch (type)
            {
                case TYPE_INT:
                {
                    int valueI = ((int) Value);
                    if (putJsonMain) resultBuild.put (Name , valueI);
                    else jsonObject.put (Name , valueI);
                    break;
                }
                case TYPE_STRING:
                {
                    String valueS = String.valueOf (Value);
                    if (putJsonMain) resultBuild.put (Name , valueS);
                    else jsonObject.put (Name , valueS);
                    break;
                }
                case TYPE_BOOL:
                {
                    boolean valueB = ((boolean) Value);
                    if (putJsonMain) resultBuild.put (Name , valueB);
                    else jsonObject.put (Name , valueB);
                    break;
                }
                case TYPE_LONG:
                {
                    long valueL = ((long) Value);
                    if (putJsonMain) resultBuild.put (Name , valueL);
                    else jsonObject.put (Name , valueL);
                    break;
                }
                case TYPE_FLOAT:
                {
                    float valueF = ((float) Value);
                    if (putJsonMain) resultBuild.put (Name , valueF);
                    else jsonObject.put (Name , valueF);
                    break;
                }
                case TYPE_DOUBLE:
                {
                    double valueD = ((double) Value);
                    if (putJsonMain) resultBuild.put (Name , valueD);
                    else jsonObject.put (Name , valueD);
                    break;
                }
                case TYPE_SHORT:
                {
                    short valueSH = ((short) Value);
                    if (putJsonMain) resultBuild.put (Name , valueSH);
                    break;
                }
                case TYPE_NEW_PUT:
                {
                    if (Value instanceof Map)
                    {
                        Map <List <Integer>, Map <String, Object>> put = (Map <List <Integer>, Map <String, Object>>) Value;
                        Map.Entry <List <Integer>, Map <String, Object>> next1 = put.entrySet ().iterator ().next ();
                        resultBuild.put (Name , Build (next1.getKey () , next1.getValue () , false));
                        break;
                    }
                }
            }
        }
        return jsonObject;
    }

    private boolean checkInternalValue (Object valueObject)
    {
        if (valueObject instanceof Map)
        {
            try
            {
                Map <List <Integer>, Map <String, Object>> value = (Map <List <Integer>, Map <String, Object>>) valueObject;
                Map.Entry <List <Integer>, Map <String, Object>> next = value.entrySet ().iterator ().next ();
                TypeTakenInternalValue = next.getKey ();
                PutTakenInternalValue = next.getValue ();
                return true;
            }
            catch (Exception ignored)
            {
            }
        }
        return false;
    }

    private List <Integer> getListTakenInternalValue ()
    {
        return TypeTakenInternalValue;
    }

    private Map <String, Object> getValueTakenInternalValue ()
    {
        return PutTakenInternalValue;
    }

    public JSONObject getResultBuild ()
    {
        return resultBuild;
    }

    public Map <String, String> getParam ()
    {
        if (isBuilt ())
        {
            Map <String, String> param = new LinkedHashMap <> ();
            param.put (GetValues.getString ("name_post__request") , getResultBuild ().toString ());
            return param;
        }
        else if (paramEmpty) return getParamJsonEmpty ();
        else return new LinkedHashMap <> ();
    }


    public void setParamEmpty ()
    {
        paramEmpty = true;
    }

    public Map <String, String> getParamJsonEmpty ()
    {
        Map <String, String> param = new LinkedHashMap <> ();
        param.put (GetValues.getString ("name_post__request") , "{}");
        return param;
    }

    public boolean isBuilt ()
    {
        return built;
    }

    @Override
    public String toString ()
    {
        return getResultBuild ().toString ();
    }

    public final static class Ready
    {

        public static Request SetOneRequest (String Key , Object Value)
        {
            Request request = new Request ();
            Request.Put put = new Request.Put ();
            if (Value instanceof String) put.Put (GetValues.getString (Key) , (String) Value);
            else if (Value instanceof Integer) put.Put (GetValues.getString (Key) , (int) Value);
            else if (Value instanceof Double) put.Put (GetValues.getString (Key) , (double) Value);
            else if (Value instanceof Float) put.Put (GetValues.getString (Key) , (float) Value);
            else if (Value instanceof Boolean)
                put.Put (GetValues.getString (Key) , (boolean) Value);
            else if (Value instanceof Short) put.Put (GetValues.getString (Key) , (short) Value);
            else if (Value instanceof Long) put.Put (GetValues.getString (Key) , (long) Value);
            else put.Put (GetValues.getString (Key) , Value.toString ());
            put.Apply ();
            request.Apply ();
            return request;
        }

        public static Request SetRequestId (int Id)
        {
            return SetOneRequest ("nr__id" , Id);
        }
    }
}
