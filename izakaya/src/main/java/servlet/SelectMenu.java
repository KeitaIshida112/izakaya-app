package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MenuDAO;
import model.Menu;

@WebServlet("/SelectMenu")
public class SelectMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String menuCategory = request.getParameter("name");
		
		System.out.println(menuCategory);
		
		Menu menu = new Menu();
		MenuDAO dao = new MenuDAO();
		List<Menu> menuList = dao.SelectMenu(menuCategory);
		
		System.out.println(menuList.size());
		
		request.setAttribute("menuList", menuList);
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/MenuCategory.jsp");
		rd.forward(request, response);
	}

}
