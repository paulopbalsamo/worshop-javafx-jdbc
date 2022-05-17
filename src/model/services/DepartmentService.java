package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Department;

public class DepartmentService {

	public List<Department> findAll() {
		List<Department> list = new ArrayList<Department>();
		list.add(new Department(1, "Loja 1"));
		list.add(new Department(2, "Loja 2"));
		list.add(new Department(3, "Loja 3"));
		return list;

	}

}
