package com.southSong.DAO;

import com.southSong.pojo.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer departmentId);

    int insert(Department record);

    Department selectByPrimaryKey(Integer departmentId);

    List<Department> selectAll();

    int updateByPrimaryKey(Department record);


}