//package com.rest.stream.springkafkastream.controller;
//
//import com.rest.stream.springkafkastream.kafka.KafkaProducer;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api")
//public class ApiController {
//    private final KafkaProducer kafkaProducer;
//
//    public ApiController(KafkaProducer kafkaProducer) {
//        this.kafkaProducer = kafkaProducer;
//    }
//
//    @PostMapping("/send")
//    public void sendMessage(@RequestBody String message) {
//        kafkaProducer.sendMessage(message);
//    }
//}
