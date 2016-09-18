package kits.erp.customerservice.domain.core;

public class WebLogin {

	public final EmailAddress emailAddress;
	public final String passwordHash;
	
	public WebLogin(EmailAddress emailAddress, String passwordHash) {
		this.emailAddress = emailAddress;
		this.passwordHash = passwordHash;
	}
	
	public boolean matches(String filter) {
		return emailAddress.matches(filter);
	}
	
}
