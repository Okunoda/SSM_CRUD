package com.southSong.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.southSong.pojo.Employee;
import com.southSong.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;


    @RequestMapping(value = "/emps/{id}" ,method = RequestMethod.GET)
    public ModelAndView list( @PathVariable("id") Integer pn){
        PageHelper.startPage(pn,5);
        List<Employee> employeeList = employeeService.getEmpList();
        PageInfo<Employee> pageInfo = new PageInfo<>(employeeList,5);
        System.out.println("controller 中的 pageInfo"+pageInfo.getList());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("list");
        modelAndView.addObject("pageInfo",pageInfo);
        return modelAndView;
    }

}
