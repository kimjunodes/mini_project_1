package movie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

class Add_acount extends Frame{
	private JLabel lbl1, lbl2, lbl3, lbl4;
	private JTextField jf1, jf2, jf3, jf4;
	private JButton bt1, bt2, bt3;
	
	private PreparedStatement sst, ist;
	
	private static final String SearchSQL = "select m_id from members where m_id = ?";
	private static final String InsertSQL = "insert into MEMBERS (M_ID, M_PW, P_NUMBER, M_ADDRESS) values (?, ?, ?, ?) ";
	
	Connection con;
	
	
	Add_acount(Connection con,String lbl){
		super(con,lbl);
		this.con = con;
		
		bt1 = setButton("중복 확인", 360, 110, 100, 40);
		bt2 = setButton("회원 가입", 360, 170, 100, 40);
		bt3 = setButton("뒤로 가기", 360, 230, 100, 40);
		
		lbl1 = new JLabel("ID : ");
		lbl1.setBounds(25, 110, 70, 30);
		lbl2 = new JLabel("PW : ");
		lbl2.setBounds(25, 165, 70, 30);
		lbl3 = new JLabel("P.N : ");
		lbl3.setBounds(25, 220, 70, 30);
		lbl4 = new JLabel("Addr. : ");
		lbl4.setBounds(25, 275, 70, 30);
		
		jf1 = new JTextField(20);
		jf1.setBounds(100, 110, 200, 30);
		jf2 = new JTextField(10);
		jf2.setBounds(100, 165, 200, 30);
		jf3 = new JTextField(11);
		jf3.setBounds(100, 220, 200, 30);
		jf4 = new JTextField(20);
		jf4.setBounds(100, 275, 200, 30);
		
		add(lbl1);
		add(lbl2);
		add(lbl3);
		add(lbl4);
		add(jf1);
		add(jf2);
		add(jf3);
		add(jf4);
		
		setVisible(true);
		
		try {
			ist = con.prepareStatement(InsertSQL);
		} catch (SQLException e2) {
			JOptionPane.showMessageDialog(null, "개발자의 미슥테이크");
		}
		
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = jf1.getText();
				try {
					sst = con.prepareStatement(SearchSQL);
					sst.setString(1, id);
					ResultSet rs = sst.executeQuery();
				
					if(id.length()<4) {
						JOptionPane.showMessageDialog(null, "아이디를 4글자 이상 입력해주세요.");
					}
				
					else if(rs.next()) {
						JOptionPane.showMessageDialog(null, "이미 존재하는 아이디입니다.");
					}
					else {
						JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다.");
						ist.setString(1, id);
					}
					
				} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "개발자의 미슥테이크");
				}
				
			}
			
		});
		
		bt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				
				String pw = jf2.getText();
				String pn = jf3.getText();
				String addr = jf4.getText();
				
				if(pw.length()<4) {
					JOptionPane.showMessageDialog(null, "비밀번호를 4자리 이상으로 입력해주세요.");
				}
				else if(pn.length() < 10) {
					JOptionPane.showMessageDialog(null, "전화번호를 10자리 이상 입력해주세요.");
				}
				else if(addr.length()<3) {
					JOptionPane.showMessageDialog(null, "주소를 3글자 이상 입력해주세요.");
				}
				else {
					try {
						
						ist.setString(2, pw);
						ist.setString(3, pn);
						ist.setString(4, addr);
						
						ist.execute();
						JOptionPane.showMessageDialog(null, "정상적으로 회원가입 되었습니다.");
						dispose();
						new Main_menu(con);
					} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, "중복 확인을 해주세요.");
					}
				}
			}
			
		});
		
		bt3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				dispose();
				new Main_menu(con);
			}
		});
	}
}