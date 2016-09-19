package kits.erp.customerservice.domain.core;

import java.util.Random;

public class CustomerIdGenerator {

	private static Random random = new Random();
	
	public static CustomerId generateCustomerId() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
		.append(createRandomChar())
		.append(createRandomChar())
		.append(createRandomChar())
		.append(createRandomDigit())
		.append(createRandomDigit())
		.append(createRandomDigit());
		
		return new CustomerId(stringBuilder.toString());
	}
	
	private static char createRandomChar() {
		return (char)('A' + random.nextInt(26));
	}
	
	private static int createRandomDigit() {
		return random.nextInt(10);
	}
	
}
