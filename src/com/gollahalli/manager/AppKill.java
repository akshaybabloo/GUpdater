package com.gollahalli.manager;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by akshayrajgollahalli on 24/12/15.
 */
public class AppKill {
    
    public void macAppKill(){
        try {
            String line;
            String test = null;
            Process p = Runtime.getRuntime().exec("pgrep -lf JCal.app");
            BufferedReader input =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                test = line;
            }

            Process kill = Runtime.getRuntime().exec("kill -9 " + (test != null ? test.split(" ") : new String[0])[0]);
            input.close();
        } catch (Exception err) {
        }
    }
    
    public void windowsAppKill(){
        
    }
}
