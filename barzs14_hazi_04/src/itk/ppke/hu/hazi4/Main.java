package itk.ppke.hu.hazi4;

import java.util.Random;

public class Main {

	private Logger[] loggers = new Logger[]{
			LoggerFactory.consoleLogger(),
			LoggerFactory.fileLogger("log.txt")			
	};
	
	private Logger multiL = LoggerFactory.multiLogger(loggers);
	
	private ThrowableLogger throwL = new ThrowableLoggerClass();
	
	private String rndStringGen(){
		char[] chArray = new char[]{
				'1','2','3','4','5','6','7','8','9','0',
				'a','b','c','d','e','f','g','h','j','k',
				'l','m','n','p','q','r','s','t','u','o',
				'i','x','z','v','y'
		};
		//35 char
		Random r = new Random();
		int Low = 0;
		int High = 35;
		StringBuilder sb = new StringBuilder(5);
		for(int i = 0; i < 5; i++){
			int Result = r.nextInt(High-Low) + Low;
			sb.append(chArray[Result]);
		}
		return sb.toString();
	}
	
	private void getException() throws MyException {
		throw new MyException();
	}
	
	public static void main(String[] args) {
		Main test = new Main();
		for(int i = 0; i < 10; i++){
			System.out.println("A consol loggert futtatom: ");
			test.loggers[0].log(test.rndStringGen());
			System.out.println("A file logger lefutott! ");
			test.loggers[1].log(test.rndStringGen());
			System.out.println("A multi loggert futtatom: ");
			test.multiL.log(test.rndStringGen());
		}
		
		try {
			System.out.println("A throwable loggert futtatom es a hiba dobast tesztelem: ");
			test.getException();
		} catch (MyException e) {
			test.throwL.log("Itt ugyan hiba van!", e);
		}
	}

}
