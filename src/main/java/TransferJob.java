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
	boolean delivered;
	
	public TransferJob(Warehouse recvWarehouse, Warehouse dispWarehouse, ArrayList<Items> listOfItems, LocalDateTime timeSent) {
		this.recvWarehouse = recvWarehouse;
		this.dispWarehouse = dispWarehouse;
		this.listOfItems = listOfItems;
		this.timeSent = timeSent;
		delivered = false;
	}
	
	public void saveJob(String path)
	{
		 try {
	         FileOutputStream fileOut = new FileOutputStream(path + "/" + System.identityHashCode(this));
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(this);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in " + path + "/" + System.identityHashCode(this));
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}
	
	
	

	
	

}
