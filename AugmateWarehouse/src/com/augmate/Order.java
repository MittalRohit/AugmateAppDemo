package com.augmate;

import java.io.Serializable;
import java.util.Date;

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
	public Order(Company c){
		company=c;
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
