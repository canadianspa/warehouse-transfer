package requests;

import java.util.ArrayList;

import entities.Item;

public class CreateJobRequest extends Request {

	Long dispWarehouseId;
	Long recvWarehouseId;
	ArrayList<ItemsRequest> listOfItems;
	
	
	class ItemsRequest 
	{
		Long itemId;
		int quantity;
		
		public ItemsRequest(Long itemId, int quantity) {
			this.itemId = itemId;
			this.quantity = quantity;
		}
	}
	
	public void createJob()
	{
		
	}

}
