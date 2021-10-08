package Mini_project;

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

	JButton messageBtn;
	JButton back;
	private PreparedStatement sfmem, sfmov;
	public static JTextArea show;
	PreparedStatement psm;
	
	public Modify_member(Connection con, String id) {
		super(con, "��������");
		messageBtn = setButton("��������", 200, 285, 100, 50);

		add(messageBtn);
		back = setButton("�ڷΰ���",360,270,100,30);
		add(back);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new UserFrame(con,"User Menu",id);
			}
		});

		try {
			String SQL = "SELECT * FROM members where USER_ID = '" + id+"'";
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
					int flag = JOptionPane.showConfirmDialog(null, " ������ �����Ͻðڽ��ϱ� ?", "Message",
							JOptionPane.YES_NO_OPTION);
					if (flag == 0) {
						try {
							String UPD = "UPDATE members SET user_pw = ?, user_pn = ?,user_addr = ? where USER_ID = ?";
							psm = con.prepareStatement(UPD);
							
							String resultPW = "";
							String resultPN = "";
							String resultADDR = "";
							resultPW = JOptionPane.showInputDialog(null,"�н����带 �Է��ϼ���: ");
							if(resultPW!=null)
								resultPN = JOptionPane.showInputDialog(null,"�ڵ��� ��ȣ�� �Է��ϼ���: ");
								if(resultPN!=null)
									resultADDR = JOptionPane.showInputDialog(null,"�ּҸ� �Է��ϼ���: ");
							
							if(resultPW!=null&&resultPN!=null&&resultADDR!=null) {
								psm.setString(1, resultPW);
								psm.setString(2, resultPN);
								psm.setString(3, resultADDR);
								psm.setString(4, id);
								psm.execute();

								JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�.", "Message",
										JOptionPane.INFORMATION_MESSAGE);
								dispose();
								new UserFrame(con,"User Menu",id);
							}
						} catch (SQLException e2) {
							JOptionPane.showMessageDialog(null, "�������� �̽�����ũ2");
						}
					}
				}
			});
		} catch (SQLException e2) {
			JOptionPane.showMessageDialog(null, "�������� �̽�����ũ");
		}
	}

}