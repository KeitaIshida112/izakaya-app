package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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


@WebServlet("/CartMenu")
public class CartMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Map<String, Integer> orderCount = (Map<String, Integer>)session.getAttribute("orderCount");
		
		if(orderCount != null && orderCount.size() > 0) {
			MenuDAO menuDAO = new MenuDAO();
			List<Menu> orderMenuList = menuDAO.orderMenu(orderCount);
			session.setAttribute("Listshow", orderMenuList);
		} else {
			orderCount = new HashMap<String, Integer>();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/OrderCart.jsp");
		dispatcher.forward(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
