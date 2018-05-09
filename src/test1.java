import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bluedot.util.DBConnection;


public class test1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		conn = DBConnection.getConn();
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sql="SELECT recruit_id_seq.nextval FROM DUAL ";
		int i = 0;
		try {
			pstm=conn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()){
				i=rs.getInt(1);
			 }
			System.out.println("Êä³öÊÇ"+i);
			String sql1="insert into test values (?)";
		    pstm=conn.prepareStatement(sql1);
		    pstm.setInt(1, i);
		    int n=pstm.executeUpdate();
		    conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			DBConnection.close(pstm, conn, rs);
		}
	}

}
