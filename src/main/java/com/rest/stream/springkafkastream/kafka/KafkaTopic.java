package com.rest.stream.springkafkastream.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {
    @Value("${spring.kafka.topic.name}")
    private String TOPIC_NAME;
    @Value("${spring.kafka.topic.output.name")
    private String OUTPUT_TOPIC;

    @Bean
    public NewTopic createTopic() {
        return TopicBuilder.name(TOPIC_NAME).build();
    }

    @Bean
    public NewTopic createOutputTopic() {
        return TopicBuilder.name(OUTPUT_TOPIC).build();
    }
}
