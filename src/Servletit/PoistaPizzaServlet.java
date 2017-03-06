package Servletit;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBHoitaja.DBHoitaja;

/**
 * Servlet implementation class PoistaPizzaServlet
 */
@WebServlet("/PoistaPizzaServlet")
public class PoistaPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBHoitaja dbh;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PoistaPizzaServlet() {
        super();
       dbh = new DBHoitaja();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		poistaPizza(request, response);
		
	}
	
	
	/**
	 * Ensin tarkista syötedyt tiedot. Jos ok, poistetaan pizzan tiedot tietokannasta.
	 * @see poistaPizza
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ei mitään
	 */
	private void poistaPizza(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String pizzaIdStr = request.getParameter("pizzaId");
		
		String REGEX_NUMERO ="\\d+";
		if(pizzaIdStr.matches(""))
			response.sendRedirect("errorNull.jsp");
		else if(!pizzaIdStr.matches(REGEX_NUMERO))
			try {
				request.getRequestDispatcher("errorNumber.jsp").forward(request, response);
				
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		else  {
		int pizza_id = Integer.parseInt(pizzaIdStr);
		 try {
				if(dbh.connectDatabase()){
					dbh.poistaaPizza(pizza_id);
					request.getRequestDispatcher("admin.jsp").forward(request, response);} 
			}
				catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

	
}
