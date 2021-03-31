package com.southSong.service.Impl;

import com.southSong.DAO.EmployeeMapper;
import com.southSong.pojo.Employee;
import com.southSong.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper ;

    @Override
    public List<Employee> getEmpList() {
        return employeeMapper.selectAllWithDept();
    }
}
