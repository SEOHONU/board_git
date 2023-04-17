package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.MessagesDTO;

public class MessageDAO {
	private static MessageDAO instance;
	
	private MessageDAO() {}
	public synchronized static MessageDAO getInstance() {
		if(instance == null) {
			instance = new MessageDAO();
		}
		return instance;
	}
	
	private Connection getConnection() throws Exception{
		Context ictx = new InitialContext();
		DataSource ds = (DataSource)ictx.lookup("java:/comp/env/jdbc/ora");
		return ds.getConnection();
	}
	
	public void insertMessage(MessagesDTO dto) throws Exception{
		String sql = "insert into messages values (message_seq.nextval,?,?)";
		try(
				Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				){
			pstat.setString(1, dto.getWriter());
			pstat.setString(2, dto.getMessage());
			pstat.executeUpdate();
			con.commit();
		}
	}
	
}
