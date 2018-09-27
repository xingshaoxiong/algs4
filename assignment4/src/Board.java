import java.util.ArrayList;


public class Board {
	private int[][] blocks;
	private int n;
	public Board(int [][] blocks) {
		n=blocks.length;
		this.blocks=new int[n][n];
		//二维数组中含有一维数组的引用，有Arrays.copyOf会改变原数组
		for(int row=0;row<n;row++) {
			for(int col=0;col<n;col++) {
				this.blocks[row][col]=blocks[row][col];
			}
		}
	}
	
	public int dimension() {
		return n;
	}
	
	public int hamming() {
		int hamming=0;
		for(int row=0;row<n;row++) {
			for(int col=0;col<n;col++) {
				if(blocks[row][col]!=getNumber(row, col)&&blocks[row][col]!=0) {
					hamming++;
				}
			}
		}
		return hamming;
	}
	
	public int manhattan() {
		int manhattan=0;
		for(int row=0;row<n;row++) {
			for(int col=0;col<n;col++) {
				if(blocks[row][col]!=getNumber(row, col)&&blocks[row][col]!=0) {
					manhattan+=(Math.abs(getRow(blocks[row][col])-row)+Math.abs(getCol(blocks[row][col])-col));
				}
			}
		}
		return manhattan;
	}
	
	public boolean isGoal() {
		return(this.hamming()==0);
	}
	
	public Board twin() {
		int[][] twinBlocks=new int[n][n];
		for(int row=0;row<n;row++) {
			for(int col=0;col<n;col++) {
				twinBlocks[row][col]=blocks[row][col];
			}
		}
		Board board=new Board(twinBlocks);
		if(twinBlocks[0][0]!=0&&twinBlocks[0][1]!=0) {
			board.swap(0, 0, 0, 1);
		}
		else {
			board.swap(n-1, n-2, n-1, n-1);
		}
		return board;
	}
	
	public boolean equals(Object y) {
		if(y==null) return false;
		if(this==y) return true;
		if(y.getClass().isInstance(this)) {
			Board yBoard=(Board) y;
			if(yBoard.n==this.n) {
				for(int row=0;row<n;row++) {
					for(int col=0;col<n;col++) {
						if(this.blocks[row][col]!=yBoard.blocks[row][col]) {
							return false;
						}
					}
				}
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	public Iterable<Board> neighbors(){
		ArrayList<Board> neighbors = new ArrayList<Board>();
		for(int row=0;row<n;row++) {
			for(int col=0;col<n;col++) {
				if(blocks[row][col]==0) {
					if(row>0) {
						Board neighborUp=new Board(blocks);
						neighborUp.swap(row, col, row-1, col);
						neighbors.add(neighborUp);
					}
					if(row<n-1) {
						Board neighborDown=new Board(blocks);
						neighborDown.swap(row, col, row+1, col);
						neighbors.add(neighborDown);
					}
					if(col>0) {
						Board neighborLeft=new Board(blocks);
						neighborLeft.swap(row, col, row, col-1);
						neighbors.add(neighborLeft);
					}
					if(col<n-1) {
						Board neighborRight=new Board(blocks);
						neighborRight.swap(row, col, row, col+1);
						neighbors.add(neighborRight);
					}
					
				}
			}
		}
		return neighbors;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(n + "\n");
		for (int row = 0; row < n; row++) {
		    for (int col = 0; col < n; col++) {
		        sb.append(String.format("%2d ", blocks[row][col]));
		    }
		    sb.append("\n");
		}
		return sb.toString();
	}
	
	private int getRow(int number) {
		return (number-1)/n;
	}
	private int getCol(int number) {
		return (number-1)%n;
	}
	private int getNumber(int row,int col) {
		return row*n+col+1;
	}
	private void swap(int row1,int col1,int row2,int col2) {
		int temp=blocks[row1][col1];
		blocks[row1][col1]=blocks[row2][col2];
		blocks[row2][col2]=temp;
	}
	
}
