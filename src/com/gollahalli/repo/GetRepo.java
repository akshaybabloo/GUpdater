package com.gollahalli.repo;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


public class GetRepo {

    public String returner() {
        URL url = null;
        try {
            url = new URL("https://api.github.com/repos/gollahalli/JCal/releases/latest");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStream is = null;
        try {
            assert url != null;
            is = url.openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert is != null;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String lines = null;
        try {
            lines = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }
}
