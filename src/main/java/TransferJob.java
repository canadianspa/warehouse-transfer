import java.util.ArrayList;
import java.util.Date;

public class TransferJob {
	
	Warehouse recvWarehouse;
	Warehouse dispWarehouse;
	ArrayList<Items> listOfItems;
	Date timeSent;
	boolean delivered;
	
	public TransferJob(Warehouse recvWarehouse, Warehouse dispWarehouse, ArrayList<Items> listOfItems, Date timeSent) {
		this.recvWarehouse = recvWarehouse;
		this.dispWarehouse = dispWarehouse;
		this.listOfItems = listOfItems;
		this.timeSent = timeSent;
		delivered = false;
	}
	
	
	

	
	

}
