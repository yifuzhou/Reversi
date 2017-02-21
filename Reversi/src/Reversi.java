import java.util.ArrayList;
import java.util.Scanner;

public class Reversi {
	public static int [][]board = {{0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 1, -1, 0, 0, 0},
		{0, 0, 0, -1, 1, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0}};

	public Reversi()
	{
		
	}
	public void AIreaction(String s)
	{
		int sum = Integer.parseInt(s);
		int x = sum % 8;
		int y = sum / 8;
		Move move = new Move(x, y);
		board = Rule.updateBoard(board, move, -1);
		int[][] temp = Algorithm.copy2Darray(board); // save the value
		//Rule.printBoard(board);
		int alpha = -999, beta = 999, depth = 12;
		int value = Algorithm.alphBeta(board, alpha, beta, depth, 1);
		
	    board = Algorithm.copy2Darray(temp); // save the value
	    System.out.println("evaluation is " + value);
		System.out.println("next move x is " + Algorithm.AI_move.getRow());
		System.out.println("next move y is " + Algorithm.AI_move.getCol());
//		//board = Rule.updateBoard(board, next_move, 1);
	    
		board = Rule.updateBoard(board, Algorithm.AI_move, 1);
		Rule.printBoard(board);
	}
	
	public int[][] getBoard()
	{
		return board;
	}
	

	
	public static void main(String args[])
	{
		System.out.println("x is " + board[3][4]);
	}
}