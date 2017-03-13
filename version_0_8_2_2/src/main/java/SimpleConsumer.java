import kafka.consumer.Consumer;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by garnett on 17. 3. 13.
 */
public class SimpleConsumer extends Thread {
    public static void main(String[] args) {
        PropertyManagement propertyManagement = new PropertyManagement();
        propertyManagement.setBrokerInfo("localhost:9092");
        propertyManagement.setProperty("zookeeper.connect", "localhost:2181");
        kafka.consumer.ConsumerConfig consumerConfig = new kafka.consumer.ConsumerConfig(propertyManagement.getProperties());

        ConsumerConnector consumerConnector = Consumer.createJavaConsumerConnector(consumerConfig);

        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put("garnett_kafka_test", 1);

        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumerConnector.createMessageStreams(topicCountMap);

        List<KafkaStream<byte[], byte[]>> streamList = consumerMap.get("garnett_kafka_test");

        KafkaStream<byte[], byte[]> stream = streamList.get(0);

        ConsumerIterator<byte[], byte[]> iterator = stream.iterator();
        while(iterator.hasNext()) {
            System.out.println(new String(iterator.next().message()));
        }
    }

    public static void processRecords(Map<String, ConsumerRecords<String, String>> records) {
        List<ConsumerRecord<String, String>> messages = records.get("garnett_kafka_test").records();
        if(messages != null) {
            for (ConsumerRecord<String, String> next : messages) {
                try {
                    System.out.println(next.value());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("No messages");
        }
    }
}