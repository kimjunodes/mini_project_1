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
	public String lists = "";
	Frame frm;

	private static final String SQL = "SELECT movie_id FROM members WHERE m_id = ?";
	private static final String MOV = "SELECT * FROM movie WHERE m_id = ?";

	public Reservation_check() {
		init();

		conn = Main.makeConnection();
		try {
			sfmem = conn.prepareStatement(SQL);
			sfmem.setString(1, "1234");
			ResultSet rs = sfmem.executeQuery();
			
			sfmov = conn.prepareStatement(MOV);
			sfmov.setInt(1, rs.getInt(1));
			ResultSet rm = sfmov.executeQuery();
			while (rs.next()) {

				String id = rm.getString("M_ID");
				lists += id;
				System.out.print("id : " + rs.getInt("M_ID") + "\t");
				System.out.print("name : " + rs.getString("M_NAME") + "\t");
				System.out.print("time : " + rs.getString("TIME") + "\t");
				System.out.print("hall : " + rs.getString("HALL") + "\t");
				System.out.print("seat : " + rs.getString("SEAT") + "\t");
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
