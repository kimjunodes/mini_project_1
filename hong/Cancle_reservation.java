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
import javax.swing.JOptionPane;
import java.util.Properties;

public class Cancle_reservation extends JFrame {

	public static JButton messageBtn;
	private static Connection conn;
	private ResultSet rs, rm, rt;
	private String lists = "";
	private int flag;
	private Properties info = null;
	Frame frm;

	public Cancle_reservation() {
		init();
		conn = Main.makeConnection();

		try {
			String SQL = "SELECT * FROM members where m_id ='qwer'"; // member_id
			PreparedStatement pssmt = conn.prepareStatement(SQL);
			ResultSet rs = pssmt.executeQuery();

			String MOV = "SELECT * FROM movie where m_id = 2"; // movie_id
			PreparedStatement pstmt = conn.prepareStatement(MOV);
			ResultSet rm = pstmt.executeQuery();

			while (rm.next()) {
				String movie_name = rm.getString(2);

				messageBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int flag = JOptionPane.showConfirmDialog(null, movie_name + " 예매를 취소하시겠습니까 ?", "예매취소",
								JOptionPane.YES_NO_OPTION);

						if (flag == 0) {
							try {
								String Del = "UPDATE members SET movie_id = NULL where m_id = 'qwer'";
								PreparedStatement dst = conn.prepareStatement(Del);
								dst.executeUpdate(Del);

							} catch (SQLException e2) {
								JOptionPane.showMessageDialog(null, "개발자의 미슥테이크2");
							}

						}
					}
				});
			}
		} catch (SQLException e2) {
			JOptionPane.showMessageDialog(null, "개발자의 미슥테이크");
		}
	}

	private void init() {
		frm = new Frame("예매취소");

		messageBtn = frm.setButton("예매취소", 150, 130, 200, 100);
		frm.add(messageBtn);

		frm.setVisible(true);
	}

	public static void main(String[] args) {
		new Cancle_reservation();
	}

}