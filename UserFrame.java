package test;

import javax.swing.JButton;

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
	}
}
