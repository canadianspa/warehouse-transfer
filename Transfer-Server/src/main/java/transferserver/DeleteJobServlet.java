package transferserver;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.googlecode.objectify.ObjectifyService;

import entities.TransferJob;
import requests.CreateJobRequest;
import requests.changeTransferJobRequest;
@WebServlet(
		name = "DeleteJobServlet",
		urlPatterns = {"/DeleteJob"}
		)
public class DeleteJobServlet  extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		
		try {
			Gson g = new Gson();
			
			changeTransferJobRequest cjr = g.fromJson(request.getReader().readLine(), changeTransferJobRequest.class);
			TransferJob tj = ObjectifyService.ofy().load().type(TransferJob.class).id(cjr.TransferJobId).now();
			if(cjr.getLevel() < 3 && !ObjectifyService.ofy().load().key(tj.creator).now().email.equals(cjr.getUser().email))
			{
				throw new Exception("Need higher Level or need to be creator");
			}
			tj.deleteDelivery();
			
		} catch (Exception e) {
			response.getWriter().print("Failed");

		}
		
	}
}
