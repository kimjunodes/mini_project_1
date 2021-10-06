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
	JButton ticket = setButton("영화 예매",40,100,210,75);
	JButton ticket_check = setButton("예매 조회",40,175,210,75);
	JButton ticket_cancel = setButton("예매 취소",40,250,210,75);
	JButton movie_info = setButton("영화 정보",250,100,210,75);
	JButton user_info = setButton("회원 정보",250,175,210,75);
	JButton log_out = setButton("로그아웃",250, 250, 150, 75);
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
			// 예매 정보 확인 메세지 창
			try {
				String MOV = "SELECT * FROM TICKET";
				PreparedStatement pstmt = con.prepareStatement(MOV);
				ResultSet rm = pstmt.executeQuery();

				while (rm.next()) {
					if(rm.getString("U_ID").equals(id))
						lists += "영화 제목: " + rm.getString(2) + "\n" + 
							"영화 시간: " + rm.getString(3).substring(5,16) +"\n" +
							"상영관: " + rm.getInt(4) + "관";
				}	
				if(lists.isEmpty())
					JOptionPane.showMessageDialog(null, "예매한 목록이 없습니다.","예매확인", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, lists, "예매확인", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e2) {
				JOptionPane.showMessageDialog(null, "개발자의 미슥테이크");
			}
	    });
		
		ticket_cancel.addActionListener(event -> {
			// 예매 정보와 YES NO 확인 창 생성
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
			// 로그인창 생성
			setVisible(false);
			new Main_menu(con);
	    });
	}
}
