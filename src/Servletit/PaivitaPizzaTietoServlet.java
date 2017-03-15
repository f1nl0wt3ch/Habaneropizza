package Servletit;

import java.io.IOException;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBHoitaja.DBHoitaja;
import Luokat.Pizza;

/**
 * Servlet implementation class PaivitaPizzaTietoServlet
 */
@WebServlet("/updatepizza")
public class PaivitaPizzaTietoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       DBHoitaja dbh;
       Pizza pizza;
       boolean onAuki= false;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaivitaPizzaTietoServlet() {
        super();
        dbh = new DBHoitaja();
    }

     /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			paivitaTiedot(request, response);
			 request.getRequestDispatcher("admin.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	
			
	}
	
	/**
	 * tarkista syätedyt tiedot ok tai ei, tulee virheilmoitus jos se ei oikean muotoinen.
	 * Jos sopii, tallennetaan tietokantaan. Jos sähköposti on oleva tietokannassa, ei tallenneta. Mutta jos puhelinnumero 
	 * on uusi mutta sähköposti on sama, tallennetaan vain puhelinnumero.
	 * @see paivitaTiedot 
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @throws IOException
	 * @throws SQLException
	 * @return ei mitään
	 */
     public void paivitaTiedot(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String inputStr = request.getParameter("input");
		
		if (inputStr.matches("[\\D]{2,100}")){
			int valinta = Integer.parseInt(request.getParameter("numero"));
			int pizzaId = Integer.parseInt(request.getParameter("pizzaId"));
			
			pizza = new Pizza(pizzaId,inputStr,inputStr,0,null);
			if(dbh.connectDatabase()!= null)
			 dbh.paivitaTiedot(pizza, valinta);
			// onAuki = dbh.Sulje();
			
		}
		else if (inputStr.matches("\\d{4}[./-]\\d{2}[./-]\\d{2}")){
			int valinta = Integer.parseInt(request.getParameter("numero"));
			int pizzaId = Integer.parseInt(request.getParameter("pizzaId"));
			
			Date inputDate;
			try {
				
				inputDate = sdf.parse(inputStr);
				java.sql.Date dateDB = new java.sql.Date(inputDate.getTime());
				pizza = new Pizza(pizzaId,null,null,0,dateDB);
				if(dbh.connectDatabase()!= null)
				dbh.paivitaTiedot(pizza, valinta);
				//onAuki = dbh.Sulje();
			} catch (ParseException e) {
				System.out.print("Error Date");
				e.printStackTrace();
			}
			

		}
		
		else if (inputStr.matches("\\d{1,2}[.]\\d{1,2}")){
			Double inputDouble = Double.parseDouble(inputStr);
			int valinta = Integer.parseInt(request.getParameter("numero"));
			int pizzaId = Integer.parseInt(request.getParameter("pizzaId"));
			pizza = new Pizza(pizzaId,null,null,inputDouble,null);
			if(dbh.connectDatabase()!= null)
			dbh.paivitaTiedot(pizza, valinta);
			//onAuki = dbh.Sulje();
			
		}
		
		
		
		
	}

}
