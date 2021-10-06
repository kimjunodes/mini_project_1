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

				
				lists += "영화 제목: " + rm.getString(2) + "\n" + 
						"영화 시간: " + rm.getString(3).substring(5,16) +"\n" +
						"상영 관: " + rm.getInt(4) + "관";

				messageBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null, lists, "Message", JOptionPane.ERROR_MESSAGE);
					}
				});
			}
		} catch (SQLException e2) {
			JOptionPane.showMessageDialog(null, "개발자의 미슥테이크");
		}
	}

	private void init() {
		frm = new Frame("예매조회");

		messageBtn = frm.setButton("예매조회", 150, 130, 200, 100);
		frm.add(messageBtn);

		frm.setVisible(true);
	}

	public static void main(String[] args) {
		new Reservation_check();
	}

}
