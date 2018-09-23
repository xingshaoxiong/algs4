import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private Node first;
	private Node last;
	private int N;
	private class Node{
		Item item;
		Node next;
		Node prev;
	}
	public Deque() {
		first=null;
		last=first;
		N=0;
	}
	public boolean isEmpty() {
		return N==0;
	}
	public int size() {
		return N;
	}
	public void addFirst(Item item) {
		if(item==null) throw new IllegalArgumentException();
		Node oldfirst=first;
		first=new Node();
		first.item=item;
		first.prev=null;
		if(isEmpty()) {
			last=first;
			first.next=null;
		}
		else {
			first.next=oldfirst;
			oldfirst.prev=first;
		}
		N++;
	}
	public void addLast(Item item) {
		if(item==null) throw new IllegalArgumentException();
		Node oldlast=last;
		last=new Node();
		last.item=item;
		last.next=null;
		if(isEmpty()) {
			first=last;
			last.prev=null;
		}
		else {
			last.prev=oldlast;
			oldlast.next=last;
		}
		N++;
	}
	public Item removeFirst() {
		if(isEmpty()) throw new NoSuchElementException();
		Item item=first.item;
		first=first.next;
		N--;
		if(isEmpty()) {
			last=null;
		}
		else {
			first.prev=null;
		}
		
		return item;
	}
	public Item removeLast() {
		if(isEmpty()) throw new NoSuchElementException();
		Item item=last.item;
		last=last.prev;
		N--;
		if(isEmpty()) {
			first=null;
		}
		else {
			last.next=null;
		}

		return item;
	}
	public Iterator<Item> iterator(){
		return new ListIterator();
	}
	private class ListIterator implements Iterator<Item>{
		private Node current=first;
		public boolean hasNext() {
			return current!=null;
		}
		public void remove() {
			throw new UnsupportedOperationException();
		}
		public Item next() {
			if(!hasNext()) throw new NoSuchElementException();
			Item item=current.item;
			current=current.next;
			return item;
		}
	}
	
}
