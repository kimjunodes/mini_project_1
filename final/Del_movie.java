package Mini_project;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

class Del_movie extends Frame{
	Vector<String> lst1 = new Vector<String>();
	JButton del, back;
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM월 dd일 HH시 mm분");
	PreparedStatement psmt, psmt1;
	String sql = null;
	String sql1 = null;
	String name, date;
	int m_id;
	JLabel lbl1, lbl2;
	Timestamp da;
	ResultSet rn, rt, rs;
	JComboBox jbox1 = null;
	JComboBox jbox2 = new JComboBox();
	
	Del_movie(Connection con, String title){
		super(con,"영화 삭제");
		this.con = con;
		
		sql = "Select * from movie_Table";
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				lst1.add(rs.getString("M_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		jbox1 = new JComboBox(lst1);
		jbox1.setBounds(30,200,150,30);
		jbox1.setEditable(false);
		jbox2.setEditable(false);
		jbox2.setBounds(180,200,150,30);
		add(jbox1);
		add(jbox2);
		
		JButton del = setButton("영화 삭제",375, 120, 90, 50);
		JButton back = setButton("뒤로 가기",375, 190, 90, 50);
		lbl1 = setLabel("영화 제목" , 30,150,150,30);
		lbl2 = setLabel("예매 시간", 180,150,150,30);
		lbl1.setFont(getFont().deriveFont(25.0f));
		lbl2.setFont(getFont().deriveFont(25.0f));
		
		getContentPane().add(lbl1);
		getContentPane().add(lbl2);
		
		add(jbox1);
		add(jbox2);
		add(del);
		add(back);
		
		jbox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbox2.removeAllItems();
				try {
					name= jbox1.getSelectedItem().toString();
					System.out.println(name);
		        	sql = "select * from movie where M_NAME='" + name +"'";
		        	psmt = con.prepareStatement(sql);
		        	rs = psmt.executeQuery();
		    		

		    			while(rs.next()) {
		    				da = rs.getTimestamp("m_time");
		    				System.out.println(da);
		    				date = simpleDateFormat.format(da);
		    				System.out.println(date);
		    				jbox2.addItem(date);
		    			}

		    		
				}catch(SQLException e1) {
					JOptionPane.showMessageDialog(null, "오류 발생... admin_del.jbox1");
				}
			}

		});
		
		
		
		del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					name = jbox1.getSelectedItem().toString();
					date = jbox2.getSelectedItem().toString();
					try {
						sql = "select * from movie where M_NAME='" + name +"'";
						psmt = con.prepareStatement(sql);
						rs = psmt.executeQuery();
						
						
						while(rs.next()) {
							if(date.equals(simpleDateFormat.format(rs.getTimestamp("m_time")))) {
			    				m_id = rs.getInt("M_ID");
			    			}
						}
						
						int result = 0;
						result = JOptionPane.showConfirmDialog(null, "해당 영화를 삭제하시겠습니까?", "Message", JOptionPane.YES_NO_OPTION);
						if (result == JOptionPane.YES_OPTION) {
							sql1 = "delete from movie where m_id = ?";
							psmt1 = con.prepareStatement(sql1);
							psmt1.setInt(1, m_id);
							psmt1.execute();
							
							JOptionPane.showMessageDialog(null, "정상적으로 삭제되었습니다.");
							dispose();
							new Del_movie(con, "삭제 완료");
						}
						else if (result == JOptionPane.CLOSED_OPTION) {
							JOptionPane.showMessageDialog(null, "취소되었습니다.");
						}
						else {
							JOptionPane.showMessageDialog(null, "취소되었습니다.");
						}
						
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "오류 발생... admin_del.del");
					}
				}catch (NullPointerException e2) {
					int result = 0;
					result = JOptionPane.showConfirmDialog(null, "해당 영화에 배정된 시간이 없습니다. "
							+ "해당 영화를 삭제하시겠습니까?", "Message", JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						sql1 = "delete from movie_table where m_name = '" + name + "'";
						try {
							psmt1 = con.prepareStatement(sql1);
							psmt1.execute();
							JOptionPane.showMessageDialog(null, "정상적으로 삭제되었습니다.");
							dispose();
							new Del_movie(con, "삭제 완료.");
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, "에러 멈춰");
						}
						
					}
					else if (result == JOptionPane.CLOSED_OPTION) {
						JOptionPane.showMessageDialog(null, "취소되었습니다.");
					}
					else {
						JOptionPane.showMessageDialog(null, "취소되었습니다.");
					}
				}
				
			}
		});
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AdminFrame(con);
			}
		});
		
		setVisible(true);
		
	}
}