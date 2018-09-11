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

public class TransferJob{

	
	public Long id;
	public Warehouse recvWarehouse;
	public Warehouse dispWarehouse;
	public ArrayList<Items> listOfItems;
	public Date timeSent;
	public Date timeCompleted;
	public String status;

	
	
	public TransferJob(String json)
	{
		
	}
	
	
	public void confirmJob()
	{
	
	}

	public void deleteJob()
	{
		
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
