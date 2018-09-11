package transferserver;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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

@WebServlet(
		name = "TransferJobServlet",
		urlPatterns = {"/TransferJob"}
		)
public class TransferJobsServlet extends HttpServlet {


	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException {


		ObjectifyService.register(TransferJob.class); 
		ObjectifyService.register(Warehouse.class); 
		ObjectifyService.register(Item.class); 
		ObjectifyService.register(Items.class); 
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
	
		Iterable<TransferJob> it = ObjectifyService.ofy().load().type(TransferJob.class);
		for(TransferJob t: it)
		{
			response.getWriter().print(t.id + "\r\n");
			response.getWriter().print(t + "\r\n");
			for(Items i: t.listOfItems)
			{
				response.getWriter().print(i + "\r\n");

			}
			response.getWriter().print("\r\n");
		}
		




	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectifyService.register(TransferJob.class); 
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		
		try {
			Gson g = new Gson();
			
			CreateJobRequest cjr = g.fromJson(request.getReader().readLine(), CreateJobRequest.class);
			cjr.createJob();


			response.getWriter().print("Success");
		} catch (JsonSyntaxException e) {
			response.getWriter().print("Failed");

		}
		
	}



}