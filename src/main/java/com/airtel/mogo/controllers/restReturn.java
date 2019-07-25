package com.airtel.mogo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.airtel.mogo.model.Employee;
import com.airtel.mogo.service.EmployeesDAO;

@RestController
public class restReturn {

	@Autowired
	private EmployeesDAO employeeDao;
	
	@RequestMapping( value = "/employee", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Employee>> employees(){
		
		HttpHeaders headers = new HttpHeaders();
		
		List<Employee> employeeList = employeeDao.listEmployee();
		//return new ResponseEntity<List<Employee>>(employeeList, headers, HttpStatus.OK);
		
		if(employeeList == null) {
			return new ResponseEntity<List<Employee>>(HttpStatus.NOT_FOUND);
		}
		headers.add("Number of records found: ", String.valueOf(employeeList.size()));
		
		return new ResponseEntity<List<Employee>>(employeeList, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "employee/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") Long employeeId){
		
		String id = employeeId.toString();
		HttpHeaders headers = new HttpHeaders();
		Employee emp = employeeDao.findEmployeeById(id);
		if(emp == null) {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		employeeDao.deleteEmployee(emp);
		headers.add("Employee Deleted-", id);
		return new ResponseEntity<Employee>(emp, headers, HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="/employee", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
		HttpHeaders headers = new HttpHeaders();
		if(employee == null) {
			return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
		}
		employeeDao.addEmployee(employee);
		headers.add("Employee created: ", employee.getId());
		return new ResponseEntity<Employee>(employee, headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long EmployeeId, @RequestBody Employee employee){
		
		HttpHeaders headers = new HttpHeaders();
		String id = EmployeeId.toString();
		Employee isExist = employeeDao.findEmployeeById(id);
		if(isExist == null) {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		} else if(employee == null) {
			return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
		}
		employeeDao.deleteEmployee(isExist);
		employeeDao.addEmployee(employee);
		headers.add("employee updated - ", id);
		return new ResponseEntity<Employee>(employee, headers, HttpStatus.OK);
	}
	
	
}
