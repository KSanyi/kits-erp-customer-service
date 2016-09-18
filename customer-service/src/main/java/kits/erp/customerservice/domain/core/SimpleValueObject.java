package kits.erp.customerservice.domain.core;

public abstract class SimpleValueObject {

	public final String value;
	
	public SimpleValueObject(String value) {
		if(value == null) throw new NullPointerException();
		this.value = value;
	}
	
	public boolean matches(String filter) {
		return value.contains(filter);
	}
	
	public boolean isEmpty() {
		return value.isEmpty();
	}
	
	@Override
	public String toString(){
		return value;
	}
	
	public abstract boolean equals(Object other);
	
	@Override
	public int hashCode() {
		return value.hashCode();
	}
	
}
