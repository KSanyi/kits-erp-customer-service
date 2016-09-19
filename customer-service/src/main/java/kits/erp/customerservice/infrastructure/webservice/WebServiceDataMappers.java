package kits.erp.customerservice.infrastructure.webservice;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import kits.erp.customerservice.domain.core.Address;
import kits.erp.customerservice.domain.core.Customer;
import kits.erp.customerservice.domain.core.CustomerData;
import kits.erp.customerservice.domain.core.EmailAddress;
import kits.erp.customerservice.domain.core.Name;
import kits.erp.customerservice.domain.core.PhoneNumber;

class WebServiceDataMappers {

	private static final String KEY_ID = "customerId";
	private static final String KEY_NAME = "name";
	private static final String KEY_EMAIL = "email";
	private static final String KEY_PHONE = "phone";
	
	private static final String KEY_INVOICE_ADDRESS_IS_THE_SAME = "invoiceAddressIsTheSAme";
	
	private static final String KEY_ADDRESS = "address";
	private static final String KEY_ZIP = "zip";
	private static final String KEY_TOWN = "town";
	private static final String KEY_STREET = "street";
	private static final String KEY_HOUSE_NUMBER = "houseNumber";
	private static final String KEY_COMMENT = "comment";
	private static final String KEY_INVOICE_ADDRESS = "invoiceAddress";
	
	static JsonObject mapToJsonObject(Customer customer){
		
		CustomerData customerData = customer.customerData;
		
		JsonObjectBuilder builder = Json.createObjectBuilder();
		
		builder
			.add(KEY_ID, customer.customerId.value)
			.add(KEY_NAME, customerData.name.toString())
			.add(KEY_EMAIL, customerData.emailAddress.toString())
			.add(KEY_PHONE, customerData.phoneNumber.toString())
			.add(KEY_ADDRESS, Json.createObjectBuilder()
					.add(KEY_ZIP, customerData.address.zip)
					.add(KEY_TOWN, customerData.address.town)
					.add(KEY_STREET, customerData.address.street)
					.add(KEY_HOUSE_NUMBER, customerData.address.houseNumber))
			.add(KEY_COMMENT, customerData.comment);
		
		if(!customerData.invoiceAddressIsTheSame){
			builder.add(KEY_INVOICE_ADDRESS, Json.createObjectBuilder()
						.add(KEY_ZIP, customerData.address.zip)
						.add(KEY_TOWN, customerData.address.town)
						.add(KEY_STREET, customerData.address.street)
						.add(KEY_HOUSE_NUMBER, customerData.address.houseNumber));
		}
		
		return builder.build();
	}

	static CustomerData mapToCustomerData(JsonObject jsonObject) {
		
		Name name = new Name(jsonObject.getString(KEY_NAME));
		EmailAddress emailAddress = new EmailAddress(jsonObject.getString(KEY_EMAIL, ""));
		PhoneNumber phoneNumber = new PhoneNumber(jsonObject.getString(KEY_PHONE, ""));
		
		Address address = mapToAddress(jsonObject.getJsonObject(KEY_ADDRESS));
		
		boolean invoiceAddressIsTheSAme = jsonObject.getBoolean(KEY_INVOICE_ADDRESS_IS_THE_SAME, true);
		Address invoiceAddress = invoiceAddressIsTheSAme ? null : mapToAddress(jsonObject.getJsonObject(KEY_INVOICE_ADDRESS));
		
		String comment = jsonObject.getString(KEY_COMMENT, "");
		return new CustomerData(name, address, invoiceAddressIsTheSAme, invoiceAddress, emailAddress, phoneNumber, comment);
	}
	
	private static Address mapToAddress(JsonObject jsonObject) {
		String zip = jsonObject.getString(KEY_ZIP);
		String town = jsonObject.getString(KEY_TOWN);
		String street = jsonObject.getString(KEY_STREET);
		String houseNumber = jsonObject.getString(KEY_HOUSE_NUMBER);
		String comment = jsonObject.getString(KEY_COMMENT, "");
		return new Address(zip, town, street, houseNumber, comment);
	}
	
}
