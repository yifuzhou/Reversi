
import javax.swing.*;

import com.sun.glass.events.MouseEvent;

import java.awt.*;
import java.awt.image.ImageObserver;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

class MyMouseInputAdapter extends MouseInputAdapter{
	   Point point=new Point(0,0); //坐标点
	   
	      
	   
}

public class drawUI extends JPanel{

   public static void main(String[] args) {
       JFrame frame=new JFrame("Reversi");  //title
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(400,400);
       frame.setVisible(true);
       
       JPanel panel = (JPanel)frame.getContentPane();  
       panel.setLayout(null);

       JLabel label = new JLabel();  
       label.setIcon(new ImageIcon("graph/board.jpg"));
       panel.add(label, -1);
       Dimension size = label.getPreferredSize();
       label.setBounds(0, 0, size.width, size.height);
       
       label.addMouseListener(new MouseAdapter() 
       {
    	   public void mousePressed(MouseEvent arg0) {
    			  
        	   System.out.println("click@");
        	   ImageIcon image2 = new ImageIcon("graph/white.jpg");
               label.setIcon(image2);
           }
       });
       
       
	      MyMouseInputAdapter listener=new MyMouseInputAdapter();  //鼠标事件处理
	      label.addMouseListener(listener);  //增加标签的鼠标事件处理
	      label.addMouseMotionListener(listener);       
       
//       for (int y = 0; y < 8; y++) {
//    	   for (int x = 0; x < 8; x++) {
//    		   JLabel empty = new JLabel();  
//    		   empty.setIcon(new ImageIcon("graph/empty.jpg"));
//    	       panel.add(empty, 0);
//    	       Dimension size_e = empty.getPreferredSize();
//    	       empty.setBounds(26 + x * 37, 26 + y * 37, size_e.width, size_e.height);
//    	   }
//       }
    
       }
   
   public void mouseClicked(MouseEvent e) {
	   
   }
}

//import java.awt.Dimension;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//
//public class drawUI extends JFrame {
//
//    public static void main(String[] args) {
//    final JFrame frame = new drawUI();
//    final JPanel panel = new JPanel();
//    panel.setLayout(null);
//    frame.setContentPane(panel);
//
//    frame.addMouseListener(new MouseAdapter() {
//        @Override
//        public void mouseClicked(MouseEvent e) {
//        // if you want an image instead, use the JLabel(Icon image)
//        // constructor
//        JLabel label = new JLabel("test");
//        label.setBounds(e.getX(), e.getY(), label.getPreferredSize().width, label
//            .getPreferredSize().height);
//        panel.add(label);
//        panel.validate();
//        frame.repaint();
//        }
//    });
//
//    frame.setSize(new Dimension(200, 200));
//    frame.setVisible(true);
//    }
//
//}