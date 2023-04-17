package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
<<<<<<< HEAD
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
=======
>>>>>>> 6185bef087508e80665881830a0a4d30196c7996

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

<<<<<<< HEAD
import dto.MessagesDTO;

public class MessagesDAO {

	private static MessagesDAO instance = null;
	
	public synchronized static MessagesDAO getInstance() {
		if (instance == null) { // 다중접속자 때문에 true가 생길 수 있음(DAO 만들어지는 사이에 true가 될 수가 있음)
			instance = new MessagesDAO();
		}

		return instance;
	}
	
	private MessagesDAO() {
	}

	private Connection getconnection() throws Exception {
		Context iCtx = new InitialContext();
		DataSource ds = (DataSource) iCtx.lookup("java:/comp/env/jdbc/ora");// lookup 찾아봐라
		return ds.getConnection();
	}
	
	public List<MessagesDTO> select() throws Exception {
		String sql = "select * from messages";
		try(Connection con = this.getconnection();
				PreparedStatement pstm = con.prepareStatement(sql);
				ResultSet rs = pstm.executeQuery()){
			List<MessagesDTO>result = new ArrayList<>();
			while(rs.next()) {
				int id = rs.getInt("id");
				String writer = rs.getString("writer");
				String msg = rs.getString("message");
				MessagesDTO dto = new MessagesDTO(id, writer, msg);
				result.add(dto);
			}return result;
		}
	}
=======

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

>>>>>>> 6185bef087508e80665881830a0a4d30196c7996
}
