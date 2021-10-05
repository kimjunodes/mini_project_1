package test;

import javax.swing.JButton;

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
	}
}
