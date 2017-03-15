package Servletit;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DBHoitaja.DBHoitaja;

/**
 * Servlet implementation class KirjauduSisaanServlet
 */
@WebServlet("/login")
public class KirjauduSisaanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     DBHoitaja dbh;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KirjauduSisaanServlet() {
        super();
        dbh = new DBHoitaja();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			request.getRequestDispatcher("login.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(tarkistaTiedot(request, response))
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		else
			request.getRequestDispatcher("backlogin.jsp").forward(request, response);
	}
	
	/**
	 * tämä metodi tarkistaa onko syötedyt tiedot sama kuin tietokannassa tai ei. 
	 * Jos oikein anna kirjautua sisään ylläpito sivulle, jos ei tulee ilmoitus virhe.
	 * @see tarkistaTiedot
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @throws IOException
	 * @return true tai false
	 */
	public boolean tarkistaTiedot(HttpServletRequest request, HttpServletResponse response) throws IOException {
		boolean paluu = true;
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(900);
	    String user = request.getParameter("username");
		String pass = request.getParameter("password");
		System.out.println(user+" "+pass);
		session.setAttribute("username",user);
		if(dbh.connectDatabase()!= null){
			paluu = (dbh.tarkistaTiedot(user, pass) == 0)? false : true;
		}
		return paluu;
		
   }
}
