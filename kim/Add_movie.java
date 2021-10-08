package team_test;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;



public class Add_movie extends JFrame{
	Connection con;
	
	JButton exit;
	JButton bt1, bt2, bt3;
	JLabel lname, lactor, ldir, ltime, lage, lstory, lrun, lprice;
	JTextField tname, tactor, tdir, ttime, tage, trun, tprice;
	JTextArea tstory;
	int age, run_time, price, m_id;
	String m_name, actor, dir, mt_time, story;
	ResultSet rs;
	PreparedStatement pstmt1, pstmt2;
	String mt_sql = "insert into movie_table (m_name, actor, dir, mt_time, age, story, run_time, price) "
			+ "values (?, ?, ?, to_date(?, 'rr/mm/dd HH24:mi:ss'), ?, ?, ?, ?) ";
	
	Add_movie(Connection con){
		this.con = con;
		
		setTitle("Bit cinema");
		
		JLabel lTitle = new JLabel("��ȭ �߰�", SwingConstants.CENTER);
        lTitle.setBounds(220, 25, 300, 50);
        getContentPane().add(lTitle);
        lTitle.setFont(new Font("���� ���",Font.BOLD,30));
		
		setSize(750, 600);
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
        setResizable(false);
        
        lname = new JLabel("��ȭ ����");
		lname.setBounds(50, 110, 100, 30); add(lname);
		lage = new JLabel("����");
		lage.setBounds(300, 110, 100, 30); add(lage);
		lactor = new JLabel("�ֿ� ���");
		lactor.setBounds(50, 185, 100, 30); add(lactor);
		ldir = new JLabel("���� �̸�");
		ldir.setBounds(300, 185, 100, 30); add(ldir);
		ltime = new JLabel("�� �ð� (yy/MM/dd HH:mm:ss)");
		ltime.setBounds(50, 260, 200, 30); add(ltime);
		lrun = new JLabel("����Ÿ��");
		lrun.setBounds(300, 260, 100, 30); add(lrun);
		lprice = new JLabel("����");
		lprice.setBounds(50, 335, 100, 30); add(lprice);
		lstory = new JLabel("���丮 (�ѱ� : 100��, ���� : 200��)");
		lstory.setBounds(180, 375, 450, 105); add(lstory);
		
		tname = new JTextField(); //m_name
		tname.setBounds(50, 150, 200, 25); add(tname);
		tage = new JTextField(); //age
		tage .setBounds(300, 150, 200, 25); add(tage);
		tactor = new JTextField(); //actor
		tactor.setBounds(50, 225, 200, 25); add(tactor);
		tdir = new JTextField(); //dir
		tdir.setBounds(300, 225, 200, 25); add(tdir);
		ttime = new JTextField(); //time
		ttime.setBounds(50, 300, 200, 25); add(ttime);
		trun = new JTextField(); //run
		trun.setBounds(300, 300, 200, 25); add(trun);
		tprice = new JTextField(); //price
		tprice.setBounds(50, 375, 200, 25); add(tprice);
		tstory = new JTextArea(); //story
		tstory.setBounds(50, 450, 450, 95); add(tstory);
		tstory.setEditable(true);
		tstory.setLineWrap(true);
		
		exit = new JButton("EXIT");
		exit.setBounds(650, 500, 60, 30); add(exit);
		exit.addActionListener(event -> {
			System.exit(0);
	    });
		
		bt1 = new JButton("��ȭ �߰� Ȯ��");
		bt1.setBounds(565, 140, 150, 80); add(bt1);
		
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				result = JOptionPane.showConfirmDialog(null, "������ �� �Էµƴ��� Ȯ���غ��ðڽ��ϱ�?", "Confirm", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
						m_name = tname.getText();
						actor = tactor.getText();
						dir = tdir.getText();
						mt_time = ttime.getText();
						age = Integer.parseInt(tage.getText());
						story = tstory.getText();
						run_time = Integer.parseInt(trun.getText());
						price = Integer.parseInt(tprice.getText());
						
						if(m_name.length() == 1 || actor.length() < 1 || dir.length() < 1 
								|| mt_time.length() < 1 ||  story.length() < 1) {
							JOptionPane.showMessageDialog(null, "������ �׸��� �ְų� ���Ŀ� ���� �ʴ� �׸��� �ֽ��ϴ�.");
						}
						else if(age < 1 || run_time < 1 || price < 1) {
							JOptionPane.showMessageDialog(null, "����, �󿵽ð�, ������ ������ �Է��ؾ� �մϴ�.");
						}
						else {
						JOptionPane.showMessageDialog(null, "�̻� �����ϴ�.");
						add(bt2);
						}
					
				}
				else if (result == JOptionPane.CLOSED_OPTION) {
					JOptionPane.showMessageDialog(null, "��ҵǾ����ϴ�.");
				}
				else {
					JOptionPane.showMessageDialog(null, "��ҵǾ����ϴ�.");
				}		
			}
		});
		
		bt2 = new JButton("��ȭ �߰�");
		bt2.setBounds(565, 240, 150, 80);
		
		bt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					pstmt2 = con.prepareStatement(mt_sql);
					pstmt2.setString(1, m_name);
					pstmt2.setString(2, actor);
					pstmt2.setString(3, dir);
					pstmt2.setString(4, mt_time);
					pstmt2.setInt(5, age);
					pstmt2.setString(6, story);
					pstmt2.setInt(7, run_time);
					pstmt2.setInt(8, price);
					pstmt2.execute();

					JOptionPane.showMessageDialog(null, "���������� �߰��Ǿ����ϴ�.");
					dispose();
					new Add_movie(con);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "���� �߻�... Add_movie.bt2");
				}
				
			}
		});
		
		bt3 = new JButton("�ڷΰ���");
		bt3.setBounds(565, 340, 150, 80); add(bt3);
		
		bt3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Admin(con);
			}
		});
		
		setVisible(true);
		
	}
	
}