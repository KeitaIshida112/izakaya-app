package staffServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MenuKanriDAO;
import model.Menu;

@WebServlet("/NewMenu")
public class NewMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/staffJsp/NewMenu.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
		
		request.setCharacterEncoding("UTF-8");
		MenuKanriDAO dao = new MenuKanriDAO();
		String msg = "";
		List<String> errorMsg = new ArrayList<>();
		
		//登録か更新の値が入る
		String submit = request.getParameter("submit");
		
		//IDの全角入力を半角に
		String id = request.getParameter("id");
		int id2 = Integer.parseInt(id);
		id = String.valueOf(id2);
		String s = "";
		if (id.equals("")) {
			errorMsg.add("商品IDは必須項目です");
		} else if (id.length() > 4) {
			errorMsg.add("IDは4文字以下で入力してください");
		} else {
			//IDの桁を4桁に
			for (int i = 0; i < (4 - id.length()); i++) {
				s += "0";
			}
			id = s + id;
		} 
		//ID検索
		if(submit.equals("登録")) {
			int count = dao.selectId(id);
			if(count == 1) {
				errorMsg.add("この商品IDは使用されています");
			}
		}
				
		String name = request.getParameter("name");
		if (name.equals("")) {
			errorMsg.add("商品名は必須項目です");
		}
		
		String bunrui = request.getParameter("bunrui");
		if (bunrui == null) {
			errorMsg.add("商品分類は必須項目です");
		}
		
		String priceStr = request.getParameter("price");
		int price = 0;
		boolean boo1 = true;
		if (priceStr.contains(".")) {
			errorMsg.add("販売単価には整数を入力してください");
		} else if (!(priceStr.equals(""))) {
			for (int i = 0; i < priceStr.length(); i++) {
				if (!Character.isDigit(priceStr.charAt(i))) {
					boo1 = false;
				}
			}
			if(boo1 == true) {
				price = Integer.parseInt(priceStr);
			} else {
				errorMsg.add("仕入単価には整数を入力してください");
			}
		}
		
		String stockStr = request.getParameter("stock");
		int stock = 0;
		boolean boo2 = true;
		if (stockStr.contains(".")) {
			errorMsg.add("販売単価には整数を入力してください");
		} else if (!(stockStr.equals(""))) {
			for (int i = 0; i < stockStr.length(); i++) {
				if (!Character.isDigit(stockStr.charAt(i))) {
					boo2 = false;
				}
			}
			if(boo2 == true) {
				stock = Integer.parseInt(stockStr);
			} else {
				errorMsg.add("仕入単価には整数を入力してください");
			}
		}
		
		Menu menu = new Menu(id, name, bunrui, price, stock);
		
		if(submit.equals("更新")) {
			
			int i = dao.MenuUpdate(menu);
			
			if(i == 1) {
				msg += "更新されました。\n";
			} else {
				msg += "更新に失敗しました。\n";
				
				request.setAttribute("msg", msg);
				
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/staffJsp/Edit.jsp");
				rd.forward(request, response);
			}
			
		} else if(submit.equals("登録")) {
			
			int i = dao.insertMenu(menu);
			
			if(i == 1) {
				msg += "登録完了。\n";
			} else {
				msg += "登録に失敗しました。\n";
			}
		}
		
		request.setAttribute("msg", msg);
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/staffJsp/NewMenu.jsp");
		rd.forward(request, response);
	}

}
