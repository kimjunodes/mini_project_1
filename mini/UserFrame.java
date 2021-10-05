package test;

import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class UserFrame extends Frame {
	JButton ticket = setButton("영화 예매",40,100,210,75);
	JButton ticket_check = setButton("예매 조회",40,175,210,75);
	JButton ticket_cancel = setButton("예매 취소",40,250,210,75);
	JButton movie_info = setButton("영화 정보",250,100,210,75);
	JButton user_info = setButton("회원 정보",250,175,210,75);
	JButton log_out = setButton("로그아웃",250, 250, 150, 75);
	
	
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
			// 정보창 생성
	    });
		
		user_info.addActionListener(event -> {
			setVisible(false);
			User_info a = new User_info(user_info.getText());
	    });
		log_out.addActionListener(event -> {
			// 로그인창 생성
			setVisible(false);
			Frame fm = new Frame(log_out.getText());
	    });
	}
}
