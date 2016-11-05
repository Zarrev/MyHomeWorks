package barzs14_hazi_06;

public class Main {

	public volatile static boolean addEnd = false;
	
	public static void main(String[] args) {
		MyQueue<Integer> mq = new MyQueue<>();
		MyThreadOne th1 = new MyThreadOne(mq);
		MyThreadTwo th2 = new MyThreadTwo(mq);
		new Thread(th1).start();
		new Thread(th2).start();
	}

}
