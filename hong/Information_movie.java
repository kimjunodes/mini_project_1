package mini;

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
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Information_movie extends Frame {

	public static JTextArea area1, area2, area3;
	public static JButton bt1,bt2,bt3;
	Frame frm;

	public Information_movie(Connection con, String id) {
		super(con, "영화정보");

		setVisible(true);

		try {
			String SQL1 = "SELECT * FROM movie_table where m_name ='WALL-E'";
			PreparedStatement pstmt1 = con.prepareStatement(SQL1);
			ResultSet rs = pstmt1.executeQuery();

			while (rs.next()) {
				String ifmt1 = "1. "+ "제목: "+rs.getString(1) +" / 주연배우: "+ rs.getString(2)+ 
						" / 감독: "+rs.getString(3)+ " / 개봉일: "+ rs.getString(4).substring(2,10)+
						"\n"+"   상영시간: " + rs.getString(5)+ "분 / 제한연령: " + rs.getInt(6) + "살"+
						"\n";
				JTextArea area1 = new JTextArea();
				area1.setText(ifmt1);
				area1.setEditable(false);
				area1.setBounds(20, 90, 450, 40);
				add(area1);
				
				bt1 = setButton("줄거리 보기", 360, 135, 100, 20);
				String story1 = rs.getString(7);
				bt1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, story1 ,"줄거리", JOptionPane.INFORMATION_MESSAGE);
						}
					});
			}

			String SQL2 = "SELECT * FROM movie_table where m_name ='SAVING PRIVATE RYAN'";
			PreparedStatement pstmt2 = con.prepareStatement(SQL2);
			ResultSet rd = pstmt2.executeQuery();
			
			while(rd.next()) {
				String ifmt2 = "2. "+ "제목: "+rd.getString(1) +" / 주연배우: "+ rd.getString(2)+ 
						" / 감독: "+rd.getString(3)+ " / 개봉일: "+ rd.getString(4).substring(2,10)+
						"\n"+"   상영시간: " + rd.getString(5)+ "분 / 제한연령: " + rd.getInt(6) + "살"+
						"\n";
				JTextArea area2 = new JTextArea();
				area2.setText(ifmt2);
				area2.setEditable(false);
				area2.setBounds(20, 160, 450, 40);
				add(area2);
				
				bt2 = setButton("줄거리 보기", 360, 205, 100, 20);
				String story2 = rd.getString(7);
				bt2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, story2 ,"줄거리", JOptionPane.INFORMATION_MESSAGE);
						}
					});
			}
			
			String SQL3 = "SELECT * FROM movie_table where m_name ='Titanic'";
			PreparedStatement pstmt3 = con.prepareStatement(SQL3);
			ResultSet rf = pstmt3.executeQuery();	
			
			while(rf.next()) {
				String ifmt2 = "3. "+ "제목: "+rf.getString(1) +" / 주연배우: "+ rf.getString(2)+ 
						" / 감독: "+rf.getString(3)+ " / 개봉일: "+ rf.getString(4).substring(2,10)+
						"\n"+"   상영시간: " + rf.getString(5)+ "분 / 제한연령: " + rf.getInt(6) + "살"+
						"\n";
				JTextArea area3 = new JTextArea();
				area3.setText(ifmt2);
				area3.setEditable(false);
				area3.setBounds(20, 230, 450, 40);
				add(area3);
				
				bt3 = setButton("줄거리 보기", 360, 275, 100, 20);
				String story3 = rf.getString(7);
				bt3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, story3 ,"줄거리", JOptionPane.INFORMATION_MESSAGE);
						}
					});
			}
		} catch (SQLException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage());
		}
	}

	public static void main(String[] args) {
		Connection con = makeConnection();
		new Information_movie(con, "xodud");
	}
}
