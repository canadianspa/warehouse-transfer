package entities;



import java.util.ArrayList;
import java.util.Date;

import org.joda.time.LocalDateTime;

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

	public TransferJob() {}

	public TransferJob(Key<Warehouse> recvWarehouse, Key<Warehouse> dispWarehouse, ArrayList<Items> listOfItems) {
		this.recvWarehouseKey = recvWarehouse;
		this.dispWarehouseKey = dispWarehouse;
		this.listOfItems = listOfItems;
		status = "Transit";
		timeSent = LocalDateTime.now().toDate();
	}

	public void confirmDelivery()
	{
		if(status.equals("Transit"))
		{
			timeCompleted = LocalDateTime.now().toDate();
			status = "Delivered";
			ObjectifyService.ofy().save().entity(this).now();
		}

	}
	
	public void deleteDelivery()
	{
		if(status.equals("Transit"))
		{
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
