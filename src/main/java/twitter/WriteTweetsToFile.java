package twitter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import twitter4j.Status;

public class WriteTweetsToFile {
	
	public static FileWriter openFileToWrite(Properties writeProperties) {
		
		String pathToWrite = writeProperties.getProperty(keys.Twitter.PATH_TO_WRITE_TWEETS).toString();
		FileWriter fileTowrite;
		try {
			fileTowrite = new FileWriter(pathToWrite);
			return fileTowrite;
		} catch (IOException e) {
			System.out.println("I/O Exception: Unable to open file " + writeProperties.getProperty(
					keys.Twitter.PATH_TO_WRITE_TWEETS).toString() +
					" for writing tweets");
			e.printStackTrace();
			return null;
		}
	}
	
	public static void write(FileWriter file, Status status ) {
		try {
			if(!status.isRetweet())
	    		file.write("@" + status.getUser().getScreenName() + " - " + status.getText() + " -> "+ status.getCreatedAt() +"\n");
	    	else
	    		file.write("@" + status.getUser().getScreenName() + " - " + status.getRetweetedStatus().getText() + " -> "+ status.getCreatedAt() +"\n");
	    	file.flush();
		} catch (IOException e) {
			System.out.println("IO exception while opening file" + file.toString()
					 + "to write tweets" );
			e.printStackTrace();
		}
		
	}
}
