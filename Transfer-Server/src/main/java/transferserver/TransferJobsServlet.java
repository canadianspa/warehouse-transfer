package transferserver;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

import entities.Item;
import entities.Items;
import entities.TransferJob;
import entities.Warehouse;
import requests.CreateJobRequest;
import requests.ItemsReply;
import java.util.logging.Logger;

@WebServlet(
		name = "TransferJobServlet",
		urlPatterns = {"/TransferJob"}
		)
public class TransferJobsServlet extends HttpServlet {


	
	class TransferJobReply
	{
		public Long id;
		public Warehouse recvWarehouse;
		public Warehouse dispWarehouse;
		public ArrayList<ItemsReply> listOfItems;
		public Date timeSent;
		public Date timeCompleted;
		public String status;
		public TransferJobReply(Long id, Warehouse recvWarehouse, Warehouse dispWarehouse, ArrayList<ItemsReply> listOfItems,
				Date timeSent, Date timeCompleted, String status) {
			this.id = id;
			this.recvWarehouse = recvWarehouse;
			this.dispWarehouse = dispWarehouse;
			this.listOfItems = listOfItems;
			this.timeSent = timeSent;
			this.timeCompleted = timeCompleted;
			this.status = status;
		}
		
		
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException {

		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
	
		ArrayList<TransferJobReply> result = new ArrayList<TransferJobReply>();
		Iterable<TransferJob> it = ObjectifyService.ofy().load().type(TransferJob.class);
		for(TransferJob t: it)
		{
			 Date timeSent = new Date((long)t.timeSent*1000);
			 Date timeCompleted = new Date((long)t.timeSent*1000);
			 TransferJobReply tjr = new TransferJobReply(t.id,t.getRecvWarehouse(),t.getDispWarehouse(),t.getItems(),timeSent,timeCompleted,t.status);
			 result.add(tjr);
			
		}
		
		Gson g = new Gson();
		response.getWriter().println(g.toJson(result));

		
		




	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final Logger log = Logger.getLogger(TransferJobsServlet.class.getName());
		log.info("Creating a Transfer Job");
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		try {
			Gson g = new Gson();
			
			CreateJobRequest cjr = g.fromJson(request.getReader().readLine(), CreateJobRequest.class);
			if(cjr.listOfItemsRequest.size() == 0)
			{
				throw new Exception("Need Items in Transfer");
			}
			if(cjr.getLevel() < 2)
			{
				throw new Exception("Need higher Level");
			}
			response.getWriter().print(cjr.createJob());
		} catch (Exception e) {
			response.getWriter().print("Failed");

		}
		
	}



}