package repo;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by akshayrajgollahalli on 5/10/15.
 */
public class JSON {
    public String[] returner() {

        String[] result = new String[4];

        GetRepo getRepo = new GetRepo();
        String jsonString = getRepo.returner();

        JSONObject jsonObject = new JSONObject(jsonString);
        JSONObject jsonArray = jsonObject.getJSONArray("assets").getJSONObject(0);
        result[0] = jsonArray.getString("browser_download_url");

        result[1] = jsonObject.getString("tag_name");
        result[2] = jsonObject.getString("body");

        JSONObject jsonArray1 = jsonObject.getJSONArray("assets").getJSONObject(0);
        result[3] = jsonArray1.getString("name");

        return result;
    }
}
