package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MenuDAO;
import model.Menu;

@WebServlet("/CheckMenu")
public class CheckMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String menuId  = request.getParameter("menuId");
		MenuDAO menuDAO = new MenuDAO();
		Menu menu = menuDAO.SelectId(menuId);
		request.setAttribute("menu", menu);
		
		HttpSession session = request.getSession();
		Map<String,Integer> orderCount = new HashMap<String,Integer>();
		orderCount = (Map<String,Integer>)session.getAttribute("orderCount");
		
		if(orderCount == null || orderCount.size() == 0 ) {
			orderCount = new HashMap<String,Integer>();
		}
		
		if(!(orderCount.containsKey(menuId))) {
			orderCount.put(menuId, 1);
		}
		
		session.setAttribute("orderCount", orderCount);
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/SelectCount.jsp");
		rd.forward(request, response);
	}

}
