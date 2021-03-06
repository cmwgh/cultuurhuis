package be.vdab.servlets;

import java.io.IOException;
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

import be.vdab.repositories.VoorstellingenRepository;

/**
 * Servlet implementation class Mandje
 */
@WebServlet("/reservatiemandje.htm")
public class MandjeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/reservatiemandje.jsp";
	private static final String HOME_URL = "%s/index.htm";	
	private static final String REDIRECT_URL = "%s/reservatiemandje.htm";
	private static final String MANDJE = "mandje";
	private transient boolean mandje_empty = false;
	
	private final transient VoorstellingenRepository voorstellingenRepository = new VoorstellingenRepository();

	@Resource(name = VoorstellingenRepository.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		voorstellingenRepository.setDataSource(dataSource);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			@SuppressWarnings("unchecked")
			Map<Long, Long> mandje = (Map<Long, Long>) session.getAttribute(MANDJE);
			if (mandje != null) {
				Set<Long> idsAlsSet = mandje.keySet();
				request.setAttribute("mandjeMap", mandje);
				request.setAttribute("idsSet", idsAlsSet);
				if (idsAlsSet != null) {
					request.setAttribute("voorstelling", voorstellingenRepository.readMandje(idsAlsSet));
				}
			}
			
		} else {
			request.getRequestDispatcher(HOME_URL).forward(request, response);
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String[] idsAlsString=request.getParameterValues("id");
		if (idsAlsString != null) {
			@SuppressWarnings("unchecked")
			Map<Long, Long> mandje = (Map<Long, Long>) session.getAttribute(MANDJE);

			for (int i = 0; i < idsAlsString.length; i++) {

				mandje.remove(Long.parseLong(idsAlsString[i]));
			}
			if (mandje.isEmpty()){
				mandje_empty = true;
			}
			session.setAttribute(MANDJE, mandje);
		}

		if (mandje_empty) {
			response.sendRedirect(response.encodeRedirectURL(
					String.format(HOME_URL, request.getContextPath())));
			
		} else {
			response.sendRedirect(response.encodeRedirectURL(
					String.format(REDIRECT_URL, request.getContextPath())));

			
		}
		
	}

}
