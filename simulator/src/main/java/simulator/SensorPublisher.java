package simulator;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Random;

public class SensorPublisher {

    private static final String TOPIC = "vehicle.sensor.events";
    private static final String BOOTSTRAP_SERVERS = "localhost:9092";

    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Random random = new Random();

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        String[] vins = {"VIN001", "VIN002", "VIN003"};

        while (true) {
            String vin = vins[random.nextInt(vins.length)];
            double temp = 60 + random.nextDouble() * 60; // 60 - 120°C
            double speed = random.nextDouble() * 150; // 0 - 150 km/h
            int rpm = 500 + random.nextInt(5500); // 500 - 6000 rpm
            long timestamp = System.currentTimeMillis();

            SensorEvent event = new SensorEvent(vin, temp, speed, rpm, timestamp);
            String json = mapper.writeValueAsString(event);

            ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, vin, json);
            producer.send(record);

            System.out.println("Sent sensor event: " + json);

            Thread.sleep(3000); // every 3 seconds
        }
    }
}
