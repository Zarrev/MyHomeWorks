package itk.ppke.hu.hazi4;

import java.text.SimpleDateFormat;
import java.util.Date;

public interface Logger {	
	default String getCurrentTimeStamp() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	    Date now = new Date();
	    String strDate = sdf.format(now);
	    return strDate;
	}
	void log(String s);
}
