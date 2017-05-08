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
import be.vdab.util.StringUtils;



/**
 * Servlet implementation class VoorstellingenServlet
 */
@WebServlet("/voorstellingen.htm")
public class VoorstellingenServlet extends HttpServlet {
	@Resource(name = VoorstellingenRepository.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		voorstellingenRepository.setDataSource(dataSource);
	}
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/voorstellingen.jsp";
	private final transient VoorstellingenRepository voorstellingenRepository = new VoorstellingenRepository();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getQueryString() != null) {
			String idString = request.getParameter("id");
			long idLong = 0;
			if (StringUtils.isLong(idString)) {
				idLong = new Long(idString);
				request.setAttribute("voorstelling", voorstellingenRepository.findGenre(idLong));
			}//fout handling later here
			}
//			if (fouten.isEmpty()) {
//				
//			} else {
//				request.setAttribute("fouten", fouten);
//			}
		
		
		
		
//		List<Voorstellingen> voorstellingen = voorstellingenRepository.findAll();
//		request.setAttribute("voorstelling", voorstellingen);
		request.getRequestDispatcher(VIEW).forward(request, response); /* must be last */
		}
}
