package team_test;

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
	JButton del, logout;
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM월 dd일 HH시 mm분");
	PreparedStatement psmt, psmt1;
	Connection conn;
	String sql = null;
	String sql1 = null;
	String name, date;
	int m_id;
	JLabel lbl1, lbl2;
	Timestamp da;
	ResultSet rn, rt, rs;
	JComboBox jbox1 = null;
	JComboBox jbox2 = new JComboBox();
	
	Del_movie(Connection con, String title) throws SQLException{
		super(title);
		this.con = con;
		
		exit.setBounds(375, 300, 85, 40);
		comboBox_init();
		
		JButton del = setButton("영화 삭제",375, 120, 90, 50);
		JButton logout = setButton("로그아웃",375, 190, 90, 50);
		lbl1 = setLabel("영화 제목" , 30,150,150,30);
		lbl2 = setLabel("예매 시간", 180,150,150,30);
		lbl1.setFont(getFont().deriveFont(30.0f));
		lbl2.setFont(getFont().deriveFont(30.0f));
		
		getContentPane().add(lbl1);
		getContentPane().add(lbl2);
		
		add(jbox1);
		add(jbox2);
		add(del);
		add(logout);
		
		jbox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbox2.removeAllItems();
				try {
					name= jbox1.getSelectedItem().toString();
		        	sql = "select * from movie where M_NAME='" + name +"'";
		        	psmt = con.prepareStatement(sql);	
		        	rs = psmt.executeQuery();
		    		String date= null;
		    		while(rs.next()) {
		    			da = rs.getTimestamp("m_time");
		    			date = simpleDateFormat.format(da);
		    			jbox2.addItem(date);
		    		}
				}catch(SQLException e1) {
					JOptionPane.showMessageDialog(null, "오류 발생... admin_del.jbox1");
				}
				name = jbox1.getSelectedItem().toString();
				date = jbox2.getSelectedItem().toString();
			}

		});
		
		jbox2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name = jbox1.getSelectedItem().toString();
				date = jbox2.getSelectedItem().toString();
				System.out.println(date);
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
					result = JOptionPane.showConfirmDialog(null, "해당 영화를 삭제하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						sql1 = "delete from movie where m_id = ?";
						psmt1 = con.prepareStatement(sql1);
						psmt1.setInt(1, m_id);
						psmt1.execute();
						
						JOptionPane.showMessageDialog(null, "정상적으로 삭제되었습니다.");
						dispose();
						new Del_movie(con, "삭제 완료.");
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
			}
		});
		
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Main();
			}
		});
		
		setVisible(true);
		
	}
	
	private void comboBox_init() throws SQLException {
		sql = "Select * from movie_Table";
		psmt = con.prepareStatement(sql);
		rs = psmt.executeQuery();
		
		while(rs.next()) {
			lst1.add(rs.getString("M_NAME"));
		}
		jbox1 = new JComboBox(lst1);
		jbox1.setBounds(30,200,150,30);
		jbox1.setEditable(true);
		jbox2.setEditable(true);
		jbox2.setBounds(180,200,150,30);
		this.add(jbox1);
		this.add(jbox2);
		
		psmt.close();
		rs.close();
	}
	
}