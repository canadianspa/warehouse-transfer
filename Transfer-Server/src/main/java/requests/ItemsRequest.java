package requests;

public class ItemsRequest {
	Long itemId;
	int quantity;
	
	public ItemsRequest(Long itemId, int quantity) {
		this.itemId = itemId;
		this.quantity = quantity;
	}
}
