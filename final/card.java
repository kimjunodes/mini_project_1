package movie;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

class JTextFieldLimit extends PlainDocument {
	   private int limit;
	   private boolean toUppercase = false;

	   JTextFieldLimit(int limit) {
	      super();
	      this.limit = limit;
	   }

	   JTextFieldLimit(int limit, boolean upper) {
	      super();
	      this.limit = limit;
	      this.toUppercase = upper;
	   }

	   public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
	      if (str == null) {
	         return;
	      }

	      if ( (getLength() + str.length()) <= limit) {
	         if (toUppercase) {
	            str = str.toUpperCase();
	         }
	         super.insertString(offset, str, attr);
	      }
	   }
	}

class card extends Frame {
	JButton check;	JButton back;
	JLabel lb1;  JLabel lb2;
	JLabel lb3;  JLabel lb4;
	JLabel price;
	String lst[]= {"국민은행", "기업은행","외환은행", "신한은행","한국시티은행","우리은행"};
	JComboBox<String> box;
	JTextField tf1; JPasswordField tf2;
	JTextField tf3; JPasswordField tf4;
	JPasswordField pf;
	
	String sql;
	String pw="";
	PreparedStatement psmt = null;
	
	card(Connection con, String lbl,String id, int m_id, int nextNumber,String movie) {
		super(con, "Pay with Card");
		check = setButton("예매",375,100,85,50);
		back = setButton("메뉴보기",375,150,85,50);
		
		lb1 =setLabel("금액 :", 50, 100, 80, 30);
		lb2 =setLabel("카드사 :", 50, 150, 80, 30);
		lb3 =setLabel("카드번호 :", 50, 200, 80, 30);
		lb4 =setLabel("비밀번호 :", 50, 250, 80, 30);
		
		box = new JComboBox<String>(lst);
		box.setBounds(131,150,219,30);
		
		tf1 = new JTextField();	tf2 = new JPasswordField();
		tf3 = new JTextField();	tf4 = new JPasswordField();
		tf1.setBounds(131, 200, 48, 30); tf1.setDocument(new JTextFieldLimit(4));
		tf2.setDocument(new JTextFieldLimit(4)); tf2.setBounds(188, 200, 48, 30);		
		tf3.setDocument(new JTextFieldLimit(4)); tf3.setBounds(245, 200, 48, 30);		
		tf4.setDocument(new JTextFieldLimit(4)); tf4.setBounds(302, 200, 48, 30);		
		
		pf = new JPasswordField();
		pf.setBounds(131, 250,219, 30);
		int a=0;
		try {
			sql = "select * from Movie_Table where M_name = '" + movie+"'";
			psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				a = rs.getInt("PRICE");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		price = setLabel(a+"원",131,100 ,219, 30);
		
		
		add(lb1); add(lb2);
		add(lb3); add(lb4);
		add(tf1); add(tf2);
		add(tf3); add(tf4);
		add(pf); add(box);
		add(price);
		add(check); add(back);
		
		
		check.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					sql = "select * from members where USER_id ='"+id+"'" ;
					psmt = con.prepareStatement(sql);
					ResultSet rs = psmt.executeQuery();
					while(rs.next()) {
						pw = rs.getString("USER_PW");
						break;
					}
					if(!(pf.getText().toString().equals(pw)))
						JOptionPane.showMessageDialog(null, "비밀번호가 틀립니다.");
					else {
						sql = "Insert into ticket values (?, ?, ?,'card','YES')";
						psmt = con.prepareStatement(sql);
						psmt.setInt(1, nextNumber);
						psmt.setInt(2, m_id);
						psmt.setString(3, id);
						psmt.execute();
						
						sql = "update members set Ticket_ID = ? where USER_ID = ?";
						psmt =con.prepareStatement(sql);
						psmt.setInt(1, nextNumber);
						psmt.setString(2,id);
						psmt.execute();
						
						JOptionPane.showMessageDialog(null, "예매가 완료되었습니다.");
						dispose();
						new UserFrame(con,"영화 예매",id);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
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
