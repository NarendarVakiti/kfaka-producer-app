package com.kafka.producer.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.producer.model.User;

@RestController
@RequestMapping("/kafka")
public class UserResource {
	
    @Autowired
    KafkaTemplate<String, User> kafkaTemplate;

    public static final String TOPIC = "KAFKA-TOPIC";

    @GetMapping("/publish/{id}")
    public String getKafkaMessage(@PathVariable("id") Integer id){
        try {
			kafkaTemplate.send(TOPIC, new User(id, "Narendar", "Enterprise Services" , 50000.00));
		} catch (Exception e) {
			e.printStackTrace();
		}
       return "Published Message Successfully...";
    }
}
