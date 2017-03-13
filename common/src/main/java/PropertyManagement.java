import java.util.Properties;

/**
 * Created by garnett on 17. 3. 13.
 */
public class PropertyManagement {
    private Properties props = new Properties();

    public PropertyManagement() {
        setDefaultProperties();
    }

    public Properties getProperties() {
        return props;
    }

    public void setProperty(String key, String value) {
        props.put(key,value);
    }

    public void setDefaultProperties(){
        setProperty("group.id", "test");
        setProperty("enable.auto.commit", "true");
        setProperty("auto.commit.interval.ms", "1000");
        setProperty("session.timeout.ms", "30000");
        setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    }
    public void setBrokerInfo(String value){
        setProperty("bootstrap.servers", value);
    }
}
