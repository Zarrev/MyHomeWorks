package barzs14_hazi_06;

import java.util.Stack;

public class MyQueue<T> {
	private Stack<T> in;
	private Stack<T> out;
	private int size;
	
	public MyQueue() {
		super();
		this.in = new Stack<>();
		this.out = new Stack<>();
		this.size = 0;
	}
	
	
	public void add(T e){
		synchronized (in) {
			this.in.push(e);
			this.size++;
		}
	}
	
	public T remove(){
		synchronized (out) {
		
		if(this.out.isEmpty()){
			while(!this.in.isEmpty()){
				this.out.push(this.in.pop());
			}
		}
		
		this.size--;
		return this.out.pop();
		}
	}
	
	public boolean isEmpty(){
		if(this.size == 0)
			return true;
		
		return false;
	}
	
	public int getSize(){
		return this.size;
	}
}
