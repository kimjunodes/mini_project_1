package test;
 
import javax.swing.JButton;
 
public class Test2 {
	
    public static void main(String[] args) {
        Frame frm = new Frame("Bit Cinema");
        JButton j = frm.setButton("·Î±×ÀÎ",200,200,100,30);
        
        j.addActionListener(event -> {
        	frm.setVisible(false);
        	UserFrame f = new UserFrame("User Menu");
        });
        frm.setVisible(true);
    }
}
