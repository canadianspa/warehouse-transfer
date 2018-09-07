import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class StockTest {

	public static void main(String[] args) {
		String APIKEY = "***REMOVED***";

		System.out.println("original");
		String sellableid = "28454970";
		String warehouseid = "19852";
		Client client = ClientBuilder.newClient();
		Response response = client.target("https://api.veeqo.com/sellables/" +sellableid + "/warehouses/" + warehouseid + "/stock_entry")
				.request(MediaType.APPLICATION_JSON_TYPE)
				.header("x-api-key", APIKEY)
				.get();


		String body = response.readEntity(String.class);
		Gson g = new Gson();
		StockEntry se = g.fromJson(body, StockEntry.class);
		System.out.println(se.physical_stock_level);
		se.physical_stock_level -= 1;

		System.out.println("putting");

		response = client.target("https://api.veeqo.com/sellables/" +sellableid + "/warehouses/" + warehouseid + "/stock_entry")
				.request(MediaType.APPLICATION_JSON_TYPE)
				.header("x-api-key", APIKEY)
				.put(Entity.json(g.toJson(se)));
		
		System.out.println("getting again");

		
		response = client.target("https://api.veeqo.com/sellables/" +sellableid + "/warehouses/" + warehouseid + "/stock_entry")
				.request(MediaType.APPLICATION_JSON_TYPE)
				.header("x-api-key", APIKEY)
				.get();

		body = response.readEntity(String.class);
		StockEntry se2 = g.fromJson(body, StockEntry.class);
		System.out.println(se2.physical_stock_level);

	}

	class StockEntry
	{
		int physical_stock_level;
		boolean infinite;

		public StockEntry(int physical_stock_level, boolean infinite) {
			this.physical_stock_level = physical_stock_level;
			this.infinite = infinite;
		}


	}


}

