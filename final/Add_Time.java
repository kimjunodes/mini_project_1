package movie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

class Add_Time extends Frame {
	JComboBox<String> box; String name;
	Vector<String> lst = new Vector<String>();;
	String sql1, sql2,sql3;
	PreparedStatement psmt1,psmt2,psmt3; ResultSet rs1,rs2;
	int i=0;
	JTextField tf1,tf2;
	JLabel lbl1,lbl2,txt;
	JButton btn1, btn2;
	
	Add_Time(Connection con, String title){
		super(con,title);
		
		btn1 = setButton("시간추가",375,150,85,50);
		btn2 = setButton("뒤로가기",275, 300, 100, 30);
		lbl1 = setLabel("시간 :", 50, 150, 100, 30);
		lbl2 = setLabel("상영관 :", 50, 230, 100, 30);
		txt = setLabel("예시) 2021-10-12 15:30", 150, 180, 225, 30);
		tf1 = new JTextField();
		tf1.setBounds(150, 150, 225, 30);
		tf2 = new JTextField();
		tf2.setBounds(150, 230, 225, 30);
		
		add(btn1);
		add(tf1);
		add(tf2);
		add(lbl1);
		add(lbl2);
		add(txt);
		try {
			sql1 = "SELECT * FROM movie_table";
			psmt1 = con.prepareStatement(sql1);
			rs1 = psmt1.executeQuery();
			
			while(rs1.next()) {
				lst.add(rs1.getString("M_NAME"));
			}
			
			box = new JComboBox<String>(lst);
			box.setBounds(20, 100,180,20);
			add(box);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tf1.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "추가할 시간을 입력하세요.");	
				}else if(tf2.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "추가할 상영관을 입력하세요.");
				}else {

				try {
					i=0;
					name = box.getSelectedItem().toString();

					sql2 = "Select * from movie";
					psmt2 = con.prepareStatement(sql2);
					rs2= psmt2.executeQuery();
					
					while(rs2.next()) {
						i++;
					}
					
					i++;
					sql3="Insert into movie values (?,?,to_date(?,'YYYY-MM-DD HH24:mi'),?)";
					psmt3 = con.prepareStatement(sql3);
					
					psmt3.setInt(1, i);
					psmt3.setString(2, name);
					psmt3.setString(3, tf1.getText());
					psmt3.setInt(4, Integer.parseInt(tf2.getText()));
					
					
					int result = JOptionPane.showConfirmDialog(null,"영화 시간 : " + tf1.getText() + "\n"
							+"영화명 : "+ name +"\n" + "상영관 : "+tf2.getText(),"예매 확인",JOptionPane.YES_NO_OPTION);
	    			if (result == 0) {
	    				psmt3.execute();
	    				JOptionPane.showMessageDialog(null, "시간이 추가되었습니다.");
	    			}
					
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "형식에 맞게 입력하세요");
				}
				
				}	
			}
		});
		
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AdminFrame(con);
				
			}
		});
	}
}
