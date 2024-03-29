package requests;

import java.util.ArrayList;

import com.googlecode.objectify.Key;
import entities.Item;
import entities.Items;
import entities.TransferJob;
import entities.User;
import entities.Warehouse;

public class CreateJobRequest extends Request {

	Long dispWarehouseId;
	Long recvWarehouseId;
	public ArrayList<ItemsRequest> listOfItemsRequest;
	

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
			Key<User> userKey = Key.create(User.class, getUser().email);
			TransferJob tj = new TransferJob(recvKey,  dispKey, listOfItems,userKey);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
	}

}
