package com.augmate;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Order implements Serializable{
	int idOrder;
	int User_idUser;
	int Company_idCompany;
	Date date;
	int status;
	int employee;
	
	Company company;
	User activeUser;
	
	public Order(Company c){
		company=c;
		activeUser = c.activeUser;
	}
	
	
	public ArrayList<orderProducts> getProducts(){
		ArrayList<orderProducts> products = new ArrayList<orderProducts>();
		Product tmp = new Product();
		int statusCode=-1;
		try{
			DefaultHttpClient client = new DefaultHttpClient();
			
			//Get connection url
            HttpGet httpGet= ConnectionHelper.getHttpGet("orderProducts/"+this.idOrder, this.activeUser.getUsername(), this.activeUser.getPassword());
            
            //Make/exec call
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				
				try{
					JSONArray jObj= new JSONArray(ConnectionHelper.getResponseString(response));
					for(int i=0; i< jObj.length(); i++){
						HashMap<String, String> map= new HashMap<String, String>();
						JSONObject o= jObj.getJSONObject(i);
						orderProducts product=new orderProducts();
						product.parseJson(o);
						products.add(product);
					}
				}catch(JSONException e){
					Log.e(AugmateWarehouseActivity.class.toString(), "Error creating JSon Object");
				}
				
			}
			
		}catch(ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return products;
	}
	
	
	
	/**
	 * Parse json for user object
	 * @param jsobject
	 */
	public void parseJson(JSONObject jsobject){
		try{
			idOrder=(jsobject.has("idOrder"))?jsobject.getInt("idOrder"):0;
			User_idUser=(jsobject.has("User_idUser"))?jsobject.getInt("User_idUser"):0;
			Company_idCompany=(jsobject.has("Company_idCompany"))?jsobject.getInt("Company_idCompany"):0;
			status=(jsobject.has("status"))?jsobject.getInt("status"):0;
			employee=(jsobject.has("employee"))?jsobject.getInt("employee"):0;
			
		}catch(JSONException e){
			Log.e(User.class.toString(), "Failed to parse"+e.toString());
		}
	}
	
	public String toString(){
		return "Order #"+idOrder;
	}
	

	
	
	
}
