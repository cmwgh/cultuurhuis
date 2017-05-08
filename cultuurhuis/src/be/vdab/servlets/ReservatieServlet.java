package be.vdab.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	private final transient VoorstellingenRepository voorstellingenRepository = new VoorstellingenRepository();
	
	@Resource(name = VoorstellingenRepository.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		voorstellingenRepository.setDataSource(dataSource);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idString = request.getParameter("id");
		if (StringUtils.isLong(idString)) {
			voorstellingenRepository.read(Long.parseLong(idString))
			.ifPresent(voorstelling -> request.setAttribute("voorstelling", voorstelling));
		} 
		//			else {
//			request.setAttribute("fout", "id niet correct");
//		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}