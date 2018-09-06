package transferserver;


import com.google.appengine.repackaged.org.joda.time.LocalDateTime;
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
	LocalDateTime timeDelivered;
	String status;

	public TransferJob() {}

	public TransferJob(Key<Warehouse> recvWarehouse, Key<Warehouse> dispWarehouse, LocalDateTime timeSent) {
		this.recvWarehouseKey = recvWarehouse;
		this.dispWarehouseKey = dispWarehouse;
		this.timeSent = timeSent;
		status = "Transit";
	}

	@Override
	public String toString() {
		String output = "";
		Warehouse dispWarehouse = ObjectifyService.ofy().load().key(dispWarehouseKey).now();
		Warehouse recvWarehouse = ObjectifyService.ofy().load().key(recvWarehouseKey).now();
		if(status.equals("Transit"))
		{
			return "Dispatched from " + dispWarehouse.name + " on " + timeSent.toString() + " to Warehouse " + recvWarehouse.name; 
		}
		else if(status.equals("Delivered"))
		{
			return "Delivered from " + dispWarehouse.name + " on " + timeDelivered.toString() + " to Warehouse " + recvWarehouse.name; 
		}
		else
		{
			return "Failed to delivered from " + dispWarehouse.name + " sent out at " + timeSent.toString() + " to Warehouse " + recvWarehouse.name;
		}
	}
	
	
	

	
	

}
