package requests;

import java.util.ArrayList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;


public class CreateJobRequest extends Request {

	Long dispWarehouseId;
	Long recvWarehouseId;
	ArrayList<ItemsRequest> listOfItemsRequest;



	public CreateJobRequest(Long dispWarehouseId, Long recvWarehouseId, ArrayList<ItemsRequest> listOfItemsRequest) {
		this.dispWarehouseId = dispWarehouseId;
		this.recvWarehouseId = recvWarehouseId;
		this.listOfItemsRequest = listOfItemsRequest;
	}
	

	public static void main(String[] args) {
		Gson g = new Gson();

		
		CreateJobRequest cjr;
		Long dispWarehouseId = 20720l;
		Long recvWarehouseId = 24618l;
		ArrayList<ItemsRequest> listOfItemsRequest = new ArrayList<ItemsRequest>();
		ItemsRequest i = new ItemsRequest(28454970l,2);
		listOfItemsRequest.add(i);
		cjr = new CreateJobRequest(dispWarehouseId,recvWarehouseId,listOfItemsRequest);
		System.out.println(g.toJson(cjr));
		
		Client client = ClientBuilder.newClient();
		Response response = client.target(Settings.serverPath + "TransferJob")
				.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.json(g.toJson(cjr)));

		String body = response.readEntity(String.class);	
		System.out.println(body);
	}

}
