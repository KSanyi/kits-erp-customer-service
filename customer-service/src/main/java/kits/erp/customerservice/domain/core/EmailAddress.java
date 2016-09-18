package kits.erp.customerservice.domain.core;

import static kits.util.basic.StringUtil.containsIgnoreCase;

public class EmailAddress extends SimpleValueObject {

	static EmailAddress createEmptyEmailAddress() {
		return new EmailAddress("");
	}
	
	public EmailAddress(String value) {
		super(value);
	}
	
	public boolean matches(String filter) {
		return containsIgnoreCase(value, filter);
	}
	
	@Override
	public boolean equals(Object other) {
		return other instanceof Name ? ((Name)other).value.equals(value) : false;
	}
}
