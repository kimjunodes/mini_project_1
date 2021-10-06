package movie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

class Ticket_m extends Frame{
	Connection con;
	JLabel lbl1;
	JLabel lbl_name ;
	JLabel lbl2;
	JLabel lbl_time;
	JLabel lbl3;
	JLabel lbl_number;
	JLabel lbl4;
	String sql;
	JRadioButton cash;
	JRadioButton card;
	ButtonGroup bgp;
	
	PreparedStatement psmt = null;
	ResultSet rs = null;
	
	JButton check;
	JButton back;
	
	Ticket_m(Connection con, String string, String id, int m_id) {
		super(con,"���� ���� Ȯ��");
		check = setButton("����",375,100,85,50);
		back = setButton("�޴�����",375,150,85,50);
		 
		sql = "select * from movie where M_id= " + m_id ;
    	try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				lbl_name = setLabel(rs.getString("M_NAME"), 150, 100, 225, 30);
				lbl_time = setLabel(rs.getString("TIME"), 150, 150, 225, 30);
				lbl_number = setLabel(rs.getString("HALL"), 150, 200, 225, 30);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
    	
		lbl1 = setLabel("��ȭ�� : ", 50, 100, 80, 30);
		lbl2 = setLabel("�� �ð� : ", 50, 150, 80, 30);
		lbl3 = setLabel("�� ��ȣ : ", 50, 200, 80, 30);
		lbl4 = setLabel("���� ���� : ", 50, 250, 80, 30);
		
		bgp = new ButtonGroup();
		card = new JRadioButton("ī��",true);
		cash = new JRadioButton("����",false);
		card.setBounds(150, 250, 80, 30);
		cash.setBounds(250, 250, 80, 30);
		bgp.add(card);
		bgp.add(cash);
		
		add(lbl1);
		add(lbl2);
		add(lbl3);
		add(lbl4);
		add(lbl_name);
		add(lbl_number);
		add(lbl_time);
		add(check);
		add(back);
		add(card);
		add(cash);
		
		check.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cash.isSelected()) {
					// �������� �����Ѵٴ� �� �߰�
					JOptionPane.showMessageDialog(null, "����Ǿ����ϴ�.");
					dispose();
					new UserFrame(con,"User Menu",id);
				}else {
					// ī�� ���� â ����
					
					dispose();
					new card(con,"ī�� ����",id, m_id);
				}
			}
		});
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new UserFrame(con,"��ȭ ����",id);
			}
		});
	}
}