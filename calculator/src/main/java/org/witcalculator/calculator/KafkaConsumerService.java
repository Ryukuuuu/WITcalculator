package org.witcalculator.calculator;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "calculator_requests", groupId = "calculator-group")
    public void processMessage(String message) {
        String[] split = message.split(":");
        String operation = split[0];
        int a = Integer.parseInt(split[1]);
        int b = Integer.parseInt(split[2]);

        int result = switch (operation){
            case "add" -> a + b;
            case "subtract" -> a - b;
            default -> 0;
        };

        System.out.println("Result: " + result);
    }
}
