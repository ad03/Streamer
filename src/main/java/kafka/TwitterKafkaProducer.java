package kafka;

import java.util.Properties;

import keys.PropertyBuilder;
import twitter.StatusListenerBuilder;
import twitter.TwitterStreamInstanceBuilder;
import twitter4j.FilterQuery;
import twitter4j.StatusListener;
import twitter4j.TwitterStream; 

public class TwitterKafkaProducer {
	
	public static void main(String[] args) {
	
		Properties twitterAuth = PropertyBuilder.buildTwitterAuthProperties();
		Properties fileWriterConfig = PropertyBuilder.buildFileWriterConfigProperties();
		
		TwitterStreamInstanceBuilder streamBuilder = new TwitterStreamInstanceBuilder(twitterAuth);
		TwitterStream twitterStream = streamBuilder.buildTwitterInstace();
		
		StatusListenerBuilder listenerBuilder = new StatusListenerBuilder(fileWriterConfig);
		StatusListener listener = listenerBuilder.buildStatusListener();
		
		FilterQuery fq = new FilterQuery();
	    String keywords[] = {"the", "i", "to", "a", "and", "is", "in", "it", "the", "at", "with"};

	    fq.track(keywords);
	    fq.language(new String[]{"en"});
		twitterStream.addListener(listener);
		twitterStream.filter(fq);
		
	}
}
