package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class MessagesDAO {
	private MessagesDAO() {};
	private static MessagesDAO instance=null;
	public synchronized static MessagesDAO getInstance() {
		if(instance==null) {
			instance= new MessagesDAO();
		}
		return instance;
	}

	private Connection getConnection() throws Exception{
		Context iCxt = new InitialContext();
		DataSource ds = (DataSource)iCxt.lookup("java:/comp/env/jdbc/ora");
		return ds.getConnection();
	}

	public int updateMessages(int id,String writer, String message) throws Exception{
		String sql = "update messages set writer=?, message=? where id=?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(3, id);
			pstat.setString(1, writer);
			pstat.setString(2, message);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}

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
