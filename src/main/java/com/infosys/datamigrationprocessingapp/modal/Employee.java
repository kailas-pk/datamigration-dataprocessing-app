package com.infosys.datamigrationprocessingapp.modal;

public class Employee {
	private String name;
	private String employeeNumber;
	private String email;
	private String phoneNumber;
	
	public Employee() {
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmployeeNumber() {
		return employeeNumber;
	}
	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", employeeNumber=" + employeeNumber + ", email=" + email + ", phoneNumber="
				+ phoneNumber + "]";
	}
	
	
}
