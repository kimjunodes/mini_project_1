package team_test;

import java.awt.Button;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import javax.swing.*;

public class Add_acount extends JFrame{
	private JLabel lbl1, lbl2, lbl3, lbl4;
	private static JTextField jf1, jf2, jf3, jf4;
	public static JButton bt1, bt2;
	
	private static Connection conn;
	private static PreparedStatement sst, ist;
	
	private static final String SearchSQL = "select m_id from members where m_id = ?";
	private static final String InsertSQL = "insert into MEMBERS (M_ID, M_PW, P_NUMBER, M_ADDRESS) values (?, ?, ?, ?) ";
	
	
	
	public Add_acount(){
		init();
		
		conn = Main.makeConnection();
		try {
			ist = conn.prepareStatement(InsertSQL);
		} catch (SQLException e2) {
			JOptionPane.showMessageDialog(null, "�������� �̽�����ũ");
		}
		
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = jf1.getText();
				try {
					sst = conn.prepareStatement(SearchSQL);
					sst.setString(1, id);
					ResultSet rs = sst.executeQuery();
				
					if(id.length()<4) {
						JOptionPane.showMessageDialog(null, "���̵� 4���� �̻� �Է����ּ���.");
					}
				
					else if(rs.next()) {
						JOptionPane.showMessageDialog(null, "�̹� �����ϴ� ���̵��Դϴ�.");
					}
					else {
						JOptionPane.showMessageDialog(null, "��� ������ ���̵��Դϴ�.");
						ist.setString(1, id);
					}
					
				} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "�������� �̽�����ũ");
				}
				
			}
			
		});
		
		bt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
					} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, "�������� �̽�����ũ");
					}
				}
			}
		});
	}
	
	private void init() {
		Frame frm = new Frame("ȸ������");
		
		bt1 = frm.setButton("�ߺ� Ȯ��", 360, 110, 100, 40);
		bt2 = frm.setButton("ȸ�� ����", 360, 170, 100, 40);
		
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
		
		frm.add(lbl1);
		frm.add(lbl2);
		frm.add(lbl3);
		frm.add(lbl4);
		frm.add(jf1);
		frm.add(jf2);
		frm.add(jf3);
		frm.add(jf4);
		
		frm.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Add_acount();
	}
	
		
}

