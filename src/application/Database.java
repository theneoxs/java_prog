package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.collections.ObservableList;

public class Database {
	
	private Connection conn;
	public Database() {
		this.conn = null;
	}
	// ��������� ����������. ���������� true ���� ���������
	public boolean openConnection(String login, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.conn = DriverManager.getConnection("jdbc:mysql://localhost/db2?user="+login+"&password="+password+"");
		} 
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			System.out.println("SQL error: " + e.getMessage());
			return false;
		}
		return true;
	}
	// ��������� ����������
	public void closeConnection() {
		try {
			if (this.conn != null)
				this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.conn = null;
	}
	
	// �������� ���������� ��� ���� ����������� �������
	public List<Tech> getAllTech() throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		Statement st = null;
		ResultSet rs = null;
		List<Tech> lTech = new ArrayList<Tech>();
		if (openConnection(login, password)) {
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
	// �������� ���������� ��� ���� ��������������
	public List<Subd> getAllSubd() throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		Statement st = null;
		ResultSet rs = null;
		List<Subd> lS = new ArrayList<Subd>();
		if (openConnection(login, password)) {
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
	// �������� ���������� ��� ���� �������
	public List<Worker> getAllWorker() throws IOException {
		FileReader lvl = new FileReader("lvl");
		Scanner scan = new Scanner(lvl);
		String level_accept = scan.nextLine();
		String login = scan.nextLine();
		String password = scan.nextLine();
		lvl.close();
		Statement st = null;
		ResultSet rs = null;
		List<Worker> lS = new ArrayList<Worker>();
		if (openConnection(login, password)) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery("select * from db2.worker");
				while (rs.next()) {
					lS.add(new Worker(rs.getInt("idworker"), rs.getString("name"), rs.getString("surname"),
							rs.getString("position"), rs.getInt("experience"), rs.getString("login"),
							rs.getString("password"), rs.getInt("subdividion_id")));
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

	// �������� ���������� ��� ���� ����������������
	public List<Transportation> getAllTr() throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		Statement st = null;
		ResultSet rs = null;
		List<Transportation> lS = new ArrayList<Transportation>();
		if (openConnection(login, password)) {
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
	//������ �������
	public List<String> listAllWork() throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		Statement st = null;
		ResultSet rs = null;
		List<String> lWork = new ArrayList<String>();
		if (openConnection(login, password)) {
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
	//������ �������
	public List<String> listAllTech() throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
			Statement st = null;
			ResultSet rs = null;
			List<String> lWork = new ArrayList<String>();
			if (openConnection(login, password)) {
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
	//������ �������������
	public List<String> listAllSubd() throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		Statement st = null;
		ResultSet rs = null;
		List<String> ls = new ArrayList<String>();
		if (openConnection(login, password)) {
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
	//�������� ������ ���������� �������
	public boolean newTech(String name, String model, Date date, float cost, int room_num, int mat_resp_id, int subdividion_id) throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		int res = 0;
		if (openConnection(login, password)) {
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
	//���������� ������
	public boolean updTech(Integer idtechnic, String name, String model, Date date, Float cost, Integer room_num, Integer mat_resp_id, Integer subdividion_id) throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		int res = 0;
		if (openConnection(login, password)) {
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
	//�������� �������
	public boolean delTech(Integer idtechnic) throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		int res = 0;
		if (openConnection(login, password)) {
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
	//�������� ������ �������������
	public boolean newSubd(String fname, String shname) throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		int res = 0;
		if (openConnection(login, password)) {
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
	//���������� ������ �������������
	public boolean updSubd(Integer idsubd, String fname, String shname) throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		int res = 0;
		if (openConnection(login, password)) {
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
	//�������� �������������
	public boolean delSubd(Integer idsubd) throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		int res = 0;
		if (openConnection(login, password)) {
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
	//�������� ������ ��������
	public boolean newWorker(String name, String surname, String position, Integer experience, String login, String password, int subdividion_id) throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login2 = scan.nextLine();
        String password2 = scan.nextLine();
        lvl.close();
		int res = 0;
		if (openConnection(login2, password2)) {
			Statement st = null;
			try {
				st = conn.createStatement();
				if (level_accept.equals("Administrator") || level_accept.equals("root")) {
					res = st.executeUpdate("GRANT SELECT, UPDATE, INSERT, DELETE ON db2.* TO '" + login
							+ "'@'localhost' IDENTIFIED BY '" + password + "';");
					if (position.equals("Administrator")) {
						res = st.executeUpdate("GRANT ALL PRIVILEGES ON *.* TO '" + login
								+ "'@'localhost' IDENTIFIED BY '" + password + "' WITH GRANT OPTION;");
					}
					if (level_accept.equals("Administrator")) {
						res = st.executeUpdate(
								"update db2.worker set password = '" + password + "' where login = '" + login + "';");
					} else {
						res = st.executeUpdate(
								"insert into db2.worker (name, surname, position, experience, login, password, subdividion_id) values('"
										+ name + "','" + surname + "','" + position + "','" + experience + "','" + login
										+ "','" + password + "','" + subdividion_id + "');");
					}
				} else {
					res = st.executeUpdate(
							"insert into db2.worker (name, surname, position, experience, login, password, subdividion_id) values('"
									+ name + "','" + surname + "','" + position + "','" + experience + "','" + login
									+ "','" + password + "','" + subdividion_id + "');");
				}

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
	//���������� ������
	public boolean updWorker(Integer idworker, String name, String surname, String position, Integer experience, String login, String password, int subdividion_id) throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login2 = scan.nextLine();
        String password2 = scan.nextLine();
        lvl.close();
		int res = 0;
		if (openConnection(login2, password2)) {
			Statement st = null;
			try {
				st = conn.createStatement();
		        if (level_accept.equals("Administrator")) {
					res = st.executeUpdate("SET PASSWORD FOR '"+login+"'@'localhost' = PASSWORD('"+password+"');");
				}
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
	//�������� ��������
	public boolean delWorker(Integer idworker, String login) throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login2 = scan.nextLine();
        String password2 = scan.nextLine();
        lvl.close();
		int res = 0;
		if (openConnection(login2, password2)) {
			Statement st = null;
			try {
				st = conn.createStatement();
		        if (level_accept.equals("Administrator")) {
					res = st.executeUpdate("REVOKE ALL PRIVILEGES, GRANT OPTION FROM '"+login+"'@'localhost';");
					res = st.executeUpdate("DROP USER '"+login+"'@'localhost';");
				}
		        else {
		        	res = st.executeUpdate("delete from db2.worker where idtechnic = '" + idworker + "';");
		        }
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
	//�������� ����� ���������������
	public boolean newTr(Date date_transportation, String status, int new_mat_resp, int technic_id, int new_subdividion_id) throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		int res = 0;
		if (openConnection(login, password)) {
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
	//���������� ������
	public boolean updTr(Integer idtransportation, Date date_transportation, String status, int new_mat_resp, int technic_id, int new_subdividion_id) throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		int res = 0;
		if (openConnection(login, password)) {
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
	//�������� �������
	public boolean delTr(Integer idtransportation) throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		int res = 0;
		if (openConnection(login, password)) {
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

	public String getTech(int i) throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		Statement st = null;
		ResultSet rs = null;
		String ls = "";
		if (openConnection(login, password)) {
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
	public String getSubd(int i) throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		Statement st = null;
		ResultSet rs = null;
		String ls = "";
		if (openConnection(login, password)) {
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
	public String getWorker(int i) throws IOException {
		FileReader lvl= new FileReader("lvl");
        Scanner scan = new Scanner(lvl);
        String level_accept = scan.nextLine();
        String login = scan.nextLine();
        String password = scan.nextLine();
        lvl.close();
		Statement st = null;
		ResultSet rs = null;
		String ls = "";
		if (openConnection(login, password)) {
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
	public boolean login(String login, String password) throws IOException {
		Statement st = null;
		ResultSet rs = null;
		String ls = "";
		String lvl = "";
		if (openConnection("root", "admin")) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery("select * from db2.worker where login = '"+ login + "';");
				while (rs.next()) {
					ls += rs.getString("password");
					lvl += rs.getString("position");
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
			FileWriter file = new FileWriter("lvl");
			file.write(lvl+"\n");
			file.write(login+"\n");
			file.write(ls+"\n");
			file.close();
			return true;
		}
		else {
			return false;
		}
	}
}
