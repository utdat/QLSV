package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import context.DBcontext;
import entity.SubjectGroup;

public class SubjectGroupDao extends BaseDao{
	public int DeleteSubGroup(int id) {
		int kq = 0;
		String sql = "";
		try {
			conn = new DBcontext().getSQLServerConnection();
			sql = "DELETE FROM SUBJECT_GROUP WHERE sg_id = ?;";

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
	
	public int InsertSubGroup(String name, int dep_id) {
		int kq = 0;
		String sql = "";
		try {
			conn = new DBcontext().getSQLServerConnection();
			sql = "INSERT INTO SUBJECT_GROUP([sg_name],[dep_id]) VALUES(?,?);";

			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, dep_id);

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
	
	public int UpdateSubGroup(int id, String name, int dep_id) {
		int kq = 0;
		String sql = "";
		try {
			conn = new DBcontext().getSQLServerConnection();
			sql = "UPDATE SUBJECT_GROUP SET sg_name = ?, dep_id = ? WHERE sg_id = ?;";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, dep_id);
			ps.setInt(3, id);

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
	
	public ArrayList<SubjectGroup> getSubGroupList(){
		ArrayList<SubjectGroup> list = new ArrayList<SubjectGroup>();
		SubjectGroup sg = null;
		String sql = "";
		
		try {
			conn = new DBcontext().getSQLServerConnection();
			sql = "SELECT * FROM SUBJECT_GROUP;";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				sg = new SubjectGroup();
				
				sg.setId(rs.getInt(1));
				sg.setName(rs.getString(2));
				sg.setDep_id(rs.getInt(3));
				
				list.add(sg);
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
	
	public HashMap<Integer, String> getSubGroupMap(){
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		String sql = "";
		
		try {
			conn = new DBcontext().getSQLServerConnection();
			sql = "SELECT sg_id, sg_name FROM SUBJECT_GROUP;";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				map.put(rs.getInt(1), rs.getString(2));
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
