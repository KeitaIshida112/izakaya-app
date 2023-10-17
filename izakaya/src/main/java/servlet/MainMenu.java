package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MenuDAO;
import model.Guest;
import model.Menu;

@WebServlet("/MainMenu")
public class MainMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/MenuTop.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
		
		request.setCharacterEncoding("UTF-8");
		String table = request.getParameter("table");
		int tableNumber = Integer.parseInt(table);
		String number = request.getParameter("number");
		
		Guest guest = new Guest(table,Integer.parseInt(number));
		
		MenuDAO dao = new MenuDAO();
		ServletContext application = getServletContext();
		List<Menu> allOrder = new ArrayList<>(); 
		HttpSession session = request.getSession();
		session.setAttribute("guest", guest);
		
		//何卓が入っているのか
		boolean[] tableList = new boolean[10];
		tableList = (boolean[])application.getAttribute("tableList");
		
		if(tableList == null) {
			tableList = new boolean[10];
		}
		
		if(tableList[tableNumber - 1] == false) {
			tableList[tableNumber - 1] = true;
			
			allOrder = (List<Menu>)application.getAttribute("allOrder");
			if(allOrder == null) {
				allOrder = new ArrayList<Menu>();
			}
			
			Menu menu = dao.SelectId("0000");
			menu.setTableNo(table);
			menu.setCount(tableNumber);
			allOrder.add(menu);
		}
		
		//確認用
		for(int i = 0;i < 10;i++) {
			System.out.println(i+ 1 + "卓 ," + tableList[i]);
		}
		
		application.setAttribute("tableList", tableList);
		application.setAttribute("allOrder", allOrder );
		
//		session.setAttribute("tableList", tableList);
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/MenuTop.jsp");
		rd.forward(request, response);
		
	}
}
