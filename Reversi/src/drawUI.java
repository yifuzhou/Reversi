import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

class UpdateBoard extends Thread {

	UpdateBoard(Reversi game, JPanel panel, int[][] save_board)
	{
		int movex = -1;
		int movey = -1;
		int move_t = -2;
		for (int y = 0; y < 8; y++) {
	    	   for (int x = 0; x < 8; x++) {
	    		   if (game.board[y][x] != 0) {
	    			   if (save_board[y][x] == 0) {
		    			   movex = x;
		    			   movey = y;
		    			   move_t = game.board[y][x];
	    				   }
	    			   if (game.board[y][x] == -1) {
	    				   ImageIcon image = new ImageIcon("graph/black.jpg");
	    				   
	    				   Object obj = panel.getComponent(63 - (y * 8 + x));
	    					if (obj instanceof JLabel) {
	    						JLabel temp = (JLabel) obj;
	    						temp.setIcon(image);
	    					}
		    		   }
	    			   else {
	    				   ImageIcon image = new ImageIcon("graph/white.jpg");
	    				   Object obj = panel.getComponent(63 - (y * 8 + x));
	    					if (obj instanceof JLabel) {
	    						JLabel temp = (JLabel) obj;
	    						temp.setIcon(image);
	    					}
	    			   }   
	    		   }
	    	   }
		}
		if (move_t == -1) {
			ImageIcon image = new ImageIcon("graph/black_c.jpg");
			   Object obj = panel.getComponent(63 - (movey * 8 + movex));
				if (obj instanceof JLabel) {
					JLabel temp = (JLabel) obj;
					temp.setIcon(image);
				}
		}
		else {
			ImageIcon image = new ImageIcon("graph/white_c.jpg");
			   Object obj = panel.getComponent(63 - (movey * 8 + movex));
				if (obj instanceof JLabel) {
					JLabel temp = (JLabel) obj;
					temp.setIcon(image);
				}
		}
	}
}


public class drawUI extends JFrame {
	public static int[][] save_board;


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
       save_board = Algorithm.copy2Darray(game.board);
       for (int y = 0; y < 8; y++) {
    	   for (int x = 0; x < 8; x++) {
    		   JLabel pieces = new JLabel(); 
    		   if (game.board[y][x] == 0) {
    			   pieces.setIcon(new ImageIcon("graph/empty.jpg"));
        	       panel.add(pieces, 0);
    		   }
    		   if (game.board[y][x] == -1) {
    			   pieces.setIcon(new ImageIcon("graph/black.jpg"));
        	       panel.add(pieces, 0);
    		   }
    		   if (game.board[y][x] == 1) {
    			   pieces.setIcon(new ImageIcon("graph/white.jpg"));
        	       panel.add(pieces, 0);
    		   }
    	       Dimension size_p = pieces.getPreferredSize();
    	       pieces.setBounds(26 + x * 37, 26 + y * 37, size_p.width, size_p.height);
    	       String s = "" + (y * 8 + x);
    	       pieces.setText(s);
    	       
    	       pieces.addMouseListener(new MouseAdapter()
    			 {
    		    public void mouseClicked(MouseEvent e) {
    		    	game.setPlayerMoveBoard(pieces.getText());
//    		    	Rule.printBoard(save_board);
//    		    	Rule.printBoard(game.board);
    		    	new UpdateBoard(game, panel, save_board).start();
    		    	
    		    	save_board = Algorithm.copy2Darray(game.board);
    		    	game.AIreaction();
    		    
    		    	new UpdateBoard(game, panel, save_board).start();
    		    
    		    }
    		});
    	   }
       }
    
       }

}
