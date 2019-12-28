package kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import keys.PropertyBuilder;
import twitter4j.Status;

public class StreamTweetsToKafka {

	@SuppressWarnings("unchecked")
	public static void stream(Status status) {
		Properties kafkaProducerConfig = PropertyBuilder.buildKafkaProducerProperties();
		KafkaProducerBuilder producerBuilder = new KafkaProducerBuilder(kafkaProducerConfig);
		@SuppressWarnings("rawtypes")
		KafkaProducer producer = producerBuilder.buildKafkaProducer();
		String value = "";
		if(!status.isRetweet()) {
			value = "@" + status.getUser().getScreenName() + " - " +
					status.getText() + " -> "+ status.getCreatedAt() +"\n";
		}
		else {
			value = "@" + status.getUser().getScreenName() + " - " + 
					status.getRetweetedStatus().getText() + " -> "+ status.getCreatedAt() +"\n";
		}
			
		@SuppressWarnings("rawtypes")
		ProducerRecord record = new ProducerRecord(kafkaProducerConfig.getProperty(keys.Kafka.KAFKA_TOPIC), value);
	//	@SuppressWarnings("rawtypes")
		producer.send(record);
	
		
	}
	
}
