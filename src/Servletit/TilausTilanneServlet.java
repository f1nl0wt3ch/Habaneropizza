package Servletit;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DBHoitaja.DBHoitaja;
import Luokat.HistoriaTilaus;

/**
 * Servlet implementation class TilausTilanneServlet
 */
@WebServlet("/TilausTilanneServlet")
public class TilausTilanneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      DBHoitaja dbh; 
      HistoriaTilaus historia;
      List<HistoriaTilaus> lista;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TilausTilanneServlet() {
        super();
        dbh = new DBHoitaja();
        historia = new HistoriaTilaus();
        lista  = new ArrayList<HistoriaTilaus>();
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("keyword").trim();
		
		tarkistaTilanne(request, response, keyword);
			
	}

	/**
	 * Asiakas voi tarkistaa kaikki tilaukset sähköpostilla tai tilausnumerolla. Ensimmäisesti tarkistaa
	 * hakusana ok tai ei. Jos se ei ole sähköposti tai numero, anna virheilmoitus. Jos sähköposti tai tilausnumero
	 * ei ole tietokannassa, myöskin anna ilmoitus.
	 * @see tarkistaTilanne
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @param keyword hakusana voi olla shköposti tai numero
	 * @throws ServletException
	 * @throws IOException
	 * @return ei mitään.
	 */
	private void tarkistaTilanne(HttpServletRequest request,
			HttpServletResponse response, String keyword) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(1);
		int ordernumber;
		
		String REGEX_ORDERNUMBER ="\\d{6}";
		String REGEX_EMAIL = "\\w.+[@]\\w+[.][A-ZÅÄÖa-zåäö-]{2,3}";
		if (keyword.equals("")) {
			response.sendRedirect("errorNull.jsp");
			
		}
		
		else {
			if(keyword.matches(REGEX_ORDERNUMBER)) {
			ordernumber = Integer.parseInt(keyword);
			if(dbh.connectDatabase()) {
				historia = dbh.tarkistaTilausTilanne(ordernumber);
				if(historia.getTilausNo() == 0)
					request.getRequestDispatcher("errorData.jsp").forward(request, response);
				else {
				session.setAttribute("historiatilaus", historia);
				request.getRequestDispatcher("orderDetail.jsp").forward(request, response);
				
			}
		}
	}
		else if( keyword.matches(REGEX_EMAIL)) {
			 if(dbh.connectDatabase()){
				 lista = dbh.tarkistaTilausTilanne(keyword);
				 if(lista.size() == 0)
					 request.getRequestDispatcher("errorData.jsp").forward(request, response);
				 else {
				 request.setAttribute("j",lista.size());	 
				 session.setAttribute("kaikkitilaukset", lista);
			     request.getRequestDispatcher("orderDetails.jsp").forward(request, response);
				 
			 }
		}
		
	}			 
		else if (!keyword.matches(REGEX_EMAIL) || !keyword.matches(REGEX_ORDERNUMBER)) {
			request.getRequestDispatcher("errorInput.jsp").forward(request, response);
		   }
	    }
    }
}
	 
	
			

