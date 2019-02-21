package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Program {

	public static void main(String[] args) {
		Connection conn = null;
		Statement st = null;

		try {
			conn = DB.getConnection();
			
			conn.setAutoCommit(false);
			
			st = conn.createStatement();
						
			int rowsAffected1 = st.executeUpdate("UPDATE seller set BaseSalary = 2090 where DepartmentId = 1");
			
			int x = 1;
			if(x<2) {
				throw new SQLException("Fake error!"); 
			}
			
			int rowsAffected2 = st.executeUpdate("UPDATE seller set BaseSalary = 3090 where DepartmentId = 2");
			
			conn.commit();
			
			System.out.print("Done! Lines affected 1: " + rowsAffected1);
			System.out.println(" Lines affected 2: " + rowsAffected2);

		} catch (SQLException e) {
			try {
				conn.rollback();
				throw new DbException("Transaction rolled back! Caused by: "+ e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Error try to rollback! Caused by: " + e.getMessage());
			}
			

		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}
