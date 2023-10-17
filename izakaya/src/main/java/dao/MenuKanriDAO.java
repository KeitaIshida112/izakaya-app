package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Menu;

public class MenuKanriDAO {

	private final String URL = "jdbc:postgresql://localhost:5432/restaurant";
	private final String USER = "postgres";
	private final String PASSWORD = "Un7c";

	public MenuKanriDAO() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<Menu> SelectMenu() {

		List<Menu> menuList1 = new ArrayList<Menu>();

		String sql = "SELECT * FROM menu ORDER BY menu_id; ";

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement st = con.createStatement();) {

			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				Menu menu = new Menu();
				menu.setMenuId(rs.getString("menu_id"));
				menu.setMenuName(rs.getString("menu_name"));
				menu.setMenuCategory(rs.getString("menu_category"));
				menu.setPrice(rs.getInt("price"));
				menu.setStock(rs.getInt("stock"));
				menuList1.add(menu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return menuList1;
	}

	public int selectId(String id) {

		int count = 0;

		String sql = "SELECT COUNT(*) FROM menu WHERE menu_id = '" + id + "';";

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement st = con.createStatement();) {

			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				count = rs.getInt("count");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return count;
		}
		return count;
	}

	public Menu SelectMenu(String menuId) {

		Menu menu = new Menu();

		String sql = "SELECT * FROM menu WHERE menu_id = '" + menuId + "' ; ";

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement st = con.createStatement();) {

			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				menu.setMenuId(rs.getString("menu_id"));
				menu.setMenuName(rs.getString("menu_name"));
				menu.setMenuCategory(rs.getString("menu_category"));
				menu.setPrice(rs.getInt("price"));
				menu.setStock(rs.getInt("stock"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return menu;
	}

	public List<Menu> SelectName(String name) {

		List<Menu> menuList1 = new ArrayList<Menu>();

		String sql = "SELECT * FROM menu WHERE menu_name LIKE '%" + name + "%' ORDER BY menu_id; ";

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement st = con.createStatement();) {

			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				Menu menu = new Menu();
				menu.setMenuId(rs.getString("menu_id"));
				menu.setMenuName(rs.getString("menu_name"));
				menu.setMenuCategory(rs.getString("menu_category"));
				menu.setPrice(rs.getInt("price"));
				menu.setStock(rs.getInt("stock"));
				menuList1.add(menu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return menuList1;
	}

	public List<Menu> SelectCategory(String category) {

		List<Menu> menuList1 = new ArrayList<Menu>();

		String sql = "SELECT * FROM menu WHERE menu_category = '" + category + "' ORDER BY menu_id;";

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement st = con.createStatement();) {

			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				Menu menu = new Menu();
				menu.setMenuId(rs.getString("menu_id"));
				menu.setMenuName(rs.getString("menu_name"));
				menu.setMenuCategory(rs.getString("menu_category"));
				menu.setPrice(rs.getInt("price"));
				menu.setStock(rs.getInt("stock"));
				menuList1.add(menu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return menuList1;
	}

	public List<Menu> SelectNameCategory(String name, String category) {

		List<Menu> menuList1 = new ArrayList<Menu>();

		String sql = "SELECT * FROM menu WHERE menu_name LIKE '%" + name + "%' AND menu_category = '" + category
				+ "' ORDER BY menu_id; ";

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement st = con.createStatement();) {

			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				Menu menu = new Menu();
				menu.setMenuId(rs.getString("menu_id"));
				menu.setMenuName(rs.getString("menu_name"));
				menu.setMenuCategory(rs.getString("menu_category"));
				menu.setPrice(rs.getInt("price"));
				menu.setStock(rs.getInt("stock"));
				menuList1.add(menu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return menuList1;
	}

	public int insertMenu(Menu menu) {

		int count = 0;

		String sql = "INSERT INTO menu VALUES (?,?,?,?,?)";

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement st = con.prepareStatement(sql);) {

			st.setString(1, menu.getMenuId());
			st.setString(2, menu.getMenuName());
			st.setString(3, menu.getMenuCategory());
			st.setInt(4, menu.getPrice());
			st.setInt(5, menu.getStock());

			count = st.executeUpdate();

		} catch (SQLException e) {
			e.getStackTrace();
			System.out.println("インサート時エラー");
			return count;
		}
		return count;
	}

	public int deleteMenu(String id) {

		int count = 0;
		String sql = "DELETE FROM menu WHERE menu_id = '" + id + "';";

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement st = con.createStatement();) {

			count = st.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("削除時エラー");
			return count;
		}
		return count;
	}

	public int MenuUpdate(Menu menu) {

		int count = 0;

		String sql = "UPDATE menu SET menu_id = ?,menu_name = ?,menu_category = ?,price = ?, stock = ?";
		sql += "WHERE menu_id = '" + menu.getMenuId() + "';";

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement st = con.prepareStatement(sql);) {

			st.setString(1, menu.getMenuId());
			st.setString(2, menu.getMenuName());
			st.setString(3, menu.getMenuCategory());
			st.setInt(4, menu.getPrice());
			st.setInt(5, menu.getStock());

			count = st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("更新時エラー");
			return count;
		}
		return count;
	}
}
