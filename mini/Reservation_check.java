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
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class Reservation_check extends Frame {

	private static JTextArea textArea;
	public static JButton messageBtn;

	private ResultSet rs, rm;
	private String lists = "";

	Reservation_check(Connection con, String id) {
		super(con, "������ȸ");
		messageBtn = setButton("������ȸ", 150, 130, 200, 100);
		add(messageBtn);
		setVisible(true);
		
		try {
//			String SQL = "SELECT movie_id FROM members";
//			sfmem = con.prepareStatement(SQL);
//			ResultSet rs = sfmem.executeQuery();

			String MOV = "SELECT * FROM TICKET";
			PreparedStatement pstmt = con.prepareStatement(MOV);
			ResultSet rm = pstmt.executeQuery();

			while (rm.next()) {
				if(rm.getString("U_ID").equals(id))
					lists += "��ȭ ����: " + rm.getString(2) + "\n" + 
						"��ȭ �ð�: " + rm.getString(3).substring(5,16) +"\n" +
						"�󿵰�: " + rm.getInt(4) + "��";
			}	
			messageBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(lists.isEmpty())
						JOptionPane.showMessageDialog(null, "������ ����� �����ϴ�.","����Ȯ��", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, lists, "����Ȯ��", JOptionPane.INFORMATION_MESSAGE);
					}
				});
		} catch (SQLException e2) {
			JOptionPane.showMessageDialog(null, "�������� �̽�����ũ");
		}
	}

}