package team_test;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

class Add_movie extends Frame {
   Connection con;
   
   public static JButton bt1, bt2;
   public static JLabel lb1, lb2, lb3, lb4, lb5, lb6, lb7, lb8, lb9;
   public static JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9;
   
   Add_movie(Connection con, String adm) throws SQLException {
      super(adm);
      this.con = con;
   
      //��ư
      bt1 = new JButton("��ȭ �߰�");
      bt1.setBounds(575, 200, 150, 100);
      
      bt2 = new JButton("�α׾ƿ�");
      bt2.setBounds(575, 400, 150, 100);
      
      //��
      lb1 = new JLabel("��ȭ ����");
      lb1.setBounds(50, 110, 100, 30);
      
      lb2 = new JLabel("����");
      lb2.setBounds(300, 110, 100, 30);
      
      lb3 = new JLabel("�ֿ� ���");
      lb3.setBounds(50, 185, 100, 30);
      
      lb4 = new JLabel("���� �̸�");
      lb4.setBounds(300, 185, 100, 30);
      
      lb5 = new JLabel("�� �ð�");
      lb5.setBounds(50, 260, 100, 30);
      
      lb6 = new JLabel("����Ÿ��");
      lb6.setBounds(300, 260, 100, 30);
      
      lb7 = new JLabel("�󿵰�");
      lb7.setBounds(50, 335, 100, 30);
      
      lb8 = new JLabel("����");
      lb8.setBounds(300, 335, 100, 30);
      
      lb9 = new JLabel("���丮");
      lb9.setBounds(250, 410, 100, 30);
      
      //�ؽ�Ʈ�ʵ�
      tf1 = new JTextField();
      tf1.setBounds(50, 150, 200, 25);
      
      tf2 = new JTextField();
      tf2.setBounds(300, 150, 200, 25);
      
      tf3 = new JTextField();
      tf3.setBounds(50, 225, 200, 25);
      
      tf4 = new JTextField();
      tf4.setBounds(300, 225, 200, 25);
      
      tf5 = new JTextField();
      tf5.setBounds(50, 300, 200, 25);
      
      tf6 = new JTextField();
      tf6.setBounds(300, 300, 200, 25);
      
      tf7 = new JTextField();
      tf7.setBounds(50, 375, 200, 25);
      
      tf8 = new JTextField();
      tf8.setBounds(300, 375, 200, 25);
      
      tf9 = new JTextField();
      tf9.setBounds(50, 450, 450, 125);
      
      bt2.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent e) {
    		  dispose();
    	  }
      });
      
      this.setSize(750, 600);
      
      add(bt1); add(bt2);
      
      add(lb1); add(tf1);
      add(lb2); add(tf2);
      add(lb3); add(tf3);
      add(lb4); add(tf4);
      add(lb5); add(tf5);
      add(lb6); add(tf6);
      add(lb7); add(tf7);
      add(lb8); add(tf8);
      add(lb9); add(tf9);
      
      setVisible(true);
   }

}