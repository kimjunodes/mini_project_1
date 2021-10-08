package team_test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class Admin extends JFrame {
	String sql = "select * from members where user_id = ?";
	
	public static JButton bt1, bt2, bt3;
	private static PreparedStatement I;
	
	Frame frm;
	
	public Admin(Connection con) {
		
		init();
		
		bt1.addActionListener(new ActionListener() { //��ȭ �߰�
			public void actionPerformed(ActionEvent e) {	
					new Add_movie(con);
					
			}
		});
		
		bt2.addActionListener(new ActionListener() { //��ȭ ����
			public void actionPerformed(ActionEvent e) {	
				try {
					new Del_movie(con, "��ȭ ����");
					frm.dispose();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "�⺻ �˸�â�Դϴ�.");

				}
				
			}
		});
		
		bt3.addActionListener(new ActionListener() { //�α׾ƿ�
			public void actionPerformed(ActionEvent e) {	
				frm.dispose();	
				new Main();
			}
		});
	}
	
	private void init() {
		frm = new Frame("ADMIN");
		
		bt1 = new JButton("��ȭ �߰�");
		bt1.setBounds(50, 130, 150, 100);
		
		bt2 = new JButton("��ȭ ����");
		bt2.setBounds(300, 130, 150, 100);	
		
		bt3 = new JButton("�α׾ƿ�");
		bt3.setBounds(275, 300, 100, 30);	
		
		frm.add(bt1);
		frm.add(bt2);
		frm.add(bt3);
		
		frm.setVisible(true);
	}
	
}