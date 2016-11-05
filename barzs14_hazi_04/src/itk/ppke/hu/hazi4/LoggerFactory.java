package itk.ppke.hu.hazi4;

public class LoggerFactory {
	public static Logger consoleLogger() {
		return new ConsoleLogger();
	}

	public static Logger fileLogger(String fileName) {
		return new FileLogger(fileName);
	}

	public static Logger multiLogger(Logger[] logger) {
		return new MultiLogger(logger);
	}
}
