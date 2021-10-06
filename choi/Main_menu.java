package team_test;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class Main_menu extends JFrame {
	String sql = "select * from members where m_id = ? AND m_pw = ?";
	String sql2 = "select * from members where p_number = ?";
	String sql3 = "select * from members where m_id = ? AND p_number = ?";
	public static JLabel lb1, lb2;
	public static JTextField tf, pn;
	public static JPasswordField pf;
	public static JButton bt1, bt2, bt3, bt4;
	private static Connection con;
	private static PreparedStatement I;
	
	Frame frm;
	
	public Main_menu() {
		init();
		con = Main.makeConnection();
		
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
							con.close();
						} else {
							JOptionPane.showMessageDialog(null, "user 계정입니다.");
							con.close();
						}
					} else {
						JOptionPane.showMessageDialog(null, "잘못된 아이디 또는 패스워드 입니다.");
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "시스템 오류. 1");
				}
			}	
		});
		    
	      bt2.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            Add_acount ac = new Add_acount();
	            frm.dispose();
	         }   
	      });
	      
	      
	      bt3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pn = new JTextField();
					String pnum;
					
					try {
						pnum = JOptionPane.showInputDialog("전화번호를 입력하세요.");
						
						I = con.prepareStatement(sql2);
						I.setString(1, pnum);
						ResultSet rs = I.executeQuery();

						if(rs.next()) {
							JOptionPane.showMessageDialog(null, "회원님의 아이디는 " + rs.getString(1) + " 입니다.");
							con.close();
						} else {
							JOptionPane.showMessageDialog(null, "일치하는 전화번호가 없습니다.");
							con.close();
						}
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "시스템 오류. 2");
					}
				}	
			});
		
			bt4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String id = tf.getText();
					String pnum;
					try {
						id = JOptionPane.showInputDialog("아이디를 입력하세요.");
						pnum = JOptionPane.showInputDialog("전화번호를 입력하세요.");
						
						I = con.prepareStatement(sql3);
						
						I.setString(1, id);
						I.setString(2, pnum);
						
						ResultSet rs = I.executeQuery();
						
						if(rs.next()) {						
							JOptionPane.showMessageDialog(null, "회원님의 비밀번호는 " + rs.getString(2) + " 입니다." );
							con.close();
						} else {
							JOptionPane.showMessageDialog(null, "일치하는 아이디 또는 전화번호가 없습니다.");
							con.close();
							}
						}	
					 catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "시스템 오류 3.");				
					}
				}	
			});
	}
	
	private void init() {
		frm = new Frame("Bit cinema");
		
		bt1 = new JButton("Login");
		bt1.setBounds(375, 120, 95, 50);
		bt2 = new JButton("회원가입");
	    bt2.setBounds(375, 190, 95, 50);    
	    bt3 = new JButton("ID 찾기");
		bt3.setBounds(50, 300, 125, 25);
	    bt4 = new JButton("PW 찾기");
		bt4.setBounds(215, 300, 125, 25);
		
		lb1 = new JLabel("ID :");
		lb1.setBounds(30, 125, 100, 30);
		tf = new JTextField();
		tf.setBounds(150, 125, 200, 30);		
		lb2 = new JLabel("PASSWORD :");
		lb2.setBounds(30, 200, 100, 30);
		pf = new JPasswordField();
		pf.setBounds(150, 200, 200, 30);		
		
		frm.add(lb1); 
		frm.add(tf);
		frm.add(lb2); 
		frm.add(pf);
		frm.add(bt1);
		frm.add(bt2);
		frm.add(bt3);
		frm.add(bt4);
		frm.setSize(500, 400);
		frm.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new Main_menu();
		
	}
}
