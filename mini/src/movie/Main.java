package movie;
import java.sql.*;
import java.util.Scanner;

public class Main {
	public static Connection makeConnection() {
	      String url = "jdbc:oracle:thin:@localhost:1521:xe";
	      Connection con = null;
	      try {
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         
	         System.out.println("�����ͺ��̽� ������ ...");
	         con = DriverManager.getConnection(url, "hr", "hr");
	         System.out.println("�����ͺ��̽� ���� ����");   
	      } catch (ClassNotFoundException e) {
	         System.out.println("JDBC ����̹��� ã�� ���߽��ϴ�...");
	      } catch (SQLException e) {
	         System.out.println("�����ͺ��̽� ���� ����");
	         
	      }
	      return con;
	   }
	
	public static void main(String[] args) throws SQLException  {
		Connection con = makeConnection();
		Menu.menu(con);
		con.close();
	}
}
