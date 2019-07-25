package com.airtel.mogo.service;

import java.util.List;

import com.airtel.mogo.model.Employee;

public interface EmployeesDAO {
	
	public void addEmployee(Employee employee);
	public List<Employee> listEmployee();
	public void deleteEmployee(Employee employee);
	public void updateEmployee(Employee employee);
	public Employee findEmployeeById(String id);

}
