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

import be.vdab.entities.Klant;
import be.vdab.repositories.KlantRepository;


@WebServlet("/inloggen.htm")
public class InloggenServlet extends HttpServlet {
	@Resource(name = KlantRepository.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		klantRepository.setDataSource(dataSource);
	}
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/inloggen.jsp";
	private static final String HOME_URL = "/WEB-INF/JSP/index.jsp";
	
	private final transient KlantRepository klantRepository = new KlantRepository();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> fouten = new HashMap<>();
		String gebruikersnaam = request.getParameter("gebruikersnaam");
		String paswoord = request.getParameter("paswoord");
		
		if (!Klant.isGebruikersnaamValid(gebruikersnaam)) {
			fouten.put("naam", "verplicht");
		}
		if (!Klant.isPaswoordValid(paswoord)) {
			fouten.put("paswoord", "verplicht");
		}
		if (fouten.isEmpty()) {
			List<Klant> klant = klantRepository.findKlant(gebruikersnaam);
			if (klantRepository.findPw(gebruikersnaam).equals(paswoord)){
				request.setAttribute("pw", "true");
			} else {
				request.setAttribute("pw", "false");
			}
			if (klant.isEmpty()) {
				fouten.put("klant", "niet gevonden");
				request.setAttribute("fouten", fouten);
				request.getRequestDispatcher(VIEW).forward(request, response);
			}else {
				request.setAttribute("klantInfo", klantRepository.findKlant(gebruikersnaam));
				request.getRequestDispatcher(VIEW).forward(request, response);
				}
			}
			else {
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
		
		
		
		//doGet(request, response);
	}

}