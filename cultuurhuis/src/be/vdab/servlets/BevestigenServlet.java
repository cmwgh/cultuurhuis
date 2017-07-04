package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import be.vdab.repositories.VoorstellingenRepository;
import be.vdab.util.StringUtils;

/**
 * Servlet implementation class BevestigenServlet
 */
@WebServlet("/bevestigen.htm")
public class BevestigenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/bevestigen.jsp";
	private static final String MANDJE = "mandje";
	//private final transient KlantRepository klantRepository = new KlantRepository();	
	private final transient VoorstellingenRepository voorstellingenRepository = new VoorstellingenRepository();
	long klantIdL;
	
	@Resource(name = VoorstellingenRepository.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		voorstellingenRepository.setDataSource(dataSource);
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
		HttpSession session = request.getSession(false);
		String klantIdString = (String) session.getAttribute("klantIdLong");
		if (StringUtils.isLong(klantIdString)) {
			klantIdL = Long.parseLong(klantIdString);
		}

		
		if (session != null) {
//			@SuppressWarnings("unchecked")
//			List<Klant> klant =  (List<Klant>) session.getAttribute("klantInfo");
//			for(Klant tp : klant) {
//				long klantId = ((Klant)tp).getId();
//			}
			//List klantDetails = (List) session.getAttribute("klantInfo");
			//long klantid = (long) klant.get(0);

			//klantID = klant.get(0);
			@SuppressWarnings("unchecked")
			Map<Long, Long> klantMandje = (Map<Long, Long>) session.getAttribute(MANDJE);
			Map<Long, Long> mandjeMislukte = new HashMap<>();
			if (klantMandje != null) {
			Set<Long> mislukte = (Set<Long>) voorstellingenRepository.bevestig(klantIdL, klantMandje);
			if (!mislukte.isEmpty()) {
				for (Long item : mislukte) {
					if (klantMandje.containsKey(item)){
						mandjeMislukte.put(item, klantMandje.get(item));
						klantMandje.remove(item);
					}
				}
				

				request.setAttribute("mislukte", voorstellingenRepository.readMandje(mislukte));
			}
			if (!klantMandje.isEmpty()){
				Set<Long> idsAlsSet = klantMandje.keySet();
				request.setAttribute("mandjeMap", klantMandje);
				request.setAttribute("mandjeMislukteMap", mandjeMislukte);
				//request.setAttribute("idsSet", idsAlsSet);
				//request.setAttribute("voorstellingGelukte", klantMandje);
				if (idsAlsSet != null) {
					request.setAttribute("voorstellingGelukte", voorstellingenRepository.readMandje(idsAlsSet));
				}
			}
			//request.setAttribute("mandjeMap", MANDJE);
			request.setAttribute("test1", "true");
			}
		}
		session.removeAttribute(MANDJE);
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	
}
