import java.util.Arrays;

public class test {

	public static void main(String[] args) {
		Point[] p=new Point[20];
		p[0]=new Point(1245, 4461);
		p[1]=new Point(14331, 6301);
		p[2]=new Point(10257, 3592);
		p[3]=new Point(14331, 13725);
		p[4]=new Point(1245, 5401);
		p[5]=new Point(7593,8794);
		p[6]=new Point(7593,12967);
		p[7]=new Point(12095,2118);
		p[8]=new Point(7593,11481);
		p[9]=new Point(10257,16018);
		p[10]=new Point(7593,5551);
		p[11]=new Point(1245,13971);
		p[12]=new Point(10257,18159);
		p[13]=new Point(10257,10141);
		p[14]=new Point(12095,2541);
		p[15]=new Point(1245,11768);
		p[16]=new Point(12095,9157);
		p[17]=new Point(12095,12478);
		p[18]=new Point(14331,10041);
		p[19]=new Point(14331,13039);
		FastCollinearPoints fc=new FastCollinearPoints(p);
		int n=fc.numberOfSegments();
		System.out.println(n);
	}

}
