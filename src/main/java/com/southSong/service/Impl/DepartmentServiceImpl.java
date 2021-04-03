package com.southSong.service.Impl;

import com.southSong.DAO.DepartmentMapper;
import com.southSong.pojo.Department;
import com.southSong.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    public DepartmentMapper getDepartmentMapper() {
        return departmentMapper;
    }

    public void setDepartmentMapper(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> getAllDept() {
        return departmentMapper.selectAll();
    }
}
