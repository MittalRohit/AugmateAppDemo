package com.augmate;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class AugmateWarehouseOrder extends Activity {
	ArrayList<Order> orders;
	User user;
	Context context;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    context=(Context)this;
	    // TODO Auto-generated method stub
	    setContentView(R.layout.orders);
	    
	    user= (User)getIntent().getSerializableExtra("User");
	    
	    Toast.makeText(this, "Hello "+user.getName()+" we are loading orders. Please wait", Toast.LENGTH_LONG).show();
	    
	    Company company= user.getCompany();
	    orders= company.getPendingOrders();
	    
	    ListView listView= (ListView) findViewById(R.id.listView1);
	    
	    /*ArrayAdapter<Order> adapter= new ArrayAdapter<Order>(
	    		this, 
	    		android.R.layout.simple_list_item_1, 
	    		android.R.id.text1, 
	    		orders
	    		);
	    */
	    orderArrayAdapter adapter= new orderArrayAdapter(this, orders);
	    
	    listView.setAdapter(adapter);
	    
	    listView.setOnItemClickListener(new OnItemClickListener() {
	    	
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),
    			"Click ListItem Number " + orders.get(position).idOrder, Toast.LENGTH_LONG)
    			.show();
				Intent intent = new Intent(context, AugmateWarehouseProduct.class);
				intent.putExtra("Order", orders.get(position));
				startActivity(intent);
			}
	    }); 
	    
	}

}
