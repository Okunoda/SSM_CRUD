package com.southSong.service;

import com.southSong.pojo.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    public List<Employee> getEmpList();

}
