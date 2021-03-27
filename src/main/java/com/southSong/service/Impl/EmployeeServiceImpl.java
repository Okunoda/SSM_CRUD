package com.southSong.service.Impl;

import com.southSong.DAO.EmployeeDao;
import com.southSong.pojo.Employee;
import com.southSong.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("employeeServiceImpl")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    @Transactional
    public Employee getEmpById(Integer id) {
        Employee employee = new Employee();
        employee.setId(id);
        return employeeDao.getEmpById(employee);
    }
}
