package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BaseDao {
	protected Connection conn;
	protected PreparedStatement ps;
	protected ResultSet rs;
	
	public BaseDao() {
	}

	public BaseDao(Connection conn, PreparedStatement ps, ResultSet rs) {
		super();
		this.conn = conn;
		this.ps = ps;
		this.rs = rs;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public PreparedStatement getPs() {
		return ps;
	}

	public void setPs(PreparedStatement ps) {
		this.ps = ps;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
	
}
