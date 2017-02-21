import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class drawUI extends JFrame {

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
       
       Reversi game = new Reversi();
       
   
       //int final x;
       for (int y = 0; y < 8; y++) {
    	   for (int x = 0; x < 8; x++) {
    		   JLabel pieces = new JLabel(); 
    		   if (game.board[y][x] == 0) {
    			   pieces.setIcon(new ImageIcon("graph/empty.jpg"));
        	       panel.add(pieces, 0);
    		   }
    		   
    	       Dimension size_p = pieces.getPreferredSize();
    	       pieces.setBounds(26 + x * 37, 26 + y * 37, size_p.width, size_p.height);
    	       String s = "" + (y * 8 + x);
    	       pieces.setText(s);
    	       pieces.addMouseListener(new MouseAdapter()
    			 {
    		    public void mouseClicked(MouseEvent e) {
    		    	System.out.println("Hello");
    		    	System.out.println("x is : " + pieces.getText());
    		    	ImageIcon image2 = new ImageIcon("graph/white.jpg");
    		    	pieces.setIcon(image2);
    		    	
    		    }
    		});
    	   }
       }
    
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