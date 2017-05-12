package be.vdab.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


import be.vdab.repositories.VoorstellingenRepository;

/**
 * Servlet implementation class Mandje
 */
@WebServlet("/reservatiemandje.htm")
public class MandjeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/reservatiemandje.jsp";
	private static final String VIEW_ERROR = "/WEB-INF/JSP/reserveren.jsp";	
	private static final String MANDJE = "mandje";
	private final transient VoorstellingenRepository voorstellingenRepository = new VoorstellingenRepository();

	@Resource(name = VoorstellingenRepository.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		voorstellingenRepository.setDataSource(dataSource);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("hoeveelPlaatsen", read);
		
	}

}
