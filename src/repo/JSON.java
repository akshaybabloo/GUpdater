package repo;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by akshayrajgollahalli on 5/10/15.
 */
public class JSON {
    public static void main(String[] args) {
        GetRepo getRepo = new GetRepo();
        String jsonString = getRepo.returner();

        JSONObject jsonObject = new JSONObject(jsonString);
        JSONObject jsonArray = jsonObject.getJSONArray("assets").getJSONObject(0);
        System.out.println(jsonArray.getString("browser_download_url"));

        System.out.println(jsonObject.getString("tag_name"));
        System.out.println(jsonObject.getString("body"));

        JSONObject jsonArray1 = jsonObject.getJSONArray("assets").getJSONObject(0);
        System.out.println(jsonArray1.getString("name"));

    }
}
