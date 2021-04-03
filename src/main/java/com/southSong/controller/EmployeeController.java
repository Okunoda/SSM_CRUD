package com.southSong.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.southSong.pojo.Department;
import com.southSong.pojo.Employee;
import com.southSong.pojo.Message;
import com.southSong.service.DepartmentService;
import com.southSong.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public DepartmentService getDepartmentService() {
        return departmentService;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Autowired
    private DepartmentService departmentService;

    //    使返回json类型的数据，增强程序的兼容性
//    获取全部员工的信息
    @ResponseBody
    @RequestMapping(value = "/emps/{pn}", method = RequestMethod.GET)
    public Message list(@PathVariable("pn") Integer pn) {
        if (pn < 1 || pn == null) pn = 1;
        PageHelper.startPage(pn, 5);
        List<Employee> employeeList = employeeService.getEmpList();
        PageInfo<Employee> pageInfo = new PageInfo<>(employeeList, 5);
//        System.out.println("controller 中的 pageInfo"+pageInfo);
        return Message.success().add("pageInfo", pageInfo);
    }

    //    查询是否名字相同
    @RequestMapping(value = "/selectByName/{name}", method = RequestMethod.GET)
    @ResponseBody
    public Message selectName(@PathVariable("name") String name) {
        String regx = "(^[a-z0-9_-]{3,16}$)|(^[\\u2E80-\\u9FFF]+$)";
        if (name.matches(regx)) {
            boolean a = employeeService.selectName(name);
            if (!a) {
                return Message.success();
            }
            return Message.defeat().add("va_mes","用户名已存在");
        }
        return Message.defeat().add("va_mes","用户名必须是3-16位字符");
    }

    //获取全部部门的信息
    @ResponseBody
    @RequestMapping(value = "/deptName", method = RequestMethod.GET)
    public Message deptName() {
        List<Department> list = departmentService.getAllDept();
        Message mes = Message.success().add("depts", list);
//        System.out.println(mes);
        return mes;
    }

    //    保存员工信息
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Message save(@Valid Employee employee , BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            Map<String,String> map = new HashMap<>();
            List<FieldError> list = bindingResult.getFieldErrors();
            for(FieldError item : list){
                map.put(item.getField(),item.getDefaultMessage());
            }
            return Message.defeat().add("errField",map);
        }
        employeeService.saveEmp(employee);
        return Message.success();
    }


//    @RequestMapping(value = "/emps/{id}" ,method = RequestMethod.GET)
//    public ModelAndView list( @PathVariable("id") Integer pn){
//        PageHelper.startPage(pn,5);
//        List<Employee> employeeList = employeeService.getEmpList();
//        PageInfo<Employee> pageInfo = new PageInfo<>(employeeList,5);
//        System.out.println("controller 中的 pageInfo"+pageInfo.getList());
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("list");
//        modelAndView.addObject("pageInfo",pageInfo);
//        return modelAndView;
//    }

}
