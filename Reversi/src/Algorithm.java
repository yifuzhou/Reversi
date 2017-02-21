import java.util.ArrayList;
import java.util.Scanner;

public class Algorithm {
	public static Move AI_move = new Move(-1, -1);
	public static int current_depth = 7;
	
	public static int alphBeta (int[][] board, int alpha, int beta, int depth, int player)
	{
		if (depth == 0) return valuation (board);
		else if (player == 1) {
			ArrayList<Move> moves = Rule.getLegalMoves(board, player);
			if (moves.size() == 0) {
				ArrayList<Move> moves_opponent = Rule.getLegalMoves(board, -player);
				if (moves_opponent.size() == 0)
					return valuation (board);
				else {
					//System.out.println("No legal Moves so it is your opponent's turn!");
					return alphBeta (board, alpha, beta, depth, -player);
				}
									
			}
			
			int[][] temp = copy2Darray(board); // save the value
			
			for (int i = 0; i < moves.size(); i++) {
				int [][] board_new = Rule.updateBoard(board, moves.get(i), player);
				int value = alphBeta(board_new, alpha, beta, depth - 1, -player);
				if (value > alpha) {
					alpha = value;
					if (depth == current_depth)
					AI_move = moves.get(i); // record AI next move
				}
				board = copy2Darray(temp);
				if (alpha >= beta) break;
			}
			return alpha;
	
		}
		else {
			ArrayList<Move> moves = Rule.getLegalMoves(board, player);
			if (moves.size() == 0) {
				ArrayList<Move> moves_another = Rule.getLegalMoves(board, -player);
				if (moves_another.size() == 0)
					return valuation (board);
				else
					return alphBeta (board, alpha, beta, depth, -player);				
			}
			int[][] temp = copy2Darray(board); // save the value
			for (int i = 0; i < moves.size(); i++) {
				int [][] board_new = Rule.updateBoard(board, moves.get(i), player);
				int value = alphBeta(board_new, alpha, beta, depth - 1, -player);
				if (value < beta) {
					beta = value;
					//AI_move = moves.get(i);
				}
				board = copy2Darray(temp);
				if (alpha >= beta) break;
			}
			return beta;

		}
	}

	public static int valuation (int[][] board)
	{
		int [][]weight = 
			   {{100, -5, 10,  5,  5, 10, -5,100},
				{ -5,-45,  1,  1,  1,  1,-45, -5},
				{ 10,  1,  3,  2,  2,  3,  1, 10},
				{  5,  1,  2,  1,  1,  2,  1,  5},
				{  5,  1,  2,  1,  1,  2,  1,  5},
				{ 10,  1,  3,  2,  2,  3,  1, 10},
				{ -5,-45,  1,  1,  1,  1,-45, -5},
				{100, -5, 10,  5,  5, 10, -5,100}};
		int valuation_AI = 0;
		int valuation_player = 0;
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				if (board[y][x] == 1)
					valuation_AI += weight[y][x];
				if (board[y][x] == -1)
					valuation_player += weight[y][x];
			}
		}
		return valuation_AI - valuation_player;
	}
	
	public static int[][] copy2Darray(int[][] board)
	{
		int[][] array = new int[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				array[i][j] = board[i][j];
			}
		}
		return array;
	}
	
	public static void main(String args[])
	{
		int [][] board =
				   {{0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 1, 1, 1, 0, 0, 0},
					{0, 0, -1, -1, 1, 0, 0, 0},
					{0, 0, 0, -1, 1, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0}};
		Scanner scan = new Scanner(System.in);
		while (true) {
			int x = scan.nextInt();
			int y = scan.nextInt();
			Move move = new Move(x, y);
			board = Rule.updateBoard(board, move, -1);
			int[][] temp = copy2Darray(board); // save the value
			Rule.printBoard(board);
			int alpha = -999, beta = 999, depth = 7;
			int value = alphBeta(board, alpha, beta, depth, 1);
			
		    board = copy2Darray(temp); // save the value
		    System.out.println("evaluation is " + value);
			System.out.println("next move x is " + AI_move.getRow());
			System.out.println("next move y is " + AI_move.getCol());
			//board = Rule.updateBoard(board, next_move, 1);
			board = Rule.updateBoard(board, AI_move, 1);
			Rule.printBoard(board);
		}
				
	}
}
