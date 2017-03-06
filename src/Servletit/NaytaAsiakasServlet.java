package Servletit;



import java.io.IOException;
import java.util.ArrayList;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DBHoitaja.DBHoitaja;
import Luokat.Asiakas;



/**
 * Servlet implementation class NaytaAsiakasServlet
 */
@WebServlet("/NaytaAsiakasServlet")
public class NaytaAsiakasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 ArrayList<Asiakas> list ;
     RequestDispatcher disp;
     DBHoitaja dbc;
     HttpSession istunto;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NaytaAsiakasServlet() {
        super();
        
        dbc = new DBHoitaja();
        list = null;
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		naytaAsiakasLista(request, response);
		
	}
    
	/**
	 * Näytä omistajalle asiakaslista
	 * @see naytaAsiakasLista
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ei mitään
	 */
	private void naytaAsiakasLista(HttpServletRequest request,HttpServletResponse response) {
		try{
		if(dbc.connectDatabase()){
		    
			list = dbc.getAsiakkaat("asiakkaat");
			//istunto = request.getSession(true);
			request.setAttribute("asiakasLista", list);
			request.getRequestDispatcher("admin.jsp").forward(request, response);
			      } else {
				  System.out.print("connection error");
			  }
			  } catch(Exception e){
				  System.out.print("cannot connect to database");
			  }
	}

	

}
