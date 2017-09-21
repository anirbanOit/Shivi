package com.example.crudexample.feature.employee.api.message.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddUserRequest {
	@NotNull(message = "GENERIC_BUSINESS~MANDATORY_PARAMETER~NAME")
	@NotEmpty(message = "GENERIC_BUSINESS~MANDATORY_PARAMETER~NAME")
	@JsonProperty("Name")
	private String name;
	
	@Min(value = 1)
	@JsonProperty("Age")
    private int age;	
	
	@NotNull(message = "GENERIC_BUSINESS~MANDATORY_PARAMETER~ADDRESS")
	@NotEmpty(message = "GENERIC_BUSINESS~MANDATORY_PARAMETER~ADDRESS")
	@JsonProperty("Address")
    private String address;
	
	@NotNull(message = "GENERIC_BUSINESS~MANDATORY_PARAMETER~ZIP")
	@NotEmpty(message = "GENERIC_BUSINESS~MANDATORY_PARAMETER~ZIP")
	@JsonProperty("Zip")
	private String zip;
	
	public AddUserRequest() {
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}
}
