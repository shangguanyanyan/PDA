package com.jxjr.utility;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class LoginPreferencesSV {
	private Context context;

	public LoginPreferencesSV(Context context) {
		this.context = context;
	} 
	
    public void save(String login_username, String login_password
    		, String login_host, String forgid) {  
        //获得SharedPreferences对象  
        SharedPreferences preferences = context.getSharedPreferences("login", Context.MODE_PRIVATE);  
        Editor editor = preferences.edit();  
        editor.putString("login_username", login_username);  
        editor.putString("login_password", login_password);  
        editor.putString("login_host", login_host);
        editor.putString("forgid", forgid);
        editor.commit();
    }  
	
    public Map<String, String> getPerferences() {
    	Map<String, String> params = new HashMap<String, String>(); 
    	SharedPreferences preferences = context.getSharedPreferences("login", Context.MODE_PRIVATE);
    	params.put("login_username", preferences.getString("login_username", "")); 
    	params.put("login_password", preferences.getString("login_password", "")); 
    	params.put("login_host", preferences.getString("login_host", "")); 
    	params.put("forgid", preferences.getString("forgid", "1"));
    	return params;
    }
    
}
