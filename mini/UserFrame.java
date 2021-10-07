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
	JButton ticket = setButton("��ȭ ����",40,100,210,75);
	JButton ticket_check = setButton("���� ��ȸ",40,175,210,75);
	JButton ticket_cancel = setButton("���� ���",40,250,210,75);
	JButton movie_info = setButton("��ȭ ����",250,100,210,75);
	JButton user_info = setButton("ȸ�� ����",250,175,210,75);
	JButton log_out = setButton("�α׾ƿ�",250, 250, 150, 75);
	
	Timestamp da;
	String date;
	int movieId;
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM�� dd�� HH�� mm��");
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
					lists += "��ȭ ����: " + rm.getString("M_NAME") + "\n" + 
						"��ȭ �ð�: " + date +"\n" +
						"�󿵰�: " + rm.getInt("HALL") + "��";
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
					JOptionPane.showMessageDialog(null, "1�δ� 1���� ������ �� �� �ֽ��ϴ�.");
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			}
	    });
		
		ticket_check.addActionListener(event -> {
			// ���� ���� Ȯ�� �޼��� â
			if(lists.isEmpty())
				JOptionPane.showMessageDialog(null, "������ ����� �����ϴ�.","����Ȯ��", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, lists, "����Ȯ��", JOptionPane.INFORMATION_MESSAGE);
		});
		
		ticket_cancel.addActionListener(event -> {
			// ���� ������ YES NO Ȯ�� â ����
			if(lists.isEmpty())
				JOptionPane.showMessageDialog(null, "������ ����� �����ϴ�.","����Ȯ��", JOptionPane.INFORMATION_MESSAGE);
			else {
				result = JOptionPane.showConfirmDialog(null, lists + "\n"+
						"�ش� ��ȭ�� ���� �Ͻðڽ��ϱ�?", "����Ȯ��",JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					//����(ticket���̺� ����, ���� update)
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
			// �α���â ����
			setVisible(false);
			new Main_menu(con);
	    });
	}
}
