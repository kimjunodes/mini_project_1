package Mini_project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;

class AdminFrame extends Frame {
	Connection con;

	private JButton bt1, bt2, bt3,bt4;
	private PreparedStatement I;
	
	AdminFrame(Connection con) {
		super(con, "Admin Menu");
		bt1 = setButton("��ȭ �߰�",50, 130, 133, 100);
		bt2 = setButton("��ȭ ����",316, 130, 133, 100);
		bt3 = setButton("�ð�ǥ �߰�",183, 130, 134, 100);
		bt4 = setButton("�α׾ƿ�",275, 300, 100, 30);
		
		add(bt1);
		add(bt2);
		add(bt3);
		add(bt4);
		
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				new Add_movie(con);
				dispose();
			}
		});
		
		bt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				new Del_movie(con,"ADMIN");
				dispose();				
			}
		});

		bt3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				new Add_Time(con,"��ȭ �ð� �߰�");
				dispose();
			}
		});
		
		
		bt4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				new Main_menu(con);
				dispose();				
			}
		});
	}
}

