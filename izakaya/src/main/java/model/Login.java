package model;

public class Login {
	
	public static final String id = "finedog";
	public static final String pass = "1234";
	String errorMsg = null;
	
	public Login() {
	}
	
	public Login(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public String inputJudge(String inputId, String inputPass) {	
		if(inputId == null || inputId.length() == 0) {
			errorMsg = "IDが未入力です";
		}else if(inputPass == null || inputPass.length() == 0) {
			errorMsg = "パスワードが未入力です";
		}else if(! (inputId.equals(id))) {
			errorMsg = "IDが違います";
		}else if(! (inputPass.equals(pass))) {
			errorMsg = "パスワードが違います";
		}
		return errorMsg;
	}
	
	public String getErrorMsg() {
		return this.errorMsg;
	}
	
}
