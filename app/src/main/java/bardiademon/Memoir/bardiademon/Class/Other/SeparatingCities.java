package bardiademon.Memoir.bardiademon.Class.Other;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import bardiademon.Memoir.bardiademon.Class.Server.MakeRequest.Request;
import bardiademon.Memoir.bardiademon.Class.Server.SendInfoToServer;
import bardiademon.Memoir.bardiademon.Class.Server.Url;
import bardiademon.Memoir.bardiademon.Interface.ExchangeInformationWithTheServer;

import bardiademon.Memoir.bardiademon.Class.Other.GetFromValue.GetValues;
import bardiademon.Memoir.bardiademon.Class.View.Wait;

public class SeparatingCities
{
    private Map<Integer, List<String>> city;
    private List<String> state;
    private boolean done = false;
    private ExchangeInformationWithTheServer.AfterExchange AfterExchange;
    private SendInfoToServer sendInfoToServer;

    public SeparatingCities (ExchangeInformationWithTheServer.AfterExchange afterGet)
    {
        this.AfterExchange = afterGet;
        getJson();
    }

    private void getAll (String json)
    {
        try
        {
            JSONArray jsonArray = new JSONArray(json);
            if (jsonArray.length() > 0)
            {
                JSONGetStringUtf8 allInfoStateAndCity, jsonOneCity;
                JSONArray allNameCity;
                city = new LinkedHashMap<>();
                List<String> listCity;
                state = new ArrayList<>();
                String nameState, nameCity;
                for (int i = 0, len = jsonArray.length(); i < len; i++)
                {
                    listCity = new ArrayList<>();
                    allInfoStateAndCity = new JSONGetStringUtf8(jsonArray.getString(i));
                    nameState = allInfoStateAndCity.getString(GetValues.getString("name_json_state"));
                    state.add(nameState);
                    allNameCity = new JSONArray(allInfoStateAndCity.getString(GetValues.getString("name_json_array_city")));
                    for (int iCity = 0, lenCity = allNameCity.length(); iCity < lenCity; iCity++)
                    {
                        jsonOneCity = new JSONGetStringUtf8(allNameCity.getString(iCity));
                        nameCity = jsonOneCity.getString(GetValues.getString("name_json_city"));
                        listCity.add(nameCity);
                    }
                    city.put(i , listCity);
                }
                done = true;
            }
        }
        catch (JSONException je)
        {
            state = null;
            city = null;
        }
        Wait.CloseWait();
        AfterExchange.Callback();
    }

    private void getJson ()
    {
        Wait.Set();
        sendInfoToServer = new SendInfoToServer(this::afterGet , Url.GetUrl("ap__get_city") , new Request().getParamJsonEmpty());
        sendInfoToServer.setMethod(com.android.volley.Request.Method.GET);
        sendInfoToServer.apply();
    }

    private void afterGet ()
    {
        try
        {
            getAll(sendInfoToServer.getAnswerServer());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public List<String> getState ()
    {
        return state;
    }

    public Map<Integer, List<String>> getCity ()
    {
        return city;
    }

    public List<String> getListCity (Integer index)
    {
        try
        {
            return city.get(index);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public boolean isDone ()
    {
        return done;
    }
}
