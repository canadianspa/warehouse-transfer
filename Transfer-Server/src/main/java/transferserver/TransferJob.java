package transferserver;


import org.joda.time.LocalDateTime;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class TransferJob {
	@Id Long id; // Can be Long, long, or String
	Key<Warehouse> recvWarehouseKey;
	Key<Warehouse> dispWarehouseKey;
	LocalDateTime timeSent;
	LocalDateTime timeCompleted;
	String status;

	public TransferJob() {}

	public TransferJob(Key<Warehouse> recvWarehouse, Key<Warehouse> dispWarehouse, LocalDateTime timesent) {
		this.recvWarehouseKey = recvWarehouse;
		this.dispWarehouseKey = dispWarehouse;
		this.timeSent = timesent;
		status = "Transit";
	}

	public void confirmDelivery()
	{
		if(status.equals("Transit"))
		{
			timeCompleted = LocalDateTime.now();
			status = "Delivered";
			ObjectifyService.ofy().save().entity(this).now();
		}

	}
	
	public void deleteDelivery()
	{
		if(status.equals("Transit"))
		{
			timeCompleted = LocalDateTime.now();
			status = "Deleted";
			ObjectifyService.ofy().save().entity(this).now();
		}
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
