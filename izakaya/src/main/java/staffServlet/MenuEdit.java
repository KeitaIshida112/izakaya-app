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
import javax.servlet.http.HttpSession;

import dao.MenuKanriDAO;
import model.Menu;

@WebServlet("/MenuEdit")
public class MenuEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
//		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
//		rd.forward(request, response);
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/staffJsp/Edit.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
		
		request.setCharacterEncoding("UTF-8");
		String msg = "";
		MenuKanriDAO dao = new MenuKanriDAO();
		String submit = request.getParameter("submit");
		if(submit.equals("検索")) {
			
			String name = request.getParameter("name");
			String category = request.getParameter("category");
			
			List<Menu> menuList1 = new ArrayList<>();
			
			if(!(name.equals(""))&& !(category.equals(""))) {
				menuList1 = dao.SelectNameCategory(name, category);
			} else if(!(name.equals("")) && category.equals("")) {
				menuList1 = dao.SelectName(name);
			} else if(name.equals("") && !(category.equals(""))) {
				menuList1 = dao.SelectCategory(category);
			} else if(name.equals("") && category.equals("")) {
				menuList1 = dao.SelectMenu();
			}
			
			if(menuList1 == null || menuList1.size() == 0) {
				msg += "該当するメニューはありません。¥n";
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("menuList1", menuList1);
			
			request.setAttribute("msg", msg);
			
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/staffJsp/Edit.jsp");
			rd.forward(request, response);
			
		} else if(submit.equals("更新")) {
			
			String id = request.getParameter("id");
			
			if(id != null) {
				
				Menu menu = dao.SelectMenu(id);
				request.setAttribute("menu1", menu);
				
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/staffJsp/UpdateMenu.jsp");
				rd.forward(request, response);
			}
			
			msg += "商品を選んでください。";
			
		} else if(submit.equals("削除")) {
			
			String id = request.getParameter("id");
			
			int i = dao.deleteMenu(id);
			
			if(i == 0) {
				msg += "削除できませんでした。\n";
			} else {
				msg += "削除しました。\n";
			}
			
		} else if(submit.equals("新規登録")) {
			
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/staffJsp/NewMenu.jsp");
			rd.forward(request, response);
			
		}
		
		request.setAttribute("msg", msg);
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/staffJsp/Edit.jsp");
		rd.forward(request, response);
		
	}

}
