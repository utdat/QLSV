package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import context.DBcontext;
import entity.StuSubject;

public class StuSubjectDao extends BaseDao{
	public ArrayList<StuSubject> showStudentSubject(String stu_id) {
		StuSubject stu_subject = null;
		ArrayList<StuSubject> list_sub = new ArrayList<>();

		String sql = "";

		try {
			conn = new DBcontext().getSQLServerConnection();
			sql = "SELECT SS.sub_id, ISNULL(SS.p_midterm, -1), ISNULL(SS.p_endterm, -1) "
					   + "FROM STU_SUBJECT SS JOIN SUBJECTS S ON S.sub_id = SS.sub_id "
					   + "WHERE SS.stu_id = ? ORDER BY S.sub_startdate;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, stu_id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				stu_subject = new StuSubject();

				stu_subject.setSub_id(rs.getInt(1));
				stu_subject.setMidterm(rs.getFloat(2));				
				stu_subject.setEndterm(rs.getFloat(3));
				
				list_sub.add(stu_subject);
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
	
	public int cancelSubRegis(int sub_id, String acc_id) {
		String sql = "";
		int kq = 0;
		try {
			conn = new DBcontext().getSQLServerConnection();
			sql = "DELETE FROM STU_SUBJECT WHERE sub_id = ? AND stu_id = ?;";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, sub_id);
			ps.setString(2, acc_id);
			
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
	
	public int submitSubject(int sub_id, String stu_id) {
		String sql = "";
		int kq = 0;
		try {
			conn = new DBcontext().getSQLServerConnection();
			sql = "INSERT INTO STU_SUBJECT([stu_id],[sub_id]) VALUES(?,?);";
			ps = conn.prepareStatement(sql);
			ps.setString(1, stu_id);
			ps.setInt(2, sub_id);
			
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
	
	public ArrayList<StuSubject> showMarkSubject(String tea_id, String sub_id) {
		StuSubject stu_subject = null;
		ArrayList<StuSubject> list_sub = new ArrayList<>();

		String sql = "";

		try {
			conn = new DBcontext().getSQLServerConnection();
			sql = "SELECT SS.sub_id, SS.stu_id, ISNULL(SS.p_midterm, -1), ISNULL(SS.p_endterm, -1) "
				+ "FROM STU_SUBJECT SS JOIN SUBJECTS S ON S.sub_id = SS.sub_id "
				+ "JOIN ACCOUNTS A ON A.acc_id = SS.stu_id "
				+ "WHERE S.tea_id = ? ";
			
			if(sub_id != null) {
				sql += " AND S.sub_id = ? ORDER BY SS.stu_id";
				ps = conn.prepareStatement(sql);
				ps.setString(1, tea_id);
				ps.setInt(2, Integer.parseInt(sub_id));
			}else {
				sql += " ORDER BY SS.sub_id, SS.stu_id";
				ps = conn.prepareStatement(sql);
				ps.setString(1, tea_id);
			}
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				stu_subject = new StuSubject();

				stu_subject.setSub_id(rs.getInt(1));
				stu_subject.setStu_id(rs.getString(2));
				stu_subject.setMidterm(rs.getFloat(3));				
				stu_subject.setEndterm(rs.getFloat(4));
				
				list_sub.add(stu_subject);
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
	
	public int updateMidScore(int sub_id, String stu_id, float midterm) {
		String sql = "";
		int kq = 0;
		try {
			conn = new DBcontext().getSQLServerConnection();
			sql = "UPDATE STU_SUBJECT SET p_midterm = ? WHERE stu_id = ? AND sub_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setFloat(1, midterm);
			ps.setString(2, stu_id);
			ps.setInt(3, sub_id);
			
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
	
	public int updateEndScore(int sub_id, String stu_id, float endterm) {
		String sql = "";
		int kq = 0;
		try {
			conn = new DBcontext().getSQLServerConnection();
			sql = "UPDATE STU_SUBJECT SET p_endterm = ? WHERE stu_id = ? AND sub_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setFloat(1, endterm);
			ps.setString(2, stu_id);
			ps.setInt(3, sub_id);
			
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
	
	public ArrayList<StuSubject> showAllList() {
		StuSubject stu_subject = null;
		ArrayList<StuSubject> list_sub = new ArrayList<>();

		String sql = "";

		try {
			conn = new DBcontext().getSQLServerConnection();
			sql = "SELECT stu_id, sub_id, ISNULL(p_midterm, -1), ISNULL(p_endterm, -1) FROM STU_SUBJECT ORDER BY sub_id, stu_id;";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				stu_subject = new StuSubject();

				stu_subject.setStu_id(rs.getString(1));
				stu_subject.setSub_id(rs.getInt(2));
				stu_subject.setMidterm(rs.getFloat(3));				
				stu_subject.setEndterm(rs.getFloat(4));
				
				list_sub.add(stu_subject);
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
}
