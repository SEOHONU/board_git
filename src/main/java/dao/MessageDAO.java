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

	
	public int insert(String writer, String message) throws Exception{
		String sql = " insert into messages values(id.nextval(),?,?)";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1,writer);
			pstat.setString (2,message);
			
		int result = pstat.executeUpdate();
		con.commit();
		return result;
		}
	}
	

}
