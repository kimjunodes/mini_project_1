package test;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
 
public class Test2 {
	private static Connection makeConnection() {
	      String url = "jdbc:oracle:thin:@localhost:1521:xe";
	      Connection con = null;
	      try {
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         System.out.println("�����ͺ��̽� ������ ...");
	         con = DriverManager.getConnection(url, "movie", "1234");
	         System.out.println("�����ͺ��̽� ���� ����");   
	      } catch (ClassNotFoundException e) {
	         System.out.println("JDBC ����̹��� ã�� ���߽��ϴ�...");
	      } catch (SQLException e) {
	         System.out.println("�����ͺ��̽� ���� ����");
	         
	      }
	      return con;
	   }
    public static void main(String[] args) {
    	Connection con = makeConnection();
        Frame frm = new Frame(con,"Bit Cinema");
        JButton j = frm.setButton("�α���",200,200,100,30);
        
        j.addActionListener(event -> {
        	frm.setVisible(false);
        	UserFrame f = new UserFrame(con,"User Menu","abcd");
        });
        frm.setVisible(true);
    }
}
