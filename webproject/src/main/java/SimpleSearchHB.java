import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataModel.Reservation;
import util.Info;
import util.UtilDBDong;

@WebServlet("/SimpleSearchHB")
public class SimpleSearchHB extends HttpServlet implements Info {
   private static final long serialVersionUID = 1L;

   public SimpleSearchHB() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String keyword = request.getParameter("keyword").trim();

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Reservations List";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
      out.println("<ul>");

      List<Reservation> listReservations = null;
      if (keyword != null && !keyword.isEmpty()) {
    	  listReservations = UtilDBDong.listReservations(keyword);
      } else {
    	  listReservations = UtilDBDong.listReservations();
      }
      
      if (listReservations.isEmpty()) {
    	  display(out);
      }else {
    	  display(listReservations, out);  
      }
      
      out.println("</ul>");
      out.println("<a href=/" + projectName + "/" + searchWebName + ">Search Data</a> <br>");
      out.println("</body></html>");
   }

   void display(List<Reservation> listReservations, PrintWriter out) {
      for (Reservation reservation : listReservations) {
         System.out.println("[DBG] " + reservation.getId() + ", " //
        		 + reservation.getFirstName() + ", " //
	             + reservation.getLastName() + ", "
	             + reservation.getPhone() + ", "
	             + reservation.getDate() + ", "
	             + reservation.getTimeOfArrival() + ", "
	             + reservation.getNumberOfPeople());

         out.println("<li>" + reservation.getId() + ", " //
        		 + reservation.getFirstName() + ", " //
	             + reservation.getLastName() + ", "
	             + reservation.getPhone() + ", "
	             + reservation.getDate() + ", "
	             + reservation.getTimeOfArrival() + ", "
	             + reservation.getNumberOfPeople() + "</li>");
      }
   }
   
   void display(PrintWriter out) {
	   
	   System.out.println("NOT FOUND (Please make sure to spell the person's first name correctly)");
	   out.println("<h2>" + "NOT FOUND" + "</h2>");
	   out.println("<li>" + "(Please make sure to spell the person's first name correctly)" + "</li>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
