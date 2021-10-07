package mini_project1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Admin extends JFrame {
	String sql = "select * from members where user_id = ?";
	
	public static JButton bt1, bt2, bt3;
	private static Connection con;
	private static PreparedStatement I;
	
	Frame frm;
	
	public Admin() {
		init();
		con = Main.makeConnection();
		
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				
			}
		});
		
		bt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				
			}
		});
		
		bt3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				frm.dispose();				
			}
		});
	}
	
	private void init() {
		frm = new Frame("ADMIN");
		
		bt1 = new JButton("영화 추가");
		bt1.setBounds(50, 130, 150, 100);
		
		bt2 = new JButton("영화 삭제");
		bt2.setBounds(300, 130, 150, 100);	
		
		bt3 = new JButton("로그아웃");
		bt3.setBounds(275, 300, 100, 30);	
		
		frm.add(bt1);
		frm.add(bt2);
		frm.add(bt3);
		
		frm.setSize(500, 400);
		frm.setVisible(true);
	}
	public static void main(String[] args) {
		new Admin();
		
	}
}