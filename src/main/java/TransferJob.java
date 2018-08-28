import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class TransferJob implements Serializable{


	Warehouse recvWarehouse;
	Warehouse dispWarehouse;
	ArrayList<Items> listOfItems;
	LocalDateTime timeSent;
	LocalDateTime timeDelivered;
	boolean delivered;

	public TransferJob(Warehouse recvWarehouse, Warehouse dispWarehouse, ArrayList<Items> listOfItems, LocalDateTime timeSent) {
		this.recvWarehouse = recvWarehouse;
		this.dispWarehouse = dispWarehouse;
		this.listOfItems = listOfItems;
		this.timeSent = timeSent;
		delivered = false;
	}

	public void saveJob()
	{
		try {
			FileOutputStream fileOut = new FileOutputStream(Settings.path + "/" + System.identityHashCode(this));
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in " + Settings.path + "/" + System.identityHashCode(this));
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	@Override
	public String toString() {
		if(!delivered)
		{
			return "Dispatched from " + dispWarehouse.name + " on " + timeSent.toString() + " to Warehouse " + recvWarehouse.name; 
		}
		else
		{
			return "Delivered from " + dispWarehouse.name + " on " + timeDelivered.toString() + " to Warehouse " + recvWarehouse.name; 
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
