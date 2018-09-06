import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;

public class TransferJob{

	
	public static void main(String[] args) {
		
		Warehouse recvWarehouse = new Warehouse("Canada House", 4);
		Warehouse dispWarehouse = new Warehouse("Verran", 5);
		ArrayList<Items> listOfItems = new ArrayList();
		LocalDateTime timeSent = LocalDateTime.now();
		
		
		TransferJob tj = new TransferJob(recvWarehouse,  dispWarehouse, listOfItems, timeSent);
		
		
		
	}
	

	Integer id;
	Warehouse recvWarehouse;
	Warehouse dispWarehouse;
	ArrayList<Items> listOfItems;
	LocalDateTime timeSent;
	LocalDateTime timeDelivered;
	String status;

	//transfer job for creating a new job
	public TransferJob(Warehouse recvWarehouse, Warehouse dispWarehouse, ArrayList<Items> listOfItems, LocalDateTime timeSent) {
		this.recvWarehouse = recvWarehouse;
		this.dispWarehouse = dispWarehouse;
		this.listOfItems = listOfItems;
		this.timeSent = timeSent;
		status = "Transit";
		//TODO remove from disp ware house and save to server also return a id
		saveJob();
		
		
	}
	
	
	public TransferJob(String json)
	{
		
	}
	
	private void saveJob()
	{
		Gson g = new Gson();
		
		System.out.println(g.toJson(this));
		Client client = ClientBuilder.newClient();
		Response response = client.target("https://warehouse-transfer.appspot.com/TransferJobs")
				.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.json(g.toJson(this)));
		
		String body = response.readEntity(String.class);		
		
	}
	
	
	
	
	
	public void confirmJob()
	{
		//TODO add to recv ware house and change status on server and set time
	}

	public void deleteJob()
	{
		
		//TODO add to disp ware house and change status on server and set time
	}

	@Override
	public String toString() {
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

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof TransferJob)
		{
			TransferJob tj = (TransferJob) obj;
			if(tj.recvWarehouse.equals(recvWarehouse) && tj.dispWarehouse.equals(dispWarehouse) && tj.timeSent.equals(timeSent))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return super.equals(obj);
		}
	}









}
