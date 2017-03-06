package Servletit;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBHoitaja.*;
import Luokat.Pizza;

/**
 * Servlet implementation class NaytaPizzaServlet
 */
@WebServlet("/NaytaPizzaServlet")
public class NaytaPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     ArrayList<Pizza> list;
     DBHoitaja dbc;
     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NaytaPizzaServlet() {
        super();
        list = null;
        dbc = new DBHoitaja();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		naytaPizzaLista(request, response);
		
	}
    
	
	/**
	 * Näytä pizzalista indexsivulle
	 * @see naytaPizzaLista
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @throws OException
	 * @throws ServletException
	 */
	public void naytaPizzaLista(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		try {
			if(dbc.connectDatabase()){
				list = dbc.getPizzat("pizzat");
				request.setAttribute("pizzaLista", list);
				request.getRequestDispatcher("admin.jsp").forward(request, response);
				} else {
					System.out.print("connection error!");
				}
		} catch (Exception e) {
			System.out.print("Cannot connect to database!");

		}
	}

}
