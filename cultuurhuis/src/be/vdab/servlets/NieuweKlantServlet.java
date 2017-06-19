package be.vdab.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.repositories.KlantRepository;
import be.vdab.entities.Klant;

/**
 * Servlet implementation class NieuweKlantServlet
 */
@WebServlet("/nieuweklant.htm")
public class NieuweKlantServlet extends HttpServlet {
	private static final String VIEW = "/WEB-INF/JSP/nieuweklant.jsp";
	private static final String INLOGGEN = "/WEB-INF/JSP/inloggen.jsp";
	private static final long serialVersionUID = 1L;
	private final transient KlantRepository klantRepository = new KlantRepository();
	@Resource(name = KlantRepository.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		klantRepository.setDataSource(dataSource);
	}
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> fouten = new HashMap<>();
		String gebruikersnaam = request.getParameter("gebruikersnaam");
		List<Klant> klant = klantRepository.findKlant(gebruikersnaam);
		
		if (!klant.isEmpty()) {
			fouten.put("gebruikersnaambezet", "kies een andere gebruikersnaam.");
		}
		if (!(request.getParameter("paswoord").equals(request.getParameter("paswoord2")))) {
			fouten.put("paswoordmatch", "Paswoord en Herhaal pawoord zijn verschillend.");
		}
		if (request.getParameter("voornaam") == null){
			fouten.put("voornaam", "Voornaam niet ingevuld.");
		}
		if (request.getParameter("familienaam") == null){
			fouten.put("familienaam", "familienaam niet ingevuld.");
		}
		if (request.getParameter("straat") == null){
			fouten.put("straat", "straat niet ingevuld.");
		}
		if (request.getParameter("huisnr") == null){
			fouten.put("huisnr", "huisnr niet ingevuld.");
		}
		if (request.getParameter("postcode") == null){
			fouten.put("postcode", "postcode niet ingevuld.");
		}
		if (request.getParameter("gemeente") == null){
			fouten.put("gemeente", "gemeente niet ingevuld.");
		}
		if (request.getParameter("gebruikersnaam") == null){
			fouten.put("gebruikersnaam", "gebruikersnaam niet ingevuld.");
		}
		if (request.getParameter("paswoord") == null){
			fouten.put("paswoord", "paswoord niet ingevuld.");
		}
		if (fouten.isEmpty()) {
			klantRepository.create(new Klant(request.getParameter("voornaam"), request.getParameter("familienaam"), 
					request.getParameter("straat"), request.getParameter("huisnr"), request.getParameter("postcode"), 
					request.getParameter("gemeente"),	gebruikersnaam, request.getParameter("paswoord")));
			request.getRequestDispatcher(INLOGGEN).forward(request, response);
		} else {
		request.setAttribute("fouten", fouten);
		request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}
}
