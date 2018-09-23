import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private final static int virtualTop=0;
	private int virtualBottom;
	private int n;
	private int gridLength;
	private boolean[] grid;
	private WeightedQuickUnionUF perinstance;
	private WeightedQuickUnionUF fullinstance;
	public Percolation(int n) {
		if(n<1) {
			throw new IllegalArgumentException("Illegal Argument");
		}
		this.n=n;
		virtualBottom=n*n+1;
		gridLength=n*n+2;
		perinstance=new WeightedQuickUnionUF(gridLength);
		fullinstance=new WeightedQuickUnionUF(gridLength-1);
		grid=new boolean[gridLength];
		for(int i=0;i<virtualBottom;i++) {
			grid[i]=false;
		}
	}
	public void open(int row,int col) {
		isIngrid(row, col);
		int tmp=xyTo1D(row, col);
		if(grid[tmp]) return;
		grid[tmp]=true;
		if(row==1) {
			perinstance.union(virtualTop, tmp);
			fullinstance.union(virtualTop, tmp);
		}
		if(row==n) {
			perinstance.union(virtualBottom, tmp);
		}
		int[] dx= {-1,0,0,1};
		int[] dy= {0,1,-1,0};
		for(int i=0;i<4;i++) {
			int xtmp=row+dx[i];
			int ytmp=col+dy[i];			
			if(xtmp>=1&&xtmp<=n&&ytmp>=1&&ytmp<=n) {
				int gridtmp=xyTo1D(xtmp, ytmp);
				if(isOpen(xtmp, ytmp)) {
					perinstance.union(gridtmp, tmp);
					fullinstance.union(gridtmp, tmp);
				}
			}
		}
	}
	public boolean isOpen(int row,int col) {
		isIngrid(row, col);
		return grid[xyTo1D(row, col)];
	}
	public boolean isFull(int row,int col) {
		isIngrid(row, col);
		int tmp=xyTo1D(row, col);
		return fullinstance.connected(virtualTop, tmp);
	}
	public int numberOfOpenSites() {
		int tmp=0;
		for(int i=1;i<virtualBottom;i++) {
			if(grid[i]==true) {
				tmp++;
			}
		}
		return tmp;
	}
	public boolean percolates() {
		return perinstance.connected(virtualTop, virtualBottom);
	}
	private int xyTo1D(int row,int col) {
		isIngrid(row, col);
		int i=(row-1)*n+col;
		return i;
	}
	private void isIngrid(int row,int col) {
		if(row>=1&&row<=n&&col>=1&&col<=n) {
			return ;
		}
		else {
			throw new IndexOutOfBoundsException("Out of grid");
		}
	}
	



}
