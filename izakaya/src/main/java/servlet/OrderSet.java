package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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

import dao.MenuDAO;
import model.Guest;
import model.Menu;

@WebServlet("/OrderSet")
public class OrderSet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		//操作中のテーブル番号を取得
		HttpSession session = request.getSession();
		Guest guest = (Guest) session.getAttribute("guest");
		String table = guest.getTable();
		//検証用（テーブル番号設定）↓↓
		System.out.println("働状況確認①");

		//未確定の注文Map（商品ID、個数）をセッションスコープから取得
		ServletContext application = getServletContext();

		Map<String, Integer> orderCount = (Map<String, Integer>) session.getAttribute("orderCount");
		if (orderCount == null) {
			orderCount = new HashMap<String, Integer>();
		}

		//未確定の注文Mapに要素が入っていれば、DAOで注文商品インスタンスが入ったリストを返す
		if (orderCount != null && !orderCount.isEmpty()) {
			MenuDAO menuDAO = new MenuDAO();
			//			List<Menu> orderMenuList = menuDAO.orderMenu(orderCount);

			List<Menu> orderMenuList = menuDAO.SetMenuList(orderCount);

			System.out.println("働状況確認②");

			//改修版
			List<Menu> allOrder = (List<Menu>)application.getAttribute("allOrder");
			if(allOrder == null) {
				allOrder = new ArrayList<>();
			}
			for (Menu menu : orderMenuList) {
				menu.setTableNo(table);
				allOrder.add(menu);
			}

			application.setAttribute("allOrder", allOrder);
			System.out.println("働状況確認⑤");
			//			}	
			orderCount.clear();
			session.removeAttribute("Listshow");
			//			orderMenuList.clear();
		}
		System.out.println("働状況確認⑥");

		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/MenuTop.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String strCount = (String) request.getParameter("count");
		int count = Integer.parseInt(strCount);

		HttpSession session = request.getSession();
		Map<String, Integer> orderCount = (Map<String, Integer>) session.getAttribute("orderCount");
		//		List<Menu> orderList = (List<Menu>) session.getAttribute("Listshow"); 

		MenuDAO dao = new MenuDAO();
		List<String> idList = dao.getId();
		String id;

		//どのIDなのか(見つけたらMapのvalueに代入
		for (String s : idList) {
			id = (String) request.getParameter(s);
			if (id != null && id.equals("確定")) {
				orderCount.replace(s, count);
			} else if (id != null && id.equals("取消")) {
				orderCount.remove(s);
				System.out.println("取り消し");
			}
		}

		//countが０個の場合削除
		for (Map.Entry<String, Integer> entry : orderCount.entrySet()) {
			if (entry.getValue() == 0) {
				orderCount.remove(entry.getKey());
			}
		}

		//Mapの中身確認
		for (Map.Entry<String, Integer> entry : orderCount.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}

		if (orderCount.isEmpty()) {
			session.removeAttribute("Listshow");
		}

		session.setAttribute("orderCount", orderCount);
		//		session.setAttribute("orderList", orderList);

		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/MenuTop.jsp");
		rd.forward(request, response);
	}

}
