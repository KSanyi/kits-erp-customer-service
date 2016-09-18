package kits.erp.customerservice.domain.core;

import static kits.util.basic.StringUtil.containsIgnoreCase;

public class CustomerId extends SimpleValueObject {

	static CustomerId createEmpty() {
		return new CustomerId("New customer");
	}
	
	public CustomerId(String value) {
		super(value);
	}
	
	public boolean matches(String filter) {
		return containsIgnoreCase(value, filter);
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof CustomerId ? ((CustomerId)other).value.equals(value) : false;
	}
	
}
