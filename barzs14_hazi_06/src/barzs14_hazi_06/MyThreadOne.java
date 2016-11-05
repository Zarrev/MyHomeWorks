package barzs14_hazi_06;

public class MyThreadOne extends Thread {

	private MyQueue<Integer> mq;
	private int e;
	
	public MyThreadOne(MyQueue<Integer> mq) {
		super();
		this.mq = mq;
		e = 100;
	}

	@Override
	public void run() {
		while(e >= 0){
			System.out.println(e + "\t" + this.getClass().getName());
			mq.add(e);
			e -= 10;
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		Main.addEnd = true;
	}

}
