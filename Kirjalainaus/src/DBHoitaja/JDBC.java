package DBHoitaja;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBC {
    java.sql.Statement stmt;

    String driver = "com.mysql.jdbc.Driver";
	String user = "root";
	String pass = "vimaru";
	String url = "jdbc:mysql://localhost/a1602549";
	
    public Connection getConnection(){
    	Connection con = null;
		try {
			Class.forName(driver);
			try {
				con = DriverManager.getConnection(url, user, pass);
				con.setAutoCommit(false);
				//con.setTransactionIsolation(con.TRANSACTION_SERIALIZABLE);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			   try {
				stmt = con.createStatement();
			} catch (SQLException e) {
				e.printStackTrace();
			}
          } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public boolean suljeConnection(){
		try {
			Connection con = getConnection();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

}
