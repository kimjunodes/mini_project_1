package mini;

import java.awt.Button;
import java.awt.Container;
import java.awt.TextArea;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import javax.swing.*;

public class Reservation_check extends JFrame {

	private static JTextArea textArea;

	private static Connection conn;
	private PreparedStatement sfmem, sfmov;
	private String userId;
	private ResultSet rs, rm;
	public String lists = "A";
	Frame frm;

	private static String SQL = "SELECT movie_id FROM members";
	private static String MOV = "SELECT * FROM movie ";

	public Reservation_check() {
		init();

		conn = Main.makeConnection();
		try {
			sfmem = conn.prepareStatement(SQL);
			ResultSet rs = sfmem.executeQuery();

			sfmov = conn.prepareStatement(MOV);
			ResultSet rm = sfmov.executeQuery();
			
			while (rs.next()) {
				String id = rm.getString("M_ID");
				lists += id;


			}

		} catch (SQLException e2) {
			JOptionPane.showMessageDialog(null, "개발자의 미슥테이크");
		}
	}

	private void init() {
		frm = new Frame("예매조회");

		textArea = new JTextArea(lists);
		textArea.setBounds(50, 90, 400, 200);
		frm.add(textArea);

		frm.setVisible(true);
	}

	public static void main(String[] args) {
		new Reservation_check();
	}

}
