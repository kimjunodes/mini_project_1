package test;

import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class UserFrame extends Frame {
	JButton ticket = setButton("��ȭ ����",40,100,210,75);
	JButton ticket_check = setButton("���� ��ȸ",40,175,210,75);
	JButton ticket_cancel = setButton("���� ���",40,250,210,75);
	JButton movie_info = setButton("��ȭ ����",250,100,210,75);
	JButton user_info = setButton("ȸ�� ����",250,175,210,75);
	JButton log_out = setButton("�α׾ƿ�",250, 250, 150, 75);
	
	
	UserFrame(String title) {
		super(title);
		exit.setBounds(400,250,60,75);
		
		ticket.addActionListener(event -> {
			setVisible(false);
			try {
				Ticket a = new Ticket(ticket.getText());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    });
		
		ticket_check.addActionListener(event -> {
			setVisible(false);
			Ticket_check a = new Ticket_check(ticket_check.getText());
	    });
		
		ticket_cancel.addActionListener(event -> {
			setVisible(false);
			Ticket_cancel a = new Ticket_cancel(ticket_cancel.getText());
	    });
		
		movie_info.addActionListener(event -> {
			// ����â ����
	    });
		
		user_info.addActionListener(event -> {
			setVisible(false);
			User_info a = new User_info(user_info.getText());
	    });
		log_out.addActionListener(event -> {
			// �α���â ����
			setVisible(false);
			Frame fm = new Frame(log_out.getText());
	    });
	}
}
