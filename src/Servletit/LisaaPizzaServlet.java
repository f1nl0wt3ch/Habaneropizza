package Servletit;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBHoitaja.DBHoitaja;
import Luokat.Pizza;

/**
 * Servlet implementation class LisaaPizzaServlet
 */
@WebServlet("/LisaaPizzaServlet")
public class LisaaPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     DBHoitaja dbh; 

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LisaaPizzaServlet() {
        super();
        dbh = new DBHoitaja();
        
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if( lisaaPizzat(request, response))
				   request.getRequestDispatcher("admin.jsp").forward(request, response);
				  else
					 request.getRequestDispatcher("admin.jsp").forward(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
	/**
	 * Laita uusi pizza tietokantaan
	 * @see lisaaPizzat
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @throws ParseException
	 * @return väärin tai oikein
	 */
	public boolean lisaaPizzat(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		   String nimi = request.getParameter("name");
		   String taytteet = request.getParameter("details");
		   
		   String hintaStr = request.getParameter("price");
		   Double hinta = Double.parseDouble(hintaStr);
		   Date date = sdf.parse(request.getParameter("date"));
		   java.sql.Date dateDB = new java.sql.Date(date.getTime());
		
		   try {
			   if(dbh.connectDatabase()!= null){
				   Pizza pizza = new Pizza(0, nimi, taytteet, hinta, dateDB);
				   dbh.lisaaPizzat(pizza);
				   }
			   
		   } catch(Exception e) {
			   
		   }
		   
		return true;
		   
		  
		
	}

}
