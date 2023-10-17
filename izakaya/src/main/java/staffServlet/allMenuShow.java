package staffServlet;


import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Menu;


@WebServlet("/allMenuShow")
public class allMenuShow extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = getServletContext();
		//アプリスコープから全テーブルの注文List<Menu>を取得
		List<Menu> staffCheck = (List<Menu>)application.getAttribute("allOrder");
		
		//アプリスコープから埋まっている席の配列を取得(埋まっている席：true)
//		boolean[] tableStatus = (boolean[])application.getAttribute("tableStatus");
		
//////////テスト用仮注文リスト
//		List<Menu> staffCheck = new ArrayList<>();
//		staffCheck.add(new Menu("0005","だし巻き卵","一品料理",600,50));
//		staffCheck.add(new Menu("0006","鶏の唐揚げ","一品料理",500,50));
//		staffCheck.add(new Menu("0007","焼き餃子","一品料理",450,50));
//		staffCheck.add(new Menu("0008","山芋鉄板焼き","一品料理",680,50));
//		staffCheck.add(new Menu("0001","枝豆","おつまみ",350,50));
//		staffCheck.add(new Menu("0002","やみつききゅうり","おつまみ",350,50));
//		staffCheck.add(new Menu("0003","もつ","おつまみ",400,50));
//		staffCheck.add(new Menu("0004","シーザーサラダ","一品料理",580,50));
//////////////
		
		//全テーブルのメニューを注文時間順に並び変える
		Collections.sort(staffCheck, (OrderList1, OrderList2) -> OrderList1.getOrderTime().compareTo(OrderList2.getOrderTime()));
		
		request.setAttribute("staffCheck", staffCheck);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/staffJsp/ShowAllMenu.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

