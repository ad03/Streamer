package twitter;

import java.util.Properties;

import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterStreamInstanceBuilder {
	
	private Properties config = null;
	
	public TwitterStreamInstanceBuilder(Properties config) {
		this.config = config;
	}
	
	public TwitterStream buildTwitterInstace() {
		
		ConfigurationBuilder configBuilder = new ConfigurationBuilder();
		configBuilder.setDebugEnabled(true);
		configBuilder.setOAuthConsumerKey(config.getProperty(keys.Twitter.OAUTH_CONSUMER_KEY).toString());
		configBuilder.setOAuthConsumerSecret(config.getProperty(keys.Twitter.OAUTH_CONSUMER_SECRET).toString());
		configBuilder.setOAuthAccessToken(config.getProperty(keys.Twitter.OAUTH_ACCESS_TOKEN).toString());
		configBuilder.setOAuthAccessTokenSecret(config.getProperty(keys.Twitter.OAUTH_ACCESS_TOKEN_SECRET).toString());
		configBuilder.setTweetModeExtended(true);
		
		TwitterStreamFactory twitterStreamFactory = new TwitterStreamFactory(configBuilder.build());
		TwitterStream twitterStream = twitterStreamFactory.getInstance();
		return twitterStream;
	}
}