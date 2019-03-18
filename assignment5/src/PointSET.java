import java.util.Stack;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdRandom;

public class PointSET {
	private SET<Point2D> points;
	
	// construct an empty set of points
	public PointSET() {
		points=new SET<Point2D>();
	}
	// is the set empty? 
	public boolean isEmpty() {
		return points.isEmpty();
	}
	// number of points in the set 
	public int size() {
		return points.size();
	}
	// add the point to the set (if it is not already in the set)
	public void insert(Point2D p) {
		if(p==null) throw new IllegalArgumentException();
		points.add(p);
	}
	// does the set contain point p? 
	public boolean contains(Point2D p) {
		if(p==null) throw new IllegalArgumentException();
		return points.contains(p);
	}
	// draw all points to standard draw 
	public void draw() {
		for(Point2D point:points) {
			point.draw();
		}
	}
	// all points that are inside the rectangle (or on the boundary)
	public Iterable<Point2D> range(RectHV rect) {
		if(rect==null) throw new IllegalArgumentException();
		 Stack<Point2D> stack=new Stack<>();
		 for(Point2D point:points) {
			 if(rect.contains(point)) {
				 stack.push(point);
			 }
		 }
		 return stack;
	}
	// a nearest neighbor in the set to point p; null if the set is empty
	public Point2D nearest(Point2D p) {
		if(p==null) throw new IllegalArgumentException();
		if(points==null) return null;
		double minDistance=2;
		Point2D nearestPoint = null;
		for(Point2D point:points) {
			double distance=point.distanceSquaredTo(p);
			if(distance<minDistance) {
				minDistance=distance;
				nearestPoint=point;
			}
		}
		return nearestPoint;
	}
	public static void main(String[] args) {
		PointSET set=new PointSET();
		for (int i = 0; i < 1000; i++) {
            double x = StdRandom.uniform(0.0, 1.0);
            double y = StdRandom.uniform(0.0, 1.0);
            set.insert(new Point2D(x,y));
        }
		System.out.println(set.nearest(new Point2D(0.1,0.1)).x());
	}
}
