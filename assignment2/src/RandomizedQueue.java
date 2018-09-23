import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] a;
	private int N;
	public RandomizedQueue() {
		a=(Item[])new Object[1];
		N=0;
	}
	public boolean isEmpty() {
		return N==0;
	}
	public int size() {
		return N;
	}
	private void resize(int max) {
		Item[] temp=(Item[]) new Object[max];
		for(int i=0;i<N;i++) {
			temp[i]=a[i];
		}
		a=temp;
	}
	public void enqueue(Item item) {
		if (item==null) throw new IllegalArgumentException();

		a[N++]=item;
		if(N==a.length) resize(2*a.length);
	}
	public Item dequeue() {
		if(isEmpty()) throw new NoSuchElementException();
		int del=StdRandom.uniform(N);
		Item item=a[del];
		a[del]=a[N-1];
		a[N--]=null;
		if(N>0&&N==a.length/4) resize(a.length/2);
		return item;
	}
	public Item sample() {
		if(isEmpty()) throw new NoSuchElementException();
		int del=StdRandom.uniform(N);
		Item item=a[del];
		return item;
	}
	public Iterator<Item> iterator(){
		return new ArrayIterator();
	}
	private class ArrayIterator implements Iterator<Item>{
		private int[] random=StdRandom.permutation(N);
		int i=0;
		public boolean hasNext() {
			return i<N;
		}
		public void remove() {
			throw new UnsupportedOperationException();
		}
		public Item next() {
			if(!hasNext()) throw new NoSuchElementException();
			Item item=a[random[i++]];
			return item;
		}
	}

}
