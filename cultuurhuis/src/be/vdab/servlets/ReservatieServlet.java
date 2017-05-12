package be.vdab.servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import be.vdab.util.StringUtils;

import be.vdab.repositories.VoorstellingenRepository;


/**
 * Servlet implementation class ReservatieServlet
 */
@WebServlet("/reserveren.htm")
public class ReservatieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW="/WEB-INF/JSP/reserveren.jsp";
	private static final String REDIRECT_URL = "%s/reserveren.htm";
	private final transient VoorstellingenRepository voorstellingenRepository = new VoorstellingenRepository();
	
	@Resource(name = VoorstellingenRepository.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		voorstellingenRepository.setDataSource(dataSource);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		//if session variables exist gebruik dat
		if (session != null) {
			String idString = (String) session.getAttribute("id");
			if (idString != null && idString != "") {
				if (StringUtils.isLong(idString)) {
					voorstellingenRepository.read(Long.parseLong(idString))
					.ifPresent(voorstelling -> request.setAttribute("voorstelling", voorstelling));
					
					//requestId = (long) session.getAttribute("id");
				}
				String plaatsen = (String) session.getAttribute("plaatsen");
				if (plaatsen != null) {
					request.setAttribute("plaatsen", plaatsen);
				}
			}
		} else { //anders gebruik parameter value
		
		String idString = request.getParameter("id");
		if (StringUtils.isLong(idString)) {
			voorstellingenRepository.read(Long.parseLong(idString))
			.ifPresent(voorstelling -> request.setAttribute("voorstelling", voorstelling));
		}
		}
		//else {
			//Map<String, String> fouten = new HashMap<>();
			//fouten.put("idfout", "tik een getal");
			//request.setAttribute("fout", "id niet correct");
		//}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("reserveren") != null) { //doPost om te reserveren
			if (request.getParameter("id") != null){ //is id true
				String idString = request.getParameter("id"); //set parameter as string om te testen
				if (StringUtils.isLong(idString)) {
					voorstellingenRepository.read(Long.parseLong(idString))
					.ifPresent(voorstelling -> request.setAttribute("voorstelling", voorstelling));
					request.setAttribute("id", request.getParameter("id")); //set session variable id to parameter id
					if (request.getParameter("plaatsen") != null){
						if (StringUtils.isLong(request.getParameter("plaatsen")))
							if (Long.parseLong(request.getParameter("plaatsen")) < 10){
								request.setAttribute("plaatsen", request.getParameter("plaatsen"));
						} else {
							request.setAttribute("fout", "niet genoeg plaatsen");
						}
						
				}
				
			}
			
			reserveren(request, response);
		}

	}
	}
	private void reserveren(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(response.encodeRedirectURL(
				String.format(REDIRECT_URL, request.getContextPath())));
	}


		
}