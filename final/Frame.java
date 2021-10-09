package Mini_project;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.*;

class Frame extends JFrame {
	Connection con;
	JButton exit = new JButton("EXIT");
	Frame(Connection con,String lbl){
		this.con= con;
		
		setSize(500,400);
		setTitle("Bit Cinema");
		getContentPane().add(exit);
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setResizable(false);
        
        JLabel lTitle = new JLabel(lbl, SwingConstants.CENTER);
        lTitle.setBounds(100, 25, 300, 50);
        getContentPane().add(lTitle);
        lTitle.setFont(new Font("���� ���",Font.BOLD,30));
        
        setVisible(true);
        
        exit.setBounds(400, 300, 60, 30);
		exit.addActionListener(event -> {
			try {
				con.close();
				System.exit(0);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    });
	}
	JButton setButton(String title,int x, int y, int width, int height) {
		JButton btn = new JButton(title);
		btn.setBounds(x, y, width, height);
        getContentPane().add(btn);
		return btn;
	}
	JLabel setLabel(String text,int x, int y, int width, int height) {
		JLabel lbl = new JLabel(text);
		lbl.setBounds(x, y, width, height);
		return lbl;
	}
	public static Connection makeConnection() {
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
	
	
	public static void main(String[] args) throws SQLException  {
		Connection con = makeConnection();
		new Frame(con,"abc");
	}
	
}
