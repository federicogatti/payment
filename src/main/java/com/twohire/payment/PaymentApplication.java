package com.twohire.payment;

import com.twohire.payment.service.pricevaluator.PolicyPriceEvaluation;
import com.twohire.payment.service.pricevaluator.StandardPolicyPriceEvaluation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
	}

	@Bean
	public PolicyPriceEvaluation defineBean() {
		return new StandardPolicyPriceEvaluation();
	}

}

