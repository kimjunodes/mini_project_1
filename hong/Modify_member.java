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

public class Modify_member extends Frame {

	public static JButton messageBtn;
	private PreparedStatement sfmem, sfmov;
	public static JTextArea show;
	Frame frm;

	public Modify_member(Connection con, String id) {
		super(con, "정보수정");
		messageBtn = setButton("정보수정", 200, 285, 100, 50);

		add(messageBtn);

		setVisible(true);

		try {
			String SQL = "SELECT * FROM members where USER_ID = 'xodud'";
			PreparedStatement pstmt = con.prepareStatement(SQL);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String ifmt = "\n        ID:         " + rs.getString(1) + "\n\n" 
						+ "        PW:      " + rs.getString(2) + "\n\n" 
						+ "        PN:       " + rs.getString(3) + "\n\n" 
						+ "        ADDR:  "
						+ rs.getString(4);

				JTextArea show = new JTextArea();
				show.setText(ifmt);
				show.setEditable(false);
				show.setBounds(170, 90, 180, 150);
				add(show);
			}
			messageBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int flag = JOptionPane.showConfirmDialog(null, " 정보를 수정하시겠습니까 ?", "정보수정",
							JOptionPane.YES_NO_OPTION);
					if (flag == 0) {
						try {
							String UPD = "UPDATE members SET user_id = ? user_pw = ? user_pn = ? user_addr = ? where USER_ID = 'xodud'";
							PreparedStatement psm = con.prepareStatement(UPD);
							
							String resultID = "";
							resultID = JOptionPane.showInputDialog("아이디를 입력하세요: ");
							System.out.println(resultID);
							String resultPW = "";
							resultPW = JOptionPane.showInputDialog("패스워드를 입력하세요: ");
							System.out.println(resultPW);
							String resultPN = "";
							resultPN = JOptionPane.showInputDialog("핸드폰 번호를 입력하세요: ");
							System.out.println(resultPN);
							String resultADDR = "";
							resultADDR = JOptionPane.showInputDialog("주소를 입력하세요: ");
							System.out.println(resultADDR);
							
							
							psm.setString(1, resultID);
							psm.setString(2, resultPW);
							psm.setString(3, resultPN);
							psm.setString(4, resultADDR);
							psm.execute();

							JOptionPane.showMessageDialog(null, "수정이 완료되었습니다.", "정보수정",
									JOptionPane.INFORMATION_MESSAGE);

						} catch (SQLException e2) {
							JOptionPane.showMessageDialog(null, "개발자의 미슥테이크2");
						}
					}
				}
			});
		} catch (SQLException e2) {
			JOptionPane.showMessageDialog(null, "개발자의 미슥테이크");
		}
	}

	public static void main(String[] args) {
		Connection con = makeConnection();
		new Modify_member(con, "xodud");
	}
}
