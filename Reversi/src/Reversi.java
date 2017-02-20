import java.util.ArrayList;
import java.util.Scanner;

class Node implements Cloneable{
	public char[][] map;
	public static char color;
	public static int height = 1;
	public static int x;
	public static int y;
	public static int child_x;
	public static int child_y;
	public static int max = -999;
	public static int min = 999;
//	public Node(char color)
//	{
//		this.color = color;
////		output = new char[8][8];
////		for (int i =0;i<8;i++)
////			for (int j=0;j<8;j++)
////			{
////				if ((i==3&&j==3)||(i==4&&j==4))
////					output[i][j] = 'W';
////				else if ((i==3&&j==4)||(i==4&&j==3))
////					output[i][j] = 'B';
////				else
////					output[i][j] = '_';		
////			}		
//	}
	public char[][] getMap()
	{
		return map;
	}
	public char getMapElement(int x, int y)
	{
		return map[y][x];
	}
	public void updateMap(char[][] _map)
	{
		map = _map;
	}
	public int getMax()
	{
		return max;
	}
	public int getMin()
	{
		return min;
	}
	public void setMax(int m)
	{
			max = m;
	}
	public void setMin(int m)
	{
			min = m;
	}
	public char getColor()
	{
		return color;
	}
	public char getComplColor()
	{
		if(color == 'W')
			return 'B';
		else
			return 'W'; 
	}
	public void setColor(char c)
	{
		color = c;
	}
	public int getHeight()
	{
		return height;
	}
	public void setHeight(int h)
	{
		height = h;
	}
	public void updataHeight()
	{
		height++;
	}
	public int getX()
	{
		return x;
	}
	public void setX(int _x)
	{
		x = _x;
	}
	public int getY()
	{
		return y;
	}
	public void setY(int _y)
	{
		y = _y;
	}	
	public int getChildX()
	{
		return child_x;
	}
	public void setChildX(int _x)
	{
		child_x = _x;
	}
	public int getChildY()
	{
		return child_y;
	}
	public void setChildY(int _y)
	{
		child_y = _y;
	}	
	@Override 
    protected Object clone() throws CloneNotSupportedException {  
		Node cloned = (Node) super.clone();
		for (int i=0;i<8;i++)
		cloned.map[i] = (char[]) map[i].clone();
        return cloned;  
    }  
}

public class Reversi {
	public static int[][] weight;
	//public static char[][] output;
//	private static int[][] weights;

	
	public static void init()
	{
		
	}
	public static void updataMap(Node nnode, int x, int y) throws CloneNotSupportedException
	{
		nnode.setX(x);
		nnode.setY(y);
		char[][] map = new char[8][8];
		char[][] tempMap = new char[8][8];
		char color;
		char complColor;
		if (nnode.getColor()=='W')
		{
			color = 'W';
			complColor = 'B';
		}
		else
		{
			color = 'B';
			complColor = 'W';
		}

//		Node newNode = (Node)nnode.clone();
//		map = newNode.getMap();
		
		tempMap = nnode.getMap();
		for(int i=0;i<8;i++)
			map[i] = tempMap[i].clone();
		
		map[y][x] = color;
		
		for (int i =-1;i<=1;i++)
		{
			for (int j=-1;j<=1;j++)
			{
				if(!(i==0&&j==0)&&x+j>=0&&x+j<=7&&y+i>=0&&y+i<=7)
				{
					if(map[y+i][x+j] == complColor)
					{
						int k = 2;
						boolean findOut = false;
						while (x+k*j>=0&&x+k*j<=7&&y+k*i>=0&&y+k*i<=7)
						{
							if (map[y+k*i][x+k*j] == complColor)
								k++;
							else if (map[y+k*i][x+k*j] == color)
							{
								findOut = true;
								break;
							}
							else
								break;
						}
						if (findOut)
						{
							k = 1;
							while (map[y+k*i][x+k*j] == complColor)
							{
								map[y+k*i][x+k*j] = color;
								k++;
							}	
						}			
					}
				}
			}
		}
		nnode.updateMap(map);
	}
	
	public static boolean move()
	{
		
		return false;
	}
	
	public static ArrayList<Integer> findLegalMove( Node root)
	{
		ArrayList<Integer> result = new ArrayList<Integer>();
		char color;
		char complColor;
		if (root.getColor() == 'W')
		{
			color = 'B';
			complColor = 'W';
		}
		else
		{
			color = 'W';
			complColor = 'B';
		}
		for (int i= 0;i<8;i++)
		{
			for (int j =0;j<8;j++)
			{
				if (root.getMapElement(j, i) == color)
				{
					for (int n =-1;n<=1;n++)
					{
						for (int m =-1;m<=1;m++)
						{
							if(i!=0&&j!=0&&i+n>=0&&i+n<=7&&j+m>=0&&j+m<=7)
							{
								if(root.getMapElement(j+m, i+n) == complColor)
								{
									int k = 2;
									while (i+k*n>=0&&i+k*n<=7&&j+k*m>=0&&j+k*m<=7)
									{
										if (root.getMapElement(j+k*m, i+k*n) == complColor)
											k++;
										else if (root.getMapElement(j+k*m, i+k*n) == '_')
										{
											result.add(j+k*m);//record x
											result.add(i+k*n);//record y
											break;
										}
										else
											break;
									}
								}
							}
						}
					}
				}
			}
		}
		
		return result;
	}
	
	public static int generalTree (Node nnode) throws CloneNotSupportedException
	{
		ArrayList<Integer> points = new ArrayList<Integer>();
		points = findLegalMove(nnode);
		
		if(!points.isEmpty()&&nnode.getHeight()<=5)
		{
			for (int i =0;i<points.size();i=i+2)
			{
				int x = points.get(i);
				int y = points.get(i+1);
				
				Node cNode = new Node();//need change
				
				char[][] tempMap = nnode.getMap();
				char[][] map = new char[8][8];
				for(int e=0;e<8;e++)
				{
					map[e] = tempMap[e].clone();
				}
				cNode.updateMap(map);	
				char cNode_color = nnode.getComplColor();
				cNode.setColor(cNode_color);
				cNode.updataHeight();
				updataMap(cNode, x, y);
				
				int cWeight = generalTree(cNode);
				if (nnode.getColor() == 'B')
				{
					if (nnode.getMax()<=cWeight)
					{
						nnode.setMax(cWeight);
						nnode.setChildX(x);
						nnode.setChildY(y);
					}
		
				}
				else
				{
					if (nnode.getMin()>=cWeight)
					{
						nnode.setMin(cWeight);
						nnode.setChildX(x);
						nnode.setChildY(y);
					}
				}
					nnode.setMax(cWeight);		
			}
			if (nnode.getColor() == 'B')
				return nnode.getMax();
			else
				return nnode.getMin();
		}
		else
			return weight[nnode.getY()][nnode.getX()];
	}
	
	public static boolean gameOver(Node nnode)
	{
		ArrayList<Integer> hasChild = findLegalMove(nnode);
		if (hasChild.isEmpty())
			return true;
		else
			return false;
	}
	
	public static void main(String args[]) throws CloneNotSupportedException
	{
		weight = new int[][] 
				{{100, -5, 10,  5,  5, 10, -5,100},
				{ -5,-45,  1,  1,  1,  1,-45, -5},
				{ 10,  1,  3,  2,  2,  3,  1, 10},
				{  5,  1,  2,  1,  1,  2,  1,  5},
				{  5,  1,  2,  1,  1,  2,  1,  5},
				{ 10,  1,  3,  2,  2,  3,  1, 10},
				{ -5,-45,  1,  1,  1,  1,-45, -5},
				{100, -5, 10,  5,  5, 10, -5,100}};
		char[][] root = new char[8][8];
		for (int i =0;i<8;i++)
			for (int j=0;j<8;j++)
			{
				if ((i==3&&j==3)||(i==4&&j==4))
					root[i][j] = 'W';
				else if ((i==3&&j==4)||(i==4&&j==3))
					root[i][j] = 'B';
				else
					root[i][j] = '_';		
			}
		Node nnode = new Node();
		nnode.updateMap(root); //init form
/*
 * This is all for test.
		nnode.setColor('W');
		generalTree (nnode);
		int nextX = nnode.getChildX();
		int nextY = nnode.getChildY();

		System.out.println(nnode.getColor());
		ArrayList<Integer> test = findLegalMove( nnode);
		for (int i =0;i<test.size();i++)
			System.out.println(test.get(i));
		
		nnode.setColor('B');
		updataMap(nnode, 4, 5);
		nnode.setColor('W');
		updataMap(nnode, 3, 5);
		nnode.setColor('B');
		updataMap(nnode, 2, 3);
		nnode.setColor('W');
		updataMap(nnode, 5, 3);
		
		ArrayList<Integer> test = findLegalMove(nnode);
		for (int i =0;i<test.size();i++)
			System.out.println(test.get(i));
					nnode.setColor('W');
		updataMap(nnode, 3, 5);
		nnode.setColor('B');
		updataMap(nnode, 4, 5);
		nnode.setColor('W');
		updataMap(nnode, 5, 5);
		generalTree (nnode);
		System.out.println(nnode.getChildX());
		System.out.println(nnode.getChildY());
		for (int i =0;i<8;i++)
			{
				for (int j=0;j<8;j++)
					System.out.print(nnode.getMapElement(j, i));
				System.out.println();
			}
		nnode.setColor('W');
		updataMap(nnode, 5, 3);
*/
		boolean isOver = false;
		for (int i =0;i<8;i++)
		{
			for (int j=0;j<8;j++)
				System.out.print(nnode.getMapElement(j, i));
			System.out.println();
		}
		Scanner scan = new Scanner(System.in);
		while (!isOver)
		{
			System.out.println("Please input x(0-7) and y(0-7):");
			int x = scan.nextInt();
			int y = scan.nextInt();
			nnode.setColor('W');
			updataMap(nnode, x, y);//player move
			generalTree (nnode);//bot calculate the best next move 
			x = nnode.getChildX();
			y = nnode.getChildY();
			nnode.setColor('B');
			updataMap(nnode, x, y);//bot move and updata
			nnode.setMax(-999);
			nnode.setMin(999);
			nnode.setHeight(1);
			for (int i =0;i<8;i++)
			{
				for (int j=0;j<8;j++)
					System.out.print(nnode.getMapElement(j, i));
				System.out.println();
			}
			isOver = gameOver(nnode);
		
		}
		scan.close();
		System.out.println("Game Over!!");


	}

}
