

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataModel.Reservation;
import util.UtilDBDong;


/**
 * Servlet implementation class MyServletHibernateDBDong
 */
@WebServlet("/MyServletHibernateDBDong")
public class MyServletHibernateDBDong extends HttpServlet {
	private static final long serialVersionUID = 1L;

	   public MyServletHibernateDBDong() {
	      super();
	   }

	   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      response.setContentType("text/html");
	      String filePath = "C:\\Users\\ghost\\OneDrive\\Documents\\git\\Spring22\\webproject\\src\\main\\webapp\\WEB-INF\\testData.txt";
	      List<String> testList = util.UtilDBDong.readFile(filePath);
	      String[] tokens;
	      
	      for (String s : testList) {
	    	  tokens = s.split(",");
	    	  UtilDBDong.createReservation(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], Integer.parseInt(tokens[5]));
	      }

	      retrieveDisplayData(response.getWriter());
	   }

	   void retrieveDisplayData(PrintWriter out) {
	      String title = "Reservations List";
	      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
	            "transitional//en\">\n"; //
	      out.println(docType + //
	            "<html>\n" + //
	            "<head><title>" + title + "</title></head>\n" + //
	            "<body bgcolor=\"#f0f0f0\">\n" + //
	            "<h1 align=\"center\">" + title + "</h1>\n");
	      out.println("<ul>");
	      List<Reservation> listReservation = UtilDBDong.listReservations();
	      listReservation = UtilDBDong.sortByTime(listReservation);
	      for (Reservation reservation : listReservation) {
	         System.out.println("[DBG] " + "(ID:" + reservation.getId() + ")" + ", " //
	               + reservation.getFirstName() + ", " //
	               + reservation.getLastName() + ", "
	               + reservation.getPhone() + ", "
	               + reservation.getDate() + ", "
	               + reservation.getTimeOfArrival() + ", "
	               + reservation.getNumberOfPeople());
	         
	         out.println("<li>" + "(ID:" + reservation.getId() + ")" + ", " //
	        		 + reservation.getFirstName() + ", " //
		             + reservation.getLastName() + ", "
		             + reservation.getPhone() + ", "
		             + reservation.getDate() + ", "
		             + reservation.getTimeOfArrival() + ", "
		             + reservation.getNumberOfPeople());
	      }
	      out.println("</ul>");
	      out.println("</body></html>");
	   }

	   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      doGet(request, response);
	   }
}
