package Servletit;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DBHoitaja.DBHoitaja;
import Luokat.Pizza;
import Luokat.Tilaukset;



/**
 * Servlet implementation class AikaPalveluServlet
 */
@WebServlet("/invoice")
public class AikaPalveluServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<Pizza> list;
	DBHoitaja dbh;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AikaPalveluServlet() {
        super();
        dbh = new DBHoitaja();
    }
    
    
    public class OtaEtuNimi {
    	
    	/**
         * Ota pizzien ensimmäinen sana välilyönnin jälkeen. Ja perustuu tähän,lasketaan montako pizzaa asiakas on tilaannut 
         * ja paljonko yhteensä maksut.
         * @see otaEtuNimi
         * @throws ServletException
         * @throws IOException
         * @param nimi, täydellinen pizzan nimi
         * @return String
         */
        public String otaEtuNimi(String nimi){
       	 if(nimi.indexOf(' ') > -1)
       		 return nimi.substring(0, nimi.indexOf(' '));
       	 else 
       		 return nimi;
        }
   }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DecimalFormat df = new DecimalFormat("0.00");
		HttpSession session = request.getSession();
		if(tarkistaaTilausAika(request, response)) {
			List<String> listStr = new ArrayList<String>();
			List<Integer>  listInt = new ArrayList<Integer>();
			 List<Tilaukset> tilaukset = new ArrayList<Tilaukset>();
					tilaukset = otaTilauksenTiedot(request,response);
			    for(int i=0; i< tilaukset.size(); i++) {
			    	listStr = tilaukset.get(i).getPizzoja();
			    	listInt = tilaukset.get(i).getMaaria();
			    	
			    }
			    Double yhteensa = Double.parseDouble(df.format(otaYhteensa(request,response)));
			    int sum = listInt.stream().mapToInt(Integer::intValue).sum();
			    int jStr = listStr.size();
			    int jInt = listInt.size();
			    request.setAttribute("jStr", jStr);
			    request.setAttribute("jInt", jInt);
			    session.setAttribute("sum", sum);
			    
			    request.setAttribute("listStr", listStr);
		    	request.setAttribute("listInt", listInt);
		    	session.setAttribute("yhteensa", yhteensa);
			    request.getRequestDispatcher("order.jsp").forward(request, response);
		    	
		}
		else
			request.getRequestDispatcher("close.jsp").forward(request, response);
		 
		
		}
     /**
      * Tämä metodi laskea yhteensä maksut ja palautaa decimaliluku.
      * @see otaYhteensa
      * @param HttpServletRequest request
      * @param HttpServletResponse response
      * @return decimaliluku
      */
	
	private Double otaYhteensa(HttpServletRequest request, HttpServletResponse response){
		
		int maara;
		String pizzan_nimi;
		Double hinta;
		Double yhteensa = null;
		List<Tilaukset> tilaukset = otaTilauksenTiedot(request,response);
		List<String> listNimi = new ArrayList<String>();
		List<Integer> listMaara = new ArrayList<Integer>();
		List<Double> sum = new ArrayList<Double>();
		for (int i=0; i< tilaukset.size(); i++) {
			listNimi = tilaukset.get(i).getPizzoja();
			listMaara = tilaukset.get(i).getMaaria();
		}
		
		for(int i=0; i < listNimi.size(); i++) {
			maara = listMaara.get(i);
			pizzan_nimi = listNimi.get(i);
			if(dbh.connectDatabase() != null){
				hinta = dbh.otaPizzanHinta(pizzan_nimi);
				yhteensa = ( Double ) hinta*maara;
				sum.add(yhteensa);
			}
		}
		Double sumDouble = sum.stream().mapToDouble(Double::doubleValue).sum();
		return sumDouble;
	}
	
	 /**
     * Tämä metodi ota asiakkaan tilaukset ja palautaa listalla .
     * @see otaTilauksenTiedot
     * @return lista
     */
	 private List<Tilaukset> otaTilauksenTiedot(HttpServletRequest request, HttpServletResponse response) {
		OtaEtuNimi OEN = new OtaEtuNimi();
		List<Tilaukset> tilaukset = new ArrayList<Tilaukset>();
		int maara;
		String pizzan_nimi;
		List<Integer> ListMaara = new ArrayList<Integer>();
		List<String> ListNimi, ListNimiNew = new ArrayList<String>();
		
	       if(dbh.connectDatabase() != null){
			ListNimi = dbh.otaTiedotSarake("pizzan_nimi", "pizzat");
			
			for(int i=0; i< ListNimi.size(); i++) {
				pizzan_nimi = ListNimi.get(i);
			    maara=Integer.parseInt(request.getParameter(OEN.otaEtuNimi(pizzan_nimi)));
			    if (maara != 0){
			    	ListNimiNew.add(pizzan_nimi);
			    	ListMaara.add(maara);
			    	
			    }
			}
		}
	       tilaukset.add(new Tilaukset(ListNimiNew, ListMaara));
	       
	      
		return tilaukset;
		
		
	      
	}
 
	 /**
     * Tämä metodi tarkistaa tilausaika on sopiva palvelun aukioloajalle tai ei. Jos sopii, asiakas saa tilata.
     * Jos ei antaa ilmoitus.
     * @see tarkistaaTilausAika
     * @param HttpServletRequest request, HttpServletResponse response
     * @return oikein tai väärin
     */
	
	private boolean tarkistaaTilausAika(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		boolean tosi = true;
		/*SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Date kelo = new Date();
		String aika = sdf.format(kelo);
		int tunti = Integer.parseInt(aika.substring(0,2));
		int minuutti = Integer.parseInt(aika.substring(3,5));*/
		String aika = request.getParameter("asiakasKello");
		
		System.out.println("Aika "+ aika);
		System.out.println("Aika "+ aika.split(":")[0]);
		int tunti = Integer.parseInt(aika.split(":")[0]);
		int minuutti = Integer.parseInt(aika.split(":")[1]);
		if (10 <= tunti && tunti < 23) {
			return tosi;
			}
		else if (tunti == 23 && minuutti == 0 ) {
			return tosi;
		}
		else {
			return !tosi;
		}
	}

}
