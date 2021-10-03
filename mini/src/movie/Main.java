package movie;
import java.sql.*;
import java.util.Scanner;

public class Main {
	public static Connection makeConnection() {
	      String url = "jdbc:oracle:thin:@localhost:1521:xe";
	      Connection con = null;
	      try {
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         
	         System.out.println("데이터베이스 연결중 ...");
	         con = DriverManager.getConnection(url, "hr", "hr");
	         System.out.println("데이터베이스 연결 성공");   
	      } catch (ClassNotFoundException e) {
	         System.out.println("JDBC 드라이버를 찾지 못했습니다...");
	      } catch (SQLException e) {
	         System.out.println("데이터베이스 연결 실패");
	         
	      }
	      return con;
	   }
	
	public static void main(String[] args) throws SQLException  {
		Connection con = makeConnection();
		Menu.menu(con);
		con.close();
	}
}
