package Control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kohdeluokat.Lainaus;
import kohdeluokat.Niteenlainaus;
import DBHoitaja.JDBC;
import DBHoitaja.LainausJDBC;

/**
 * Servlet implementation class LainausServlet
 */
@WebServlet("/LainausServlet")
public class LainausServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JDBC jdbc;
	LainausJDBC lainausJdbc;
	List<Niteenlainaus> listNide;
	Lainaus lainaus;
	List<Integer> listNumerot; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LainausServlet() {
        super();
        jdbc = new JDBC();
        lainausJdbc = new LainausJDBC();
        listNide = new ArrayList<Niteenlainaus>();
        lainaus = new Lainaus();
        listNumerot = new ArrayList<Integer>();  
    }

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		showLainausNumerot(request, response);
		String numeroStr = request.getParameter("numero");
		int numero = Integer.parseInt(numeroStr);
		getLainauksenTiedot(request, response, numero);
		showLainaukset(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	private void showLainausNumerot(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(jdbc.getConnection() != null){
			listNumerot = lainausJdbc.getLainausNumerot();
			request.setAttribute("listNumerot", listNumerot);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			jdbc.suljeConnection();
		}
		
	}
	
	private void getLainauksenTiedot(HttpServletRequest request, HttpServletResponse response, int numero) throws ServletException, IOException {
		if(jdbc.getConnection()!= null){
			lainaus = lainausJdbc.getLainauksen(numero);
			request.setAttribute("lainaus", lainaus);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			jdbc.suljeConnection();
		}
		
	}
	
	private void showLainaukset(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(jdbc.getConnection() != null){
			
			listNide = lainausJdbc.getLiteenlainaukset();
			request.setAttribute("listNide", listNide);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			jdbc.suljeConnection();
			
		}
		
	}

}
