package movie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import java.text.SimpleDateFormat;

class UserFrame extends Frame {
	JButton ticket = setButton("영화 예매",40,100,210,75);
	JButton ticket_check = setButton("예매 조회",40,175,210,75);
	JButton ticket_cancel = setButton("예매 취소",40,250,210,75);
	JButton movie_info = setButton("영화 정보",250,100,210,75);
	JButton user_info = setButton("회원 정보",250,175,210,75);
	JButton log_out = setButton("로그아웃",250, 250, 150, 75);
	
	Timestamp da;
	String date;
	int movieId;
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM월 dd일 HH시 mm분");
	PreparedStatement psmt;
	ResultSet rs;
	
	Connection con;
	String id;
	String sql;
	int result;
	
	private String lists="";
	UserFrame(Connection con,String title, String id) {
		super(con, title);
		this.id = id;

		exit.setBounds(400,250,60,75);
		
		try {
			sql = "select * from ticket";
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				if(rs.getString("USER_ID").equals(id)) {	
					movieId = rs.getInt("M_ID");
					break;
				}
			}
			
			lists="";
			String MOV = "SELECT * FROM MOVIE";
			PreparedStatement pstmt = con.prepareStatement(MOV);
			ResultSet rm = pstmt.executeQuery();
			while (rm.next()) {
				if(rm.getInt("M_ID") == movieId) {
					da = rm.getTimestamp("M_TIME");
					date = simpleDateFormat.format(da);
					lists += "영화 제목: " + rm.getString("M_NAME") + "\n" + 
						"영화 시간: " + date +"\n" +
						"상영관: " + rm.getInt("HALL") + "관";
					break;
				}
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		ticket.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			try {
				String sql = "select * from ticket";
				psmt = con.prepareStatement(sql);
				rs = psmt.executeQuery();
				boolean b = false;
				while(rs.next()) {
					if(rs.getString("USER_ID").toString().equals(id)) {
						b = true;
					}
				}
				if(!b) {
					new Ticket(con, ticket.getText(),id);
					dispose();
				}else
					JOptionPane.showMessageDialog(null, "1인당 1개의 예약을 할 수 있습니다.");
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			}
	    });
		
		ticket_check.addActionListener(event -> {
			// 예매 정보 확인 메세지 창
			if(lists.isEmpty())
				JOptionPane.showMessageDialog(null, "예매한 목록이 없습니다.","예매확인", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, lists, "예매확인", JOptionPane.INFORMATION_MESSAGE);
		});
		
		ticket_cancel.addActionListener(event -> {
			// 예매 정보와 YES NO 확인 창 생성
			if(lists.isEmpty())
				JOptionPane.showMessageDialog(null, "예매한 목록이 없습니다.","예매확인", JOptionPane.INFORMATION_MESSAGE);
			else {
				result = JOptionPane.showConfirmDialog(null, lists + "\n"+
						"해당 영화를 삭제 하시겠습니까?", "예매확인",JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					//삭제(ticket테이블 삭제, 유저 update)
					try {
						sql = "delete from ticket where User_id = ?";
						psmt =con.prepareStatement(sql);
						psmt.setString(1, id);
						psmt.execute();
						
						sql = "update MEMbers set Ticket_ID = NULL where USER_ID = ?";
						psmt =con.prepareStatement(sql);
						psmt.setString(1, id);
						psmt.execute();
						dispose();
						new UserFrame(con,"User Menu",id);
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
			}
		});
		
		movie_info.addActionListener(event -> {
			setVisible(false);
			new movie_info(con,movie_info.getText());
	    });
		
		user_info.addActionListener(event -> {
			setVisible(false);
			new Modify_member(con, "xodud");
	    });
		log_out.addActionListener(event -> {
			// 로그인창 생성
			setVisible(false);
			new Main_menu(con);
	    });
	}
}
