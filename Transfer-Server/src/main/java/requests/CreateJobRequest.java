package requests;

import java.util.ArrayList;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

import entities.Item;
import entities.Items;
import entities.TransferJob;
import entities.Warehouse;

public class CreateJobRequest extends Request {

	Long dispWarehouseId;
	Long recvWarehouseId;
	ArrayList<ItemsRequest> listOfItemsRequest;
	
	public CreateJobRequest(Long dispWarehouseId, Long recvWarehouseId, ArrayList<ItemsRequest> listOfItemsRequest) {
		this.dispWarehouseId = dispWarehouseId;
		this.recvWarehouseId = recvWarehouseId;
		this.listOfItemsRequest = listOfItemsRequest;
	}

	public boolean createJob()
	{
		try {
			Key<Warehouse> recvKey = Key.create(Warehouse.class, recvWarehouseId);
			Key<Warehouse> dispKey = Key.create(Warehouse.class, dispWarehouseId);
			ArrayList<Items> listOfItems = new ArrayList<Items>();
			for(ItemsRequest i : listOfItemsRequest)
			{
				Key<Item> itemKey = Key.create(Item.class, i.itemId);
				listOfItems.add(new Items(itemKey,i.quantity));
				
			}
			TransferJob tj = new TransferJob(recvKey,  dispKey, listOfItems);
			ObjectifyService.ofy().save().entity(tj).now();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
	}

}
