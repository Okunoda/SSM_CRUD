package com.southSong.DAO;

import com.southSong.pojo.Employee;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer employeeId);

    int insert(Employee record);

    Employee selectByPrimaryKey(Integer employeeId);

    List<Employee> selectAll();

    Employee selectByPrimaryKeyWithDept(Integer employeeId);

    List<Employee> selectAllWithDept();

    int updateByPrimaryKey(Employee record);
}