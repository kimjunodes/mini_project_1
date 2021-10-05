package mini_project1;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class Main_menu extends JFrame {
	
	public static void main(String[] args) {
		Connection con = Main.makeConnection();
		
		String sql = "select * from members where m_id = ? AND m_pw = ?";
		
		Frame frm = new Frame("Bit cinema");
		
		
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
					PreparedStatement I = con.prepareStatement(sql);
					I.setString(1, id);
					I.setString(2, pw);
					ResultSet rs = I.executeQuery();
					
					if(rs.next()) {
						if(rs.getInt(1) == 0) {
							JOptionPane.showMessageDialog(null, "admin 계정입니다.");
						} else {
							JOptionPane.showMessageDialog(null, "user 계정입니다.");
						}
					} else {
						JOptionPane.showMessageDialog(null, "잘못된 아이디 또는 패스워드 입니다.");
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "DB에 연결되지 않았습니다.");
				}
			}	
		});
		
		JButton bt2 = new JButton("회원가입");
		bt2.setBounds(375, 190, 95, 50);		
		bt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}	
		});
		
		JButton bt3 = new JButton("ID 찾기");
		bt3.setBounds(50, 300, 125, 25);
		bt3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}	
		});
		
		JButton bt4 = new JButton("PW 찾기");
		bt4.setBounds(215, 300, 125, 25);
		bt4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}	
		});
		
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

}
