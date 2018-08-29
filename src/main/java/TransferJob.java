import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class TransferJob implements Serializable{


	String path;
	Warehouse recvWarehouse;
	Warehouse dispWarehouse;
	ArrayList<Items> listOfItems;
	LocalDateTime timeSent;
	LocalDateTime timeDelivered;
	String status;


	public TransferJob(Warehouse recvWarehouse, Warehouse dispWarehouse, ArrayList<Items> listOfItems, LocalDateTime timeSent) {
		this.recvWarehouse = recvWarehouse;
		this.dispWarehouse = dispWarehouse;
		this.listOfItems = listOfItems;
		this.timeSent = timeSent;
		status = "Transit";
	}


	public void refresh() throws Exception
	{
		FileInputStream fileIn = new FileInputStream(path);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		TransferJob tj = (TransferJob) in.readObject();	
		in.close();
		fileIn.close();
		
		this.status = tj.status;
	}
	
	
	public void confirmJob()
	{
		
		try {
			refresh();
			
			if(status.equals("Transit"))
			{
			timeDelivered = LocalDateTime.now();
			status = "Delivered";

			try {
				FileOutputStream fileOut = new FileOutputStream(path);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(this);
				out.close();
				fileOut.close();
				System.out.printf("Serialized data is saved in " + path);
			} catch (IOException i) {
				i.printStackTrace();
			}
			}
			else
			{
			   throw new Exception("Status not expected");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		//add to recv ware house
	}

	public void deleteJob()
	{
		
		try {
			refresh();
			
			if(status.equals("Transit"))
			{
			status = "Failed";
			
			try {
				FileOutputStream fileOut = new FileOutputStream(path);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(this);
				out.close();
				fileOut.close();
				System.out.printf("Serialized data is saved in " + path);
			} catch (IOException i) {
				i.printStackTrace();
			}
			//add to disp warehouse
			}
			else
			{
			 throw new Exception("Status not expected");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}






	public void saveJob()
	{
		try {
			path = Settings.path + "/" + System.identityHashCode(this);
			FileOutputStream fileOut = new FileOutputStream(path);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in " + path);
		} catch (IOException i) {
			i.printStackTrace();
		}
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
