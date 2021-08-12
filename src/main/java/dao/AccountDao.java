package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import context.DBcontext;
import entity.Accounts;

public class AccountDao extends BaseDao {

	public Accounts FindByEmail(String email) {
		Accounts acc = null;

		String sql = "";

		try {
			conn = new DBcontext().getSQLServerConnection();
			sql = "SELECT * FROM ACCOUNTS WHERE acc_email = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();

			if (rs.next()) {
				acc = new Accounts(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getDate(9), rs.getInt(10), rs.getInt(11),
						rs.getString(12));
			}
			ps.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return acc;
	}

	public Accounts FindByID(String id) {
		Accounts acc = null;

		String sql = "";

		try {
			conn = new DBcontext().getSQLServerConnection();
			sql = "SELECT * FROM ACCOUNTS WHERE acc_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				acc = new Accounts(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getDate(9), rs.getInt(10), rs.getInt(11),
						rs.getString(12));
			}
			ps.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return acc;
	}

	public int InsertAccount(String id, String firstname, String lastname, int dep_id, String email, String gender,
			int type, String password) {
		int kq = 0;
		String sql = "";
		try {
			conn = new DBcontext().getSQLServerConnection();
			sql = "INSERT INTO ACCOUNTS([acc_id]," + "[acc_firstname]," + "[acc_lastname]," + "[dep_id],"
					+ "[acc_email]," + "[acc_gender]," + "[acc_type]," + "[acc_password]) "
					+ "VALUES (?,?,?,?,?,?,?,?);";

			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, firstname);
			ps.setString(3, lastname);
			ps.setInt(4, dep_id);
			ps.setString(5, email);
			ps.setString(6, gender);
			ps.setInt(7, type);
			ps.setString(8, password);

			kq = ps.executeUpdate();

			ps.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return kq;
	}
	
	public int InsertAccount(String id, String firstname, String lastname, int dep_id, String email, int type, String password) {
		int kq = 0;
		String sql = "";
		try {
			conn = new DBcontext().getSQLServerConnection();
			sql = "INSERT INTO ACCOUNTS([acc_id]," + "[acc_firstname]," + "[acc_lastname]," + "[dep_id],"
					+ "[acc_email]," + "[acc_type]," + "[acc_password]) "
					+ "VALUES (?,?,?,?,?,?,?);";

			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, firstname);
			ps.setString(3, lastname);
			ps.setInt(4, dep_id);
			ps.setString(5, email);
			ps.setInt(6, type);
			ps.setString(7, password);

			kq = ps.executeUpdate();

			ps.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return kq;
	}

	public int UpdateAccount(String firstname, String lastname, String id, String email, String password, String gender,
			String phone, String address, String birthdate, String cur_id) {
		int kq = 0;
		String sql = "";
		try {
			conn = new DBcontext().getSQLServerConnection();
			sql = "UPDATE ACCOUNTS SET [acc_firstname] = ?," + "[acc_lastname] = ?," + "[acc_id] = ?,"
					+ "[acc_email] = ?," + "[acc_password] = ?," + "[acc_gender] = ?," + "[acc_phone] = ?,"
					+ "[acc_address] = ?," + "[acc_birthdate] = ? " + "WHERE acc_id = ?;";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, firstname);
			ps.setString(2, lastname);
			ps.setString(3, id);
			ps.setString(4, email);
			ps.setString(5, password);
			ps.setString(6, gender);
			ps.setString(7, phone);
			ps.setString(8, address);
			ps.setString(9, birthdate);
			ps.setString(10, cur_id);

			kq = ps.executeUpdate();

			ps.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return kq;
	}
	
	public int UpdateAccount(String firstname, String lastname, String id, String email, String password, String gender,
			String phone, String address, String birthdate, String cur_id, int dep_id) {
		int kq = 0;
		String sql = "";
		try {
			conn = new DBcontext().getSQLServerConnection();
			sql = "UPDATE ACCOUNTS SET [acc_firstname] = ?," + "[acc_lastname] = ?," + "[acc_id] = ?,"
					+ "[acc_email] = ?," + "[acc_password] = ?," + "[acc_gender] = ?," + "[acc_phone] = ?,"
					+ "[acc_address] = ?," + "[acc_birthdate] = ?, " + "[dep_id] = ? " + "WHERE acc_id = ?;";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, firstname);
			ps.setString(2, lastname);
			ps.setString(3, id);
			ps.setString(4, email);
			ps.setString(5, password);
			ps.setString(6, gender);
			ps.setString(7, phone);
			ps.setString(8, address);
			ps.setString(9, birthdate);
			ps.setInt(10, dep_id);
			ps.setString(11, cur_id);

			kq = ps.executeUpdate();

			ps.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return kq;
	}

	public int UpdateAccount(String avatar, String id) {
		int kq = 0;
		String sql = "";
		try {
			conn = new DBcontext().getSQLServerConnection();
			sql = "UPDATE ACCOUNTS SET [acc_avatar] = ? " + "WHERE acc_id = ?;";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, avatar);
			ps.setString(2, id);
			kq = ps.executeUpdate();

			ps.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return kq;
	}
	
	public HashMap<String, String> showMarkStudent(String tea_id){
		String sql = "";
		HashMap<String, String> stu_map = new HashMap<String, String>();
		
		try {
			conn = new DBcontext().getSQLServerConnection();
			sql = "SELECT DISTINCT A.acc_id, A.acc_firstname + ' ' + A.acc_lastname "
				+ "FROM ACCOUNTS A JOIN STU_SUBJECT SS ON SS.stu_id = A.acc_id "
				+ "JOIN SUBJECTS S ON S.sub_id = SS.sub_id "
				+ "WHERE S.tea_id = ?;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, tea_id);
			rs = ps.executeQuery();

			while (rs.next()) {
				stu_map.put(rs.getString(1), rs.getString(2));
			}

			ps.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return stu_map;
	}
	
	public ArrayList<Accounts> showAllUser(){
		ArrayList<Accounts> list = new ArrayList<>();
		Accounts acc = null;
		String sql = "";
		
		try {
			conn = new DBcontext().getSQLServerConnection();
			sql = "SELECT acc_id, acc_firstname, acc_lastname, dep_id, acc_type, acc_email, acc_password "
				+ "FROM ACCOUNTS WHERE acc_type <> 3 ORDER BY acc_id;";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				acc = new Accounts();
				
				acc.setId(rs.getString(1));
				acc.setFirstname(rs.getString(2));
				acc.setLastname(rs.getString(3));
				acc.setDep_id(rs.getInt(4));
				acc.setType(rs.getInt(5));
				acc.setEmail(rs.getString(6));
				acc.setPassword(rs.getString(7));
				
				list.add(acc);
			}

			ps.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return list;
	}
	
	public int DeleteAccount(String id) {
		int kq = 0;
		String sql = "";
		try {
			conn = new DBcontext().getSQLServerConnection();
			sql = "DELETE FROM ACCOUNTS WHERE acc_id = ?;";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			kq = ps.executeUpdate();

			ps.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return kq;
	}
	
	public HashMap<String, String> getAccountMap(int type){
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "";
		
		try {
			conn = new DBcontext().getSQLServerConnection();
			sql = "SELECT acc_id, acc_firstname + ' ' + acc_lastname FROM ACCOUNTS WHERE acc_type = ?;";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, type);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				map.put(rs.getString(1), rs.getString(2));
			}

			ps.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return map;
	}

}
