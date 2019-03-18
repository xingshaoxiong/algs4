import java.util.Stack;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdRandom;

public class KdTree {
	private Node root=null;
	private int size=0;
	private class Node{
		private Point2D point;
		private RectHV rect;
		private Node left;
		private Node right;
		private boolean isEven;
		public Node(Point2D point, RectHV rect, Node left, Node right, boolean isEven) {
			this.point=point;
			this.rect=rect;
			this.left=left;
			this.right=right;
			this.isEven=isEven;
		}
	}
	public boolean isEmpty() {
		return size==0;
	}
	// number of points in the set 
	public int size() {
		return size;
	}
	// add the point to the set (if it is not already in the set)
	public void insert(Point2D p) {
		if(p==null) throw new IllegalArgumentException();
		root=insert(root,null,p,false);
	}
	private Node insert(Node x,Node parent,Point2D p,boolean isLeft) {
		if(x==null) {
			if(parent==null) {
				size++;
				return new Node(p, new RectHV(0, 0, 1, 1),null,null,false);
			}else {
				RectHV rect=null;
				if(isLeft) {
					if(parent.isEven) {
						rect=new RectHV(parent.rect.xmin(),parent.rect.ymin(),parent.rect.xmax(),parent.point.y());
					}else {
						rect=new RectHV(parent.rect.xmin(),parent.rect.ymin(),parent.point.x(),parent.rect.ymax());
					}
				}else {
					if(parent.isEven) {
						rect=new RectHV(parent.rect.xmin(),parent.point.y(),parent.rect.xmax(),parent.rect.ymax());
					}else {
						rect=new RectHV(parent.point.x(),parent.rect.ymin(),parent.rect.xmax(),parent.rect.ymax());
					}
				}
				size++;
				return new Node(p,rect,null,null,!parent.isEven);
			}
		}else {
			int comp=compare(p,x.point,x.isEven);
			if(comp==-1) {
				x.left=insert(x.left,x,p,true);
			}else if(comp==1) {
				x.right=insert(x.right,x,p,false);
			}
			return x;
		}
	}
	private int compare(Point2D p, Point2D q, boolean isEven) {
		if(p==null||q==null) throw new IllegalArgumentException();
		if(p.equals(q)) return 0;
		if(isEven) return p.y()<q.y()?-1:1;
		else return p.x()<q.x()?-1:1;
	}
	// does the set contain point p? 
	public boolean contains(Point2D p) {
		if(p==null) throw new IllegalArgumentException();
		return contains(root,p);
	}
	private boolean contains(Node root,Point2D p) {
		if(root==null) return false;
		int comp=compare(p,root.point,root.isEven);
		if(comp==-1) return contains(root.left,p);
		if(comp==1) return contains(root.right,p);
		return true;
	}
	// draw all points to standard draw 
	public void draw() {
		draw(root);
	}
	private void draw(Node root) {
		if(root==null) return ;
		root.point.draw();
		draw(root.left);
		draw(root.right);
	}
	// all points that are inside the rectangle (or on the boundary)
	public Iterable<Point2D> range(RectHV rect) {
		Stack<Point2D> stack=new Stack<>();
		range(root,rect,stack);
		return stack;
	}
	private void range(Node root,RectHV rect,Stack<Point2D> stack) {
		if(root==null) return;
		if(rect.contains(root.point)) stack.push(root.point);
		if(root.left!=null&&rect.intersects(root.left.rect)) {
			range(root.left,rect,stack);
		}
		if(root.right!=null&&rect.intersects(root.right.rect)) {
			range(root.right,rect,stack);
		}
	}
	// a nearest neighbor in the set to point p; null if the set is empty
	public Point2D nearest(Point2D p) {
		if(p==null) throw new IllegalArgumentException();
		if(root==null) return null;
		return nearest(root,root.point,p);
		
	}
	private Point2D nearest(Node node,Point2D nearest,Point2D p) {
		if(node==null) return nearest;
		double distance=p.distanceTo(node.point);
		double nearestDistance=p.distanceTo(nearest);
		if(distance<nearestDistance) {
			nearest=node.point;
			nearestDistance=distance;
		}
		if(node.left!=null&&nearestDistance>node.left.rect.distanceSquaredTo(p)) {
			nearest=nearest(node.left,nearest,p);
		}
		if(node.right!=null&&nearestDistance>node.right.rect.distanceSquaredTo(p)) {
			nearest=nearest(node.right,nearest,p);
		}
		return nearest;
	}
	public static void main(String[] args) {
		KdTree tree=new KdTree();
		PointSET set=new PointSET();
		for (int i = 0; i < 1000; i++) {
            double x = StdRandom.uniform(0.0, 1.0);
            double y = StdRandom.uniform(0.0, 1.0);
            tree.insert(new Point2D(x,y));
            set.insert(new Point2D(x,y));
        }
		System.out.println(tree.nearest(new Point2D(0.1,0.1)).x());
		System.out.println(set.nearest(new Point2D(0.1,0.1)).x());
	}
}
