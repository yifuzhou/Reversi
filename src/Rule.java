import java.util.ArrayList;
import java.util.Scanner;

class LegalMoveDir {
	public int dir_x;
	public int dir_y;
	public LegalMoveDir(int dir_x, int dir_y) {
		this.dir_x = dir_x;
		this.dir_y = dir_y;
	}
}

public class Rule {
	
	/*
	 * find whether it is a legalMove and return all the legalMove directions
	 * If it is not a legalMove, so the return arraylist's size is 0
	 */
	public static ArrayList<LegalMoveDir> isLegalMove(int[][] board, Move move, int color)
	{
		ArrayList<LegalMoveDir> LMD = new ArrayList<LegalMoveDir>();
		for (int dir_x = -1; dir_x <= 1; dir_x++) {
			for (int dir_y = -1; dir_y <= 1; dir_y++) { 
				if (dir_x == 0 && dir_y == 0)
					continue;
				int x = move.getRow() + dir_x;
				int y = move.getCol() + dir_y;
				if (isInBoard(x, y) && board[y][x] == -color) {
					for (x = x + dir_x, y = y + dir_y; isInBoard(x, y); x += dir_x, y += dir_y) {
						if (board[y][x] == -color)
							continue;
						else if (board[y][x] == color) {
							LegalMoveDir m_dir = new LegalMoveDir(dir_x, dir_y);
							LMD.add(m_dir);
							break;
						}
						else
							break;
					}
						
				}
				
			}
		}
		return LMD;
	}
	
	public static boolean isInBoard(int row, int col)
	{
		if (row < 0 || row >= 8 || col < 0 || col >= 8)
			return false;
		else
			return true;
	}
	
	public static ArrayList<Move> getLegalMoves(int[][] board, int color)
	{
		ArrayList<Move> moves = new ArrayList<Move>();
		
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				if (board[y][x] == 0) {
					Move move = new Move(x, y);
					if (isLegalMove(board, move, color).size() != 0) 
						moves.add(move);
				}
				
			}
		}
		return moves;
	}
	
	public static int[][] updateBoard(int[][] board, Move move, int color)
	{
		ArrayList<LegalMoveDir> LMD = new ArrayList<LegalMoveDir>();
		LMD = isLegalMove(board, move, color);
		if (LMD.size() != 0) {
			board[move.getCol()][move.getRow()] = color;
			for (int index = 0; index < LMD.size(); index++) {
				int dir_x = LMD.get(index).dir_x;
				int dir_y = LMD.get(index).dir_y;
				int x = move.getRow() + dir_x;
				int y = move.getCol() + dir_y;
				Move n_move = new Move(x,y);
				board[y][x] = color;
				board = updateBoard(board, n_move, color);
			}
		}
		return board;
	}
	
	public static void printBoard(int[][] board)
	{
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
//	public static void main(String args[])
//	{
//		int [][] board =
//				   {{0, 0, 0, 0, 0, 0, 0, 0},
//					{0, 0, 0, 0, 1, 1, -1, 0},
//					{0, 0, 0, 1, -1, 1, 0, 0},
//					{0, 0, 0, 1, 1, -1, 0, 0},
//					{0, 0, 0, -1, 1, 0, 0, 0},
//					{0, 0, 0, 0, 0, 0, 0, 0},
//					{0, 0, 0, 0, 0, 0, 0, 0},
//					{0, 0, 0, 0, 0, 0, 0, 0}};
//		Scanner scan = new Scanner(System.in);
//		while (true) {
//			int x = scan.nextInt();
//			int y = scan.nextInt();
//			Move move = new Move(x, y);
//			board = updateBoard(board, move, -1);
//			printBoard(board);
//		}
//				
//	}

}
