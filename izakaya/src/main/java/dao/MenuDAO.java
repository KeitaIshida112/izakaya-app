package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.Menu;

public class MenuDAO {
	
	private final String URL = "jdbc:postgresql://localhost:5432/restaurant";
	private final String USER = "postgres";
	private final String PASSWORD = "Un7c";
	
	public MenuDAO() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//カテゴリー別（詳細画面
	public List<Menu> SelectMenu(String category) {
		
		List<Menu> menuList = new ArrayList<Menu>();
		
		String sql = "SELECT * FROM menu WHERE menu_category = '" + category + "' ORDER BY menu_id; ";
		
		try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement st = con.createStatement();){
				
				ResultSet rs = st.executeQuery(sql);
				
				while(rs.next()) {
					Menu menu = new Menu();
					menu.setMenuId(rs.getString("menu_id"));
					menu.setMenuName(rs.getString("menu_name"));
					menu.setMenuCategory(rs.getString("menu_category"));
					menu.setPrice(rs.getInt("price"));
					menu.setStock(rs.getInt("stock"));
					menuList.add(menu);
				}
				
			}catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		return menuList;
	}
	
	public Menu SelectId(String menuId) {
		
		Menu menu = new Menu();
		
		String sql = "SELECT * FROM menu WHERE menu_id = '" + menuId + "' ; ";
		
		try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement st = con.createStatement();){
				
				ResultSet rs = st.executeQuery(sql);
				
				while(rs.next()) {
					menu.setMenuId(rs.getString("menu_id"));
					menu.setMenuName(rs.getString("menu_name"));
					menu.setMenuCategory(rs.getString("menu_category"));
					menu.setPrice(rs.getInt("price"));
					menu.setStock(rs.getInt("stock"));
				}
				
			}catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		return menu;
	}
	
	//MenuId一覧
	public List<String> getId() {
		
		List<String> idList = new ArrayList<String>();
		
		String sql = "SELECT * FROM menu ORDER BY menu_id ; ";
		
		try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement st = con.createStatement();){
				
				ResultSet rs = st.executeQuery(sql);
				
				while(rs.next()) {
					idList.add(rs.getString("menu_id"));
				}
				
			}catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		return idList;
	}
	
	//DB全商品データをList変換
		public List<Menu> allMenu() {
			List<Menu> menuList = new ArrayList<Menu>();
			String sql = "";
			sql += "SELECT * FROM menu;";
			
			try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
					Statement st = con.createStatement();) {
				
				ResultSet rs = st.executeQuery(sql);
				
				while(rs.next()) {
					Menu menu = new Menu(rs.getString("menu_id"),
									rs.getString("menu_name"),
									rs.getString("menu_category"),
									rs.getInt("price"),
									rs.getInt("stock")
							);
					
					menuList.add(menu);
				}
				
			} catch(Exception e) {
				System.out.println("DB接続エラー");
				e.printStackTrace();
				
			}
			
			return menuList;
			
		}
		
		//注文Map<String, Integer>から注文商品のインスタンスを作成してListに格納
		public List<Menu> orderMenu(Map<String, Integer> orderMenu) {
			Set<String> orderMenuId = orderMenu.keySet();
			
			List<Menu> menuList = new ArrayList<Menu>();
			
			String sql = "";
			sql += "SELECT * FROM menu ";
			sql += "WHERE menu_id IN (? ";
			for(int i = 1; i < orderMenuId.size(); i++) {
				sql += ",? ";
			}
			sql += ");";
			
			try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
					PreparedStatement st = con.prepareStatement(sql);) {
				int i = 1;
				for(String key: orderMenuId) {
					st.setString(i, key);
					i++;
				}
				
				ResultSet rs = st.executeQuery();
				
				while(rs.next()) {
					Menu menu = new Menu(rs.getString("menu_id"),
										 rs.getString("menu_name"),
										 rs.getString("menu_category"),
										 rs.getInt("price"),
										 rs.getInt("stock")
							);
					int count = orderMenu.get(rs.getString("menu_id"));
					menu.setCount(count);
					menuList.add(menu);				
				}
				
			} catch(Exception e) {
				System.out.println("DB接続エラー");
				e.printStackTrace();
				
			}
			
			return menuList;
			
		}
		
		public List<Menu> SetMenuList(Map<String,Integer> orderCount){
			
			List<Menu> orderMenuList = new ArrayList<>();
			
			String sql = "SELECT * FROM menu WHERE menu_id = ? ;";
			
			try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement st = con.prepareStatement(sql);){
				
				for(String key : orderCount.keySet()) {
					
					st.setString(1, key);
					
					ResultSet rs = st.executeQuery();
					
					while(rs.next()) {
						Menu menu = new Menu(rs.getString("menu_id"),
											 rs.getString("menu_name"),
											 rs.getString("menu_category"),
											 rs.getInt("price"),
											 rs.getInt("stock")
								);
						int count = orderCount.get(key);
						menu.setCount(count);
						orderMenuList.add(menu);				
					}
				}
				
			} catch(Exception e) {
				System.out.println("DB接続エラー");
				e.printStackTrace();
				return null;
			}
			return orderMenuList;
		}
	
}
