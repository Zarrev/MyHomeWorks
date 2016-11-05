package barzs14_hazi_06;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MyThreadTwo extends Thread {

	private MyQueue<Integer> mq;
	
	public MyThreadTwo(MyQueue<Integer> mq) {
		super();
		this.mq = mq;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stu
		try {
			PrintWriter pw = new PrintWriter(new FileWriter("out.txt"));
			while(mq.getSize() > 0 || !Main.addEnd){
				try {
					Thread.sleep(15);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//System.out.println(mq.remove()+ "\t" + this.getClass().getName());
				pw.println(mq.remove()+ "\t" + this.getClass().getName());
			}
			pw.close();
		}
		catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
		}
	}
}
