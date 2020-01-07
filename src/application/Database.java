package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;

public class Database {
	
	private Connection conn;
	public Database() {
		this.conn = null;
	}
	// Открывает соединение. Возвращает true если открылось
	public boolean openConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.conn = DriverManager.getConnection("jdbc:mysql://localhost/db2?user=u2&password=2222");
		} 
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			System.out.println("SQL error: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}
	// Закрывает соединение
	public void closeConnection() {
		try {
			if (this.conn != null)
				this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.conn = null;
	}
	
	// Получает информацию обо всех экземплярах техники
	public List<Tech> getAllTech() {
		Statement st = null;
		ResultSet rs = null;
		List<Tech> lTech = new ArrayList<Tech>();
		if (openConnection()) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery("select * from db2.technic");
				while (rs.next()) {
					lTech.add(new Tech(rs.getInt("idtechnic"), rs.getString("name"), rs.getString("model"),
							rs.getDate("date"), rs.getFloat("cost"), rs.getInt("room_num"), rs.getInt("mat_resp_id"), rs.getInt("subdividion_id")));
				}
			} 
			catch (SQLException e) {
				
				System.out.println("SQl exception: " + e.getMessage());
				e.printStackTrace();
				return null;
			} 
			finally {
				try {
					if (st != null)
						st.close();
					closeConnection();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
				st = null;
			}
		}
		return lTech;
	}
	//список рабочих
	public List<String> listAllWork() {
		Statement st = null;
		ResultSet rs = null;
		List<String> lWork = new ArrayList<String>();
		if (openConnection()) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery("select * from db2.worker");
				while (rs.next()) {
					lWork.add(String.valueOf(rs.getInt("idworker"))+" " +rs.getString("name") + " " + rs.getString("surname"));
				}
			} 
			catch (SQLException e) {
				
				System.out.println("SQl exception: " + e.getMessage());
				e.printStackTrace();
				return null;
			} 
			finally {
				try {
					if (st != null)
						st.close();
					closeConnection();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
				st = null;
			}
		}
		return lWork;
	}
	//список подразделений
	public List<String> listAllSubd() {
		Statement st = null;
		ResultSet rs = null;
		List<String> ls = new ArrayList<String>();
		if (openConnection()) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery("select * from db2.subdividion");
				while (rs.next()) {
					ls.add(String.valueOf(rs.getInt("idsubdividion"))+" " +rs.getString("full_name"));
				}
			} 
			catch (SQLException e) {
				
				System.out.println("SQl exception: " + e.getMessage());
				e.printStackTrace();
				return null;
			} 
			finally {
				try {
					if (st != null)
						st.close();
					closeConnection();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
				st = null;
			}
		}
		return ls;
	}
	//создание нового экземпляра техники
	public boolean newTech(String name, String model, Date date, float cost, int room_num, int mat_resp_id, int subdividion_id) {
		int res = 0;
		if (openConnection()) {
			Statement st = null;
			try {
				st = conn.createStatement();
				res = st.executeUpdate("insert into db2.technic (name, model, date, cost, room_num, mat_resp_id, subdividion_id) values('"
						+ name + "','" + model + "','" + date + "','" + cost + "','" + room_num + "','" + mat_resp_id + "','" + subdividion_id + "');");
			} 
			catch (SQLException e) {
				res = 0;
				System.out.println("SQl exception" + e.getMessage());
				e.printStackTrace();
			} 
			finally {
				try {
					if (st != null)
						st.close();
					closeConnection();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
				st = null;
			}
			
		}
		return (res == 1);
	}
	//обновление данных
	public boolean updTech(Integer idtechnic, String name, String model, Date date, Float cost, Integer room_num, Integer mat_resp_id, Integer subdividion_id) {
		int res = 0;
		if (openConnection()) {
			Statement st = null;
			try {
				st = conn.createStatement();
				res = st.executeUpdate("update db2.technic set name ='"+name+"', model = '"
				+model+"', date = '"+date+"', cost = '" + cost +"', room_num = '"+room_num+"', mat_resp_id = '"+mat_resp_id+"', subdividion_id = '"+subdividion_id+"' where idtechnic = '"+idtechnic+"';");
			} 
			catch (SQLException e) {
				res = 0;
				System.out.println("SQl exception " + e.getMessage());
				e.printStackTrace();
			} 
			finally {
				try {
					if (st != null)
						st.close();
					closeConnection();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
				st = null;
			}
		}
		return (res == 1);
	}
	//удаление техники
	public boolean delTech(Integer idtechnic) {
		int res = 0;
		if (openConnection()) {
			Statement st = null;
			try {
				st = conn.createStatement();
				res = st.executeUpdate("delete from db2.technic where idtechnic = '" + idtechnic + "';");
			} 
			catch (SQLException e) {
				res = 0;
				System.out.println("SQl exception" + e.getMessage());
				e.printStackTrace();
			} 
			finally {
				try {
					if (st != null)
						st.close();
					closeConnection();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
				st = null;
			}
		}
		return (res == 1);
	}
}
