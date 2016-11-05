package itk.ppke.hu.hazi4;

public class MultiLogger implements Logger {
	
	private Logger[] logger;
	
	public MultiLogger(Logger[] logger) {
		this.logger = logger;
	}

	@Override
	public void log(String s) {
		for(Logger l : logger){
			l.log(s);
		}
	}

}
