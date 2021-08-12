package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import context.DBcontext;
import entity.Accounts;
import entity.Subjects;

public class SubjectDao extends BaseDao {
	public ArrayList<Subjects> showStudentSubject(String stu_id) {
		Subjects sub = null;
		ArrayList<Subjects> list_sub = new ArrayList<>();

		String sql = "";

		try {
			conn = new DBcontext().getSQLServerConnection();
			sql = "SELECT S.sub_id, S.sub_name, S.sub_schedule, S.sub_room, S.sub_startdate "
					+ "FROM STU_SUBJECT SS JOIN SUBJECTS S ON S.sub_id = SS.sub_id "
					+ "WHERE SS.stu_id = ? ORDER BY S.sub_startdate;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, stu_id);
			rs = ps.executeQuery();

			while (rs.next()) {
				sub = new Subjects();

				sub.setId(rs.getInt(1));
				sub.setName(rs.getString(2));
				sub.setSchedule(rs.getString(3));
				sub.setRoom(rs.getString(4));
				sub.setStartdate(rs.getDate(5));

				list_sub.add(sub);
			}

			ps.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return list_sub;
	}

	public HashMap<Subjects, String> showRegisSubject(String stu_id, int dep_id) {
		Subjects sub = null;
		HashMap<Subjects, String> sub_list = new HashMap<Subjects, String>();

		String sql = "";

		try {
			conn = new DBcontext().getSQLServerConnection();
			sql = "SELECT S.sub_id, S.sub_name, S.sub_schedule, S.sub_room, A.acc_firstname + ' ' + A.acc_lastname, S.sub_startdate "
				+ "FROM SUBJECTS S JOIN ACCOUNTS A ON A.acc_id = S.tea_id "
				+ "JOIN SUBJECT_GROUP SG ON SG.sg_id = S.sg_id "
				+ "WHERE S.sub_startdate > GETDATE() AND SG.dep_id = ? AND "
				+ "S.sub_id NOT IN (SELECT SS.sub_id FROM STU_SUBJECT SS WHERE SS.stu_id = ?) "
				+ "ORDER BY S.sub_startdate;";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, dep_id);
			ps.setString(2, stu_id);
			rs = ps.executeQuery();

			while (rs.next()) {
				sub = new Subjects();

				sub.setId(rs.getInt(1));
				sub.setName(rs.getString(2));
				sub.setSchedule(rs.getString(3));
				sub.setRoom(rs.getString(4));
				sub.setStartdate(rs.getDate(6));

				sub_list.put(sub, rs.getString(5));
			}

			ps.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return sub_list;
	}
	
	public HashMap<Subjects, String> showTeaSchedule(String tea_id, int dep_id) {
		Subjects sub = null;
		HashMap<Subjects, String> sche_map = new HashMap<Subjects, String>();

		String sql = "";

		try {
			conn = new DBcontext().getSQLServerConnection();
			sql = "SELECT S.sub_id, S.sub_name, S.sub_schedule, S.sub_room, S.sub_startdate, S.sub_enddate, SG.sg_name "
				+ "FROM SUBJECTS S JOIN SUBJECT_GROUP SG ON SG.sg_id = S.sg_id "
				+ "WHERE S.tea_id = ? AND SG.dep_id = ?;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, tea_id);
			ps.setInt(2, dep_id);
			rs = ps.executeQuery();

			while (rs.next()) {
				sub = new Subjects();

				sub.setId(rs.getInt(1));
				sub.setName(rs.getString(2));
				sub.setSchedule(rs.getString(3));
				sub.setRoom(rs.getString(4));
				sub.setStartdate(rs.getDate(5));
				sub.setEnddate(rs.getDate(6));

				sche_map.put(sub, rs.getString(7));
			}

			ps.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return sche_map;
	}
	
	public HashMap<Integer, Subjects> showMarkSubject(String tea_id) {
		HashMap<Integer, Subjects> sub_map = new HashMap<Integer, Subjects>();
		Subjects sub = null;
		String sql = "";

		try {
			conn = new DBcontext().getSQLServerConnection();
			sql = "SELECT S.sub_id, S.sub_name, S.sub_startdate, S.sub_enddate "
				+ "FROM SUBJECTS S "
				+ "WHERE S.tea_id = ?;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, tea_id);
			rs = ps.executeQuery();

			while (rs.next()) {
				sub = new Subjects();
				
				sub.setId(rs.getInt(1));
				sub.setName(rs.getString(2));
				sub.setStartdate(rs.getDate(3));
				sub.setEnddate(rs.getDate(4));
				
				sub_map.put(rs.getInt(1), sub);
			}

			ps.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return sub_map;
	}
	
	public HashMap<Integer, String> getSubMap() {
		HashMap<Integer, String> sub_map = new HashMap<Integer, String>();
		String sql = "";

		try {
			conn = new DBcontext().getSQLServerConnection();
			sql = "SELECT sub_id, sub_name FROM SUBJECTS;";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				sub_map.put(rs.getInt(1), rs.getString(2));
			}

			ps.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return sub_map;
	}
	
	public ArrayList<Subjects> getSubList(){
		ArrayList<Subjects> list = new ArrayList<Subjects>();
		Subjects sub = null;
		String sql = "";
		
		try {
			conn = new DBcontext().getSQLServerConnection();
			sql = "SELECT * FROM SUBJECTS;";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				sub = new Subjects();
				
				sub.setId(rs.getInt(1));
				sub.setName(rs.getString(2));
				sub.setStartdate(rs.getDate(3));
				sub.setEnddate(rs.getDate(4));
				sub.setSchedule(rs.getString(5));
				sub.setRoom(rs.getString(6));
				sub.setTea_id(rs.getString(7));
				sub.setSg_id(rs.getInt(8));
				
				list.add(sub);
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
	
	public int InsertSubject(String name, String schedule, String room, String startdate, String enddate, String tea_id, int sg_id) {
		int kq = 0;
		String sql = "";
		try {
			conn = new DBcontext().getSQLServerConnection();
			sql = "INSERT INTO SUBJECTS([sub_name],[sub_startdate],[tea_id],[sg_id],[sub_enddate],[sub_schedule],[sub_room])"
				+ " VALUES(?,?,?,?,?,?,?);";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, startdate);
			ps.setString(3, tea_id);
			ps.setInt(4, sg_id);
			ps.setString(5, enddate);
			ps.setString(6, schedule);
			ps.setString(7, room);
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
	
	public Subjects FindBySubID(int id) {
		Subjects sub = new Subjects();
		String sql = "";
		try {
			conn = new DBcontext().getSQLServerConnection();
			sql = "SELECT * FROM SUBJECTS WHERE sub_id = ?;";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				sub.setId(rs.getInt(1));
				sub.setName(rs.getString(2));
				sub.setStartdate(rs.getDate(3));
				sub.setEnddate(rs.getDate(4));
				sub.setSchedule(rs.getString(5));
				sub.setRoom(rs.getString(6));
				sub.setTea_id(rs.getString(7));
				sub.setSg_id(rs.getInt(8));
			}
			
			ps.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return sub;
	}
	
	public int DeleteSubject(int id) {
		int kq = 0;
		String sql = "";
		try {
			conn = new DBcontext().getSQLServerConnection();
			sql = "DELETE FROM SUBJECTS WHERE sub_id = ?;";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
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
	
	public int UpdateSubject(int id, String name, String schedule, String room, String startdate, String enddate, String tea_id, int sg_id) {
		int kq = 0;
		String sql = "";
		try {
			conn = new DBcontext().getSQLServerConnection();
			sql = "UPDATE SUBJECTS SET sub_name = ?, sub_startdate = ?, tea_id = ?, sg_id = ?, "
				+ "sub_enddate = ?, sub_schedule = ?, sub_room = ? WHERE sub_id = ?;";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, startdate);
			ps.setString(3, tea_id);
			ps.setInt(4, sg_id);
			ps.setString(5, enddate);
			ps.setString(6, schedule);
			ps.setString(7, room);
			ps.setInt(8, id);
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
}
