package com.rest.stream.springkafkastream.kafka;

import com.rest.stream.springkafkastream.entity.User;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.support.serializer.JsonSerde;

import java.util.UUID;

@Configuration
@EnableKafkaStreams
public class KafkaTopology {

    @Value("${spring.kafka.topic.name}")
    private String INPUT_TOPIC;
    @Value("${spring.kafka.topic.output.name}")
    private String OUTPUT_TOPIC;

    @Bean
    public KStream<UUID, String> kStream(StreamsBuilder streamsBuilder) {
        KStream<String, User> inputStream = streamsBuilder
                .stream(INPUT_TOPIC, Consumed.with(Serdes.String(), new JsonSerde<>(User.class)));

        KStream<UUID, String> outputStream = inputStream
                .map((key, user) -> new KeyValue<>(user.getUserId(), user.getFullName()));

        outputStream.to(OUTPUT_TOPIC, Produced.with(Serdes.UUID(), Serdes.String()));

        return outputStream;
    }
}
