package entities;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;

import requests.Settings;
import requests.changeTransferJobRequest;

public class TransferJob{

	
	public Long id;
	public Warehouse recvWarehouse;
	public Warehouse dispWarehouse;
	public ArrayList<Items> listOfItems;
	public LocalDateTime timeSent;
	public LocalDateTime timeCompleted;
	public String status;


	public void confirmJob()
	{
		Gson g = new Gson();
		ArrayList<Item> result = new ArrayList<Item>();
		Client client = ClientBuilder.newClient();
		Response response;
		response = client.target(Settings.serverPath + "ConfirmDelivery")
				.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.json(g.toJson(new changeTransferJobRequest(id))));
		String body = response.readEntity(String.class);
		System.out.println(body);

	}

	public void deleteJob()
	{
		Gson g = new Gson();
		ArrayList<Item> result = new ArrayList<Item>();
		Client client = ClientBuilder.newClient();
		Response response;
		response = client.target(Settings.serverPath + "DeleteJob")
				.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.json(g.toJson(new changeTransferJobRequest(id))));
		String body = response.readEntity(String.class);
		System.out.println(body);
	}

	@Override
	public String toString() {
		if(status.equals("Transit"))
		{
			return "Dispatched from " + dispWarehouse.name + " on " + timeSent.toString() + " to Warehouse " + recvWarehouse.name; 
		}
		else if(status.equals("Delivered"))
		{
			return "Delivered from " + dispWarehouse.name + " on " + timeCompleted.toString() + " to Warehouse " + recvWarehouse.name; 
		}
		else
		{
			return "Failed to delivered from " + dispWarehouse.name + " sent out at " + timeSent.toString() + " to Warehouse " + recvWarehouse.name;
		}


	}



}
