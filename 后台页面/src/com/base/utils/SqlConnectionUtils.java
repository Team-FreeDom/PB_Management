package com.base.utils;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlConnectionUtils {

	public static void free(Connection conn, Statement stmt, ResultSet rs) {

		try {
			
			if (rs != null)
				rs.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			try {
				
				if (stmt != null)
					stmt.close();
				
			} catch (SQLException e) {
				e.printStackTrace(); 
				
			} finally {
				
				try {
					
					if (conn != null)
						conn.close();
					
				} catch (SQLException e) {
					
					e.printStackTrace();
				}

			}
		}
	}

}
