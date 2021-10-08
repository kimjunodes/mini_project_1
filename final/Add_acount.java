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
	private String id;
	
	private String SearchSQL = "select * from members";
	private String InsertSQL = "insert into MEMBERS (USER_ID, USER_PW, USER_PN, USER_ADDR) values (?, ?, ?, ?) ";
	ResultSet rs;
	
	Connection con;
	
	
	Add_acount(Connection con,String lbl){
		super(con,lbl);
		this.con = con;
		
		bt1 = setButton("�ߺ� Ȯ��", 360, 110, 100, 40);
		bt2 = setButton("ȸ�� ����", 360, 170, 100, 40);
		bt3 = setButton("�ڷ� ����", 360, 230, 100, 40);
		
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
			JOptionPane.showMessageDialog(null, "�������� �̽�����ũ1");
		}
		
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					id = jf1.getText();
					sst = con.prepareStatement(SearchSQL);
					rs = sst.executeQuery();
					int i=0;
					
					while(rs.next()) {
						if(rs.getString("USER_ID").equals(id)) {
							JOptionPane.showMessageDialog(null, "�̹� �����ϴ� ���̵��Դϴ�.");
							i++;
							break;
						}else if(id.length()<4) {
							JOptionPane.showMessageDialog(null, "���̵� 4���� �̻� �Է����ּ���.");
							i++;
							break;
						}
					}
					if(i==0) {
						JOptionPane.showMessageDialog(null, "��� ������ ���̵��Դϴ�.");
						ist.setString(1, id);
					}
				} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "�������� �̽�����ũ2");
				}
				
			}
			
		});
		
		bt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				
				String pw = jf2.getText();
				String pn = jf3.getText();
				String addr = jf4.getText();
				
				if(pw.length()<4) {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� 4�ڸ� �̻����� �Է����ּ���.");
				}
				else if(pn.length() < 10) {
					JOptionPane.showMessageDialog(null, "��ȭ��ȣ�� 10�ڸ� �̻� �Է����ּ���.");
				}
				else if(addr.length()<3) {
					JOptionPane.showMessageDialog(null, "�ּҸ� 3���� �̻� �Է����ּ���.");
				}
				else {
					try {
						ist.setString(2, pw);
						ist.setString(3, pn);
						ist.setString(4, addr);
						
						ist.execute();
						JOptionPane.showMessageDialog(null, "���������� ȸ������ �Ǿ����ϴ�.");
						dispose();
						new Main_menu(con);
					} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, "�ߺ� Ȯ���� ���ּ���.");
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