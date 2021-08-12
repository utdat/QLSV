package dao;

import java.sql.SQLException;
import java.util.HashMap;

import context.DBcontext;

public class SchoolDao extends BaseDao{
	public HashMap<Integer,String> getSchoolMap(){
		HashMap<Integer,String> map = new HashMap<Integer, String>();
		
		String sql = "";
		
		try {
			conn = new DBcontext().getSQLServerConnection();
			sql = "SELECT * FROM SCHOOLS;";
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
