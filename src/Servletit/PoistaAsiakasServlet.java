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
import Luokat.Asiakas;

/**
 * Servlet implementation class PoistaAsiakasServlet
 */
@WebServlet("/deletecustomer")
public class PoistaAsiakasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    DBHoitaja dbh; 
    Asiakas asiakas;
    List<Asiakas> asiakasLista;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PoistaAsiakasServlet() {
        super();
        dbh = new DBHoitaja();
        asiakasLista = new ArrayList<Asiakas>();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		poistaAsiakas(request, response);
	}

	/**
	 * Ensin tarkista syötedyt tiedot. Jos ok, poistetaan asiakkaan tiedot tietokannasta.
	 * @see poistaAsiakas
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ei mitään
	 */
	private void poistaAsiakas(HttpServletRequest request, HttpServletResponse response) {
		String asiakasIdStr = request.getParameter("poistettuID");
		String REGEX_NUMERO = "\\d+";
		if (asiakasIdStr.matches(""))
			try {
				response.sendRedirect("errorNull.jsp");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		else if (!asiakasIdStr.matches(REGEX_NUMERO))
			try {
				request.getRequestDispatcher("errorNumber.jsp").forward(request, response);
			} catch (ServletException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		else {
		
		   try {
			if(dbh.connectDatabase()!= null) {
				int asiakas_id = Integer.parseInt(asiakasIdStr);
				dbh.poistaaAsiakas(asiakas_id);
				request.getRequestDispatcher("admin.jsp").forward(request, response);
				} 
			
		} catch (Exception e) {
			
		} 
		
	}
		
}


	

}
