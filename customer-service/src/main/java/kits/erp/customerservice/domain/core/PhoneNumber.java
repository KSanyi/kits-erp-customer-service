package kits.erp.customerservice.domain.core;

public class PhoneNumber extends SimpleValueObject {

	static PhoneNumber createEmptyPhoneNumber() {
		return new PhoneNumber("");
	}
	
	public PhoneNumber(String value) {
		super(value);
	}
	
	public boolean matches(String filter) {
		return value.contains(filter);
	}
	
	@Override
	public boolean equals(Object other) {
		return other instanceof PhoneNumber ? ((PhoneNumber)other).value.equals(value) : false;
	}
	
}
