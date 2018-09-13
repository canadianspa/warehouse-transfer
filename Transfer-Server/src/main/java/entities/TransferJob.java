package entities;



import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.joda.time.LocalDateTime;

import com.google.gson.Gson;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import requests.ItemsReply;

@Entity
public class TransferJob {
	public @Id Long id; // Can be Long, long, or String
	public Key<Warehouse> recvWarehouseKey;
	public Key<Warehouse> dispWarehouseKey;
	public ArrayList<Items> listOfItems;
	public Date timeSent;
	public Date timeCompleted;
	public String status;
	public Key<User> creator;
	String APIKEY = "***REMOVED***";


	public TransferJob() {}

	public TransferJob(Key<Warehouse> recvWarehouse, Key<Warehouse> dispWarehouse, ArrayList<Items> listOfItems, Key<User> Creator) {
		this.recvWarehouseKey = recvWarehouse;
		this.dispWarehouseKey = dispWarehouse;
		this.listOfItems = listOfItems;
		this.creator = creator;
		status = "Transit";
		timeSent = LocalDateTime.now().toDate();
		for(Items i: listOfItems)
		{
			Client client = ClientBuilder.newClient();
			Response response = client.target("https://api.veeqo.com/sellables/" + i.getItem().id + "/warehouses/" + getDispWarehouse().id + "/stock_entry")
					.request(MediaType.APPLICATION_JSON_TYPE)
					.header("x-api-key", APIKEY)
					.get();


			String body = response.readEntity(String.class);
			Gson g = new Gson();
			StockEntry se = g.fromJson(body, StockEntry.class);
			System.out.println(getDispWarehouse().name);
			System.out.println(i.getItem().productTitle + ":" + se.physical_stock_level);
			se.physical_stock_level -= i.quantity;


			response = client.target("https://api.veeqo.com/sellables/" + i.getItem().id  + "/warehouses/" + getDispWarehouse().id + "/stock_entry")
					.request(MediaType.APPLICATION_JSON_TYPE)
					.header("x-api-key", APIKEY)
					.put(javax.ws.rs.client.Entity.json(g.toJson(se)));
			
			response = client.target("https://api.veeqo.com/sellables/" + i.getItem().id  + "/warehouses/" + getDispWarehouse().id + "/stock_entry")
					.request(MediaType.APPLICATION_JSON_TYPE)
					.header("x-api-key", APIKEY)
					.get();

			body = response.readEntity(String.class);
			StockEntry se2 = g.fromJson(body, StockEntry.class);
			System.out.println(i.getItem().productTitle + ":" + se.physical_stock_level);
		}
		ObjectifyService.ofy().save().entity(this).now();
	}

	class StockEntry
	{
		int physical_stock_level;
		boolean infinite;

		public StockEntry(int physical_stock_level, boolean infinite) {
			this.physical_stock_level = physical_stock_level;
			this.infinite = infinite;
		}


	}
	
	public void confirmDelivery()
	{
		if(status.equals("Transit"))
		{
			for(Items i: listOfItems)
			{
				Client client = ClientBuilder.newClient();
				Response response = client.target("https://api.veeqo.com/sellables/" + i.getItem().id + "/warehouses/" + getRecvWarehouse().id + "/stock_entry")
						.request(MediaType.APPLICATION_JSON_TYPE)
						.header("x-api-key", APIKEY)
						.get();


				String body = response.readEntity(String.class);
				Gson g = new Gson();
				StockEntry se = g.fromJson(body, StockEntry.class);
				System.out.println(getRecvWarehouse().name);
				System.out.println(i.getItem().productTitle + ":" + se.physical_stock_level);
				se.physical_stock_level += i.quantity;


				response = client.target("https://api.veeqo.com/sellables/" + i.getItem().id  + "/warehouses/" + getRecvWarehouse().id + "/stock_entry")
						.request(MediaType.APPLICATION_JSON_TYPE)
						.header("x-api-key", APIKEY)
						.put(javax.ws.rs.client.Entity.json(g.toJson(se)));
				
				response = client.target("https://api.veeqo.com/sellables/" + i.getItem().id  + "/warehouses/" + getRecvWarehouse().id + "/stock_entry")
						.request(MediaType.APPLICATION_JSON_TYPE)
						.header("x-api-key", APIKEY)
						.get();

				body = response.readEntity(String.class);
				StockEntry se2 = g.fromJson(body, StockEntry.class);
				System.out.println(i.getItem().productTitle + ":" + se.physical_stock_level);
			}
			timeCompleted = LocalDateTime.now().toDate();
			status = "Delivered";
			ObjectifyService.ofy().save().entity(this).now();
		}

	}
	
	public void deleteDelivery()
	{
		if(status.equals("Transit"))
		{
			for(Items i: listOfItems)
			{
				Client client = ClientBuilder.newClient();
				Response response = client.target("https://api.veeqo.com/sellables/" + i.getItem().id + "/warehouses/" + getDispWarehouse().id + "/stock_entry")
						.request(MediaType.APPLICATION_JSON_TYPE)
						.header("x-api-key", APIKEY)
						.get();


				String body = response.readEntity(String.class);
				Gson g = new Gson();
				StockEntry se = g.fromJson(body, StockEntry.class);
				System.out.println(getDispWarehouse().name);
				System.out.println(i.getItem().productTitle + ":" + se.physical_stock_level);
				se.physical_stock_level += i.quantity;


				response = client.target("https://api.veeqo.com/sellables/" + i.getItem().id  + "/warehouses/" + getDispWarehouse().id + "/stock_entry")
						.request(MediaType.APPLICATION_JSON_TYPE)
						.header("x-api-key", APIKEY)
						.put(javax.ws.rs.client.Entity.json(g.toJson(se)));
				
				response = client.target("https://api.veeqo.com/sellables/" + i.getItem().id  + "/warehouses/" + getDispWarehouse().id + "/stock_entry")
						.request(MediaType.APPLICATION_JSON_TYPE)
						.header("x-api-key", APIKEY)
						.get();

				body = response.readEntity(String.class);
				StockEntry se2 = g.fromJson(body, StockEntry.class);
				System.out.println(i.getItem().productTitle + ":" + se.physical_stock_level);
			}
			timeCompleted = LocalDateTime.now().toDate();
			status = "Deleted";
			ObjectifyService.ofy().save().entity(this).now();
		}
	}
	
	public Warehouse getDispWarehouse()
	{
		return ObjectifyService.ofy().load().key(dispWarehouseKey).now();
	}
	
	public Warehouse getRecvWarehouse()
	{
		return ObjectifyService.ofy().load().key(recvWarehouseKey).now();
	
	}
	
	public ArrayList<ItemsReply> getItems()
	{
		ArrayList<ItemsReply> output = new ArrayList<ItemsReply>();
		for(Items i: listOfItems)
		{
			output.add(new ItemsReply(i.getItem(),i.quantity));
		}
		return output;
	}
	@Override
	public String toString() {
		String output = "";
		Warehouse dispWarehouse = ObjectifyService.ofy().load().key(dispWarehouseKey).now();
		Warehouse recvWarehouse = ObjectifyService.ofy().load().key(recvWarehouseKey).now();
		if(status.equals("Transit"))
		{
			return "Dispatched from " + dispWarehouse.name + " on " + timeSent.toString() + " to " + recvWarehouse.name; 
		}
		else if(status.equals("Delivered"))
		{
			return "Delivered from " + dispWarehouse.name + " on " + timeCompleted.toString() + " to " + recvWarehouse.name; 
		}
		else
		{
			return "Failed to delivered from " + dispWarehouse.name + " sent out at " + timeSent.toString() + " to " + recvWarehouse.name + " but returned at " + timeCompleted.toString();
		}
	}







}
