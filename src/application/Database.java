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
	// Получает информацию обо всех подразделениях
	public List<Subd> getAllSubd() {
		Statement st = null;
		ResultSet rs = null;
		List<Subd> lS = new ArrayList<Subd>();
		if (openConnection()) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery("select * from db2.subdividion");
				while (rs.next()) {
					lS.add(new Subd(rs.getInt("idsubdividion"), rs.getString("full_name"), rs.getString("short_name")));
				}
			} catch (SQLException e) {

				System.out.println("SQl exception: " + e.getMessage());
				e.printStackTrace();
				return null;
			} finally {
				try {
					if (st != null)
						st.close();
					closeConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				st = null;
			}
		}
		return lS;
	}
	// Получает информацию обо всех рабочих
		public List<Worker> getAllWorker() {
			Statement st = null;
			ResultSet rs = null;
			List<Worker> lS = new ArrayList<Worker>();
			if (openConnection()) {
				try {
					st = conn.createStatement();
					rs = st.executeQuery("select * from db2.worker");
					while (rs.next()) {
						lS.add(new Worker(rs.getInt("idworker"), rs.getString("name"), rs.getString("surname"), rs.getString("position"),rs.getInt("experience"), 
								rs.getString("login"), rs.getString("password"), rs.getInt("subdividion_id")));
					}
				} catch (SQLException e) {

					System.out.println("SQl exception: " + e.getMessage());
					e.printStackTrace();
					return null;
				} finally {
					try {
						if (st != null)
							st.close();
						closeConnection();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					st = null;
				}
			}
			return lS;
		}

	// Получает информацию обо всех транспортировках
	public List<Transportation> getAllTr() {
		Statement st = null;
		ResultSet rs = null;
		List<Transportation> lS = new ArrayList<Transportation>();
		if (openConnection()) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery("select * from db2.transportation");
				while (rs.next()) {
					lS.add(new Transportation(rs.getInt("idtransportation"), rs.getDate("date_transportation"), rs.getString("status"),
							rs.getInt("new_mat_resp"),rs.getInt("technic_id"),rs.getInt("new_subdividion_id")));
				}
			} catch (SQLException e) {

				System.out.println("SQl exception: " + e.getMessage());
				e.printStackTrace();
				return null;
			} finally {
				try {
					if (st != null)
						st.close();
					closeConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				st = null;
			}
		}
		return lS;
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
	//список техники
		public List<String> listAllTech() {
			Statement st = null;
			ResultSet rs = null;
			List<String> lWork = new ArrayList<String>();
			if (openConnection()) {
				try {
					st = conn.createStatement();
					rs = st.executeQuery("select * from db2.technic");
					while (rs.next()) {
						lWork.add(String.valueOf(rs.getInt("idtechnic"))+" " +rs.getString("name") + ", Model - " + rs.getString("model"));
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
	//создание нового подразделения
	public boolean newSubd(String fname, String shname) {
		int res = 0;
		if (openConnection()) {
			Statement st = null;
			try {
				st = conn.createStatement();
				res = st.executeUpdate("insert into db2.subdividion (full_name, short_name) values('"
						+ fname + "','" + shname + "');");
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
	//обновление данных подразделений
	public boolean updSubd(Integer idsubd, String fname, String shname) {
		int res = 0;
		if (openConnection()) {
			Statement st = null;
			try {
				st = conn.createStatement();
				res = st.executeUpdate("update db2.subdividion set full_name ='"+fname+"', short_name = '" +shname+"' where idsubdividion = '"+idsubd+"';");
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
	//удаление подразделения
	public boolean delSubd(Integer idsubd) {
		int res = 0;
		if (openConnection()) {
			Statement st = null;
			try {
				st = conn.createStatement();
				res = st.executeUpdate("delete from db2.subdividion where idsubdividion = '" + idsubd + "';");
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
	//Создание нового рабочего
	public boolean newWorker(String name, String surname, String position, Integer experience, String login, String password, int subdividion_id) {
		int res = 0;
		if (openConnection()) {
			Statement st = null;
			try {
				st = conn.createStatement();
				res = st.executeUpdate("insert into db2.worker (name, surname, position, experience, login, password, subdividion_id) values('"
						+ name + "','" + surname + "','" + position + "','" + experience + "','" + login + "','" + password + "','" + subdividion_id + "');");
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
	public boolean updWorker(Integer idworker, String name, String surname, String position, Integer experience, String login, String password, int subdividion_id) {
		int res = 0;
		if (openConnection()) {
			Statement st = null;
			try {
				st = conn.createStatement();
				res = st.executeUpdate("update db2.worker set name ='"+name+"', surname = '"
				+surname+"', position = '"+position+"', experience = '" + experience +"', login = '"+login+"', password = '"+password+"', subdividion_id = '"+subdividion_id+"' where idworker = '"+idworker+"';");
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
	//удаление рабочего
	public boolean delWorker(Integer idworker) {
		int res = 0;
		if (openConnection()) {
			Statement st = null;
			try {
				st = conn.createStatement();
				res = st.executeUpdate("delete from db2.worker where idtechnic = '" + idworker + "';");
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
	//Создание новой транспортировки
	public boolean newTr(Date date_transportation, String status, int new_mat_resp, int technic_id, int new_subdividion_id) {
		int res = 0;
		if (openConnection()) {
			Statement st = null;
			try {
				st = conn.createStatement();
				res = st.executeUpdate("insert into db2.transportation (date_transportation, status, new_mat_resp, technic_id, new_subdividion_id) values('"
						+ date_transportation + "','" + status + "','" + new_mat_resp + "','" + technic_id + "','" + new_subdividion_id + "');");
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
	public boolean updTr(Integer idtransportation, Date date_transportation, String status, int new_mat_resp, int technic_id, int new_subdividion_id) {
		int res = 0;
		if (openConnection()) {
			Statement st = null;
			try {
				st = conn.createStatement();
				res = st.executeUpdate("update db2.transportation set date_transportation = '"+date_transportation+"', status = '" + status +"', new_mat_resp = '"
				+new_mat_resp+"', technic_id = '"+technic_id+"', new_subdividion_id = '"+new_subdividion_id+"' where idtransportation = '"+idtransportation+"';");
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
	public boolean delTr(Integer idtransportation) {
		int res = 0;
		if (openConnection()) {
			Statement st = null;
			try {
				st = conn.createStatement();
				res = st.executeUpdate("delete from db2.transportation where idtransportation = '" + idtransportation + "';");
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

	public String getTech(int i) {
		Statement st = null;
		ResultSet rs = null;
		String ls = "";
		if (openConnection()) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery("select * from db2.technic where idtechnic = '"+ i + "';");
				while (rs.next()) {
					ls += rs.getInt("idtechnic") +" "+ rs.getString("name") + " Model - " +rs.getString("model");
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
	public String getSubd(int i) {
		Statement st = null;
		ResultSet rs = null;
		String ls = "";
		if (openConnection()) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery("select * from db2.subdividion where idsubdividion = '"+ i + "';");
				while (rs.next()) {
					ls += rs.getInt("idsubdividion") + " " + rs.getString("full_name");
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
	public String getWorker(int i) {
		Statement st = null;
		ResultSet rs = null;
		String ls = "";
		if (openConnection()) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery("select * from db2.worker where idworker = '"+ i + "';");
				while (rs.next()) {
					ls += rs.getInt("idworker") + " " + rs.getString("name") + " " + rs.getString("surname");
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
	public boolean login(String login, String password) {
		Statement st = null;
		ResultSet rs = null;
		String ls = "";
		if (openConnection()) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery("select * from db2.worker where login = '"+ login + "';");
				while (rs.next()) {
					ls += rs.getString("password");
				}
			} 
			catch (SQLException e) {
				
				System.out.println("SQl exception: " + e.getMessage());
				e.printStackTrace();
				return false;
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
		if (ls.equals(password)) {
			return true;
		}
		else {
			return false;
		}
	}
}
