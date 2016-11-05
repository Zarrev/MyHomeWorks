package itk.ppke.hu.hazi4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileLogger implements Logger {

	private boolean newRun = true;
	private String fileName;
	
	public FileLogger(String fileName){
		this.fileName = fileName;
	}

	@Override
	public void log(String s) {
		if(newRun){
			try {
				File f = new File("log.txt");
				if(f.exists())
					f.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			newRun = false;
			PrintWriter pw = new PrintWriter(new FileWriter(fileName, true));
			pw.println(getCurrentTimeStamp() + " : " + s);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
