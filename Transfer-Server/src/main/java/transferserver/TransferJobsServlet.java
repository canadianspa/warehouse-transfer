package transferserver;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.LocalDateTime;

import com.google.appengine.repackaged.com.google.type.Date;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

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
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");




		Warehouse recvWarehouse = new Warehouse("Canada House", 4L);
		Warehouse dispWarehouse = new Warehouse("Verran", 5L);
		ObjectifyService.ofy().save().entity(recvWarehouse).now();
		ObjectifyService.ofy().save().entity(dispWarehouse).now();

		Key<Warehouse> recvKey = Key.create(Warehouse.class, 4L);
		Key<Warehouse> dispKey = Key.create(Warehouse.class, 5L);
	
		
		TransferJob tj = new TransferJob(recvKey,  dispKey, new LocalDateTime());

		ObjectifyService.ofy().save().entity(tj).now();
	
		Iterable<TransferJob> it = ObjectifyService.ofy().load().type(TransferJob.class);
		for(TransferJob t: it)
		{
			response.getWriter().print(t.id + "\r\n");
			response.getWriter().print(t.timeSent + "\r\n");
			response.getWriter().print(t + "\r\n");

		}
		




	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectifyService.register(TransferJob.class); 
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");

		ObjectifyService.ofy().save().entity(new TransferJob()).now();

		response.getWriter().print(request.toString());
	}



}