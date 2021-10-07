package movie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;

class AdminFrame extends Frame {
	String sql = "select * from members where user_id = ?";
	Connection con;

	public static JButton bt1, bt2, bt3;
	private static PreparedStatement I;
	
	AdminFrame(Connection con,String title) {
		super(con, "Admin Menu");
		bt1 = setButton("영화 추가",50, 130, 150, 100);
		bt2 = setButton("영화 삭제",300, 130, 150, 100);
		bt3 = setButton("로그아웃",275, 300, 100, 30);
		
		add(bt1);
		add(bt2);
		add(bt3);
		
		
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
				dispose();				
			}
		});
	}
}

