package itk.ppke.hu.hazi4;

public interface ThrowableLogger extends Logger {
	void log(String s, Throwable cause);
}
