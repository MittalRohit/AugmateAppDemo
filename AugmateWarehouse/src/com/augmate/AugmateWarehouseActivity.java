package com.augmate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.AbstractHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class AugmateWarehouseActivity extends Activity {
    /** Called when the activity is first created. */
	Button bLoggin;
	EditText editTextUsername;
	EditText editTextPassword;
	
	User user= new User();
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        bLoggin =(Button) findViewById(R.id.bLogin);
        editTextUsername=(EditText) findViewById(R.id.editTextUsername);
        editTextPassword= (EditText) findViewById(R.id.editTextPassword);
        addListenerLogin();
    }
    
    public void addListenerLogin(){
    	final Context context=this;
    	bLoggin =(Button) findViewById(R.id.bLogin);
    	bLoggin.setOnClickListener(new OnClickListener(){
    		
    		public void onClick(View arg0){
    			
    			//Conect to server to check user login
    			String username=editTextUsername.getText().toString();
				String password=editTextPassword.getText().toString();
			
				int statusCode=user.login(username, password);
    			if(statusCode==200){
					//Create Order intent and pass the user object
					Intent intent = new Intent(context, AugmateWarehouseOrder.class);
					intent.putExtra("User", user);
					startActivity(intent);
    			}else if(statusCode == 401){
    				Toast.makeText(context, "Unautorized. Check user and password", Toast.LENGTH_LONG).show();
    			}else {
    				Toast.makeText(context, "Failed to connect", Toast.LENGTH_LONG).show();
    				Log.e(AugmateWarehouseActivity.class.toString(), "Failed to Connect file");
    			}
    		}
    	});
    }
}