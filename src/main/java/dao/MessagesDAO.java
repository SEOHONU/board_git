package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

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
}
