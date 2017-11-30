package teatro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Dao{
	
static Connection con = null;


	//Conexão Com o Banco
	public static Connection getConnection(){
		try{
			String URL = "jdbc:mysql://localhost:3306/bancoteatro?useSSL=false";
			String USER = "root";
			String PASSWORD = "";
			String DRIVER = "com.mysql.jdbc.Driver";
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return con;
	}
	public static void closeConnection(Connection con){
		try {
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
