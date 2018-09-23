import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
	private double[] x;
	private int trials;
	public PercolationStats(int n,int trials) {
		if(n<=0||trials<=0) {
			throw new IllegalArgumentException("Illeagal Argument");
		}
		x=new double[trials+1];
		this.trials=trials;
		for(int i=0;i<trials;i++) {
			Percolation per=new Percolation(n);
			while(!per.percolates()) {
				int x=StdRandom.uniform(n)+1;
				int y=StdRandom.uniform(n)+1;
				per.open(x, y);
			}
			x[i]=(double)per.numberOfOpenSites()/(n*n);
		}
	}
	public double mean() {
		double sum=0;
		for(int i=0;i<trials;i++) {
			sum+=x[i];
		}
		return sum/trials;
	}
	public double stddev() {
		double sum=0;
		double mean=mean();
		for(int i=0;i<trials;i++) {
			sum+=(x[i]-mean)*(x[i]-mean);
		}
		return Math.sqrt(sum/(trials-1));
	}
	public double confidenceLo() {
		double mean=mean();
		double s=stddev();
		return mean-1.96*s/(Math.sqrt((double)trials));
	}
	public double confidenceHi() {
		double mean=mean();
		double s=stddev();
		return mean+1.96*s/(Math.sqrt((double)trials));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PercolationStats per1=new PercolationStats(200, 100);
		StdOut.println("mean="+per1.mean());
		StdOut.println("stddev="+per1.stddev());
		StdOut.println("95% confidence interval=["+per1.confidenceLo()+","+per1.confidenceHi()+"]");
	}

}
