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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import be.vdab.entities.Klant;
import be.vdab.repositories.KlantRepository;


@WebServlet("/inloggen.htm")
public class InloggenServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/inloggen.jsp";
	private static final String NIEUWE_KLANT = "%s/nieuweklant.htm";
	private static final String BEVESTIGEN = "%s/bevestigen.htm";
	private final transient KlantRepository klantRepository = new KlantRepository();	
	private static final String KLANTINFO = "klantInfo";
	@Resource(name = KlantRepository.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		klantRepository.setDataSource(dataSource);
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if (request.getParameter("nieuweklant") != null) {
			
			
			response.sendRedirect(response.encodeRedirectURL(
					String.format(NIEUWE_KLANT, request.getContextPath())));
				return;
		}
		
		if (request.getParameter("bevestigen") != null) {
			if (request.getParameter("klantId") != null) {
			session.setAttribute("klantIdLong", request.getParameter("klantId"));
			}
			
			response.sendRedirect(response.encodeRedirectURL(
					String.format(BEVESTIGEN, request.getContextPath())));
				return;
		}
		
		
		
		
		
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
				//passwords match
				if (klant.isEmpty()) {
					fouten.put("klant", "Verkeerde gebruikersnaam of paswoord.");
					request.setAttribute("fouten", fouten);
					request.getRequestDispatcher(VIEW).forward(request, response);
				}else { //successful login

					session.setAttribute(KLANTINFO, klantRepository.findKlant(gebruikersnaam));

					request.getRequestDispatcher(VIEW).forward(request, response);
					}
			} else {
				//passwords do not match
				fouten.put("klant", "Verkeerde gebruikersnaam of paswoord.");
				request.setAttribute("fouten", fouten);
				request.getRequestDispatcher(VIEW).forward(request, response);
			}
			
			}
			else {
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
		
		
	}

}
