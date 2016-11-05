package itk.ppke.hu.hazi4;

public class ThrowableLoggerClass implements ThrowableLogger {

	@Override
	public void log(String s) {
		System.out.println(s);
	}

	@Override
	public void log(String s, Throwable cause) {
		System.out.println(s + "\t" + cause.getMessage());
	}

}
