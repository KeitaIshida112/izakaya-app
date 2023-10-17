package staffServlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Login;

@WebServlet("/StaffTop")
public class StaffTop extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/staffJsp/StaffTop.jsp");
		rd.forward(request, response);	
	}
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String inputId = request.getParameter("id");
		String inputPass = request.getParameter("password");
		
		Login login = new Login();
		String errorMsg = login.inputJudge(inputId, inputPass);
		
//		System.out.println(errorMsg);
		
		if(errorMsg != null) {
			Login user = new Login(errorMsg);
			request.setAttribute("loginUser", user);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/staffJsp/LoginResult.jsp");
		rd.forward(request, response);	
	}
}