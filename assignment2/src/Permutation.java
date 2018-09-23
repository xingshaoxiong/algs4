import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomizedQueue<String> rq=new RandomizedQueue<>();
		int k=Integer.valueOf(args[0]);
		while(!StdIn.isEmpty()) {
			String item=StdIn.readString();
			rq.enqueue(item);
		}
		while (k>0) {
			StdOut.println(rq.dequeue());
			k--;
		}
	}

}
