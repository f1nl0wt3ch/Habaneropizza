package Servletit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Luokat.HistoriaTilaus;
import DBHoitaja.DBHoitaja;

/**
 * Servlet implementation class NaytaTilauksetServlet
 */
@WebServlet("/NaytaTilauksetServlet")
public class NaytaTilauksetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    DBHoitaja dbh;
    List<HistoriaTilaus> tilauslista;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NaytaTilauksetServlet() {
        super();
        dbh = new DBHoitaja();
        tilauslista = new ArrayList<HistoriaTilaus>();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String action = request.getParameter("action");
	    
	    System.out.print(action);
		if(action.equalsIgnoreCase("Nayta kaikki tilaukset")) 
			    naytaKaikki(request, response);
	    else if(action.equalsIgnoreCase("Nayta viisi lahintilausta")) 
		        naytaViisiTilausta(request, response);
	    else
	    	    request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	
	/**
	 * Näytä kaikki tilaukset omistajalle
	 * @see naytaKaikki 
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ei mitään
	 */
  private void naytaKaikki(HttpServletRequest request, HttpServletResponse response) {
		try {
			if(dbh.connectDatabase()!= null){
				tilauslista = dbh.jarjestaTaulukko("tilaukset", "tilauspaiva");
				request.setAttribute("tilauslista", tilauslista);
				request.getRequestDispatcher("admin.jsp").forward(request, response);
			       }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	   }
	
	    
  /**
	 * Näytä viisi tilausta omistajalle kun tietokanta asiakkaasta on iso. Helppo hallinta omistajalle kun uusi tilaus tuulee.
	 * @see naytaKaikki 
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ei mitään
	 */
	private void naytaViisiTilausta(HttpServletRequest request, HttpServletResponse response){
	    	   try {
				if(dbh.connectDatabase()!= null){
						tilauslista = dbh.getLimitTilaukset("tilaukset", 5);
						request.setAttribute("tilauslista", tilauslista);
						request.getRequestDispatcher("admin.jsp").forward(request, response);}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			       }
				}
	        }

	


