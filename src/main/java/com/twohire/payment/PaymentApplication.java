package com.twohire.payment;

import com.twohire.payment.service.pricevaluator.PolicyPriceEvaluation;
import com.twohire.payment.service.pricevaluator.StandardPolicyPriceEvaluation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
	}

	@Bean
	public PolicyPriceEvaluation defineBean() {
		return new StandardPolicyPriceEvaluation();
	}

}

