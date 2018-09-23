import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {
	private Point[] p;
	private List<LineSegment> coLine =new ArrayList<>();
	private int n;
	public FastCollinearPoints(Point[] points) {
		if(points==null) throw new IllegalArgumentException();
		n=points.length;
		p=Arrays.copyOf(points, n);
		Arrays.sort(p);
		for(int i=0;i<n;i++) {
			if(p[i]==null) throw new IllegalArgumentException();
			for(int j=i+1;j<n;j++) {
				if(p[i].slopeTo(p[j])==Double.NEGATIVE_INFINITY) throw new IllegalArgumentException();
			}
		}
		findLineSegment();
	}
	
	
	
	
	
	private void findLineSegment() {
		for(int i=0;i<n-3;i++) {
			int sum=1;
			Point[] q=Arrays.copyOf(p, n);
			Arrays.sort(q, p[i].slopeOrder());
			for(int j=2;j<n;j++) {
				if(q[0].slopeTo(q[j])==q[0].slopeTo(q[j-1])) {
					sum++;
				}
				else {
					if(sum>=3) {
						addLine(q,j-1-sum+1,j-1);
					}
					sum=1;
				}
				if(j==n-1&&sum>=3) {
					addLine(q,j-sum+1,j);
					sum=1;
				}
			}
			
		}
		
	}





	private void addLine(Point[] p2, int i, int j) {
		Point[] p3=Arrays.copyOfRange(p2, i, j+1);
		int s=j-i+1;
		Arrays.sort(p3);
		if(p2[0].compareTo(p3[0])==-1) {
			coLine.add(new LineSegment(p2[0],p3[s-1]));
		}
		
	}





	public int numberOfSegments() {
		return coLine.size();	
	}
	public LineSegment[] segments() {
		return coLine.toArray(new LineSegment[numberOfSegments()]);
	}
}
