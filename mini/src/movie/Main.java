package movie;
import java.sql.*;
import java.util.Scanner;

public class Main {
	public static Connection makeConnection() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, "movie", "1234");
			System.out.println("��ȭ�� ���α׷� ����");
		}catch(ClassNotFoundException e){}catch(SQLException e){}
		return con;
	}
	
	public static void main(String[] args) throws SQLException  {
		Connection con = makeConnection();
		Menu.menu(con);
		con.close();
	}
}
