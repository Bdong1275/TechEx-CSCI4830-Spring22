import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Info;
import util.UtilDBDong;

@WebServlet("/SimpleInsertHB")
public class SimpleInsertHB extends HttpServlet implements Info {
   private static final long serialVersionUID = 1L;

   public SimpleInsertHB() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String firstName = request.getParameter("firstName");
      String lastName = request.getParameter("lastName");
      String phone = request.getParameter("phone");
      String date = request.getParameter("date");
      String timeOfArrival = request.getParameter("timeOfArrival");
      String numberOfPeople = request.getParameter("numberOfPeople");
      UtilDBDong.createReservation(firstName, lastName, phone, date, timeOfArrival, Integer.parseInt(numberOfPeople));

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Reservation Result(s)";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
      out.println("<ul>");
      out.println("<li> Name: " + firstName + lastName);
      out.println("<li> Phone: " + phone); 
      out.println("<li> Date: " + date);
      out.println("<li> Arrival Time: " + timeOfArrival);
      out.println("<li> Number Of People Attending: " + numberOfPeople);
      out.println("</ul>");
      out.println("<a href=/" + projectName + "/" + searchWebName + ">Search Data</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
