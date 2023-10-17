package staffServlet;

import java.awt.Menu;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/TableManage")
public class TableManage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String tableNumber = request.getParameter("tableN");
		// System.out.println(tableNunber);

		HttpSession session = request.getSession();

		session.setAttribute("tableNumber", tableNumber);

		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/staffJsp/okaikeiTable.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext application = this.getServletContext();
		HttpSession session = request.getSession();

		Map<String, List<Menu>> doneOrder = (Map<String, List<Menu>>) application.getAttribute("doneOrder");
		String table = (String) session.getAttribute("tableNumber");
		doneOrder.remove(table);

		application.setAttribute("teble", table);

		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/table.jsp");
		rd.forward(request, response);
	}

}
