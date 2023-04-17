package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class MessagesDAO {
	
	private static MessagesDAO instance = null;

	public synchronized static MessagesDAO getInstance() {
		if (instance == null) {
			instance = new MessagesDAO();
		}
		return instance;
	}

	private MessagesDAO() {
	}

	private Connection getConnection() throws Exception {
		Context iCtx = new InitialContext();
		DataSource ds = (DataSource) iCtx.lookup("java:/comp/env/jdbc/ora");
		return ds.getConnection();
	}

	
	public int insert(String writer, String message) throws Exception{
		String sql = " insert into messages values(messages_seq.nextval,?,?)";
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
