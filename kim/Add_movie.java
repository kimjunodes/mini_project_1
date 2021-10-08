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
		
		JLabel lTitle = new JLabel("영화 추가", SwingConstants.CENTER);
        lTitle.setBounds(220, 25, 300, 50);
        getContentPane().add(lTitle);
        lTitle.setFont(new Font("맑은 고딕",Font.BOLD,30));
		
		setSize(750, 600);
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
        setResizable(false);
        
        lname = new JLabel("영화 제목");
		lname.setBounds(50, 110, 100, 30); add(lname);
		lage = new JLabel("연령");
		lage.setBounds(300, 110, 100, 30); add(lage);
		lactor = new JLabel("주연 배우");
		lactor.setBounds(50, 185, 100, 30); add(lactor);
		ldir = new JLabel("감독 이름");
		ldir.setBounds(300, 185, 100, 30); add(ldir);
		ltime = new JLabel("상영 시간 (yy/MM/dd HH:mm:ss)");
		ltime.setBounds(50, 260, 200, 30); add(ltime);
		lrun = new JLabel("러닝타임");
		lrun.setBounds(300, 260, 100, 30); add(lrun);
		lprice = new JLabel("가격");
		lprice.setBounds(50, 335, 100, 30); add(lprice);
		lstory = new JLabel("스토리 (한글 : 100자, 영어 : 200자)");
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
		
		bt1 = new JButton("영화 추가 확인");
		bt1.setBounds(565, 140, 150, 80); add(bt1);
		
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				result = JOptionPane.showConfirmDialog(null, "정보가 잘 입력됐는지 확인해보시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
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
							JOptionPane.showMessageDialog(null, "누락된 항목이 있거나 형식에 맞지 않는 항목이 있습니다.");
						}
						else if(age < 1 || run_time < 1 || price < 1) {
							JOptionPane.showMessageDialog(null, "나이, 상영시간, 가격은 정수로 입력해야 합니다.");
						}
						else {
						JOptionPane.showMessageDialog(null, "이상 없습니다.");
						add(bt2);
						}
					
				}
				else if (result == JOptionPane.CLOSED_OPTION) {
					JOptionPane.showMessageDialog(null, "취소되었습니다.");
				}
				else {
					JOptionPane.showMessageDialog(null, "취소되었습니다.");
				}		
			}
		});
		
		bt2 = new JButton("영화 추가");
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

					JOptionPane.showMessageDialog(null, "정상적으로 추가되었습니다.");
					dispose();
					new Add_movie(con);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "오류 발생... Add_movie.bt2");
				}
				
			}
		});
		
		bt3 = new JButton("뒤로가기");
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