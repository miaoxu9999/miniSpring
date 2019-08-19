package com.mooc.mx.controller;

import com.mooc.mx.beans.AutoWired;
import com.mooc.mx.service.SalaryService;
import com.mooc.mx.web.mvc.Controller;
import com.mooc.mx.web.mvc.RequestMapping;
import com.mooc.mx.web.mvc.RequestParam;

/**
 * @ClassName SalaryController
 * @Description TODO
 * @Author miaoxu
 * @Date 2019/8/18 10:56
 * @Version 1.0
 **/
@Controller
public class SalaryController {
    @AutoWired
    private SalaryService salaryService;

    @RequestMapping("/get_salary.json")
    public Integer getSalary(@RequestParam("name") String name, @RequestParam("experience")String experience){
        System.out.println("salaryService de zhi wei " + experience);
//        return 1000;
        System.out.println(salaryService == null);
        return salaryService.calSalary(Integer.parseInt(experience));
    }
}
