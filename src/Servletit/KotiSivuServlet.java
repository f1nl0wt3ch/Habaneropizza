package Servletit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBHoitaja.DBHoitaja;
import Luokat.Pizza;

/**
 * Servlet implementation class KotiSivuServlet
 */
@WebServlet("/home")
public class KotiSivuServlet extends HttpServlet { 
	private static final long serialVersionUID = 1L;
    DBHoitaja dbh;
    List<Pizza> list;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KotiSivuServlet() {
        dbh = new DBHoitaja();
        list = new ArrayList<Pizza>();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		naytaPizzaLista(request, response);
		
	}

	
	
	/**
	 * Tämä metodi tulee näkymään pizzalista tietokannasta kotisivulle
	 * @see naytaPizzaLista
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ei palautaa mitään
	 */
	private void naytaPizzaLista(HttpServletRequest request, HttpServletResponse response) {
		try {
                if(dbh.connectDatabase()!= null){
		    list = dbh.getPizzat("pizzat");
			request.setAttribute("pizzaLista", list);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			      } else {
				  System.out.print("connection error");
			  }
			  } catch(Exception e){
				  System.out.print("cannot connect to database");
			  }


}
}
