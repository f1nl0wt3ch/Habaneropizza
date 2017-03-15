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
import Luokat.HistoriaTilaus;

/**
 * Servlet implementation class TilausTilanneServlet
 */
@WebServlet("/orderstatus")
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
		lista = new ArrayList<HistoriaTilaus>();
	}
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("order.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("keyword").trim();

		tarkistaTilanne(request, response, keyword);

	}

	/**
	 * Asiakas voi tarkistaa kaikki tilaukset sähköpostilla tai
	 * tilausnumerolla. Ensimmäisesti tarkistaa hakusana ok tai ei. Jos se ei
	 * ole sähköposti tai numero, anna virheilmoitus. Jos sähköposti tai
	 * tilausnumero ei ole tietokannassa, myöskin anna ilmoitus.
	 * 
	 * @see tarkistaTilanne
	 * @param HttpServletRequest
	 *            request
	 * @param HttpServletResponse
	 *            response
	 * @param keyword
	 *            hakusana voi olla shköposti tai numero
	 * @throws ServletException
	 * @throws IOException
	 * @return ei mitään.
	 */
	private void tarkistaTilanne(HttpServletRequest request, HttpServletResponse response, String keyword)
			throws ServletException, IOException {
		if (isNotInteger(keyword)) {
			if (dbh.connectDatabase() != null) {
				lista = dbh.tarkistaTilausTilanne(keyword);
				if (lista.size() != 0) {
					request.setAttribute("j", lista.size());
					request.setAttribute("kaikkitilaukset", lista);
					request.getRequestDispatcher("orderDetails.jsp").forward(
							request, response);
				} else 
					request.getRequestDispatcher("errorData.jsp").forward(request, response);
			} 
		} else {
			if (dbh.connectDatabase() != null){
			historia = dbh.tarkistaTilausTilanne(Integer.parseInt(keyword));
			if (historia != null) {
		     request.setAttribute("historiatilaus", historia);
			 request.getRequestDispatcher("orderDetail.jsp").forward(request, response);
			  }else 
			request.getRequestDispatcher("errorData.jsp").forward(request, response);
		}
	  }
	}
	public static boolean isNotInteger(String input){
		try {
			Integer.parseInt(input);
			return false;
		} catch (NumberFormatException e) {
			return true;
		}
	}
}
