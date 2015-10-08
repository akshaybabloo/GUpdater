package com.gollahalli.repo;

import org.json.JSONObject;

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

        result[3] = jsonArray.getString("name");

        return result;
    }
}
