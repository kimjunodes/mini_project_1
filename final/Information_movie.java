package movie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

class Information_movie extends Frame {
	Vector<String> lst = new Vector<String>();;
	public JTextArea area1, area2, area3;
	public JButton bt1,bt2,bt3,back; 
	JComboBox<String> box; String name;
	String story1; String ifmt1;
	String SQL1; String SQL2;
	PreparedStatement pstmt; ResultSet rs;
	
	public Information_movie(Connection con, String id) {
		super(con, "��ȭ ����");
		try {
			SQL1 = "SELECT * FROM movie_table";
			pstmt = con.prepareStatement(SQL1);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				lst.add(rs.getString("M_NAME"));
			}
			
			box = new JComboBox<String>(lst);
			box.setBounds(20, 90,180,20);
			add(box);
			
			
			box.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					name = box.getSelectedItem().toString();
					SQL2 = "SELECT * FROM movie_table where m_name ='" + name + "'";
					try {
						pstmt = con.prepareStatement(SQL2);
						rs = pstmt.executeQuery();
						while (rs.next()) {
							ifmt1 = "1. "+ "����: "+rs.getString(1) +" / �ֿ����: "+ rs.getString(2)+ "\n"+
									"  ����: "+rs.getString(3)+ " / ������: "+ rs.getString(4).substring(2,10)+
									"\n"+"   �󿵽ð�: " + rs.getString(5)+ "�� / ���ѿ���: " + rs.getInt(6) + "��"+
									"\n";
							story1 = rs.getString(7);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					area1.setText(ifmt1);
				}
			});
			
			area1 = new JTextArea();
			area1.setText(ifmt1);
			area1.setEditable(false);
			area1.setBounds(20, 150, 450, 100);
			area1.setBorder(BorderFactory.createEmptyBorder(20,20,0,0));
			add(area1);
			
			bt1 = setButton("�ٰŸ� ����", 370, 250 , 100, 30);
			
			bt1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, story1 ,"�ٰŸ�", JOptionPane.INFORMATION_MESSAGE);
				}
			});
			
			back = setButton("�ڷΰ���",300,300,100,30);
			add(back);
			back.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					new UserFrame(con,"User Menu",id);
				}
			});
			
		} catch (SQLException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage());
		}
		
	}
}