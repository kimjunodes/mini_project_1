package mini_project1;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Main_menu extends JFrame {
	
	public static void main(String[] args) {
		
		Frame frm = new Frame("Bit cinema");
		
		
		JLabel lb1 = new JLabel("ID :");
		lb1.setBounds(30, 125, 100, 30);
		
		JTextField tf = new JTextField();
		tf.setBounds(150, 125, 200, 30);		
		
		JLabel lb2 = new JLabel("PASSWORD :");
		lb2.setBounds(30, 220, 100, 30);		
		
		JPasswordField pf = new JPasswordField();
		pf.setBounds(150, 220, 200, 30);		
		
		JButton bt1 = new JButton("Login");
		bt1.setBounds(375, 120, 95, 50);				
		bt1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}	
		});
		
		JButton bt2 = new JButton("회원가입");
		bt2.setBounds(375, 210, 95, 50);		
		bt2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}	
		});
		
		frm.add(lb1); 
		frm.add(tf);
		frm.add(lb2); 
		frm.add(pf);
		frm.add(bt1);
		frm.add(bt2);
		frm.setSize(500, 400);
		frm.setVisible(true);
	}

}
