package com.southSong.service;

import com.southSong.pojo.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    public void saveEmp(Employee employee);

    public List<Employee> getEmpList();

    boolean selectName(String name);
}
