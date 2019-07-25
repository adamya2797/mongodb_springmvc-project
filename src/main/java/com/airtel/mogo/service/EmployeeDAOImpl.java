package com.airtel.mogo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.airtel.mogo.model.Employee;
import com.airtel.mogo.repository.MongoFactory;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.model.Filters;

@Repository
public class EmployeeDAOImpl implements EmployeesDAO{
	
//	@Autowired
//	private MongoTemplate mongoTemplate;
	//public static final String collectionName = "employee";

	DBCollection employeedb = MongoFactory.getCollection("demodb", "employee");

	@Override
	public void addEmployee(Employee employee) {
		
		BasicDBObject doc = new BasicDBObject();
		doc.put("_id", Double.parseDouble(employee.getId()));
		doc.put("name", employee.getName());
		doc.put("city", employee.getCity());
		doc.put("age", employee.getAge());
		doc.put("aadhaar", employee.getAadhaar());
		
		employeedb.insert(doc);
	}

	@Override
	public List<Employee> listEmployee() {
		//System.out.println("In employee DAO");
		
		List<Employee> employee_list = new ArrayList<Employee>();
		DBCursor cursor = employeedb.find();
		//System.out.println(cursor.hasNext());
		while(cursor.hasNext()) {
			
			Employee employee = new Employee();
			
			DBObject current = cursor.next();
			employee.setId(current.get("_id").toString());
			employee.setAge(current.get("age").toString());
			employee.setCity(current.get("city").toString());
			employee.setName(current.get("name").toString());
			employee.setAadhaar(current.get("aadhaar").toString());
			//System.out.println("***********");
			//System.out.println(employee);
			employee_list.add(employee);
			//cursor = (DBCursor) cursor.next();
			
		}
		//System.out.println(employee_list);
		
		return employee_list;
		
	}

	@Override
	public void deleteEmployee(Employee employee) {
		//mongoTemplate.remove(employee, collectionName);
		
		DBObject query = new BasicDBObject();
		Double id = Double.parseDouble(employee.getId());
		query.put("_id", id);
		
		BasicDBObject current = (BasicDBObject) employeedb.findOne(query);
		
		employeedb.remove(current);
	}

	@Override
	public void updateEmployee(Employee employee) {
		
		DBObject query = new BasicDBObject();
		Double id = Double.parseDouble(employee.getId());
		query.put("_id", id);
		
		BasicDBObject current = (BasicDBObject) employeedb.findOne(query);
		
		BasicDBObject edited = new BasicDBObject();
		edited.put("_id", employee.getId());
		edited.put("name", employee.getName());
		edited.put("city", employee.getCity());
		edited.put("age", employee.getAge());
		edited.put("aadhaar", employee.getAadhaar());
		System.out.println("changing "+current.getString("age")+" to "+employee.getAge());
		employeedb.update(current, edited);
		
		//mongoTemplate.insert(employee, collectionName);
		
	}
	
	@Override
	public Employee findEmployeeById(String idString) {
		Employee emp = new Employee();
		Double id = Double.parseDouble(idString);
		System.out.println("current id:"+id);
		DBObject query = new BasicDBObject();
		query.put("_id", id);
		
		BasicDBObject current = (BasicDBObject) employeedb.findOne(query);
		
		if(current != null) {
		emp.setId(current.getString("_id"));
		emp.setAadhaar(current.getString("aadhaar"));
		emp.setAge(current.getString("age"));
		emp.setCity(current.getString("city"));
		emp.setName(current.getString("name"));
		//System.out.println(emp == null);
		return emp;}
		
		return null;
	}
	
	

}
