package twitter;

import java.io.FileWriter;
import java.util.Properties;

import kafka.StreamTweetsToKafka;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

public class StatusListenerBuilder {
	
	public FileWriter fileToWrite = null;
	
	public StatusListenerBuilder(Properties writeConfig) {
		fileToWrite = WriteTweetsToFile.openFileToWrite(writeConfig);
	}

	public StatusListener buildStatusListener() {
		
		StatusListener listener = new StatusListener() {		
			
			public void onStatus(Status status) {
				//Writing tweets to a file for persistence
				WriteTweetsToFile.write(fileToWrite, status);
				
				//Streaming tweets to a Kafka Cluster
				StreamTweetsToKafka.stream(status);
	        }

	        public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
	            System.out.println("Got a status deletion notice id: " + statusDeletionNotice.getStatusId());
	        }

	        public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
	            System.out.println("Got track limitation notice: " + numberOfLimitedStatuses);
	        }

	        public void onScrubGeo(long userId, long upToStatusId) {
	            System.out.println("Got scrub_geo event userId: " + userId + " upToStatusId:" + upToStatusId);
	        }
	        
	        public void onStallWarning(StallWarning warning) {
				System.out.println("Got stall warning: " + warning.getMessage());
			}

	        public void onException(Exception ex) {
	            ex.printStackTrace();
	        }		
		};
		return listener;
	}
}
