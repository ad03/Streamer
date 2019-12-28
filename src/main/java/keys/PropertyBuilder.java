package keys;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyBuilder {
	
	public static Properties buildTwitterAuthProperties() {
		Properties twitterAuth = new Properties();
		try {
			twitterAuth.load(new FileReader("config/TwitterAuth.properties"));
		} catch (FileNotFoundException e) {
			System.out.println("TwitterAuth.properties File not found Exception");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Unable to read Twitter auth file");
			e.printStackTrace();
		}
		return twitterAuth;
	}
	
	public static Properties buildFileWriterConfigProperties() {
		Properties fileWriterConfig = new Properties();
		try {
			fileWriterConfig.load(new FileReader("config/FileWriter.properties"));
		} catch (FileNotFoundException e) {
			System.out.println("FileWriter.properties File not found Exception");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Unable to read FileWriter properties file");
			e.printStackTrace();
		}
		return fileWriterConfig;
	}
	
	public static Properties buildKafkaProducerProperties() {
		Properties kafkaProducerConfig = new Properties();
		try {
			kafkaProducerConfig.load(new FileReader("config/Kafka.properties"));
		} catch (FileNotFoundException e) {
			System.out.println("Kafka.properties File not found Exception");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Unable to read Kafka properties file");
			e.printStackTrace();
		}
		return kafkaProducerConfig;
	}
}
