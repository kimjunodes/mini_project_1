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
		super(con, "예매조회");
		messageBtn = setButton("예매조회", 150, 130, 200, 100);
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
					lists += "영화 제목: " + rm.getString(2) + "\n" + 
						"영화 시간: " + rm.getString(3).substring(5,16) +"\n" +
						"상영관: " + rm.getInt(4) + "관";
			}	
			messageBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(lists.isEmpty())
						JOptionPane.showMessageDialog(null, "예매한 목록이 없습니다.","예매확인", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, lists, "예매확인", JOptionPane.INFORMATION_MESSAGE);
					}
				});
		} catch (SQLException e2) {
			JOptionPane.showMessageDialog(null, "개발자의 미슥테이크");
		}
	}

}