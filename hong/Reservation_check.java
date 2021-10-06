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

public class Reservation_check extends JFrame {

	private static JTextArea textArea;
	public static JButton messageBtn;

	private static Connection conn;
	private PreparedStatement sfmem, sfmov;
	private ResultSet rs, rm;
	private String lists = "";
	Frame frm;


	public Reservation_check() {
		init();
		conn = Main.makeConnection();
		try {
			String SQL = "SELECT movie_id FROM members";
			sfmem = conn.prepareStatement(SQL);
			ResultSet rs = sfmem.executeQuery();

			String MOV = "SELECT * FROM movie where m_id = 2";
			PreparedStatement pstmt = conn.prepareStatement(MOV);
			ResultSet rm = pstmt.executeQuery();

			while (rm.next()) {

				
				lists += "��ȭ ����: " + rm.getString(2) + "\n" + 
						"��ȭ �ð�: " + rm.getString(3).substring(5,16) +"\n" +
						"�� ��: " + rm.getInt(4) + "��";

				messageBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null, lists, "Message", JOptionPane.ERROR_MESSAGE);
					}
				});
			}
		} catch (SQLException e2) {
			JOptionPane.showMessageDialog(null, "�������� �̽�����ũ");
		}
	}

	private void init() {
		frm = new Frame("������ȸ");

		messageBtn = frm.setButton("������ȸ", 150, 130, 200, 100);
		frm.add(messageBtn);

		frm.setVisible(true);
	}

	public static void main(String[] args) {
		new Reservation_check();
	}

}
