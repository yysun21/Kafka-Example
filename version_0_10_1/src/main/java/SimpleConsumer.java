import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;

/**
 * Created by garnett on 17. 3. 13.
 */
public class SimpleConsumer {
    public static void main(String[] args){
        PropertyManagement propertyManagement = new PropertyManagement();
        propertyManagement.setBrokerInfo("localhost:9092");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(propertyManagement.getProperties());

        consumer.subscribe(Arrays.asList("garnett_kafka_test"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records)
                System.out.printf(record.value());
        }
    }
}
