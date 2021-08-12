package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import context.DBcontext;
import entity.Departments;

public class DepartmentDao extends BaseDao{
	public HashMap<Integer,String> getDepMap(){
		HashMap<Integer,String> map = new HashMap<Integer, String>();
		
		String sql = "";
		
		try {
			conn = new DBcontext().getSQLServerConnection();
			sql = "SELECT * FROM DEPARTMENTS;";
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
	
	public ArrayList<Departments> getDepList(){
		ArrayList<Departments> list = new ArrayList<Departments>();
		Departments dep = null;
		String sql = "";
		
		try {
			conn = new DBcontext().getSQLServerConnection();
			sql = "SELECT * FROM DEPARTMENTS;";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				dep = new Departments();
				
				dep.setId(rs.getInt(1));
				dep.setName(rs.getString(2));
				dep.setSch_id(rs.getInt(3));
				
				list.add(dep);
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
	
	public int DeleteDepartment(int id) {
		int kq = 0;
		String sql = "";
		try {
			conn = new DBcontext().getSQLServerConnection();
			sql = "DELETE FROM DEPARTMENTS WHERE dep_id = ?;";

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
	
	public int InsertDepartment(String name, int sch_id) {
		int kq = 0;
		String sql = "";
		try {
			conn = new DBcontext().getSQLServerConnection();
			sql = "INSERT INTO DEPARTMENTS([dep_name],[sch_id]) VALUES(?,?);";

			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, sch_id);

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
	
	public int UpdateDepartment(int id, String name, int sch_id) {
		int kq = 0;
		String sql = "";
		try {
			conn = new DBcontext().getSQLServerConnection();
			sql = "UPDATE DEPARTMENTS SET dep_name = ?, sch_id = ? WHERE dep_id = ?;";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, sch_id);
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
}
