package itk.ppke.hu.hazi4;

public class ConsoleLogger implements Logger {

	public ConsoleLogger(){}
	
	@Override
	public void log(String s) {
		System.out.println(getCurrentTimeStamp() + " : " + s);
	}

}
