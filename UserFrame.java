package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class UserFrame extends Frame {
	JButton ticket = setButton("��ȭ ����",40,100,210,75);
	JButton ticket_check = setButton("���� ��ȸ",40,175,210,75);
	JButton ticket_cancel = setButton("���� ���",40,250,210,75);
	JButton movie_info = setButton("��ȭ ����",250,100,210,75);
	JButton user_info = setButton("ȸ�� ����",250,175,210,75);
	JButton log_out = setButton("�α׾ƿ�",250, 250, 150, 75);
	Connection con;
	String id;
	private String lists;
	UserFrame(Connection con,String title, String id) {
		super(con, title);
		this.con = con;
		this.id = id;
		exit.setBounds(400,250,60,75);
		
		ticket.addActionListener(event -> {
			setVisible(false);
			try {
				new Ticket(con, ticket.getText(),id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    });
		
		ticket_check.addActionListener(event -> {
			// ���� ���� Ȯ�� �޼��� â
			try {
				String MOV = "SELECT * FROM TICKET";
				PreparedStatement pstmt = con.prepareStatement(MOV);
				ResultSet rm = pstmt.executeQuery();

				while (rm.next()) {
					if(rm.getString("U_ID").equals(id))
						lists += "��ȭ ����: " + rm.getString(2) + "\n" + 
							"��ȭ �ð�: " + rm.getString(3).substring(5,16) +"\n" +
							"�󿵰�: " + rm.getInt(4) + "��";
				}	
				if(lists.isEmpty())
					JOptionPane.showMessageDialog(null, "������ ����� �����ϴ�.","����Ȯ��", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, lists, "����Ȯ��", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e2) {
				JOptionPane.showMessageDialog(null, "�������� �̽�����ũ");
			}
	    });
		
		ticket_cancel.addActionListener(event -> {
			// ���� ������ YES NO Ȯ�� â ����
	    });
		
		movie_info.addActionListener(event -> {
			setVisible(false);
			new movie_info(con,movie_info.getText());
	    });
		
		user_info.addActionListener(event -> {
			setVisible(false);
			new User_info(con, user_info.getText(),id);
	    });
		log_out.addActionListener(event -> {
			// �α���â ����
			setVisible(false);
			new Main_menu(con);
	    });
	}
}
