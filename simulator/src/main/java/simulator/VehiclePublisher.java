package simulator;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.*;

public class VehiclePublisher {
    private static final String TOPIC = "vehicle.dtc.events";
    private static final String BOOTSTRAP_SERVERS = "localhost:9092";

    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        List<String> vins = Arrays.asList("VIN001", "VIN002", "VIN003");
        List<String> dtcCodes = Arrays.asList("P0138", "P0301", "P0171");

        Random random = new Random();

        while (true) {
            String vin = vins.get(random.nextInt(vins.size()));
            String dtcCode = dtcCodes.get(random.nextInt(dtcCodes.size()));
            LocalDateTime timestamp = LocalDateTime.now();

            DtcEvent event = new DtcEvent(vin, dtcCode, timestamp);
            String json = mapper.writeValueAsString(event);

            ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, vin, json);
            producer.send(record, (metadata, exception) -> {
                if (exception == null) {
                    System.out.printf("✔ Sent: %s%n", json);
                } else {
                    exception.printStackTrace();
                }
            });

            Thread.sleep(3000); // send every 3 seconds
        }
    }
}