package org.witcalculator.rest;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculate")
public class CalculatorController {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public CalculatorController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/{operation}")
    public String sendCalculation(@PathVariable String operation, @RequestParam int a, @RequestParam int b) {
        String message = operation + ":" + a + ":" + b;
        kafkaTemplate.send("calculation_requests", message);
        return "Sent: " + message;
    }
}
