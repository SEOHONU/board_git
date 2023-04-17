package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MessageDAO {

	private static MessageDAO instance = null; 
	
	public synchronized static MessageDAO getInstance() {
		if (instance == null) {
			instance = new MessageDAO(); 
		}
		return instance; 
	}
	
	private MessageDAO() {
		
	}
	
	private Connection getConnection() throws Exception {
		Context iCtx = new InitialContext();
		DataSource ds = (DataSource) iCtx.lookup("java:/comp/env/jdbc/ora");
		return ds.getConnection();
	}
	
	public int delete(int id) throws Exception {
		String sql = "delete from messages where id =?"; 
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql); ) {
			pstat.setInt(1, id);
			int result = pstat.executeUpdate(); 
			con.commit();
			return result; 
		}
	}
}
