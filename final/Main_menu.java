package Mini_project;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

class Main_menu extends Frame {
	private final String sql;
	private final String sql2;
	private final String sql3;
	
	public static JLabel lb1, lb2;
	public static JTextField tf, pn;
	public static JPasswordField pf;
	public static JButton bt1, bt2, bt3, bt4;
	
	private static Connection con;
	private static PreparedStatement I;
	
	
	Main_menu(Connection con) {
		super(con,"예매 시스템");
		this.con = con;
		sql = "select * from members where (USER_ID = ? AND USER_PW = ?)";
		sql2 = "select * from members where USER_PN = ?";
		sql3 = "select * from members where USER_ID = ? AND USER_PN = ?";
		
		
		JLabel lb1 = new JLabel("ID :");
		lb1.setBounds(30, 125, 100, 30);
		
		JTextField tf = new JTextField();
		tf.setBounds(150, 125, 200, 30);		
		
		JLabel lb2 = new JLabel("PASSWORD :");
		lb2.setBounds(30, 200, 100, 30);		
		
		JPasswordField pf = new JPasswordField();
		pf.setBounds(150, 200, 200, 30);		
		
		JButton bt1 = new JButton("Login");
		bt1.setBounds(375, 120, 95, 50);				
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				String id = tf.getText();
				String pw = pf.getText();			
				try {
					I = con.prepareStatement(sql);
					I.setString(1, id);
					I.setString(2, pw);
					ResultSet rs = I.executeQuery();
					
					if(rs.next()) {
						if("admin".equals(rs.getString(1))) {
							JOptionPane.showMessageDialog(null, "admin 계정입니다.");
							dispose();
							new AdminFrame(con);
						} else {
							JOptionPane.showMessageDialog(null, "user 계정입니다.");
							dispose();
							new UserFrame(con,"User Menu",id);
						}
					} else {
						JOptionPane.showMessageDialog(null, "잘못된 아이디 또는 패스워드 입니다.");
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "시스템 오류. 1");
				}
			}	
		});
		
		JButton bt2 = new JButton("회원가입");
	      bt2.setBounds(375, 190, 95, 50);      
	      bt2.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            new Add_acount(con,bt2.getText());
	            dispose();
	         }   
	      });
		
		JButton bt3 = new JButton("ID 찾기");
		bt3.setBounds(50, 300, 125, 25);
		bt3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField pn = new JTextField();
				String pnum;
				
				try {
					pnum = JOptionPane.showInputDialog("전화번호를 입력하세요.");
					
					I = con.prepareStatement(sql2);
					I.setString(1, pnum);
					ResultSet rs = I.executeQuery();

					if(rs.next()) {
						JOptionPane.showMessageDialog(null, "회원님의 아이디는 " + rs.getString(1) + " 입니다.");
					} else {
						JOptionPane.showMessageDialog(null, "일치하는 전화번호가 없습니다.");
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "시스템 오류. 2");
				}
			}	
		});
		
		JButton bt4 = new JButton("PW 찾기");
		bt4.setBounds(215, 300, 125, 25);
		bt4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = tf.getText();
				String pnum;
				try {
					id = JOptionPane.showInputDialog("아이디를 입력하세요.");
					pnum = JOptionPane.showInputDialog("전화번호를 입력하세요.");
					
					PreparedStatement I = con.prepareStatement(sql3);
					
					I.setString(1, id);
					I.setString(2, pnum);
					
					ResultSet rs = I.executeQuery();
					
					if(rs.next()) {						
						JOptionPane.showMessageDialog(null, "회원님의 비밀번호는 " + rs.getString(2) + " 입니다." );						
					} else {
						JOptionPane.showMessageDialog(null, "일치하는 아이디 또는 전화번호가 없습니다.");
						}
					}	
				 catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "DB에 연결되지 않았습니다.3");				
				}
			}	
		});
		
		
		add(lb1); 
		add(tf);
		add(lb2); 
		add(pf);
		add(bt1);
		add(bt2);
		add(bt3);
		add(bt4);
		setVisible(true);
	}
}
