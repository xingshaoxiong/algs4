import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
	private Point[] p;
	private List<LineSegment> coLine =new ArrayList<>();
	private int n;
	public BruteCollinearPoints(Point[] points) {
		if(points==null) throw new IllegalArgumentException();
		n=points.length;
		p=Arrays.copyOf(points, n);
		Arrays.sort(p);
		for(int i=0;i<n;i++) {
			if(p[i]==null) throw new IllegalArgumentException();
		}
		findLineSegment();
	}
	private void findLineSegment() {
		for(int i=0;i<n;i++) {
			for(int j=i+1;j<n;j++) {
				if(p[i].compareTo(p[j])==0) throw new IllegalArgumentException();
				for (int k=j+1;k<n;k++) {
					if(p[i].slopeTo(p[k])==p[j].slopeTo(p[k])){
						for(int l=k+1;l<n;l++) {
							if(p[i].slopeTo(p[j])==p[i].slopeTo(p[l])) {
								coLine.add(new LineSegment(p[i],p[l]));
							}
						}
					}
				}
			}
		}
	}
	public int numberOfSegments() {
		return coLine.size();
	}
	public LineSegment[] segments() {
		return coLine.toArray(new LineSegment[numberOfSegments()]);
	}

}