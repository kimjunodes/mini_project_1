package test;
import java.awt.*;
import javax.swing.*;

class Frame extends JFrame {
	JButton exit = new JButton("EXIT");
	Frame(String lbl){
		setSize(500,400);
		setTitle("Bit Cinema");
		getContentPane().add(exit);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        getContentPane().add(exit);
        
        JLabel lTitle = new JLabel(lbl, SwingConstants.CENTER);
        lTitle.setBounds(100, 25, 300, 50);
        getContentPane().add(lTitle);
        lTitle.setFont(new Font("¸¼Àº °íµñ",Font.BOLD,30));
        
        exit.setBounds(400, 300, 60, 30);
		exit.addActionListener(event -> {
			System.exit(0);
	    });
	}
	JButton setButton(String title,int x, int y, int width, int height) {
		JButton btn = new JButton(title);
		btn.setBounds(x, y, width, height);
        getContentPane().add(btn);
		return btn;
	}
	JLabel setLabel(String text,int x, int y, int width, int height) {
		JLabel lbl = new JLabel(text);
		lbl.setBounds(x, y, width, height);
        getContentPane().add(lbl);
		return lbl;
	}
}
