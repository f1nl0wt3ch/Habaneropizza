package Servletit;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DBHoitaja.DBHoitaja;
import Luokat.Asiakas;
import Luokat.HistoriaTilaus;

/**
 * Servlet implementation class SaadaTilausServlet
 */
@WebServlet("/SaadaTilausServlet")
public class SaadaTilausServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    DBHoitaja dbh;
    RequestDispatcher disp;
    Asiakas asiakas;
    HistoriaTilaus historia;
    String nimi, puhelinnumeroStr, spostiosoite;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaadaTilausServlet() {
        super();
        dbh = new DBHoitaja();    }

	/**
	 * @throws IOException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
   
    
   
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			saadaAsiakasTilaus(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Luodaan automaattisesti tilausnumero omistajalle hallita tilaus.
	 * @see generateTilausNumero 
	 * @return int lukuarvo koostuu kuusi numeroa
	 */
	public int generateTilausNumero(){
		Random rnd = new Random();
	    int tilausNo = 100000 + rnd.nextInt(900000);
	    if(tilausNo > 0)
	           return tilausNo;
	    else
	    	   return -tilausNo ;
	}
	
	
	/**
	 * Tarkista asiakkaan syötedyt tiedot tilauslomakkeesta. Tulee virheilmoitus jos pelkästään yksi niistä ei ok, 
	 * annetaan virheilmoitus. Jos kaikki on ok, annetaan tilaamaan ja tallennetaan tilauksensa tietokantaan. Sen jälkeen 
	 * lähetetään vahvistus asiakkaan sähköposti osoitteeseen.
	 * @see saadaAsiakasTilaus 
	 * @throws ServletException, IOException, ParseException
	 * @return ei mitään
	 */
	public void saadaAsiakasTilaus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		
		HttpSession session = request.getSession();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
		
		Date tanaan = new Date();
		String tanaanStr = sdf.format(tanaan);
		String tilausaikaStr = sdf1.format(tanaan);
		session.setAttribute("tilausaikaStr", tilausaikaStr);
		
		java.sql.Date tilauspaiva =  java.sql.Date.valueOf(tanaanStr);
		int maara = (int) session.getAttribute("sum");
		String REGEX_EMAIL = "\\w.+[@]\\w+[.][A-ZAa-z-\\-\\.]{2,}";
		String REGEX_NIMI = "[A-ZÅÄÖa-zåäo\\s]{1,}";
		String REGEX_PUHNUMERO = "[0]\\d{9}";
		String nimi = request.getParameter("name").trim();
	
		String puhelinnumeroStr = request.getParameter("phone").trim();
		String spostiosoite = request.getParameter("email").trim();
		String maksutapa = request.getParameter("Maksutapa");
		int sum = (int) session.getAttribute("sum");
		double yhteensa = (double) session.getAttribute("yhteensa");
		int tilausnumero = new SaadaTilausServlet().generateTilausNumero();
		session.setAttribute("tilausnumero", tilausnumero);
		Double maksut = (Double) session.getAttribute("yhteensa"); 
		
		LahettaSpostiServlet lss = new LahettaSpostiServlet();
		String subject = "Tilauksenne on otettu vastaan " + tilausnumero;
		String message = "Kiitos tilauksestanne,\nTilausnumeronne on "+ tilausnumero+""+
		"\nMäärä : "+sum+" kpl"+
		"\nYhteensä : "+yhteensa+" €"+
		"\nMaksutapa : "+maksutapa+
		"\nTilauksi on vastanotettu ja voi noutaa tilauksen pizzeriasta puolen tunnin kuluttua."+
		"\n"+
		"\nYstävällisin terveisin, \nHabanero pizzarien"+
		"\n"+"\n"+
		"\nMalminkaari 15"+ 
		"\n00700 Helsinki"+"\n050-654-3210";
		
		if (puhelinnumeroStr == null || spostiosoite == null || nimi == null)
	    	request.getRequestDispatcher("errorNull.jsp").forward(request, response); 	
		else if (puhelinnumeroStr.matches(REGEX_PUHNUMERO) && spostiosoite.matches(REGEX_EMAIL) && nimi.matches(REGEX_NIMI)) 
    	{
    		if(dbh.connectDatabase()!= null) {
				 int puhelinnumero = Integer.parseInt(puhelinnumeroStr);
				 asiakas = new Asiakas(0, nimi,puhelinnumero, spostiosoite);
				 historia = new HistoriaTilaus(tilausnumero,maara,maksut,maksutapa,spostiosoite,tilauspaiva);
				           if( dbh.tarkistaAsiakas(spostiosoite) == null) {
				        	   dbh.lisaaAsiakkaat(asiakas);
			    	           dbh.tallentaTilausTietokantaan(historia);
				        	     }
				           else {
				        	   dbh.paivitaPuhelinnumero("puhelinnumero","spostiosoite","asiakkaat", puhelinnumeroStr, spostiosoite);
				        	   dbh.tallentaTilausTietokantaan(historia);
				    	         
				      }
				 
					 }
         
            if(lss.sendMail(spostiosoite, subject, message, request, response))
			request.getRequestDispatcher("sucess.jsp").forward(request, response); 
         
    	} 
	    
	    else if (!nimi.matches(REGEX_NIMI)) 
	    	request.getRequestDispatcher("errorName.jsp").forward(request, response); 
	    else if(!puhelinnumeroStr.matches(REGEX_PUHNUMERO))
	        request.getRequestDispatcher("errorPhone.jsp").forward(request, response); 	
	    else if(!spostiosoite.matches(REGEX_EMAIL))
	    	request.getRequestDispatcher("errorEmail.jsp").forward(request, response); 	
	    
	    
	    	}


}
