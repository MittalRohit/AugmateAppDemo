package com.augmate;

import java.io.Serializable;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Product implements Serializable{
	int idProduct;
	String name;
	int price;
	String description;
	String image;
	int Company_idCompany;
	int sku;
	int mapX;
	int mapY;
	
	int Picked;
	int Quantity;
	
	public void parseJson(JSONObject jsobject){
		try{
			idProduct=(jsobject.has("idOrder"))?jsobject.getInt("idOrder"):0;
			price=(jsobject.has("price"))?jsobject.getInt("price"):0;
			Company_idCompany=(jsobject.has("Company_idCompany"))?jsobject.getInt("Company_idCompany"):0;
			sku=(jsobject.has("sku"))?jsobject.getInt("sku"):0;
			mapX=(jsobject.has("mapX"))?jsobject.getInt("mapX"):0;
			mapY=(jsobject.has("mapY"))?jsobject.getInt("mapY"):0;
			
			Picked=(jsobject.has("Picked"))?jsobject.getInt("Picked"):0;
			Quantity=(jsobject.has("Quantity"))?jsobject.getInt("Quantity"):0;
			
			
		}catch(JSONException e){
			Log.e(User.class.toString(), "Failed to parse"+e.toString());
		}
	}
	
	public String toString(){
		return "Product #"+idProduct;
	}
}


