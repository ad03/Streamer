package kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

public class KafkaProducerBuilder {

	private Properties producerConfig = null;

	public KafkaProducerBuilder(Properties producerConfig) {
		this.producerConfig = producerConfig;
	}
	
	@SuppressWarnings("rawtypes")
	public KafkaProducer buildKafkaProducer() {
		
		Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, 
        		producerConfig.get(keys.Kafka.BOOTSTRAP_SERVERS).toString());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		
		KafkaProducer producer = new KafkaProducer(props);
		return producer;
	}
	
}
