package be.vdab.servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	private static final String VIEW = "/WEB-INF/JSP/reserveren.jsp";
	private static final String REDIRECT_URL = "%s/reserveren.htm";
	private static final String MANDJE = "mandje";
	private final transient VoorstellingenRepository voorstellingenRepository = new VoorstellingenRepository();

	@Resource(name = VoorstellingenRepository.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		voorstellingenRepository.setDataSource(dataSource);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idString = request.getParameter("id"); // get id voor dit voorstelling
		HttpSession session = request.getSession(false);
		if (session != null) {
			@SuppressWarnings("unchecked")
			// Map<Long, Long> mandje = Stream.of(session.getAttribute(MANDJE));
			Map<Long, Long> mandje = (Map<Long, Long>) session.getAttribute(MANDJE);
			if (mandje != null) {
				if (mandje.containsKey(Long.parseLong(idString))) {
					request.setAttribute("plaatsen", mandje.get(Long.parseLong(idString)));
				}
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idString = request.getParameter("id"); // get id voor dit voorstelling
		if (StringUtils.isLong(idString)) { // test if id is a long
			voorstellingenRepository.read(Long.parseLong(idString))// true = get all fields from table voorstelling
					.ifPresent(voorstelling -> request.setAttribute("voorstelling", voorstelling)); // if it exists then set voorstelling array with all fields
		}
		
		if (request.getParameter("plaatsen") != null) {
			reserveren(request, response);
		} else {
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}

	private void display (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idString = request.getParameter("id"); // get id voor dit voorstelling
		if (StringUtils.isLong(idString)) { // test if id is a long
			voorstellingenRepository.read(Long.parseLong(idString))// true = get all fields from table voorstelling
					.ifPresent(voorstelling -> request.setAttribute("voorstelling", voorstelling)); // if it exists then set voorstelling array with all fields
		}
	}
	private void reserveren(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		Map<Long, Long> mandje = (Map<Long, Long>) session.getAttribute(MANDJE);
		if (mandje == null) {
			mandje = new HashMap<>();
		}
		String idString = request.getParameter("id");
		String plaatsen = request.getParameter("plaatsen");
		mandje.put(Long.parseLong(idString), Long.parseLong(plaatsen));
		session.setAttribute(MANDJE, mandje);

		// request.getSession().setAttribute("id", request.getParameter("id"));

		request.getRequestDispatcher(VIEW).forward(request, response);

		// response.sendRedirect(response.encodeRedirectURL(
		// String.format(REDIRECT_URL, request.getContextPath())));
	}
}