package Mini_project;

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
	int t = 0;
	JRadioButton cash;
	JRadioButton card;
	ButtonGroup bgp;
	
	PreparedStatement psmt = null;
	ResultSet rs = null;
	
	JButton check;
	JButton back;
	
	Ticket_m(Connection con, String id, int m_id) {
		super(con,"예약 정보 확인");
		check = setButton("예매",375,100,85,50);
		back = setButton("메뉴보기",375,150,85,50);
		
    	try {
    		sql = "select * from ticket";
			psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				t = rs.getInt("TICKET_ID") + 1;
			}
			
			sql = "select * from movie where M_id= " + m_id ;
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				lbl_name = setLabel(rs.getString("M_NAME"), 150, 100, 225, 30);
				lbl_time = setLabel(rs.getString("M_TIME"), 150, 150, 225, 30);
				lbl_number = setLabel(rs.getString("HALL"), 150, 200, 225, 30);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
    	
		lbl1 = setLabel("영화명 : ", 50, 100, 80, 30);
		lbl2 = setLabel("상영 시간 : ", 50, 150, 80, 30);
		lbl3 = setLabel("방 번호 : ", 50, 200, 80, 30);
		lbl4 = setLabel("결제 수단 : ", 50, 250, 80, 30);
		
		bgp = new ButtonGroup();
		card = new JRadioButton("카드",true);
		cash = new JRadioButton("현금",false);
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
					// 현금으로 결제한다는 것 추가
					try {
						sql = "Insert into ticket values (?, ?, ?,'cash','NO')";
						psmt = con.prepareStatement(sql);
						psmt.setInt(1, t);
						psmt.setInt(2, m_id);
						psmt.setString(3, id);
						psmt.execute();
						
						sql = "update members set Ticket_ID = ? where USER_ID = ?";
						psmt =con.prepareStatement(sql);
						psmt.setInt(1, t);
						psmt.setString(2,id);
						psmt.execute();
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}	
					
					JOptionPane.showMessageDialog(null, "예약되었습니다.");
					dispose();
					new UserFrame(con,"User Menu",id);
				}else {
					// 카드 결제 창 열기
					dispose();
					new card(con,"카드 결제",id, m_id, t,lbl_name.getText());
				}
			}
		});
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new UserFrame(con,"영화 예매",id);
			}
		});
	}
}
