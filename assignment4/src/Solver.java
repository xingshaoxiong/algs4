import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
	private SearchNode searchNode;
	private SearchNode twinSearchNode;
	private Stack<Board> solution;
	
	
	private class SearchNode implements Comparable<SearchNode>{
		public Board board;
		public int priority;
		public int move;
		public SearchNode preSearchNode;
		public SearchNode(Board board,SearchNode preSearchNode) {
			this.board=board;
			this.preSearchNode=preSearchNode;
			if(preSearchNode==null) {
				move=0;
			}
			else {
				move=preSearchNode.move+1;
				priority=move+board.manhattan();
				
			}
		}
		@Override
		public int compareTo(SearchNode o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.priority, o.priority);
		}
		
	}
	public Solver(Board initial) {
		if(initial==null) throw new IllegalArgumentException();
		searchNode=new SearchNode(initial, null);
		twinSearchNode=new SearchNode(initial.twin(), null);
		MinPQ<SearchNode> search=new MinPQ<>();
		MinPQ<SearchNode> twinSearch=new MinPQ<>();
		search.insert(searchNode);
		twinSearch.insert(twinSearchNode);
		while(true) {
			searchNode=search.delMin();
			if(searchNode.board.isGoal()) break;
			for(Board neighbor:searchNode.board.neighbors()) {
				if(searchNode.preSearchNode==null||!neighbor.equals(searchNode.preSearchNode.board)) {
					search.insert(new SearchNode(neighbor, searchNode));
				}
			}
			twinSearchNode=twinSearch.delMin();
			if(twinSearchNode.board.isGoal()) break;
			for(Board neighbor:twinSearchNode.board.neighbors()) {
				if(twinSearchNode.preSearchNode==null||!neighbor.equals(twinSearchNode.preSearchNode.board)) {
					twinSearch.insert(new SearchNode(neighbor, twinSearchNode));
				}
			}
		}
	}
	

	public boolean isSolvable() {
		return searchNode.board.isGoal();
	}
	
	public int moves() {
		if(isSolvable()) {
			return searchNode.move;
		}else {
			return -1;
		}
	}
	
	public Iterable<Board> solution(){
		if(isSolvable()) {
			solution=new Stack<>();
			SearchNode node=searchNode;
			while(node!=null) {
				solution.push(node.board);
				node=node.preSearchNode;
			}
			return solution;
		}else {
			return null;
		}
		
	}
	
	public static void main(String[] args) {

	    // create initial board from file
	    In in = new In(args[0]);
	    int n = in.readInt();
	    int[][] blocks = new int[n][n];
	    for (int i = 0; i < n; i++)
	        for (int j = 0; j < n; j++)
	            blocks[i][j] = in.readInt();
	    Board initial = new Board(blocks);

	    // solve the puzzle
	    Solver solver = new Solver(initial);

	    // print solution to standard output
	    if (!solver.isSolvable())
	        StdOut.println("No solution possible");
	    else {
	        StdOut.println("Minimum number of moves = " + solver.moves());
	        for (Board board : solver.solution())
	            StdOut.println(board);
	    }
	}

	
}
