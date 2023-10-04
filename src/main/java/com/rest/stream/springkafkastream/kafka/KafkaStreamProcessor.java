package com.rest.stream.springkafkastream.kafka;
//
//import com.rest.stream.springkafkastream.entity.User;
//import org.apache.kafka.common.serialization.Serdes;
//import org.apache.kafka.streams.KafkaStreams;
//import org.apache.kafka.streams.StreamsBuilder;
//import org.apache.kafka.streams.StreamsConfig;
//import org.apache.kafka.streams.Topology;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.kafka.annotation.EnableKafkaStreams;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.config.KafkaStreamsConfiguration;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//
//@Component
//@EnableKafkaStreams
//public class KafkaStreamProcessor {
//    @Value("${spring.kafka.topic.name}")
//    private String INPUT_TOPIC;
//    @Value("${spring.kafka.topic.output.name}")
//    private String OUTPUT_TOPIC;
//    @Value("${spring.kafka.application.id}")
//    private String APPLICATION_ID;
//
//    private final Logger LOGGER = LoggerFactory.getLogger(KafkaStreamProcessor.class);
//
//    public KafkaStreams createKafkaStream() {
//        Properties props = new Properties();
//        props.put(StreamsConfig.APPLICATION_ID_CONFIG, APPLICATION_ID);
//        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//
//        StreamsBuilder builder = new StreamsBuilder();
//        builder.stream(INPUT_TOPIC).to(OUTPUT_TOPIC);
//
//        Topology topology = builder.build();
//        return new KafkaStreams(topology, props);
//    }
//
////    @KafkaListener(topics = "${spring.kafka.topic.name}")
////    public void listen() {
////        LOGGER.info("Processing message...");
////        kafkaStreams.start();
////    }
//   @KafkaListener(topics = "${spring.kafka.topic.name}")
//    public void processMessage(User userData, String message) {
//        LOGGER.info("Processor Processing message: {} from {}", userData, message);
//    }
//}

import com.rest.stream.springkafkastream.entity.User;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
@EnableKafkaStreams
public class KafkaStreamProcessor {
    @Value("${spring.kafka.streams.application-id}")
    private String APPLICATION_ID;
    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String BOOTSTRAP_SERVERS;
    @Value("${spring.kafka.topic.name}")
    private String INPUT_TOPIC;
    @Value("${spring.kafka.topic.output.name}")
    private String OUTPUT_TOPIC;

    public void startStreamProcessing() {
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, APPLICATION_ID);
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

        properties.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, 1); // Ganti angka sesuai dengan jumlah thread yang Anda butuhkan

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, User> input = builder.stream(INPUT_TOPIC, Consumed.with(Serdes.String(), new JsonSerde<>(User.class)));

// Tambahkan operasi pengolahan yang Anda inginkan di sini
// Contoh: Mengubah nilai pesan
        KStream<String, User> processedStream = input.mapValues(user -> {
            // Lakukan pengolahan di sini
            // Misalnya, Anda bisa memproses objek User
            return user;
        });

        processedStream.to(OUTPUT_TOPIC);

        KafkaStreams kafkaStreams = new KafkaStreams(builder.build(), properties);
        kafkaStreams.start();
    }
}
